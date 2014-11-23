//package grammar;

//import grammar.*;
//import sexp.*;

public class Yp{
    private Ep ep;
    private Rp rp;
    public SE node;
    public Yp(){
	ep = null;
	rp = null;
    }
    public void parse(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	if(tokens.getToken().compareTo(".") == 0){
	    ep = new Ep();
	    ep.node = this.node;
	    tokens.skipToken();
	    ep.parse();
	    if(tokens.getToken().compareTo(")") == 0){
		tokens.skipToken();
	    }
	    else{
		System.out.println("ERROR: ')' is expected at position"
				   + tokens.getTokenNum() + ".");
		System.exit(0);
	    }
		
	}
	else{
	    rp = new Rp();
	    rp.node = this.node;
	    rp.parse();
	    if(tokens.getToken().compareTo(")") == 0){
		tokens.skipToken();
	    }
	    else{
		System.out.println("ERROR: ')' is expected at position"
				   + tokens.getTokenNum() + ".");
		System.exit(0);
	    }
	}
    }
    public String curToken(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	return tokens.getToken();
    }
}
