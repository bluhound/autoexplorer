/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent; 
/**
 *
 * @author Ian
 */
public class SendK {
    public SendK(){
    }
    /**
     * 
     * @param key
     * @throws AWTException 
     */
    void type(String key) throws AWTException {
        Robot robot = new Robot();
    switch (key){
        case ("Enter"): robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
        break;
        case ("Tab"): robot.keyPress(KeyEvent.VK_TAB);robot.keyRelease(KeyEvent.VK_TAB);
        break;
        
        default: break;
    }
    
    }
    
}
