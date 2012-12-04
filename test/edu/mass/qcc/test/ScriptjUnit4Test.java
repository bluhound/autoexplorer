/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc.test;

import edu.mass.qcc.*;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jeremiah
 */
public class ScriptjUnit4Test {
    
    public ScriptjUnit4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
   

    /**
     * Test of getScriptForToken method, of class Script.
     */
    @Test
    public void testGetScriptForToken() {
        System.out.println("*ScriptjUnit4Test : getScriptForToken()");
        
        
        TokenParse tp = new TokenParse();
        String[] tokenButton = {"<Click Button>","","","Click Me!"};
        String expResult = "jrScript.find(\"button\").with(\"name=='Click Me!'\").click()";
        Script script = new Script();
        String result = script.getScriptForToken("<Click Button>", tokenButton);
        assertEquals(expResult,result);
        
        
        
      
    }
     @AfterClass
    public static void tearDownClass() {
         
    }
}
