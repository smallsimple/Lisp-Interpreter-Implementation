//package sexp;

public class SE{
    public String atom;
    public SE se1, se2;
    public boolean isLeaf; // is this SE a leaf
    public boolean isLeft; // is this SE a left child
    public boolean isNum; // is this SE.atom a integer 

    public SE(){
	se1 = null;
	se2 = null;
	isLeaf = false;
	isLeft = false;
	isNum = false;
    }
    
    public SE(String str){
	this();
	isLeaf = true;
	atom = str;
	// ......
	// ......
    }

    public SE(SE se){
	this.atom = se.atom;
	if (se.se1 != null) this.se1 = new SE(se.se1);
	else this.se1 = null;
	    //this.se1 = se.se1;
	//this.se2 = se.se2;
	if (se.se2 != null) this.se2 = new SE(se.se2);
	else this.se2 = null;
	this.isLeaf = se.isLeaf;
	this.isLeft = se.isLeft;
	this.isNum = se.isNum;
    }
    /*
    public voide setRoot(){
	this.isRoot = true;
    }
    */
    public void print(){
	if (this.isLeaf){
	    System.out.print(this.atom);
	}
	else{
	    if(this.isList()){
		if(this.isLeft) System.out.print("(");
		se1.print();
		if(se2 == null){
		    System.out.println("ERROR: se2 is null");
		}
		//if(se2.atom == null) 
		//    System.out.println("ERROR: se2.atom is null");
		if(se2.isLeaf && se2.atom.compareTo("NIL") == 0){
		    
		}
		else{
		    System.out.print(" ");
		    se2.print();
		}
		if(this.isLeft) System.out.print(")");
	    }
	    else{
		System.out.print("(");
		se1.print();
		System.out.print(" . ");
		se2.print();
		System.out.print(")");
	    }
	}
    }

    public boolean isList(){
	if(this.isLeaf){
	    if(this.atom.compareTo("NIL") == 0)
		return true;
	    else 
		return false;
	    
	}
	else {
	    if (se2 != null){
		return se2.isList();
	    }
	    else{
		System.out.println("ERROR: se2 is not created!");
		System.exit(0);
		return false;
	    }
	}
	
    }
    
    public void output(){
	if(this.se1 == null || this.isList() == false){
	    //System.out.println("output1:");
	    this.print();
	    System.out.println();
	}
	else {
	    //System.out.println("output2:");
	    System.out.print("(");
	    this.print();
	    System.out.println(")");
	}
    
    }

    public boolean isLitList(){
	if(this.isLeaf){
	    if(this.atom.compareTo("NIL") == 0) return true;
	    else return false;
	}
	else{
	    if(this.se1.isLeaf){
		if(this.se1.isNum ||
		   this.se1.atom.compareTo("T") == 0||
		   this.se1.atom.compareTo("NIL") == 0){
		    return false;
		}
		else {
		    return this.se2.isLitList();
		}
	    }
	    else{
		return false;
	    }
	}
    }


}
