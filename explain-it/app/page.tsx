"use client";

import { useState } from "react";

const EXAMPLES = [
  "why pizza is round",
  "how wifi works",
  "why we sleep",
  "the stock market",
  "why the sky is blue",
  "how taxes work",
];

export default function Home() {
  const [topic, setTopic] = useState("");
  const [eli5Text, setEli5Text] = useState("");
  const [phdText, setPhdText] = useState("");
  const [loading, setLoading] = useState(false);
  const [submitted, setSubmitted] = useState("");

  const explain = async (t?: string) => {
    const query = (t ?? topic).trim();
    if (!query || loading) return;

    setLoading(true);
    setSubmitted(query);
    setEli5Text("");
    setPhdText("");

    try {
      const res = await fetch("/api/explain", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ topic: query }),
      });

      const reader = res.body!.getReader();
      const decoder = new TextDecoder();
      let buffer = "";

      while (true) {
        const { done, value } = await reader.read();
        if (done) break;
        buffer += decoder.decode(value, { stream: true });
        const lines = buffer.split("\n");
        buffer = lines.pop() ?? "";
        for (const line of lines) {
          if (!line.startsWith("data: ")) continue;
          const data = JSON.parse(line.slice(6));
          if (data.panel === "eli5" && data.text)
            setEli5Text((prev) => prev + data.text);
          if (data.panel === "phd" && data.text)
            setPhdText((prev) => prev + data.text);
        }
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-gray-950 text-white flex flex-col items-center px-4 py-12">
      <div className="w-full max-w-5xl">
        {/* Header */}
        <div className="text-center mb-10">
          <h1 className="text-5xl font-black tracking-tight mb-2">
            <span className="text-yellow-300">Explain</span>{" "}
            <span className="text-white">It</span>
          </h1>
          <p className="text-gray-400 text-lg">
            The same thing. Two very different people.
          </p>
        </div>

        {/* Input */}
        <div className="flex gap-3 mb-4">
          <input
            type="text"
            value={topic}
            onChange={(e) => setTopic(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && explain()}
            placeholder="Enter any topic..."
            className="flex-1 bg-gray-800 border border-gray-700 rounded-xl px-5 py-4 text-lg text-white placeholder-gray-500 focus:outline-none focus:border-yellow-400 transition"
          />
          <button
            onClick={() => explain()}
            disabled={loading || !topic.trim()}
            className="bg-yellow-300 text-gray-950 font-bold px-8 py-4 rounded-xl text-lg hover:bg-yellow-200 disabled:opacity-40 disabled:cursor-not-allowed transition"
          >
            {loading ? "Thinking..." : "Explain"}
          </button>
        </div>

        {/* Example chips */}
        <div className="flex flex-wrap gap-2 mb-10">
          {EXAMPLES.map((ex) => (
            <button
              key={ex}
              onClick={() => {
                setTopic(ex);
                explain(ex);
              }}
              disabled={loading}
              className="text-sm bg-gray-800 hover:bg-gray-700 text-gray-300 px-4 py-2 rounded-full transition disabled:opacity-40"
            >
              {ex}
            </button>
          ))}
        </div>

        {/* Panels */}
        {(eli5Text || phdText || loading) && (
          <>
            {submitted && (
              <p className="text-center text-gray-500 mb-6 text-sm uppercase tracking-widest">
                Topic: &ldquo;{submitted}&rdquo;
              </p>
            )}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {/* ELI5 Panel */}
              <div className="bg-yellow-50 rounded-2xl p-6 border-4 border-yellow-300 relative">
                <div className="absolute -top-4 left-5 bg-yellow-300 text-gray-950 text-xs font-black px-3 py-1 rounded-full uppercase tracking-wider">
                  Like You&apos;re 5
                </div>
                <div
                  className="mt-2 text-gray-800 text-lg leading-relaxed"
                  style={{ fontFamily: "'Comic Sans MS', cursive, sans-serif" }}
                >
                  {eli5Text || (
                    <span className="text-gray-400 animate-pulse">
                      Thinking really hard... 🧠
                    </span>
                  )}
                </div>
              </div>

              {/* PhD Panel */}
              <div className="bg-gray-900 rounded-2xl p-6 border-4 border-gray-600 relative">
                <div className="absolute -top-4 left-5 bg-gray-600 text-white text-xs font-black px-3 py-1 rounded-full uppercase tracking-wider">
                  Like a Smug PhD
                </div>
                <div
                  className="mt-2 text-gray-200 text-base leading-relaxed"
                  style={{ fontFamily: "Georgia, 'Times New Roman', serif" }}
                >
                  {phdText || (
                    <span className="text-gray-600 animate-pulse">
                      Sighing heavily before responding...
                    </span>
                  )}
                </div>
              </div>
            </div>
          </>
        )}

        {/* Empty state */}
        {!eli5Text && !phdText && !loading && (
          <div className="text-center text-gray-600 mt-8 text-lg">
            Type anything above. Absolutely anything.
          </div>
        )}
      </div>
    </main>
  );
}
