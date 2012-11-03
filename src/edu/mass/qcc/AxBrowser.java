package edu.mass.qcc;

import com.teamdev.jxbrowser.dom.DOMElement;
import com.teamdev.jxbrowser.events.NavigationEvent;
import com.teamdev.jxbrowser.events.NavigationFinishedEvent;
import com.teamdev.jxbrowser.events.NavigationListener;
import com.teamdev.jxbrowser.events.StatusChangedEvent;
import com.teamdev.jxbrowser.events.StatusListener;
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
    HTMLEvents htmlEvent = new HTMLEvents();
    
    AxBrowser(autoexplorer Ax){
    
        ax = Ax;
     
      }
    
    void open(){
        
        //Setup
        SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
       
                        ax.browserPane.add(ax.browser.getComponent());
                           }
                }); 
    
        ///////////////////////////////////
        //Action listeners start here.   //
        ///////////////////////////////////
        
        //Get the current page status and display in the statusLabel
        ax.browser.addStatusListener(new StatusListener() {
        
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
                
                ax.browser.navigate(home);
                ax.browser.waitReady();
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
                
            
                ax.browser.navigate( ax.addressBar.getText());
                ax.browser.waitReady();
                tokenString = ("<Action Navigate>::" +ax.browser.getCurrentLocation() + delim);
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
                
                   ax.browser.navigate( ax.addressBar.getText());
                   ax.browser.waitReady(); 
                    
                    tokenString = ("<Action Navigate>::" +ax.browser.getCurrentLocation() + delim);
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
                    
               ax.browser.navigate("http://www.google.com/search?q=" +  ax.searchField.getText());
               ax.browser.waitReady();
                tokenString = ("<Action Google_Search>::" +ax.browser.getCurrentLocation() + delim);
                
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
                
               ax.browser.goBack();
               ax.browser.waitReady();
                tokenString = ("<Action Back>::" +ax.browser.getCurrentLocation() + delim);
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
                
               ax.browser.goForward();
               ax.browser.waitReady();
                tokenString = ("<Action Forward>::" + ax.browser.getCurrentLocation() + delim);
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.consoleTextArea.setText(tokenString);
            }
        });    
         
            }});
        
       /*Now we need to update the address bar when we go to a new page
        * 
        * Change the navigation text, and most importantly, process clicks 
        */
          
       ax.browser.addNavigationListener(new NavigationListener() {
            @Override
        public void navigationStarted(final NavigationEvent event) {
        //Check if any data needs to be captured before leaving...
                if (captureInput) {
                    
                    document = ax.browser.getDocument();
                    
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
        
        //set the webpage title on the tab
        //set the title in its own swing thread
        String checkTitle =ax.browser.getTitle();
        if (checkTitle.length() > 40){
            for (int i = 0; i<=checkTitle.length(); ++i){
                    checkTitle = checkTitle.substring(0, 30);
            }
        
          
                }
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                ax.browserPane.setTitleAt(0,ax.browser.getTitle());
            }
        });
        
        
        
        Document document = ax.browser.getDocument();
        final DOMElement documentElement = (DOMElement) document.getDocumentElement();
        
        //Listener for element clicks
        documentElement.addEventListener("click", new EventListener() {
                @Override
            public void handleEvent(Event evt) {
                
                org.w3c.dom.events.MouseEvent event = (org.w3c.dom.events.MouseEvent) evt;
                HTMLElement target = (HTMLElement) event.getTarget();
                //Send target to HTMLEvent for processing
                htmlEvent.processThis(ax, target);
                
            }
        
        }, false);
        
        documentElement.addEventListener("focusout", new EventListener() {
                @Override
            public void handleEvent(Event evt) {
                
                org.w3c.dom.events.MouseEvent event = (org.w3c.dom.events.MouseEvent) evt;
                HTMLElement target = (HTMLElement) event.getTarget();
                htmlEvent.processThis(ax, target);

                
            }
        
        }, false);
        
        
        }
        });
        
       //Navigate to our home screen and wait for it to be ready.
       ax.browser.navigate(home);
       ax.browser.waitReady();
        
        //Change the tab text to current page
        SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
        
        //Setup theax.browser tab, html tab and addressbar
        ax.browserPane.setTitleAt(0,ax.browser.getTitle());
        String stringToken1 = ("<Navigate Home>::" + home); 
        ax.consoleTextArea.setText(stringToken1);
        //Put the current html address in the addressbar
        ax.addressBar.setText(ax.browser.getCurrentLocation());
                            }
                });
        
        }
   
        
          
        
    }       
             
 
 
    
                     