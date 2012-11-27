/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

/**
 *
 * @author Qcc 207
 */
public class Player {
    
    /*
     * Opens a script file and plays it using watij jRuby
     */
    Player(){
    String fname = "";
    SHFile shfile = new SHFile();
    fname = shfile.open(1);
    if (!"-1".equals(fname)){
    //Send filename to the webspec player
        
    
    }
    }
}
