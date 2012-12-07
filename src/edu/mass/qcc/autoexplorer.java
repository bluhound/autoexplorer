package edu.mass.qcc;

import com.teamdev.jxbrowser.Browser;
import com.teamdev.jxbrowser.BrowserFactory;
import com.teamdev.jxbrowser.BrowserType;
import com.teamdev.jxbrowser.prompt.CloseStatus;
import com.teamdev.jxbrowser.prompt.DefaultPromptService;
import com.teamdev.jxbrowser.prompt.ScriptErrorParams;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @authors 
 *  Contributors to this project include:
 *  Quinsigamond Community College, CSC207
 *  Project Leader and Instructor: Professor Hao Loi
 *  Project Managers: Alan Levin
 * 
 *  Team Leader:            Ian Hickey
 *  Team: Khem Bastola,     Naishadh Patel,   Nhut Ta, 
 *        John Donas,       Chris Mitchell,   Em Trieu, 
 *        Jeremiah Johnson, Naishadh Amin,    Julion DeVincentis 
 */  
 /* This is the class that is associated with our
  * license from Teamdev Ltd. and needs to remain
  * named autoexplorer.java, this is also the central
  * entry point to the program and launched the GUI.
  */

public class autoexplorer extends javax.swing.JFrame {
    
    String tokenString = "";
    String newline     = "\n";
    String home        = "http://www.google.com";
    String delim       = "::";
   Browser browser;
 AxBrowser axBrowser;
  Recorder recorder;
    SHFile myFile;
    
    /**
     * Starts the GUI 
     */
    public autoexplorer() {
        
        initComponents();
        System.setProperty("teamdev.license.info", "true");
        
        //Get a new browser. The browser that is returned will depend
        //on which OS is installed on the users computer.
        
         browser = BrowserFactory.createBrowser(BrowserType.getPlatformSpecificBrowser());
        
         //Make sure browser doesn't hang on javascript error
         browser.getServices().setPromptService(new DefaultPromptService(){
            @Override
            public CloseStatus scriptErrorRequested(ScriptErrorParams params) {
                return CloseStatus.OK;
            }
        });
        
        //Now that gui and browser are setup,
        //grab an axbrowser to set all listeners
        //and start processing, clicks and type on elements.
        /*
         * AxBrowser does much of the heavy lifting such as
         * attaching all click listeners that pertain to elements
         * and sending clicked Tags, as string Tokens to the 
         * Recording class which does the token parsing and 
         * generates the scripts.
         * 
         */ 
        axBrowser = new AxBrowser(this);
        try {
            axBrowser.open();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jSplitPane1.setResizeWeight(.5); 
        recordingLabel.setVisible(false);
        
                
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        browserPane = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        addTabButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        addressBar = new java.awt.TextField();
        cbHistory = new javax.swing.JComboBox();
        goButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        stopBrowserButton = new javax.swing.JButton();
        searchField = new java.awt.TextField();
        recordButton = new javax.swing.JButton();
        stopRecordButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        recordingLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        scriptTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openFileMenuOption = new javax.swing.JMenuItem();
        closeFileMenuOption = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        CheckBoxShowConsole = new javax.swing.JCheckBoxMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autoexplorer Alpha Version 0.95");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setName("Main"); // NOI18N

        browserPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Browser"));
        browserPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        browserPane.setAutoscrolls(true);
        browserPane.setMinimumSize(new java.awt.Dimension(40, 400));
        browserPane.setOpaque(true);
        browserPane.setPreferredSize(new java.awt.Dimension(190, 450));
        getContentPane().add(browserPane, java.awt.BorderLayout.CENTER);

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);
        jToolBar1.setRequestFocusEnabled(false);

        addTabButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/plus.png"))); // NOI18N
        addTabButton.setFocusable(false);
        addTabButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addTabButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addTabButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTabButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addTabButton);

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/home.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(homeButton);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/back_24.png"))); // NOI18N
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(backButton);

        forwardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/forward_24.png"))); // NOI18N
        forwardButton.setFocusable(false);
        forwardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forwardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(forwardButton);

