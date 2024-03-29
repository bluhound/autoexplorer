/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

/**
 *
 * @author Ian
 */
public class TokenParse {
    String delim = "::";
    //Recorder Tokens
    String[] parseToken =
    {"<Click Anchor>", "<Click Frame>", "<Click Image>", "<Click Link>",
     "<Click Span>",  "<Click Button>", "<Click Div>", "<Click Form>",
     "<Click Input>", "<Click Option>", "<Click Select>", "<Click Other>",
     "<Type Text>", "<Action Key>", "<Action Forward>", "<Action Back>", 
     "<Action Google_Search>", "<Action Navigate>", "<Action Home>", 
     "<Click TextArea>", "<null>",
     };
    //Index for the parseTokens[]
    
    int ANCHOR = 0;
    int FRAME = 1;
    int IMAGE = 2;
    int LINK = 3;
    int SPAN = 4;
    int BUTTON = 5;
    int DIV = 6;
    int FORM = 7;
    int INPUT = 8;
    int OPTION = 9;
    int SELECT = 10;
    int OTHER = 11;
    int TEXT = 12;
    int KEY = 13;
    int FORWARD = 14;
    int BACK = 15;
    int SEARCH = 16;
    int NAVIGATE = 17;
    int HOME = 18;
    int TEXTAREA = 19;
    int NULL = 20;
    

    
    //Element index
    int TOKEN = 0;
    int TAGNAME = 1;
    int ID = 2;
    int HREF = 4;
    int NAME = 3;
    int INNERTEXT = 3;
    int ADDRESS = 1;
    int VALUE = 4;
    int Src = 4;
  
    //try for the radio button
   
    String FOUND = "Sending Found Token to Script--->";
    
    public TokenParse(){
    
    }
    /*
     * private String tokenParse(String tpString)
     * Takes a string as input where each element in
     * the string is seperated by the delimiter ::
     * and checks the first token of each string to
     * against our parseTokens[] (see above) and sends the result to 
     * generateScriptforToken() to actually generate the script.
     * 
     * Input: tpString
     * tpString is a token string in the form: 
     * token::tagname::id::Name::attribute1::attribute2::...::attribute10::
     * 
     * Output: String jRubyScript
     * jRubyScript using the Watij plug-in can interpret
     * the script generated by this method.
     */
    public String TokenParse(String tpString){
        Script script = new Script();
        String[] token;
        String jRubyScript;
        token = tpString.split("::");
        
        if (token!=null){
        System.out.print("tokenParse-->");
        //Start matching the first token in token String with our parse tokens
        for (int i=0; i<= parseToken.length;i++){
            if (parseToken[i].matches(token[0])){
                System.out.print(FOUND);
                jRubyScript = script.getScriptForToken(parseToken[i], token);
                return jRubyScript;
            }
        
        }
        
        }
       return null; 
    }
    
    /*
     * tokenize(String[] token)
     * Takes a string array and adds a delimiter :: between each member.
     */
    public String tokenize(String[] token){
        int i;
        String _tokenStr = "";
        for (i=0; i<token.length; i++){
            if ("".equals(token[i])|| "null".equals(token[i])){
            _tokenStr = _tokenStr + parseToken[NULL] + delim;
            }
            else {
                _tokenStr = _tokenStr + token[i] + delim;
            }
        }
        System.out.print(_tokenStr);
        return _tokenStr;
    }
  //Rohit Didwania
     public Boolean hasHref(String[] tokenStr){
       if (tokenStr[HREF].matches(parseToken[NULL])){
       return false;
       }
       return true;
     }
    public Boolean hasId(String[] tokenStr){
       if (tokenStr[ID].matches(parseToken[NULL])){
       return false;
       }
       return true;
   }
   public Boolean hasName(String[] tokenStr){
       if (tokenStr[NAME].matches(parseToken[NULL])){
       return false;
       }
       return true;
   }
   //@author EM Trieu
    public Boolean hasInnerText(String[] tokenStr){
       if (tokenStr[INNERTEXT].matches("null")){
       return false;
       }
       return true;
   }
    //@author Chris Mitchell
    public Boolean hasAddress(String[] tokenStr){
       if (tokenStr[ADDRESS].matches("null")){
       return false;
       }
       return true;
       }
    //@ author James Ta & Khem
    public Boolean hasText(String[] tokenStr){
       if (tokenStr[TEXT].matches("null")){
       return false;
       }
       return true;
   }
    public Boolean hasValue(String[] tokenStr){
       if (tokenStr[VALUE].matches("null")){
       return false;
       }
       return true;
   }
    
    public Boolean hasimagSrc(String[] tokenStr){
       if (tokenStr[Src].matches(parseToken[NULL])){
       return false;
       }
       return true;
}

    
}
