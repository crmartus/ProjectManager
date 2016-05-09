package com.elle.ProjectManager.presentation;

import com.elle.ProjectManager.dao.SqlOutputWindowDAO;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * SqlOutputWindow
 * @author Carlos Igreja
 * @since  Mar 24, 2016
 */
public class SqlOutputWindow extends JFrame {

    SqlOutputWindowDAO sqlOutputWindowDAO;
    Component parentComponent;
            
    public SqlOutputWindow(String sqlCommand){
        this(sqlCommand,null);
    }
    
    /**
     * Creates new form SqlOutputWindow
     */
    public SqlOutputWindow(String sqlCommand, Component parentComponent) {
        initComponents();
        setTitle("SQL Output");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.parentComponent = parentComponent;
        setLocationRelativeTo(parentComponent);
        sqlOutputWindowDAO = new SqlOutputWindowDAO(parentComponent);
        if(setTableModel(sqlCommand)){
            setVisible(true);
        }
        else{
            dispose();
        }
    }
    
    public boolean setTableModel(String sqlCommand) {
        DefaultTableModel model = sqlOutputWindowDAO.getTableModel(sqlCommand);
        if(model != null){
            tableOutput.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
            tableOutput.setModel(model);
            tableOutput.packAll();
            if(tableOutput.getPreferredSize().width < scrollPaneOutput.getWidth()){
                tableOutput.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOutput = new javax.swing.JPanel();
        scrollPaneOutput = new javax.swing.JScrollPane();
        tableOutput = new org.jdesktop.swingx.JXTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scrollPaneOutput.setViewportView(tableOutput);

        javax.swing.GroupLayout panelOutputLayout = new javax.swing.GroupLayout(panelOutput);
        panelOutput.setLayout(panelOutputLayout);
        panelOutputLayout.setHorizontalGroup(
            panelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOutputLayout.createSequentialGroup()
                .addComponent(scrollPaneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelOutputLayout.setVerticalGroup(
            panelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelOutput;
    private javax.swing.JScrollPane scrollPaneOutput;
    private org.jdesktop.swingx.JXTable tableOutput;
    // End of variables declaration//GEN-END:variables

}
