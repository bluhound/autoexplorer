/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import javax.swing.SwingUtilities;
import org.w3c.dom.html.HTMLElement;

/**
 *
 * @author Ian
 */
public class HTMLEvents {
    String tokenString = "";
    String delim = "::";
    autoexplorer ax;
    public String  newline = "\n";
    public String  home = "http://www.google.com";
    public String[] tkString;
    public String eValue;
    int TAGNAME = 1;
    int ID = 2;
    int VALUE = 5;
    Boolean captureInput = false;
    
    
    HTMLEvents(){
    
    }
    int processThis(autoexplorer AX, HTMLElement target){
        ax = AX;
        
        
        String tagName = target.getNodeName().toLowerCase();
                String id = target.getId();
                if (id == null){id = "<null>";
                
                }
                //If element is a link, get href
                if (("a").equals(tagName)){
                
                String aname = target.getAttribute("name");
                String ahref = target.getAttribute("href");
                
                
                tokenString = ("<Click Anchor>::" + tagName + delim + id + delim + aname + delim
                                + ahref + delim );
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                
                }
                //Get the name of the frame
                else if (("frame").equals(tagName)){
  
                String frameTitle = target.getAttribute("title");
                
                tokenString = ("<Click Frame>::" + tagName + delim + id + delim + frameTitle + delim);
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
                tokenString = ("<Click Image>::" + tagName + delim + id + delim + imgAlt + delim + imgSrc + delim);
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
                
                tokenString = ("<Click Link>::" + tagName + delim + id + delim + link + delim);
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
                 //String sInner = DOMElement.getTextContent();
                tokenString = ("<Click Span>::" + tagName + delim + id + delim + sName + delim);
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
                
                tokenString = ("<Click Button>::" + tagName + delim + id + delim + bName + delim + bType + delim + bValue + delim);
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
                
                if ("".equals(dId)) {dId = "<null>";}
                if (dClass.isEmpty()) {dClass = "<null>";}
                
                tokenString = ("<Click Div>::" + tagName + delim + dId + delim + dClass + delim);
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
                if ("".equals(fName)){fName = "<null>";}
                if ("".equals(fTarget)){fName = "<null>";}
                if ("".equals(fId)){fName = "<null>";}
                
                tokenString = ("<Click Form>::" + tagName + delim + fId + delim + fName + delim + fTarget + delim);
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
                
                tokenString = ("<Click TextArea>::" + tagName + delim + id + delim + tName + delim + tType + delim + tValue + delim);
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
                if ("".equals(iValue)){iValue = "<null>";}
                if ("".equals(iType)){iType = "<null>";}
                if ("".equals(iName)){iName = "<null>";}
                if ("".equals(iId)) {iId = "<null>";}
                
                tokenString = ("<Click Input>::" + tagName + delim + iId + delim + iName + delim + iType + delim + iValue + delim);
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
                
                tokenString = ("<Click Select>::" + tagName + "::" + sForm + "::" + sLength + "::" + sMultiple + "::" + sName + "::" + sSelectIndex + "::" + sSize + "::" + sType + "::");
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
                
                tokenString = ("<Click Option>::" + tagName + "::" + oForm + "::" + oIndex + "::" + oText + "::" + oValue + "::");
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
                if ("".equals(oValue)){oValue = "<null>";}
                if ("".equals(oType)){oType = "<null>";}
                if ("".equals(oName)){oName = "<null>";}
                if ("".equals(oId)){oId = "<null>";}
                
                tokenString = ("<Click Other>::" + tagName + delim + oId + delim + oName + delim + oType + delim + oValue + delim);    
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
