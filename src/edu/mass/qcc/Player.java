/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Player {
    
    /*
     * @author Em and Chris
     * @param
     * @Opens a script file and plays it using watij jRuby
     */
    Player(){
    String fname = "";
    SHFile shfile = new SHFile();
    fname = shfile.open(1);
    if (!"-1".equals(fname)){
            try {
                //Send filename to the webspec player
                    Runtime.getRuntime().exec("cmd C:\\webspec" + fname);
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
    }
    }
}
