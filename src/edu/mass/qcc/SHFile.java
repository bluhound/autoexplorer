/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.Document;

/**
 *
 * @author Ian
 */
public class SHFile {
    
    autoexplorer ax;
    
    SHFile(){
    
    }
    SHFile(autoexplorer Ax){
        
        ax = Ax;
        
    }
    /**
     * @author ian
     * Opens a file dialog window
     * @return 
     */
    public int open(){
        
    //Get the users file selection and gives it the name fileChooser:
        JFileChooser fileChooser = new JFileChooser("c:\\webspec\\webspec\\console\\");
        
        //Get the return value of fileChooser so that we can make sure
        //no errors have happened.
        int returnValue = fileChooser.showOpenDialog(null);
        
        //Use that value to check for error and continue if none.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        
        //Get the name of the file the user picked:
        File selectedFile = fileChooser.getSelectedFile();
        
        
        //try/Catch simply try to run some piece of code and if there is
        //an error, it catches it and creates an error messege and exits.
        
        try {
                    //FileReader opens a way to read the file that we
                    //created above.
                    FileReader mFile = new FileReader(selectedFile.getPath());
                try (BufferedReader bufferedReader = new BufferedReader(mFile)) {
                    String line;
                    //This just says, keep getting a new line of text
                    //until we reach the end of the file: null
                    while ((line = bufferedReader.readLine()) != null){
                        //This is how we add the text to the text area
                        //append means to add it at the end.
                    ax.scriptTextArea.append(line + ax.newline);
                        
                    }
                }
                } catch (IOException ioex) {
                    System.err.println(ioex);
                    return(-1);
                }  
    }        
    return 0;    
    }
    public String open(int Option){
        
        //Get the users file selection and gives it the name fileChooser:
        JFileChooser fileChooser = new JFileChooser();
        
        //Get the return value of fileChooser so that we can make sure
        //no errors have happened.
        int returnValue = fileChooser.showOpenDialog(null);
        
        //Use that value to check for error and continue if none.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        
        //Get the name of the file the user picked:
        File selectedFile = fileChooser.getSelectedFile();
        return selectedFile.getAbsolutePath().toString();
        }
        return "-1";
        
    }
    /*
     * Saves the script area text in a file.
     */
    public int saveas(){
        
    // Grab the text from the text area and save it to a file
    String outTextToSave = ax.scriptTextArea.getText();   
    Document doc;
    doc = ax.consoleTextArea.getDocument();
    //This time, open the fileChooser using the showSaveDialog
    //instead of showOpendialog
    JFileChooser fileChooser = new JFileChooser("c:\\webspec\\webspec\\console\\");
   
    //Again check for errors
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        
        //Get the selected file 
        File selectedFile = fileChooser.getSelectedFile();
          
                
        //Try to print to the file using PrintWriter     
        
        try {
                try (PrintWriter printWriter = new PrintWriter(selectedFile)) {
                    printWriter.write(outTextToSave+"\n");
                    //printWriter.println(outTextToSave+"\n");
                }
            
            //Show file was saved in scriptArea
                ax.scriptTextArea.setText("File was saved.");
            
            //Catch all errors and log them.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    return 0;
    }
    public int saveas(Recorder rec){
        
    // Grab the text from the text area and save it to a file
    StringBuilder outTextToSave = rec.recBuffer;   
    Document doc;
    doc = ax.consoleTextArea.getDocument();
    //This time, open the fileChooser using the showSaveDialog
    //instead of showOpendialog
    JFileChooser fileChooser = new JFileChooser("c:\\webspec\\webspec\\console\\");
   
    //Again check for errors
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        
        //Get the selected file 
        File selectedFile = fileChooser.getSelectedFile();
          
                
        //Try to print to the file using PrintWriter     
        
        try {
                try (PrintWriter printWriter = new PrintWriter(selectedFile)) {
                    while (outTextToSave.length()!=-1){
                    printWriter.write(outTextToSave+"\n");
                    }
                    
                    
                }
            
            //Show file was saved in scriptArea
                ax.statusLabel.setText("File was saved.");
            
            //Catch all errors and log them.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    return 0;
    }
    public void saveas(File file, String str){
        
    // Grab the text from the text area and save it to a file
        String outTextToSave = str;   
    
    
    //Again check for errors
       
        //Get the selected file 
        File selectedFile = file;
          
                
        //Try to print to the file using PrintWriter     
    
        try {
                try (PrintWriter printWriter = new PrintWriter(selectedFile)) {
                     printWriter.println(outTextToSave);
                }
            
            //Show file was saved in scriptArea
                System.out.println("File was saved.");
           
            
            //Catch all errors and log them.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(autoexplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
    
} 
    

