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
 * @author Ian
 */
public class Recorder implements DocumentListener {
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
    
    Boolean recording = false;
    autoexplorer ax;
    public Document doc;
    DocumentListener documentListener;
    
    
    
    /*Recorder simple takes control of the GUI so
      startRecording() and stopRecording() can use it.*/
    Recorder(autoexplorer Ax){
    ax = Ax;
    doc = ax.consoleTextArea.getDocument();
     
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
        
            
        
        return 0;
        
    } 
    /*
     * stopRecording() removes the Documentlistener
     * from doc.
     */
    int stopRecording(){
        //Remove Listener stop recording.
        System.out.print("\n<--Stop Recording/Removing Listener-->\n");
        doc.removeDocumentListener(this.documentListener);
        this.recording = false;
        ax.recordingLabel.setVisible(false);
        
        //Save the script from the script area.
        MyFile scriptFile = new MyFile(ax);
        scriptFile.saveas();
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
                jRubyScript = generateScriptforToken(parseTokens[i], token);
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
    private String generateScriptforToken(String pt, String[] token) {
        
        
        
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
                //jRuby watij script
                pt = ("jrScript.open(\"" + token[1] + "\");");
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

    @Override
    public void insertUpdate(DocumentEvent de) {
                if (this.recording == true){
                    System.out.print("\nInsertUpdate--->");
                           
                    String newText = ax.consoleTextArea.getText();
               
                    String tpString = tokenParse(newText); 
                
                    ax.scriptTextArea.append(tpString + ax.newline);
                }
            
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        
    }
        
    }
    

