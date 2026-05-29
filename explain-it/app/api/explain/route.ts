import Anthropic from "@anthropic-ai/sdk";
import { NextRequest } from "next/server";

const client = new Anthropic();

const ELI5_SYSTEM = `You are explaining things to a 5-year-old. Use extremely simple words, silly analogies involving things like cookies, toys, or dogs, and be enthusiastic and sweet. Keep it short (3-5 sentences). Occasionally use baby talk or exclamation points. Make it feel like you're crouching down to their level.`;

const PHD_SYSTEM = `You are a smug, over-educated academic who is barely tolerating having to explain something so beneath you. Use excessive jargon, invent plausible-sounding citations (e.g. "as per Hoffmann et al., 2019"), condescend to the reader constantly, use unnecessary Latin phrases, and make the person feel like they've wasted your time. Keep it to 4-6 sentences. Sign off with your fake credentials.`;

export async function POST(req: NextRequest) {
  const { topic } = await req.json();

  if (!topic || typeof topic !== "string") {
    return new Response(JSON.stringify({ error: "Missing topic" }), {
      status: 400,
    });
  }

  const encoder = new TextEncoder();

  const stream = new ReadableStream({
    async start(controller) {
      const send = (data: object) => {
        controller.enqueue(encoder.encode(`data: ${JSON.stringify(data)}\n\n`));
      };

      try {
        // Run both explanations in parallel
        const [eli5Stream, phdStream] = await Promise.all([
          client.messages.stream({
            model: "claude-sonnet-4-6",
            max_tokens: 300,
            system: ELI5_SYSTEM,
            messages: [{ role: "user", content: `Explain: ${topic}` }],
          }),
          client.messages.stream({
            model: "claude-sonnet-4-6",
            max_tokens: 400,
            system: PHD_SYSTEM,
            messages: [{ role: "user", content: `Explain: ${topic}` }],
          }),
        ]);

        // Stream both concurrently
        await Promise.all([
          (async () => {
            for await (const chunk of eli5Stream) {
              if (
                chunk.type === "content_block_delta" &&
                chunk.delta.type === "text_delta"
              ) {
                send({ panel: "eli5", text: chunk.delta.text });
              }
            }
            send({ panel: "eli5", done: true });
          })(),
          (async () => {
            for await (const chunk of phdStream) {
              if (
                chunk.type === "content_block_delta" &&
                chunk.delta.type === "text_delta"
              ) {
                send({ panel: "phd", text: chunk.delta.text });
              }
            }
            send({ panel: "phd", done: true });
          })(),
        ]);
      } catch (err) {
        send({ error: String(err) });
      } finally {
        controller.close();
      }
    },
  });

  return new Response(stream, {
    headers: {
      "Content-Type": "text/event-stream",
      "Cache-Control": "no-cache",
      Connection: "keep-alive",
    },
  });
}
