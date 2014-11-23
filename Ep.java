//package grammar;

//import grammar.*;
//import sexp.SE;

public class Ep{
    private String atom;
    private Xp xp;
    public SE node;

    public Ep(){
	xp = null;
    }
    public void parse(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	String curToken = new String(tokens.getToken());
	if(curToken.compareTo("(") == 0){
	    tokens.skipToken();
	    xp = new Xp();
	    xp.node = this.node;
	    xp.parse();
	}
	else if (curToken.compareTo(".") == 0){
	    System.out.print("ERROR: unexpected dot token at position ");
	    System.out.println(tokens.getTokenNum() + ".");
	    System.exit(0);
	}
	else if (curToken.compareTo(")") == 0){
	    System.out.print("ERROR: unexpected ')' token at position ");
	    System.out.println(tokens.getTokenNum() + ".");
	    System.exit(0);
	}
	else if (tokens.getTokenType() == tokens.eofN){
	    System.out.print("ERROR: Need more tokens at position ");
	    System.out.println(tokens.getTokenNum() + ".");
	    System.exit(0);
	}
	else {
	    this.node.isLeaf = true;
	    this.node.atom = new String(tokens.getToken());
	    if(tokens.getTokenType() == tokens.numN)
		this.node.isNum = true;
	    tokens.skipToken();
	}
    }

    String getCurToken(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	return tokens.getToken();
    }
}