        addressBar.setFont(new java.awt.Font("DialogInput", 0, 12)); // NOI18N
        addressBar.setName(""); // NOI18N
        addressBar.setPreferredSize(new java.awt.Dimension(500, 20));
        addressBar.setText("http://");
        addressBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressBarMouseClicked(evt);
            }
        });
        jToolBar1.add(addressBar);
        jToolBar1.add(cbHistory);

        goButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/forward.png"))); // NOI18N
        goButton.setFocusable(false);
        goButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        goButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(goButton);

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/refresh.png"))); // NOI18N
        refreshButton.setToolTipText("");
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setMinimumSize(new java.awt.Dimension(39, 39));
        refreshButton.setPreferredSize(new java.awt.Dimension(39, 39));
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(refreshButton);

        stopBrowserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/stop_2.png"))); // NOI18N
        stopBrowserButton.setFocusable(false);
        stopBrowserButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopBrowserButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(stopBrowserButton);

        searchField.setPreferredSize(new java.awt.Dimension(200, 20));
        searchField.setText("google search");
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldMouseClicked(evt);
            }
        });
        jToolBar1.add(searchField);

        recordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/record.png"))); // NOI18N
        recordButton.setFocusable(false);
        recordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(recordButton);

        stopRecordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/stop.png"))); // NOI18N
        stopRecordButton.setFocusable(false);
        stopRecordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopRecordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stopRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopRecordButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(stopRecordButton);

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/play.png"))); // NOI18N
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(playButton);

        pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/pause.png"))); // NOI18N
        pauseButton.setFocusable(false);
        pauseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pauseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(pauseButton);

        recordingLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        recordingLabel.setForeground(new java.awt.Color(255, 0, 51));
        recordingLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/mass/qcc/res/Record.gif"))); // NOI18N
        recordingLabel.setText("Recording");
        jToolBar1.add(recordingLabel);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setRollover(true);
        jToolBar2.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Status:");
        jToolBar2.add(jLabel2);

        statusLabel.setBackground(new java.awt.Color(255, 255, 255));
        statusLabel.setFocusable(false);
        statusLabel.setMaximumSize(new java.awt.Dimension(800, 31));
        statusLabel.setMinimumSize(new java.awt.Dimension(248, 31));
        statusLabel.setName(""); // NOI18N
        statusLabel.setPreferredSize(new java.awt.Dimension(248, 31));
        jToolBar2.add(statusLabel);

        jSplitPane1.setResizeWeight(0.5);

        scriptTextArea.setColumns(20);
        scriptTextArea.setLineWrap(true);
        scriptTextArea.setRows(5);
        scriptTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Script"));
        jScrollPane3.setViewportView(scriptTextArea);

        jSplitPane1.setRightComponent(jScrollPane3);

        consoleTextArea.setColumns(20);
        consoleTextArea.setLineWrap(true);
        consoleTextArea.setRows(5);
        consoleTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));
        jScrollPane1.setViewportView(consoleTextArea);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addGap(1, 1, 1)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setOpaque(false);

        jMenu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setText("File");

        openFileMenuOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        openFileMenuOption.setText("Open");
        openFileMenuOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuOptionActionPerformed(evt);
            }
        });
        jMenu1.add(openFileMenuOption);

        closeFileMenuOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        closeFileMenuOption.setText("Close");
        jMenu1.add(closeFileMenuOption);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setText("New");
        jMenu1.add(jMenuItem10);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings");

        CheckBoxShowConsole.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        CheckBoxShowConsole.setSelected(true);
        CheckBoxShowConsole.setActionCommand("Close Console");
        CheckBoxShowConsole.setLabel("Show/Hide Console");
        CheckBoxShowConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxShowConsoleActionPerformed(evt);
            }
        });
        jMenu2.add(CheckBoxShowConsole);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Window Settings");
        jMenu2.add(jMenuItem4);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Browser Settings");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setText("Security");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("Scheduler");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Search Help");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("About");
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileMenuOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuOptionActionPerformed
        myFile = new SHFile(this);
        myFile.open();
         


    }//GEN-LAST:event_openFileMenuOptionActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        myFile = new SHFile(this);
        myFile.saveas();
    
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void CheckBoxShowConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxShowConsoleActionPerformed
        //Hide or display console/script pane
        if (!CheckBoxShowConsole.isSelected()){
            
            jSplitPane1.setVisible(false);
        
        }
        else {
            
            SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                jSplitPane1.setVisible(true);
                            }
                }); 
            
            
         
        }
    }//GEN-LAST:event_CheckBoxShowConsoleActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
OptionPane op = new OptionPane();
           op.setVisible(true);
           op.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void stopRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopRecordButtonActionPerformed
        recorder.stopRecording();
    }//GEN-LAST:event_stopRecordButtonActionPerformed

    private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordButtonActionPerformed
        recorder = new Recorder(this);
        recorder.startRecording();
    }//GEN-LAST:event_recordButtonActionPerformed

    private void addressBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressBarMouseClicked
        addressBar.selectAll();
    }//GEN-LAST:event_addressBarMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        browser.navigate(addressBar.getText());
        
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldMouseClicked
        searchField.selectAll();
    }//GEN-LAST:event_searchFieldMouseClicked

    private void addTabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTabButtonActionPerformed
        SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                        SendK sk = new SendK();
                try {
                    sk.type("Enter");
                    sk.type("Tab");
                } catch (AWTException ex) {
                    Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                           }
                });
    }//GEN-LAST:event_addTabButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
    //em
        Player player = new Player();
    }//GEN-LAST:event_playButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(autoexplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(autoexplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(autoexplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(autoexplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new edu.mass.qcc.autoexplorer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBoxMenuItem CheckBoxShowConsole;
    private javax.swing.JButton addTabButton;
    public java.awt.TextField addressBar;
    public javax.swing.JButton backButton;
    public javax.swing.JTabbedPane browserPane;
    public javax.swing.JComboBox cbHistory;
    public javax.swing.JMenuItem closeFileMenuOption;
    public javax.swing.JTextArea consoleTextArea;
    public javax.swing.JButton forwardButton;
    public javax.swing.JButton goButton;
    public javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem10;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JToolBar jToolBar1;
    public javax.swing.JToolBar jToolBar2;
    public javax.swing.JMenuItem openFileMenuOption;
    public javax.swing.JButton pauseButton;
    public javax.swing.JButton playButton;
    public javax.swing.JButton recordButton;
    public javax.swing.JLabel recordingLabel;
    public javax.swing.JButton refreshButton;
    public javax.swing.JTextArea scriptTextArea;
    public java.awt.TextField searchField;
    public javax.swing.JLabel statusLabel;
    public javax.swing.JButton stopBrowserButton;
    public javax.swing.JButton stopRecordButton;
    // End of variables declaration//GEN-END:variables
}
