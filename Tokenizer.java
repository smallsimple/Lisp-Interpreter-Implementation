import java.util.*;


class Token{ // record token's name and number
    int num;
    String name;
    Token(){
    }
    Token(int num0, String name0){
	num = num0;
	name = name0;
    }
}

class SpecMarker{
    //mark start position and id of special symbols in one String
    int start;
    int id;
    SpecMarker(){
    }
    SpecMarker(int start0, int id0){
	start = start0;
	id = id0;
    }
}

public class Tokenizer{
    private static boolean instanceFlag = false;
    private static Tokenizer tokenizer = null;
    private static String[] resWords = 
    {"T", "NIL", "CAR", "CDR", "CONS", "ATOM", "EQ",
     "NULL", "INT", "PLUS", "MINUS", "TIMES", 
     "QUOTIENT", "REMAINDER", "LESS", "GREATER", 
     "COND", "QUOTE", "DEFUN"};   //19 reserved words
    
    private static String[] specSymbols = {"(", ")"}; //2 special symbols
    private static String dot;
    private Vector<Token> tokens = new Vector<Token>();
    private static int curTokenNum = 0;
    public final static int dotN = 22;
    public final static int numN = 23;
    public final static int litN = 24;
    public final static int emptyN = 25;
    public final static int eofN = 26;

    private Tokenizer(){
	this.curTokenNum = 0;
	String sub;
	sub = new String();
	Scanner s = new Scanner(System.in);
	while(s.hasNext()){
	    sub = s.next();
	    this.dealToken(sub);
	}
	
	Token tok;
	tok = new Token();
	tok.name = "\0"; //use "\0" as a EOF character
	tok.num = eofN;
	this.tokens.addElement(tok);
    }
    private void dealToken(String str){
	//produce token from intput String
	int start = 0;
	Token tok;
	Vector<SpecMarker> sMarker1;
	sMarker1 = this.searchSpec(str);
	//System.out.println("dealToken:bobo0:str="+str);
	//System.out.println("dealToken:bobo1:sMarker1.size():"+sMarker1.size());
	for (int i = 0; i < sMarker1.size(); i++){
	    //deal with String before special symbol if any
	    if (start < sMarker1.get(i).start){
		
		int len = sMarker1.get(i).start - start;
		String temp = new String(str.substring(start, len + start));
		//System.out.println("dealToken:bobo5:temp:"+temp);
		if(!isLegalToken(temp)){
		    System.out.print("ERROR: \"");
		    System.out.print(temp);
		    System.out.print("\"");
		    System.out.println(" is not a legal token.");
		    System.exit(0);
		}
	    }
	    // deal with special symbol
	    tok = new Token();
	    tok.name = specSymbols[sMarker1.get(i).id];
	    //System.out.println("dealToken:bobo2:tok.name:"+tok.name);
	    tok.num = sMarker1.get(i).id + 20;
	    tokens.addElement(tok);
	    /*
	    for (int k = 0; k < tokens.size(); k++){
		System.out.print("dealToken1: tokens("+k+").name="+tokens.get(k).name);
		System.out.println("  .num="+tokens.get(i).num);
	    }
	    */
	    // move start to end of next special symbol
	    start = sMarker1.get(i).start + 
		specSymbols[sMarker1.get(i).id].length();
	    tok = null;
	}
	//System.out.println("dealToken:bobo6");
	// if there is token after the last special symbols
	if (start < str.length()){
	    String temp = str.substring(start, str.length());
	    //System.out.println("dealToken:bobo7:temp:"+temp);
	    if(!isLegalToken(temp)){
		System.out.print("ERROR: \"" + temp + "\"");
		System.out.println(" is not a legal token.");
		System.exit(0);
	    }
	    //System.out.println("dealToken:bobo9");
	}
	/*
	for (int i = 0; i < tokens.size(); i++){
	    System.out.print("dealToken: tokens("+i+").name="+tokens.get(i).name);
	    System.out.println("  .num="+tokens.get(i).num);
	}
	*/
    }
    int isRes(String str){
	for (int i = 0; i < resWords.length; i++){
	    if (str.equals(resWords[i])){
		return i + 1;
	    }
	}
	return 0;
    }
    boolean isNumAtom(String str){//is an integer
	//if first charactor is "-", it is not flase
	//System.out.println("str="+str);
	if (!(str.charAt(0) == '-' || str.charAt(0) == '+')
	    && !Character.isDigit(str.charAt(0)))
	    return false;
	//check if it is just a sign "-", "+"
	if ((str.charAt(0) == '-' || str.charAt(0) == '+')
	    && str.length() == 1)
	    return false;
	//check if rest of the chars are digits
	for (int i = 1; i < str.length(); i++){
	    if (!Character.isDigit(str.charAt(i)))
		return false;
	}
	return true;
    }
    boolean isLitAtom(String str){
	if(str.charAt(0) >= 'A' && str.charAt(0) <= 'z'){
	    for (int i = 0; i < str.length(); i++){
		if (str.charAt(i) == '.') return false;
	    }
	    return true;
	}
	else
	    return false;
    }

