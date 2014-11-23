

class MyInt { 
    public static void main(String[] args) {
	Tokenizer tokenizer;
	tokenizer = Tokenizer.initialize();
	
	//System.out.println("TokenizerPrint");
	//tokenizer.printTokensName();
	//tokenizer.printTokensNum();
	//tokenizer.printIsNum();

	SE se;
	Sp sp;

	//System.out.println("------------------------");

	MyFunctions myFun = new MyFunctions();

	while(tokenizer.getTokenType() != tokenizer.eofN){
	    //System.out.println("-----------In loop----------");
	    se = new SE();
	    sp = new Sp(se);
	    //System.out.println("begin parse");
	    sp.parse();	    

	    //System.out.println("begin output");
	    //System.out.println("       ----se.output-----");
	    //se.output();
	    
	    //System.out.println("begin evaluate:");

	    myFun.myinterpreter(se, myFun.d).output();
	    //System.out.print("MyInt:main:myFun.d.output()=");
	    //myFun.d.output();

	    //System.out.println("after output");
	    se = null;
	    sp = null;
	}

	

    }
}

    
    
