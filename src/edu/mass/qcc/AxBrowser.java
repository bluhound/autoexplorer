package edu.mass.qcc;

import com.teamdev.jxbrowser.dom.DOMElement;
import com.teamdev.jxbrowser.events.NavigationEvent;
import com.teamdev.jxbrowser.events.NavigationFinishedEvent;
import com.teamdev.jxbrowser.events.NavigationListener;
import com.teamdev.jxbrowser.events.StatusChangedEvent;
import com.teamdev.jxbrowser.events.StatusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    //import org.watij.webspec.dsl.WebSpec;
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
         DOMElement documentElement;
         EventListener clickEventListener;
         EventListener changeEventListener;
         EventListener inEventListener;
         EventListener outEventListener;
         
    AxBrowser(autoexplorer Ax){
    
        ax = Ax;
     
      }
    
    void open(){
        
        //Setup
        SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
       
                        ax.browserPane.add("Browser", ax.browser.getComponent());
                        
                        
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
        

            
        
        //Browser tab and enter listener
        
        ax.browser.getComponent().addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent ke) {
                
            }
            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {{
                if(ke.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){ 
                 
                    tokenString = ("<Action Key>::" + "Enter" + delim);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                         public void run() {
                         ax.consoleTextArea.setText(tokenString);
            }
        });
            }  {
                if(ke.getKeyCode() == java.awt.event.KeyEvent.VK_TAB){ 
                 
                    tokenString = ("<Action Key>::" + "Tab" + delim);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                         public void run() {
                         ax.consoleTextArea.setText(tokenString);
            }
        });
            }
            }
                
            }}
        });

      
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
        documentElement.removeEventListener("click", clickEventListener, false);        
        documentElement.removeEventListener("change", changeEventListener, false);      
        documentElement.removeEventListener("focusin", inEventListener, false);        
        documentElement.removeEventListener("focusout", outEventListener, false);      
        
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
        
        
        
        document = ax.browser.getDocument();
        documentElement = (DOMElement) document.getDocumentElement();
        
        
        //Listener for element clicks
        
        
        clickEventListener = new EventListener() {

         @Override
     public void handleEvent(Event evt) {
         
         org.w3c.dom.events.MouseEvent event = (org.w3c.dom.events.MouseEvent) evt;
         HTMLElement target = (HTMLElement) event.getTarget();
         //Send target to HTMLEvent for processing
         String tagName = target.getNodeName().toLowerCase();
         
         if (tagName.equals("input") || tagName.equals("textarea") || tagName.equals("select")){
             documentElement.addEventListener("change", changeEventListener, false);
                       
         }
         else {
         htmlEvent.processThis(ax, target, documentElement);
         }
         
         
         
     }
 };
                
   
                //Looks for changes in text.             
                changeEventListener = new EventListener() {

                @Override
                public void handleEvent(Event evt) {
                
                org.w3c.dom.events.MouseEvent event = (org.w3c.dom.events.MouseEvent) evt;
                HTMLElement target = (HTMLElement) event.getTarget();
                //Send target to HTMLEvent for processing
                
                htmlEvent.processThis(ax, target, documentElement);
                
                
            }
            };

        
                inEventListener = new EventListener() {

                @Override
                public void handleEvent(Event evt) {
                
                 System.out.println("Focused in, add click and text change listener");
                 //documentElement.addEventListener("change", changeEventListener, false);
                 documentElement.addEventListener("click", clickEventListener, false);
 
            }
            };

                documentElement.addEventListener("focusin", inEventListener, false);

                outEventListener = new EventListener() {

                @Override
                public void handleEvent(Event evt) {
                
                //Remove Listeners on focus out.
                System.out.println("Focused out, removing click and change listeners");
                 documentElement.removeEventListener("change", changeEventListener, false);
                 documentElement.removeEventListener("click", clickEventListener, false);
 
                }
                };

                documentElement.addEventListener("focusout", outEventListener, false);


            
            
            }
        });
        
       //Navigate to our home screen and wait for it to be ready.
       ax.browser.navigate(home);
       ax.browser.waitReady();
       
       
       //Change the tab text to current page
        SwingUtilities.invokeLater(new Runnable() {
       
        @Override
        public void run() {
        
        //Setup the ax.browser tab, html tab and addressbar
        ax.browserPane.setTitleAt(0,ax.browser.getTitle());
        String stringToken1 = ("<Navigate Home>::" + home); 
        ax.consoleTextArea.setText(stringToken1);
        //Put the current html address in the addressbar
        ax.addressBar.setText(ax.browser.getCurrentLocation());
        
                           }
                });
        
        }
   
        
          
        
    }       
             
 
 
    
                     