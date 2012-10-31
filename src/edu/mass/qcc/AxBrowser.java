package edu.mass.qcc;

import com.teamdev.jxbrowser.Browser;
import com.teamdev.jxbrowser.BrowserFactory;
import com.teamdev.jxbrowser.BrowserType;
import com.teamdev.jxbrowser.dom.DOMElement;
import com.teamdev.jxbrowser.events.NavigationEvent;
import com.teamdev.jxbrowser.events.NavigationFinishedEvent;
import com.teamdev.jxbrowser.events.NavigationListener;
import com.teamdev.jxbrowser.events.StatusChangedEvent;
import com.teamdev.jxbrowser.events.StatusListener;
import com.teamdev.jxbrowser.prompt.CloseStatus;
import com.teamdev.jxbrowser.prompt.DefaultPromptService;
import com.teamdev.jxbrowser.prompt.ScriptErrorParams;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.html.HTMLElement;

/**
 *
 * @author Ian Hickey
 */
public class AxBrowser {
    //Common Variables
    public Browser browser;
    public String  tokenString = "";
    public String  newline = "\n";
    public String  home = "http://www.google.com";
    public String  delim = "::";
    public String[] tkString;
    public String eValue;
    int TAGNAME = 1;
    int ID = 2;
    int VALUE = 5;
    Boolean captureInput = false;
    autoexplorer ax;
    Document document;
    
    AxBrowser(autoexplorer Ax){
    //Get a new browser
        //Needs try/catch!
        ax = Ax;
    
         browser = BrowserFactory.createBrowser(BrowserType.getPlatformSpecificBrowser());
        //Make sure browser doesn't hang on javascript error
         browser.getServices().setPromptService(new DefaultPromptService(){
            @Override
            public CloseStatus scriptErrorRequested(ScriptErrorParams params) {
                return CloseStatus.OK;
            }
        }); 
        
        
         
         
      }
    
