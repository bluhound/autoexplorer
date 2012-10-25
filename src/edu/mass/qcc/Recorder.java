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
    String[] parseTokens =
    {"<Click Anchor>", "<Click Frame>", "<Click Image>", "<Click Link>",
     "<Click Span>",  "<Click Button>", "<Click Div>", "<Click Form>",
     "<Click Input>", "<Click Option>", "<Click Select>", "<Click Other>",
     "<Type Text>", "<Type Enter>", "<Action Forward>", "<Action Back>", 
     "<Action Google_Search>", "<Action Navigate>", "<Action Home>", 
     "<Click TextArea>", "<null>"
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
     *startRecording() and stopRecording() can use it.
     */
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
        
        ax.scriptTextArea.append("jrScript = WebSpec.new.ie" + ax.newline);
        
        return 0;
        
    } 
    /*
     * int stopRecording() 
     * Removes the DocumentListener from the consoleTextArea document.
     * Also, sets recording to false and turns off the recording label.
     * Launches an instance of the MyFile class to save the script
     * generated in the scriptTextArea.
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
     * private String tokenParse(String tpString)
     * Takes a string as input where each element in
     * the string is seperated by the delimiter ::
     * and checks the first token of each string to
     * against our parseTokens[] (see above) and sends the result to 
     * generateScriptforToken() to actually generate the script.
     * 
     * Input: tpString
     * tpString is a token string in the form: 
     * tagname::id::attribute1::attribute2::attribute3::(...)
     * 
     * Output: String jRubyScript
     * jRubyScript using the Watij plug-in can interpret
     * the script generated by this method.
     */
    private String tokenParse(String tpString){
        String[] token;
        String jRubyScript;
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
     * private String generateScriptforToken(String pt, String[] token)
     * Iterates through the token-array (token[]) and depending on
     * which token is found and the attributes for that element, 
     * generates a jRuby script.
     * 
     */
    private String generateScriptforToken(String pt, String[] token) {
            /* Generate a script for each token by searching the first
             * token in the array as an identifier.
             * The generated script is unique depending on the attributes of
             * the elements.
             */
        
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
                
                pt = button_handle(token);
                
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
                
                pt = input_handle(token);
                
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
                pt = ("jrScript.open(\"" + token[1] + "\")");
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
    
    /*
     * Overrides the insertUpdate of DocumentListener
     * so that the Recorder can see when the document
     * has been updated with a new token-string to process.
     */
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
    
    /*
     * Methods for generating element scripts begin here.
     */
    //Julion and John
    private String input_handle(String[] token){
    //Get the type of input, text, button, password, hidden, radio, reset, submit, file, etc.
    if (token[4].equals("text")) {
        return ("jrScript.input.id('" + token[2] + "').value =\"" + token[5] + "\"");
    }
    else if (token[4].equals("button")){
        return ("jrScript.input.type(\"" + token[4] + "').value =(\"" + token[5] + "\").click()");
    }
    
        return "No Script Generated!";
    }
    
    //Ian H.
    private String button_handle(String token[]){
    if (!token[3].equals("<null>")){
        return ("jrScript.find(\"button\").with(\"name=='"+ token[3] + "'\").click()"); 
    }
       
    return "No Script Generated!";
    }
    
    //Rohit
    private String anchor_handle(String token[]) {
        return "";
    }
    private String frame_handle(String token[]) {
        return "";
    }
    //na
    private String image_handle(String token[]) {
        return "";
    }
    //Rohit
    private String link_handle(String token[]) {
        return "";
    }
    //em
    private String span_handle(String token[]) {
        return "";
    }
    //em
    private String div_handle(String token[]) {
        return "";
    }
    //jeremiah 
    private String form_handle(String token[]) {
        return "";
    }
    //james and khem
    private String option_handle(String token[]) {
        return "";
    }
    //james and khem
    private String select_handle(String token[]) {
        return "";
    }
    //Chris
    private String navigate_handle(String token[]) {
        return "";
    }
    private String textarea_handle(String token[]) {
        return "";
    }
    
        
    }
    

