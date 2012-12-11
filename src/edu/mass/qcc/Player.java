/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class Player {
    
    /*
     * @author Em and Chris
     * @param
     * @Opens a script file and plays it using watij jRuby
     * Player is not yet implemented.
     */
    Player(){
    String fname = "";
    SHFile shfile = new SHFile();
    fname = shfile.open(1);
    if (!"-1".equals(fname)){
            try {
                //Send filename to the webspec player
                System.out.println(fname);
                    Runtime.getRuntime().exec("cmd cd C:\\webspec\\webspec");
                    String[] getFname = fname.split(Pattern.quote(File.separator));
                    int length = getFname.length-1;
                    fname = getFname[length];
                    System.out.println(fname);
                    Runtime.getRuntime().exec("cmd  console console/" + fname);
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
    }
    }
}