    boolean isDot(String str){
	if(str.compareTo(".") == 0)
	    return true;
	else
	    return false;
    }
    
    boolean isLegalToken(String temp){
	int flag = 0;
	Token tok;
	tok = new Token();
	//is temp a reserved word
	if (this.isRes(temp) > 0){
	    int ID = isRes(temp);
	    flag = 1;
	    tok.name = temp;
	    tok.num = ID;
	    tokens.addElement(tok);
	}
	//is temp a numberical atom
	//System.out.println("isLegalToken:bobo2:");
	if (flag == 0 && isNumAtom(temp)){
	    //System.out.println("isLegalToken:bobo3");
	    flag = 1;
	    if (temp.charAt(0) == '+')
		tok.name = temp.substring(1);
	    else 
		tok.name = temp;
	    //System.out.println("isLegalToken:bobo4");
	    tok.num = numN;
	    tokens.addElement(tok);	    
	}
	//System.out.println("isLegalToken:bobo6");
	//is temp a literal atom
	if (flag == 0 && isLitAtom(temp)){
	    flag = 1;
	    tok.name = temp.toUpperCase();
	    tok.num = litN;
	    tokens.addElement(tok);
	}
	//is temp a dot "."
	if (flag == 0 && isDot(temp)){
	    flag = 1;
	    tok.name = temp;
	    tok.num = dotN;
	    tokens.addElement(tok);
	    
	}
	//if temp is not one of reserved wor, special symbomls
	//  numeric atom and literal atom, return false
	if (flag == 0){
	    return false;
	}
	else 
	    return true;
    }
    private Vector<SpecMarker> searchSpec(String str){
	Vector<SpecMarker> sMarker;
	sMarker = new Vector<SpecMarker> ();
	int i = 0;
	do{
	    for (int j = 1; j >= 0; j--){
		if (this.specSymbols[j].compareTo
		    (str.substring(i,this.specSymbols[j].length()+i))
		    == 0){
		    SpecMarker temp;
		    temp = new SpecMarker();
		    temp.start = i;
		    temp.id = j;
		    sMarker.addElement(temp);
		    i += specSymbols[j].length() - 1;
		    break;
		}
						      
	    }
	    i++;
	    
	}while(i < str.length());
	return sMarker;
    }
    
    public static Tokenizer initialize(){
	if (!instanceFlag){
	    tokenizer = new Tokenizer();
	    instanceFlag = true;
	    return tokenizer;
	}
	else{
	    System.out.println("ERROR: Tokenizer has" +
			       " already been initialized!");
	    System.out.println("Program stopped!");
	    System.exit(0);
	    return null;
	}
    }
    public static Tokenizer getInstance(){
	if (!instanceFlag){
	    System.out.println("ERROR: Tokenizer has" +
			       " not been initialized!");
	    System.out.println("Program stopped!");
	    System.exit(0);
	    return null;
	}
	else {
	    return tokenizer;
	}
    }
    
    public void printTokensName(){
	for (int i = 0; i < this.tokens.size(); i++){
	    System.out.print(tokens.get(i).name + " ");
	}
	System.out.println();
    }
    
    public void printTokensNum(){
	for (int i = 0; i < this.tokens.size(); i++){
	    System.out.print(tokens.get(i).num + " ");
	}
	System.out.println();
    }
    public void printIsNum(){
	for (int i = 0; i < this.tokens.size(); i++){
	    if(isNumAtom(this.tokens.get(i).name) == true) System.out.print("T ");
	    else System.out.print("F ");
	}
	System.out.println();
    }
    public String getToken(){
	return this.tokens.get(curTokenNum).name;
    }
    public void skipToken(){
	if(curTokenNum < tokens.size()){
	    curTokenNum++;
	}
	else{
	    System.out.println("ERROR: End of file!" +
			       " There are no tokens!");
	    
	}
    }
    public int intVal(){//return the value of current integer
	if(this.tokens.get(curTokenNum).num == numN){
	    return Integer.parseInt(tokens.get(curTokenNum).name);
	}
	else {
	    System.out.println("ERROR: current token is not an integer");
	    System.out.println("Program stopped!");
	    System.exit(0);
	    return 0;
	}
    }
    
    public int getTokenNum(){
	return curTokenNum;
    }
    
    public int getTokenType(){
	return this.tokens.get(curTokenNum).num;
    }
    
    
}

