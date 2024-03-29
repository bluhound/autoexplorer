/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

/**
 *
 * @author Csc 207
 */
public class Script {
    TokenParse tp = new TokenParse();
    String GS = "Generating Script for--->";
    char COMMENT = '#';
    char NEWLINE = '\n';
    private String linestr = new String();
    
    public Script(){
        linestr = "";
    }
    /*
     * private String getScriptForToken(String pt, String[] token)
     * Iterates through the token-array (token[]) and depending on
     * which token is found and the attributes for that element, 
     * generates a jRuby script.
     * 
     */
    public String getScriptForToken(String pt, String[] token) {
            /* Generate a script for each token by searching the first
             * token in the array as an identifier.
             * The generated script is unique depending on the attributes of
             * the elements.
             */
        
            if (tp.parseToken[tp.ANCHOR].matches(pt)){
                System.out.println(GS + pt);
                pt = anchor_handle(token);
                return (pt);
               
            }
            else if (tp.parseToken[tp.KEY].matches(pt)){
                System.out.println(GS + pt);
                pt = key_handle(token);
                return (pt);
            }
            
            else if (tp.parseToken[tp.IMAGE].matches(pt)){
                System.out.println(GS + pt);
                pt = image_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.LINK].matches(pt)){
                System.out.println(GS + pt);
                pt = navigate_handle(token);
                return (pt);
            }    
            else if (tp.parseToken[tp.SPAN].matches(pt)){
                System.out.println(GS + pt);
                pt = span_handle(token);
                return (pt);
            }    
            else if (tp.parseToken[tp.BUTTON].matches(pt)){
                System.out.println(GS + pt);
                pt = button_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.DIV].matches(pt)){
                System.out.println(GS + pt);
                pt = div_handle(token);
                return (pt);
            }
            
            else if (tp.parseToken[tp.INPUT].matches(pt)){
                System.out.println(GS + pt);
                pt = input_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.OPTION].matches(pt)){
                System.out.println(GS + pt);
                pt = option_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.SELECT].matches(pt)){
                System.out.println(GS + pt);
                pt = select_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.OTHER].matches(pt)){
                System.out.println(GS + pt);
                return (COMMENT + pt);
            }
            else if (tp.parseToken[tp.FORWARD].matches(pt)){
                System.out.println(GS + pt);
                pt = navigate_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.BACK].matches(pt)){
                System.out.println(GS + pt);
                pt = navigate_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.SEARCH].matches(pt)){
                System.out.println(GS + pt);
                pt = search_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.NAVIGATE].matches(pt)){
                
                System.out.println(GS + pt);
                pt = navigate_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.HOME].matches(pt)){
                System.out.println(GS + pt);
                pt = navigate_handle(token);
                return (pt);
            }
            else if (tp.parseToken[tp.TEXTAREA].matches(pt)){
                System.out.println(GS + pt);
                pt = textarea_handle(token);
                return (pt);
            }
            else {
                System.out.print("-->Token Not Found" + NEWLINE);
                return (pt);
            }
               
        }
    /**
     * @Author CSC-207
     * @param token
     * @return 
     */
    private String input_handle(String[] token){
        //Get the type of input, text, button, password, hidden, radio, reset, submit, file, etc.
        //Make sure id or name is not <null> by calling hasName and hasId
        //Julion and John
        int TYPE = 4;
        int VALUE = 5;
        this.linestr = "";
        if ("<null>".equals(token[5])){}
        else{
        switch (token[TYPE]) {
            
            case "text"://Julion and John
                if (tp.hasId(token)){
                    this.linestr = ("jrScript.input.id('".concat(token[tp.ID]).concat("').value ='").concat(token[VALUE]).concat("'"));
                    System.out.println(getLine());
                    return "";
                }else if (tp.hasName(token)){
                    this.linestr = ("jrScript.input.name('" + token[tp.NAME] + "').value ='" + token[VALUE] + "'");
                    System.out.println(getLine());
                    return "";
                }else{ 
                    return (COMMENT + "Couldn't locate name or ID for element" + NEWLINE);}
            case "button":
                if (tp.hasId(token)){
                return ("jrScript.input.id('" + token[tp.ID] + "').click()");
                }
            case "password":
                if (tp.hasId(token)){
                setLine("jrScript.input.id('" + token[tp.ID] + "').value ='" + token[VALUE] + "'");
                }
            case "hidden":
                return ("jrScript.input.type('" + token[TYPE] + "').value =(\"" + token[VALUE] + "\").click()");
            case "submit":
                if (tp.hasId(token)){
                return ("jrScript.input.id('" + token[tp.ID] + "').click()");
                }
            case "checkbox":
                return ("jrScript.input.type('" + token[TYPE] + "').click()");
            case "file":
                return ("jrScript.input.type('" + token[4] + "').click()");
            
            default:
                return (COMMENT + "Input type not yet implemented.");
        }
        
   
}
        return "";
    }    
    
    private String button_handle(String token[]){
    if (tp.hasName(token)){
        return ("jrScript.find(\"button\").with(\"name=='"+ token[tp.NAME] + "'\").click()"); 
    }else if (tp.hasId(token)){
        return ("jrScript.find(\"button\").with(\"id=='"+ token[tp.ID] + "'\").click()");
    }else {
        return (COMMENT + "Couldn't locate name or ID for element" + NEWLINE);
    }
       
    
    }
    
   /* @ author Rohit Didwania
     * param Anchor handle is a list of HTML DOM elements attribute. 
     * @return Anchor Handle script
     * 
     * generates JRuby Script for anchor handle with ID, HREF and NAME.
     */
    private String anchor_handle(String token[]) { 
    if (tp.hasId(token))//int HREF = 4;
    { return ("jrScript.open(\"" + token[tp.ID] + "\")"); 
    
    } else if (tp.hasHref(token)){
        return ("jrScript.open(\"" + token[tp.HREF] + "\")");
    } else if (tp.hasName(token)) {
        return ("jrScript.open(\"" + token[tp.NAME] + "\")");
    } else {
        return (COMMENT + "Has no id,name or href");
    }
   
    







    }
    //na
    private String image_handle(String token[]) {
        if(tp.hasId(token)&&!"".equals(token[tp.ID])){
        return ("jrScript.find(\"image\").with(\"id=='"+token[tp.ID]+"'\").click()");
        } else if(tp.hasimagSrc(token)){
            return("jrscript.find(\"image\").with(\"imgSrc=='"+token[tp.Src]+"'\").click()");
        } 
        return"";
    }
    //Rohit
    private String link_handle(String token[]) {
        int LINK = 3;
        return ("jrScript.open(\"" + token[LINK] + "\")");
    }
  
    /**
     * @ author Em Trieu
     * @param String token is a list of HTML DOM element attribute.
     * @return select script.
     * 
     * generate ruby code for select tag with InnerText, Id and Name.
     */
    private String span_handle(String token[]) {
        if (tp.hasInnerText(token))//Check for InnerText EM
         {
             return ("jrScript.find(\"span\").with(\"innerText=='"+token[tp.INNERTEXT]+"'\").click()");
         } else if (tp.hasId(token)) //Check for Id EM
            {
             //else lookup by id
             return  ("jrScript.find(\"span\").with(\"id=='"+token[tp.ID]+"'\").click()");
             //otherwhise it lookup for name
         } else if (tp.hasName(token))//Check for Name EM
         {
             return  ("jrScript.find(\"span\").with(\"name=='"+token[tp.NAME]+"'\").click()");
          
         } else //the attributes are not found, return comment EM
         {
             return (COMMENT + "Has no innerText, Id or Name so we can not find SPAN" + NEWLINE);
         
         }
             }
     /* @ author Em Trieu
     * param String token is a list of HTML DOM element attribute.
     * @return select script.
     * 
     * gennergate ruby code for select tag with InnerText, Id.
     */
    private String div_handle(String token[]) {
         
         if (tp.hasInnerText(token))//Check for InnerText EM
         {
             return ("jrScript.find(\"div\").with(\"innerText=='"+token[tp.INNERTEXT]+"'\").click()");
         } else if (tp.hasId(token))//Check for Id EM
         {
          return  ("jrScript.find(\"div\").with(\"id=='"+token[tp.ID]+"'\").click()");
         } else // the attributes are not found, return comment EM
         {
          return (COMMENT + "Has no id or innertext so we can not find DIV" + NEWLINE);
         } }
   
    
    /* @ author : James Ta and Khem Basdola
    /* param String token is a list of HTML DOM element attribute.
     * @return select script.
     * 
     * gennergate ruby code for option (Name, Value, ID).
     */
    private String option_handle(String token[]) {
        if (tp.hasName(token)&&!"".equals(token[3])&&tp.hasValue(token)&&!"<null>".equals(token[tp.VALUE]))//Check for name or value JT
         {
           return ("jrScript.find(\"option\").with(\"name=='"+token[tp.NAME]+"'\").set(\"value='"+token[4]+"'\")");
         } else 
              
         {
          return (COMMENT + "Has no id or form so we can not find DIV" + NEWLINE);
         } //if the attributes are not found, return comment JT
    }
    

    /* @ author : James Ta and Khem Basdola
    /* param String token is a list of HTML DOM element attribute.
     * @return select script.
     * 
     * gennergate ruby code for select (Name, Value, ID).
     */
    private String select_handle(String token[]) {
       if (tp.hasName(token)&&!"".equals(token[3])&&tp.hasValue(token)&&!"<null>".equals(token[tp.VALUE]))//Check for name or value kb
         {
           return ("jrScript.find(\"select\").with(\"name=='"+token[tp.NAME]+"'\").set(\"value='"+token[4]+"'\")");
         } else 
              
         {
          return (COMMENT + "Has no id or form so we can not find DIV" + NEWLINE);
         } //if the attributes are not found, return comment kb
    }
    /** 
     * @Chris Mitchell
     * @Parameter token 
     * @return select script jRuby 
     **/
    private String navigate_handle(String token[]) {
        if (tp.hasAddress(token))
        {
            return ("jrScript.open(\"" + token[tp.ADDRESS] + "\")");
        } else  {
            return (COMMENT + "Address can not be found" + NEWLINE);
    }
      
    }
    
    private String textarea_handle(String token[]) {
        
        return ("jrScript.find(\"textarea\").with(\"name=='" + token[tp.NAME] + "'\").value='" + token[5] + "'");
    
    }
    private String search_handle(String token[]) {
        int ADDRESS = 1;
        return ("jrScript.open(\"" + token[ADDRESS] + "\")");
    
    }
    private String key_handle(String token[]) {
        if (token[1].equals("Enter")){
        String returnValue = (getLine().concat("\n").concat("robot.keyPress(KeyEvent::VK_ENTER);"));    
        
        return (returnValue);// + "\n" + "robot.keyPress(KeyEvent::VK_ENTER);");    
        }
        else{
        return (getLine());// + "\n" + "robot.keyPress(KeyEvent::VK_TAB);");
        }
    }

    /**
     * @return the line
     */
    public String getLine() {
        return linestr;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.linestr = line;
        
    }
}
