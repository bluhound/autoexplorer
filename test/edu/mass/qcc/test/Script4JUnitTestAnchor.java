/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc.test;

import edu.mass.qcc.Script;
import edu.mass.qcc.TokenParse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
 import static org.junit.Assert.*;

/**
 * 
 * @author Rohit Didwania
 * Tests method of ScriptJUnit4Anchor
 * if the method is true so the test passed otherwise the test fails.
 */
public class Script4JUnitTestAnchor {
    
    public Script4JUnitTestAnchor() {
    }
    
    @BeforeClass
    public static void setUpClass() {
     }
    
   

    /**
     * Test of getScriptForToken method, of class Script.
     */
    @Test
    public void testGetScriptForToken() {
        //test input
        
        System.out.println("*ScriptJUnit4Test : getScriptForToken()");
         TokenParse tp =new TokenParse();
        String[] tokenAnchor = {"<Click Anchor>", "", "", "", "url"};
        
        String expResult = ("jrScript.open(\"" + "" + "\")");
         Script script = new Script();
        String result = script.getScriptForToken ("jrScript.open(\"\")",tokenAnchor);
        assertEquals(expResult, result);
        
        
        
        
        
        
        
        
      /*
      String pt = "";
        String[] token = null;
        Script instance = new Script();
        String expResult = "";
         String result = instance.getScriptForToken(pt, token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
         
    }
      @AfterClass
    public static void tearDownClass() {
    }

}
