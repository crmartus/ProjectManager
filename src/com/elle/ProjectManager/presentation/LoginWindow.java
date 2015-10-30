
/**
 * @author Louis W.
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
package com.elle.ProjectManager.presentation;

import com.elle.ProjectManager.database.DBConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoginWindow extends JFrame {

    // class attributes 
    private String selectedServer;              // selected server
    private String selectedDB;                  // selected database
    private String userName;                    // user name to login 
    private String userPassword;                // user password to login
    
    // class component instances
    private ProjectManagerWindow projectManager;
    private EditDatabaseWindow editDatabaseList;
    private LogWindow logWindow;
    
    public LoginWindow() {
        
        // initialize
        initComponents();
        logWindow = new LogWindow(); // this is for reporting connections to log
        
        // load selectedDB selections from the text file for the combobox
        loadDBList(); 
//        comboBoxDatabase.setSelectedIndex(2);
//        comboBoxServer.setSelectedIndex(0);
//        textFieldUsername.setText("pupone_Xiao");
//        passwordFieldPW.setText("XiaoXXXX8");
        
//        comboBoxDatabase.setSelectedIndex(3);
        
        // show window
        this.setTitle("Log in");  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jInputPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        comboBoxServer = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        passwordFieldPW = new javax.swing.JPasswordField();
        textFieldUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboBoxDatabase = new javax.swing.JComboBox();
        jButtonPanel = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnEditDB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Please input your username and password to log in.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jTextPanelLayout = new javax.swing.GroupLayout(jTextPanel);
        jTextPanel.setLayout(jTextPanelLayout);
        jTextPanelLayout.setHorizontalGroup(
            jTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jTextPanelLayout.setVerticalGroup(
            jTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Server");

        comboBoxServer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pupone", "Local", "AWS" }));
        comboBoxServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxServerActionPerformed(evt);
            }
        });

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        passwordFieldPW.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordFieldPW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldPWActionPerformed(evt);
            }
        });
        passwordFieldPW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldPWKeyPressed(evt);
            }
        });

        textFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsernameActionPerformed(evt);
            }
        });

        jLabel5.setText("Database");

        comboBoxDatabase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dummy", "Elle2015", "pupone_dummy", "pupone_Analyster" }));
        comboBoxDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDatabaseActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel/ Log off");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnLogin.setText("Log in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditDB.setText("Edit");
        btnEditDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInputPanelLayout = new javax.swing.GroupLayout(jInputPanel);
        jInputPanel.setLayout(jInputPanelLayout);
        jInputPanelLayout.setHorizontalGroup(
            jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInputPanelLayout.createSequentialGroup()
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordFieldPW)
                            .addComponent(textFieldUsername)))
                    .addGroup(jInputPanelLayout.createSequentialGroup()
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxServer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxDatabase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditDB)
                .addContainerGap())
            .addComponent(jButtonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInputPanelLayout.setVerticalGroup(
            jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comboBoxServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditDB))
                .addGap(18, 18, 18)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordFieldPW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.close();
        
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * Close down application properly
     */
    public void close(){

        // terminate window and return resources
        this.dispose();
        System.exit(0); // Terminates the currently running Java Virtual Machine.
    }
    private void btnLoginActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void passwordFieldPWKeyPressed(KeyEvent evt) {//GEN-FIRST:event_passwordFieldPWKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_passwordFieldPWKeyPressed

    private void comboBoxDatabaseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_comboBoxDatabaseActionPerformed


    }//GEN-LAST:event_comboBoxDatabaseActionPerformed

    private void comboBoxServerActionPerformed(ActionEvent evt) {//GEN-FIRST:event_comboBoxServerActionPerformed

    }//GEN-LAST:event_comboBoxServerActionPerformed

    private void btnEditDBActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnEditDBActionPerformed
        
        // create a new edit selectedDB window
        editDatabaseList = new EditDatabaseWindow(this); // maybe we can make it not dependant on this
        editDatabaseList.setLocationRelativeTo(this);
        editDatabaseList.setVisible(true);
    }//GEN-LAST:event_btnEditDBActionPerformed

    private void textFieldUsernameActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameActionPerformed

    private void passwordFieldPWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldPWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldPWActionPerformed

    /**
     *  Loads the names of the databases from a text file
     * this is if the actual selectedDB list is edited in EditDatabaseWindow
     * then it updates the combobox with the new values in LoginWindow.
     */
    public void loadDBList() {
        String temp = null;
        List<String> dbList = new ArrayList<String>();
        String dbFile = "database.txt";
        boolean hasContent = false; // has a local text file and the file has contents

        // Read text file of databases' names
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader(dbFile));
            // buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((temp = buf.readLine()) != null) {
//                if (temp.equals(server)) {
//                    while ((temp = buf.readLine()) != null && !temp.equals("-1")) {
                if (!temp.equals("")) {   // remove extra lines
                    dbList.add(temp);
                    hasContent = true;
                }
//                    }
//                    break;
//                }
            }
            if (!hasContent) {

            } else {
                String[] arr = dbList.toArray(new String[dbList.size()]);
                comboBoxDatabase.setModel(new DefaultComboBoxModel(arr));
            }
        } catch (Exception e) {

        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * login
     */
    public void login() {
        
        // get user data
        selectedServer = comboBoxServer.getSelectedItem().toString();
        selectedDB = comboBoxDatabase.getSelectedItem().toString();
        userName = textFieldUsername.getText();
        char[] pw = passwordFieldPW.getPassword();
        userPassword = String.valueOf(pw);
              

        // connect to database
        try {
            logWindow.addMessageWithDate("3:Start to connect local database...");
            DBConnection.connect(selectedServer, selectedDB, userName, userPassword);
            logWindow.addMessageWithDate("Connect successfully!");
            
        } 
        catch (SQLException ex) {

            JOptionPane.showMessageDialog(null,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            String levelMessage = "1:"+ex.getMessage();

            logWindow.addMessageWithDate(levelMessage);
            passwordFieldPW.setText("");
        }
        
        // create an projectManager object
        projectManager = new ProjectManagerWindow();

        // pass the log window to projectManager
        projectManager.setLogWindow(logWindow);

        // pass the selectedDB to projectManager
        // it is used in sql statements
        projectManager.setDatabase(selectedDB);

        // show the database name in menubar
        projectManager.showDatabase();
        
        // show projectManager
        projectManager.setLocationRelativeTo(this);
        projectManager.setVisible(true);

        // terminate this object
        this.dispose(); // returns used resources

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditDB;
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox comboBoxDatabase;
    private javax.swing.JComboBox comboBoxServer;
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JPanel jInputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jTextPanel;
    private javax.swing.JPasswordField passwordFieldPW;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables

}
