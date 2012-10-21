/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;




/**
 *
 * @author Ian
 */
public class Recorder {
    //Recorder Tokens
    String[] parseTokens ={"<Click Anchor>", "<Click Frame>", "<Click Image>", "<Click Link>",
                           "<Click Span>",  "<Click Button>", "<Click Div>", "<Click Form>",
                           "<Click Input>", "<Click Option>", "<Click Select>", "<Click Other>",
                           "<Type Text>", "<Type Enter>",
                           "<Action Forward>", "<Action Back>", "<Action Google_Search>", 
                           "<Action Navigate>", "<Action Home>", "<Click TextArea>", "<null>"
                          };
    int ANCHOR = 0;
    int FRAME = 1;
    int IMAGE = 2;
    int LINK = 3;
    int SPAN = 4;
    int BUTTON = 5;
    int DIV = 6;
    int FORM = 7;
    int INPUT = 8;
    int OPTION = 9;
    int SELECT = 10;
    int OTHER = 11;
    int TEXT = 12;
    int ENTER = 13;
    int FORWARD = 14;
    int BACK = 15;
    int SEARCH = 16;
    int NAVIGATE = 17;
    int HOME = 18;
    int TEXTAREA = 19;
    
    
    autoexplorer ax;
    public Document doc;
    public DocumentListener documentListener;
    
    
    /*Recorder simple takes control of the GUI so
      startRecording() and stopRecording() can use it.*/
    Recorder(autoexplorer Ax){
    ax = Ax;
    doc = ax.consoleTextArea.getDocument();
     
    }
    
    public int startRecording(){
        
        //Setup listener on console
        System.out.print("\nGrab Listener--->");
        this.documentListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                
               
               
               SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                System.out.print("\nInsertUpdate--->");
                           
                String newText = ax.consoleTextArea.getText();
               
                String tpString = tokenParse(newText); 
                
                ax.scriptTextArea.append("\n" + tpString);
                            }
                });
               
                          
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        
        System.out.print("<--Start Recording-->\n");
        doc.addDocumentListener(documentListener);
        ax.scriptTextArea.setText("Recording Started");
        
        //Make sure browser is ready!
        
        ax.browser.navigate(ax.addressBar.getText());
        ax.browser.waitReady();
        
            
        
        return 0;
        
    } 
    /*
     * stopRecording() removes the Documentlistener
     * from doc.
     */
    int stopRecording(){
        //Remove Listener
        System.out.print("<--Removing Listener-->\n");
        this.doc.removeDocumentListener(documentListener);
        ax.scriptTextArea.setText("Recording Stopped");
        return 0;
    } 

    /*
     * String tokenParse(String string)
     * takes a string as input where each element in
     * the string is seperated by the delimiter ::
     * and sends the first token of each string to
     * tokenParse to check against out tokens[]
     */
    private String tokenParse(String tpString){
        String[] token;
        String jRubyScript = "";
        token = tpString.split("::");
        if (token!=null){
        System.out.print("tokenParse-->");
        //Start matching the first token in token String with our parse tokens
        for (int i=0; i<= parseTokens.length;i++){
            if (parseTokens[i].matches(token[0])){
                System.out.print("Sending Found Token to Script--->");
                jRubyScript = generateScriptforToken(parseTokens[i], tpString);
                return jRubyScript;
            }
        
        }
        
        }
       return null; 
    }
    /*
     * String generateScriptforToken(String, String)
     * Iterates through the token list and depending on
     * the found token, generates a jRuby script for
     * watij to use.
     */
    private String generateScriptforToken(final String pt, String tk) {
        
        
        
        //Case for generating scripts
        
            
            if (parseTokens[ANCHOR].matches(pt)){
                System.out.print("Generating Script for--->" + pt);
                return (pt);
               
            }
            else if (parseTokens[FRAME].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[IMAGE].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[LINK].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }    
            else if (parseTokens[SPAN].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }    
            else if (parseTokens[BUTTON].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[DIV].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[FORM].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[INPUT].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[OPTION].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[SELECT].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[OTHER].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[TEXT].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[ENTER].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[FORWARD].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[BACK].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[SEARCH].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[NAVIGATE].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[HOME].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else if (parseTokens[TEXTAREA].matches(pt)){
                System.out.print("Generating Script for: " + pt);
                return (pt);
            }
            else {
                System.out.print("-->Token Not Found\n");
                return (pt);
            }
               
        }
        
    }
    

