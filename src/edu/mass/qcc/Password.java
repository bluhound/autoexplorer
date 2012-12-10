
/**@author James and khem
 * working on password for the users
 * able to save their password for the users.
 */

 
package edu.mass.qcc;
 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Password extends JDialog {
 
    private static final long serialVersionUID = -832548326686122133L;
     
    protected ResourceBundle labels;
 
    protected JPasswordField pass;
    
    protected JButton okButton;
    
    protected JButton cancelButton;
    
    protected JLabel nameLabel;
    
    protected JLabel passLabel;
 
   
    
 
    
    public void setPass(String pass){
        this.pass.setText(pass);
    }
 
    public void setOKText(String ok){
        this.okButton.setText(ok);
        pack();
    }
 
    
    public void setCancelText(String cancel){
        this.cancelButton.setText(cancel);
        pack();
    }
 
    public void setNameLabel(String name){
        this.nameLabel.setText(name);
        pack();
    }
 
    public void setPassLabel(String pass){
        this.passLabel.setText(pass);
        pack();
    }
 
    
 
    public String getPass(){
        return new String(pass.getPassword());
    }
 
    public boolean okPressed(){
        return pressed_OK;
    }
 
    private boolean pressed_OK = false;
 
    public Password(Frame parent, String title) {
 
        super(parent, title, true);
 
        setLocale(Locale.getDefault());
 // ask user to enter password kb and jt
        if (title==null){
            setTitle("Enter Password");
        }
        if (parent != null){
            setLocationRelativeTo(parent);
        }
        // super calls dialogInit, so we don't need to do it again kb and jt.
    }
 
    
    public Password(Frame parent) {
        this(parent, null);
    }
 
   
    public Password() {
        this(null, null);
    }
 
    @Override protected void dialogInit(){
 // sets the password to default if empty kb and jt
        if (labels == null){
            setLocale(Locale.getDefault());
        }
 
        
        pass = new JPasswordField("", 20);
        okButton = new JButton("ok");
        cancelButton = new JButton("cancel");
        passLabel = new JLabel(" ");
 
        super.dialogInit();
 // save user password if true and doesnot if false kb and jt
        KeyListener keyListener = (new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE ||
                        (e.getSource() == cancelButton
                        && e.getKeyCode() == KeyEvent.VK_ENTER)){
                    pressed_OK = false;
                    Password.this.setVisible(false);
                }
                if (e.getSource() == okButton &&
                        e.getKeyCode() == KeyEvent.VK_ENTER){
                    pressed_OK = true;
                    Password.this.setVisible(false);
                }
            }
        });
        addKeyListener(keyListener);
 //records the action performed by the user's password entry kb and jt
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Object source = e.getSource();
                 
                    // other actions close the dialog kb abd jt
                    pressed_OK = (source == pass || source == okButton);
                    Password.this.setVisible(false);
                
            }
        };
 
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.insets.top = 5;
        c.insets.bottom = 5;
        JPanel pane = new JPanel(gridbag);
        pane.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        c.anchor = GridBagConstraints.EAST;
        
 
        
 
        c.gridy = 1;
        gridbag.setConstraints(passLabel, c);
        pane.add(passLabel);
 
        gridbag.setConstraints(pass, c);
        pass.addActionListener(actionListener);
        pass.addKeyListener(keyListener);
        pane.add(pass);
 
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        JPanel panel = new JPanel();
        okButton.addActionListener(actionListener);
        okButton.addKeyListener(keyListener);
        panel.add(okButton);
        cancelButton.addActionListener(actionListener);
        cancelButton.addKeyListener(keyListener);
        panel.add(cancelButton);
        gridbag.setConstraints(panel, c);
        pane.add(panel);
 
        getContentPane().add(pane);
 
        pack();
    }
 
   
    public boolean showDialog(){
        setVisible(true);
        return okPressed();
    }
}   