    void open(){
        
        //Setup
        SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
       
        ax.browserPane.add("<html><body marginwidth=40>Main Browser</body></html>", browser.getComponent());
        
        
        //Add the HTML Tab 1
        ax.browserPane.add(ax.jScrollPane2);
        ax.browserPane.setTitleAt(1, "HTML");
        
        //Add the tree to the TAB 2
        JTree myDOMTree = new JTree();
        
        ax.browserPane.add(myDOMTree);
        ax.browserPane.setTitleAt(2, "DOM");
        
        //This tab will handle adding new browser windows
        JTextArea blankTextArea = new JTextArea();
        ax.browserPane.add(blankTextArea);
        ax.browserPane.setTitleAt(3, "New Tab");    
                            
                            
                 
                            }
                }); 
    
        ///////////////////////////////////
        //Action listeners start here.   //
        ///////////////////////////////////
        
        //Get the current page status and display in the statusLabel
        browser.addStatusListener(new StatusListener() {
        
        @Override
        public void statusChanged(final StatusChangedEvent event) {
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.statusLabel.setText(event.getStatusText());
            }
        });
            
        }
        });
        
        //Action Listener for homeButton
         ax.homeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                browser.navigate(home);
                browser.waitReady();
                tokenString = ("<Action Home>::" + home + delim);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                    
                              
    }});
        
        //Action Listener Go to a new http web address
         ax.goButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
            
                browser.navigate( ax.addressBar.getText());
                browser.waitReady();
                tokenString = ("<Action Navigate>::" + browser.getCurrentLocation() + delim);
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                   
               
     }});
        

            
        
        
        //Address Bar Enter Key listener, same as above but with Enter Key
         ax.addressBar.addKeyListener(new java.awt.event.KeyAdapter() {
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){ 
                
                    browser.navigate( ax.addressBar.getText());
                    browser.waitReady(); 
                    
                    tokenString = ("<Action Navigate>::" + browser.getCurrentLocation() + delim);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                         public void run() {
                         ax.consoleTextArea.setText(tokenString);
            }
        });
                     
                }
            }});
        
        //Search Bar Enter Key listener
         ax.searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
                    
                browser.navigate("http://www.google.com/search?q=" +  ax.searchField.getText());
                browser.waitReady();
                tokenString = ("<Action Google_Search>::" + browser.getCurrentLocation() + delim);
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                
                
                }
            }});
       
        //Action Listener Go Back
         ax.backButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                browser.goBack();
                browser.waitReady();
                tokenString = ("<Action Back>::" + browser.getCurrentLocation() + delim);
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });
                  
        
            
                
               
                
            }});
        
        //Action go forward
         ax.forwardButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                browser.goForward();
                browser.waitReady();
                tokenString = ("<Action Forward>::" + browser.getCurrentLocation() + delim);
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });    
         
            }});
        
       /*Now we need to update the address bar when we go to a new page
        * Also, lets get the new HTML when we go to a new page
        * Change the navigation text, and most importantly,process clicks 
        */
          
        browser.addNavigationListener(new NavigationListener() {
            @Override
        public void navigationStarted(final NavigationEvent event) {
        //Check if any data needs to be captured before leaving...
                if (captureInput) {
                    
                    document = browser.getDocument();
                    
                    //tkString[ID] is the id of the element
                    if (!("<null>".matches(tkString[ID]))){
                        System.out.print("\n<---Found element id - trying to capture--->"+tkString[2]+"\n");
                        DOMElement documentElement = (DOMElement) document.getElementById(tkString[ID]);
                        eValue = documentElement.getAttribute("value");
                        System.out.print("Captured Value for Element: " + eValue);
                        
                        //Turn off capturing.
                        captureInput = false;
                    }
                    
                
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tkString[0] + delim + tkString[1] + delim + tkString[2] + delim + tkString[3] + delim + tkString[4] + delim + eValue + delim);
            
                }
                
              });
                }
              
        }

            @Override
        public void navigationFinished(NavigationFinishedEvent event) {
        
               
        ax.addressBar.setText(event.getUrl());
        //Grab the new HTML for the current page.........
        final String HTMLstring = browser.getContent();
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.HTMLTextArea1.append(HTMLstring);
            }
        });
        
        
        //set the webpage title on the tab
        //set the title in its own swing thread
        String checkTitle = browser.getTitle();
        if (checkTitle.length() > 40){
            for (int i = 0; i<=checkTitle.length(); ++i){
                    checkTitle = checkTitle.substring(0, 40);
            }
        
          
                }
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.browserPane.setTitleAt(0, browser.getTitle());
            }
        });
        
        
        
        Document document = browser.getDocument();
        final DOMElement documentElement = (DOMElement) document.getDocumentElement();
        
        //Listener for element clicks
        documentElement.addEventListener("click", new EventListener() {
                @Override
            public void handleEvent(Event evt) {
                
                org.w3c.dom.events.MouseEvent event = (org.w3c.dom.events.MouseEvent) evt;
                HTMLElement target = (HTMLElement) event.getTarget();
                
                ////////////////////////////////////////
                //Start processing clicks on elements //
                ////////////////////////////////////////
                
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
                //Alert that we will need to capture the text
                
                String iName = target.getAttribute("name");
                String iType = target.getAttribute("type");
                String iValue = target.getAttribute("value");
                String iId = target.getId();
                if ("".equals(iValue)){iValue = "<null>";}
                if ("".equals(iType)){iType = "<null>";}
                if ("".equals(iName)){iName = "<null>";}
                if ("".equals(iId)) {iId = "<null>";}
                
                if (iType.equals("text")){
                tokenString = ("<Click Input>::" + tagName + delim + iId + delim + iName + delim + iType + delim + iValue + delim);
                //Alert that we will need to capture the text
                captureInput = true;
                //Send our split token[]'s
                tkString = tokenString.split(delim); //For use by navigater listener...
                }
                if ((iType).equals("select")){
                
                String sForm = target.getAttribute("form");
                String sLength = target.getAttribute("length");
                String sMultiple= target.getAttribute("multiple");
                String sName = target.getAttribute("name");
                String sSelectIndex = target.getAttribute("selectindex");
                String sSize = target.getAttribute("size");
                String sType = target.getAttribute("type");
                
                tokenString = ("<Click Select>::" + tagName + "::" + sForm + "::" + sLength + "::" + sMultiple + "::" + sName + "::" + sSelectIndex + "::" + sSize + "::" + sType + "::");
                ax.consoleTextArea.setText(tokenString);
                }
                //Option Element
                //NHUT TA
                if (("option").equals(tagName)){
                
                String oForm = target.getAttribute("form");
                String oIndex = target.getAttribute("index");
                String oText= target.getAttribute("text");
                String oValue = target.getAttribute("value");
                
                tokenString = ("<Click Option>::" + tagName + "::" + oForm + "::" + oIndex + "::" + oText + "::" + oValue + "::");
                ax.consoleTextArea.setText(tokenString);
                }
                }
                
                
                //Option Element
                
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
                
                
            }
        }, false);
        
        
        
        }
        });
        //Navigate to our home screen and wait for it to be ready.
        browser.navigate(home);
        browser.waitReady();
        
        //Change the tab text to current page
        SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
        
        //Setup the browser tab, html tab and addressbar
        ax.browserPane.setTitleAt(0, browser.getTitle());
        String stringToken1 = ("<Navigate Home>::" + home); 
        ax.consoleTextArea.setText(stringToken1);
        
        //Read the HTML from the homepage into a string.
        String myHTML = browser.getContent();
        
        //Output the string in the HTMLTextArea1 on the second tab
        ax.HTMLTextArea1.setText(myHTML);
        
        //Put the current html address in the addressbar
        ax.addressBar.setText(browser.getCurrentLocation());
                            }
                });
        
        }
   
        
          
        
    }       
             
 
 
    
                     