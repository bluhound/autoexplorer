/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

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
    String HEADER = "java -d32 -jar console/jruby-complete.jar -S irb -r console/console.rb";
    
    
    
    Boolean recording = false;
    autoexplorer ax;
    public Document doc;
    DocumentListener documentListener;
    TokenParse tp = new TokenParse();
   
    HTMLEvents he = new HTMLEvents();
    /*Recorder simple takes control of the GUI so
     *startRecording() and stopRecording() can use it.
     */
    Recorder(autoexplorer Ax){
    ax = Ax;
    doc = ax.consoleTextArea.getDocument();
    //get the current token string
     
    }
    
    public int startRecording(){
        //TODO add a check to see if we are already recordnig!
        
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
        doc.removeDocumentListener(this.documentListener);
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
                    
                    if (newText.equals(he.oldText)){
                        //do nothing
                        //this eliminates the doubling of script generation
                        //caused by the multithread.
                    }
                    else {
                    String tpString = tp.TokenParse(newText); 
                    ax.scriptTextArea.append(tpString + ax.newline);
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
    

