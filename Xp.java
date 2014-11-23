//package grammar;

//import grammar.*;
//import sexp.*;

public class Xp{
    private Ep ep;
    private Yp yp;
    public SE node;
    public Xp(){
	ep = null;
	yp = null;
    }
    public void parse(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	if (tokens.getToken().compareTo(")") == 0){
	    this.node.isLeaf = true;
	    this.node.atom = "NIL";
	    tokens.skipToken();
	}
	else{
	    this.node.se1 = new SE();
	    if(this.node.se1 == null) 
		System.out.println("se1: null");
	    ep = new Ep();
	    ep.node = this.node.se1;
	    node.se1.isLeft = true;
	    if(ep.node == null) System.out.println("ep: null");
	    ep.parse();
	    
	    this.node.se2 = new SE();
	    if(this.node.se2 == null) System.out.println("se2: null");
	    yp = new Yp();
	    yp.node = this.node.se2;
	    node.se2.isLeft = false;
	    if(yp.node == null) System.out.println("yp: null");
	    yp.parse();
	}
	
    }
    
    public String curToken(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	return tokens.getToken();	
    }

}
