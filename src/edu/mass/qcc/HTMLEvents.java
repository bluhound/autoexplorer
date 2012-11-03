/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import com.teamdev.jxbrowser.dom.DOMElement;
import javax.swing.SwingUtilities;
import org.w3c.dom.html.HTMLElement;

/**
 *
 * @author Ian
 */
public class HTMLEvents {
    String tokenString = "";
    String oldText;
    autoexplorer ax;
    
    HTMLEvents(){
    
    }
    /*
     * processThis(autoexplorer AX, HTMLElement target)
     * Checks that an element exists and turns the element atrributes into
     * a token string by calling the tokenize method from the TokenParse class
     * 
     */
    int processThis(autoexplorer AX, HTMLElement target, DOMElement domElement){
        ax = AX;
        TokenParse tp = new TokenParse();
        oldText = tokenString;
        String tagName = target.getNodeName().toLowerCase();
                String id = target.getId();
                if (id == null){id = tp.parseToken[tp.NULL];
                
                }
                //If element is a link, get href
                if (("a").equals(tagName)){
                
                String aName = target.getAttribute("name");
                String aHref = target.getAttribute("href");
                String[] elements = {tp.parseToken[tp.ANCHOR], tagName, id, aName, aHref};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                
                }
                //Get the name of the frame
                else if (("frame").equals(tagName)){
                String fInner = domElement.innerHTML();
                String frameTitle = target.getAttribute("title");
                String[] elements = {tp.parseToken[tp.FRAME], tagName, id, frameTitle, fInner};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //Handle image click
                else if (("img").equals(tagName)){
               
                String imgAlt = target.getAttribute("alt");
                String imgSrc = target.getAttribute("src");
                String[] elements = {tp.parseToken[tp.IMAGE], tagName, id, imgAlt, imgSrc};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //handle link click
                else if (("link").equals(tagName)){
               
                String link = target.getAttribute("href");
                String[] elements = {tp.parseToken[tp.LINK], tagName, id, link};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                
                //Look for child in span
                else if (("span").equals(tagName)){
                String sName = target.getTextContent();
                String[] elements = {tp.parseToken[tp.SPAN], tagName, id, sName};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                else if (("button").equals(tagName)){
               
                String bName = target.getAttribute("name");
                String bType = target.getAttribute("type");
                String bValue = target.getAttribute("value");
                String[] elements = {tp.parseToken[tp.BUTTON], tagName, id, bName, bType, bValue};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //Display div
                else if (("div").equals(tagName)){
               
                String dClass = target.getClassName();
                String dId = target.getId();
                String[] elements = {tp.parseToken[tp.DIV], tagName, dId, dClass};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //Display form information
                else if (("form").equals(tagName)){
                
                String fName = target.getAttribute("name");
                String fTarget = target.getAttribute("target");
                String fId = target.getId();
                String[] elements = {tp.parseToken[tp.FRAME], tagName, fId, fName, fTarget};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //TextArea
                else if (("textarea").equals(tagName)){
                
                String tName = target.getAttribute("name");
                String tType = target.getAttribute("type");
                String tValue = target.getAttribute("value");
                String[] elements = {tp.parseToken[tp.TEXTAREA], tagName, id, tName, tType, tValue};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                
                //Input Element
                else if (("input").equals(tagName)){
                   
                String iName = target.getAttribute("name");
                String iType = target.getAttribute("type");
                String iValue = target.getAttribute("value");
                String iId = target.getId();
                String[] elements = {tp.parseToken[tp.INPUT], tagName, iId, iName, iType, iValue};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                } 
                else if (tagName.equals("select")){
                
                String sForm = target.getAttribute("form");
                String sLength = target.getAttribute("length");
                String sMultiple= target.getAttribute("multiple");
                String sName = target.getAttribute("name");
                String sSelectIndex = target.getAttribute("selectindex");
                String sSize = target.getAttribute("size");
                String sType = target.getAttribute("type");
                String[] elements = {tp.parseToken[tp.SELECT], tagName, id, sName, sForm, sLength, sMultiple, sSelectIndex, sSize, sType};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                //Option Element
                //NHUT TA
                else if (tagName.equals("option")){
                
                String oForm = target.getAttribute("form");
                String oIndex = target.getAttribute("index");
                String oText= target.getAttribute("text");
                String oValue = target.getAttribute("value");
                String[] elements = {tp.parseToken[tp.OPTION], tagName, id, oForm, oIndex, oText, oValue};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                
                //Show any other elements attributes in the console
                else  {
                String oType = target.getAttribute("type");
                String oName = target.getAttribute("name");
                String oValue = target.getAttribute("value");
                String oId = target.getId();
                String[] elements = {tp.parseToken[tp.OTHER], tagName, oId, oName, oType, oValue};
                tokenString = tp.tokenize(elements);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                }
                ///////////////////////////////////
                //End processing element clicks  //
                ///////////////////////////////////
                
    return 0;
    }

}
