/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import java.lang.reflect.Array;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;




/**
 *
 * @author Ian Hickey
 * public class Recorder implements DocumentListener
 * This class stores an array of parse tokens that correspond
 * to elements that can be interacted with by the user.
 * The class implements a listener to listen for changes to the 
 * consoleTextArea (which contains a token-string), and then tries
 * to generate a jRuby script based on the token, and the tokens elements.
 */
public class Recorder implements DocumentListener {
    //Recorder Tokens
    
    char COMMENT = '#';
    String HEADER = ("[\"java\", \"#{File.dirname(__FILE__)}/../ruby/src/web_spec\","+"\n"
                    +"\"#{File.dirname(__FILE__)}/../java/dist/webspec.jar\"].concat(Dir[" +"\n"
                    +"\"#{File.dirname(__FILE__)}/../lib/*.jar\"]).each { |file| require file }"+"\n"
                    +"WebSpec.extra_careful_pause_until_ready false"+"\n"
                    +"@spec = WebSpec.new"+"\n"
                    +"#Require the robot class"+"\n"
                    +"require 'java'"+"\n"
                    +"include_class 'java.awt.Robot'"+"\n"
                    +"include_class 'java.awt.event.KeyEvent'"+"\n"
                    +"robot = Robot.new"+"\n"
            );
    
    
    
    
    Boolean recording = false;
    autoexplorer ax;
    public Document doc;
    DocumentListener documentListener;
    TokenParse tp = new TokenParse();
   
    HTMLEvents he = new HTMLEvents();
    StringBuilder recBuffer = new StringBuilder();
    /*Recorder simple takes control of the GUI so
     *startRecording() and stopRecording() can use it.
     */
    Recorder(autoexplorer Ax){
    ax = Ax;
    doc = ax.consoleTextArea.getDocument();
    
    //get the current token string
     
    }
    
    public int startRecording(){
        //TODO add a check to see if we are already recording!
        
        //Setup listener on console
        System.out.print("\nGrab Listener--->");
        recording = true;
        System.out.print("<--Start Recording-->\n");
        doc.addDocumentListener(this);
        ax.recordingLabel.setVisible(true);
        
        //Make sure browser is ready! And Refresh.
        ax.browser.navigate(ax.addressBar.getText());
        ax.browser.waitReady();
        
        //Add Script header into script console
        ax.scriptTextArea.append(COMMENT + "Starting New Script" + ax.newline);
        ax.scriptTextArea.append(HEADER + ax.newline);
        ax.scriptTextArea.append("jrScript = WebSpec.new.ie" + ax.newline);
        ax.scriptTextArea.append("jrScript.open(\"" + ax.addressBar.getText() + "\") " + ax.newline);
        recBuffer.append(COMMENT).append("Starting New Script").append(ax.newline);
        recBuffer.append(HEADER).append(ax.newline);
        recBuffer.append("jrScript = WebSpec.new.ie").append(ax.newline);
        recBuffer.append("jrScript.open(\"").append(ax.addressBar.getText()).append("\") ").append(ax.newline);
        
        
        return 0;
        
    } 
    /*
     * int stopRecording() 
     * Removes the DocumentListener from the consoleTextArea document.
     * Also, sets recording to false and turns off the recording label.
     * Launches an instance of the SHFile class to save the script
     * generated in the scriptTextArea.
     */
    int stopRecording(){
        //Remove Listener stop recording.
        System.out.print(ax.newline + "<--Stop Recording/Removing Listener-->" + ax.newline);
        doc.removeDocumentListener(documentListener);
        this.recording = false;
        ax.recordingLabel.setVisible(false);
        
        //Save the script from the script area.
        SHFile scriptFile = new SHFile(ax);
        scriptFile.saveas();
        return 0;
        
    } 
  
    /*
     * Overrides the insertUpdate of DocumentListener
     * so that the Recorder can see when the document
     * has been updated with a new token-string to process.
     */
    @Override
    public void insertUpdate(DocumentEvent de) {
        if (this.recording == true){
                    System.out.print(ax.newline + "InsertUpdate--->");       
                    String newText = ax.consoleTextArea.getText();
                    String tpString = tp.TokenParse(newText); 
                    
                    
                    if (!"".equals(tpString)&&!tpString.contains("#")&&!tpString.contains("<Click Other>")){
                    ax.scriptTextArea.append(tpString + ax.newline);
                    ax.scriptTextArea.append("sleep(2)" + ax.newline);
                    this.recBuffer.append(tpString).append(ax.newline);
                    this.recBuffer.append("sleep(2)").append(ax.newline);
                    }
                    
                }
                
            
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        
    }
    
    
    
        
    }
    

