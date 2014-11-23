//package grammar;

//import grammar.*;
//import sexp.SE;

public class Sp{
    private Ep ep;
    public Sp(){
	ep = null;
    }
    public Sp(SE se){
	this();
	ep = new Ep();
	ep.node = se;
    }
    public void parse(){
	ep.parse();
    }
    public String curToken(){
	Tokenizer tokens;
	tokens = Tokenizer.getInstance();
	return tokens.getToken();
    }
}
