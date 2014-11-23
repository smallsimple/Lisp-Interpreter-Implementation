//package grammar;

//import grammar.*;
//import sexp.*;

public class Rp{
    private Ep ep;
    private Rp rp;
    public SE node;
    public Rp(){
	ep = null;
	rp = null;
    }
    public void parse(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	if(tokens.getToken().compareTo(")") == 0){
	    this.node.atom = new String("NIL");
	    this.node.isLeaf = true;
	}
	else{
	    this.node.se1 = new SE();
	    ep = new Ep();
	    ep.node = this.node.se1;
	    ep.node.isLeft = true;
	    ep.parse();
	    node.se2 = new SE();
	    rp = new Rp();
	    rp.node = node.se2;
	    rp.node.isLeft = false;
	    rp.parse();
	}
    }
    public String curToken(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	return tokens.getToken();
    }
}
