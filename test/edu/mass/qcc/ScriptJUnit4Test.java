/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Em
 * Test of equal method , of class ScriptJUnit4Test
 * if the method is true so the test passed otherwise the test is fail
 */
public class ScriptJUnit4Test {
    
    public ScriptJUnit4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }
    
    
    /**
     * Test of getScriptForToken method, of class Script.
     * 
    
    */
    @Test
    public void testGetScriptForToken() {
        //Test Span
        System.out.println("*ScriptJUnit4Test : getScriptForToken()");
        TokenParse tp = new TokenParse();
        String[] tokenSpan = {"<Click Span>","","", "dark green",};
        
        String expResult = "jrScript.find(\"span\").with(\"innerText=='dark green'\").click()";
        Script script = new Script();
        String result = script.getScriptForToken("<Click Span>", tokenSpan);
        assertEquals(expResult,result);
       //Test Div
        System.out.println("*ScriptJUnit4Test : getScriptForToken()");
        
        String[] tokenDiv = {"<Click Div>","", "header","null"};
        String expResultDiv = "jrScript.find(\"div\").with(\"id=='header'\").click()";
         
        String resultDiv = script.getScriptForToken("<Click Div>", tokenDiv);
        assertEquals(expResultDiv,resultDiv);
        //test searchbox
        System.out.println("* ScriptJUnit4Test : getScriptForToken");
        TokenParse tpS =new TokenParse();
        String[] tokenSearch = {"<Action Google_Search>::", "www.google.com" };
      
        String expResultS = ("jrScript.open(\"" + "file:///C:/Users/Chris/Documents/NetBeansProjects/autoexplorer/src/edu/mass/qcc/SearchBox.html" + "\")");
        Script scriptS = new Script();
        String resultS = script.getScriptForToken(("jrScript.open(\"file:///C:/Users/Chris/Documents/NetBeansProjects/autoexplorer/src/edu/mass/qcc/SearchBox.html\")"), tokenSearch);
        assertEquals(expResultS,resultS);

        
        
         
    }
   @AfterClass
    public static void tearDownClass()throws Exception {
       
    }
 
}
