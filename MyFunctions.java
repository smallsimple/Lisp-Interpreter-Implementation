public class MyFunctions{
    public static SE d = new SE("NIL");

    public MyFunctions(){
	
    }

    public SE cons(SE se1, SE se2){
	//System.out.print("Bobo:cons:");
	SE se = new SE();
	SE se1N = new SE(se1);
	SE se2N = new SE(se2);
	se1N.isLeft = true;
	se2N.isLeft = false;
	//System.out.print("bobo:cons:se1:");
	//se1N.output();
	//System.out.print("bobo:con2.se2:");
	//se2N.output();
	se.se1 = se1N;
	se.se2 = se2N;
	//se.output();
	return se;
    }
    
    public SE car(SE se){
	//System.out.print("Bo:car:se=");
	//se.output();
	if (se.se1 != null){
	    //System.out.print("bobo:car:se=");
	    //se.output();
	    //System.out.print("bobo:car:se.se1=");
	    //se.se1.output();
	    SE seN = new SE(se.se1);
	    seN.isLeft = false;
	    return seN;
	}
	else {
	    System.out.println("ERROR: invalid argument to car");
	    System.exit(0);
	    return se.se1;
	}
    }
    /*
    public SE copy(){
	return this.clone();
    }
    */
    public SE cdr(SE se){
	//System.out.print("B:cdr:se=");
	//se.output();
	if(se.se2 != null){
	    return se.se2;
	}
	else {
	    System.out.println("ERROR: invalid argument to CDR");
	    System.exit(0);
	    return null;
	}
    }
    
    public SE atom(SE se){
	//System.out.println("B:atom");
	SE temp = new SE();
	if (se.isLeaf){
	    temp.atom = new String("T");
	    temp.isLeaf = true;
	    return temp;
	}
	else{
	    temp.atom = new String("NIL");
	    temp.isLeaf = true;
	    return temp;	    
	}
    }
    public SE eq(SE se1, SE se2){
	//System.out.println("B:eq:");
	if ( se1.isLeaf && se2.isLeaf){
	    if(se1.atom.compareTo(se2.atom) == 0){
		SE temp = new SE("T");
		return temp;
	    }
	    else {
		SE temp = new SE("NIL");
		return temp;
	    }
	}
	else {
	    System.out.println("ERROR: one of the input s-exp" + 
			       " is not an atom to EQ.");
	    System.exit(0);
	    return null;
	}
    }
    public SE null_my(SE se){
	//System.out.print("B:null_my:se=");
	//se.output();
	if (!se.isLeaf){
	    //System.out.println("Bobo:null_my:!isLeaf");
	    SE temp = new SE("NIL");
	    return temp;
	}
	
	else {
	    if (se.atom == null){
		System.out.println("INTERNAL ERROR: null");
		System.exit(0);
		return null;
	    }
	    else if (se.atom.compareTo("NIL") == 0){
		SE temp = new SE("T");
		return temp;
	    }
	    else {
		SE temp = new SE("NIL");
		return temp;
	    }
	}
    }
    public SE int_my(SE se){
	if (se.isNum){
	    SE temp = new SE("T");
	    return temp;
	}
	else{
	    SE temp = new SE("NIL");
	    return temp;
	}
    }
    public SE plus(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer to PLUS");
	    System.exit(0);
	    return null;
	}
	else{
	    int sum;
	    sum = Integer.parseInt(se1.atom) + Integer.parseInt(se2.atom);
	    SE temp = new SE(Integer.toString(sum));
	    temp.isNum = true;
	    return temp;
	}
    }
    public SE minus(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer to MINUS");
	    System.exit(0);
	    return null;
	}
	else{
	    int diff;
	    diff = Integer.parseInt(se1.atom) - Integer.parseInt(se2.atom);
	    SE temp = new SE(Integer.toString(diff));
	    temp.isNum = true;
	    return temp;
	}
    }
    public SE times(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer to TIMES");
	    System.exit(0);
	    return null;
	}
	else{
	    int product;
	    product = Integer.parseInt(se1.atom) * Integer.parseInt(se2.atom);
	    SE temp = new SE(Integer.toString(product));
	    temp.isNum = true;
	    return temp;
	}
    }
    public SE quotient(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer QUOTIENT");
	    System.exit(0);
	    return null;
	}
	else{
	    int quo;
	    quo = Integer.parseInt(se1.atom) / Integer.parseInt(se2.atom);
	    SE temp = new SE(Integer.toString(quo));
	    temp.isNum = true;
	    return temp;
	}
    }
    public SE remainder(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer REMAINDER");
	    System.exit(0);
	    return null;
	}
	else{
	    int rem;
	    rem = Integer.parseInt(se1.atom) % Integer.parseInt(se2.atom);
	    SE temp = new SE(Integer.toString(rem));
	    temp.isNum = true;
	    return temp;
	}
    }
    public SE less(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer LESS");
	    System.exit(0);
	    return null;
	}
	else{
	    if(Integer.parseInt(se1.atom) < Integer.parseInt(se2.atom)){
		SE temp = new SE("T");
		return temp;
	    }
	    else {
		SE temp = new SE("NIL");
		return temp;
	    }
	}
	
    }
    public SE greater(SE se1, SE se2){
	if (!se1.isNum || !se2.isNum){
	    System.out.println("ERROR: one of the input is not an integer GREATER");
	    System.exit(0);
	    return null;
	}
	else{
	    if(Integer.parseInt(se1.atom) > Integer.parseInt(se2.atom)){
		SE temp = new SE("T");
		return temp;
	    }
	    else {
		SE temp = new SE("NIL");
		return temp;
	    }
	}
    }
    public SE myinterpreter (SE exp, SE d){
	SE temp = new SE("NIL");
	return eval(exp, temp, d);
    }
    public SE eval(SE exp, SE a, SE d){
	/*
	  System.out.print("B:eval:exp=");
	  exp.output();
	  System.out.print("Bobo:eval:a=");
	  a.output();
	  System.out.print("bobo:eval:d=");
	  d.output();
	*/
	if (exp.isLeaf){
	    //System.out.println("B:eval:isLeaf");
	    if (exp.atom.compareTo("T") == 0){
		SE temp = new SE("T");
		return temp;
	    }
	    else if(exp.atom.compareTo("NIL") == 0){
		SE temp = new SE("NIL");
		return temp;
	    }
	    else if(exp.isNum){
		return exp;
	    }
	    else if(bound(exp, a)){
		return getval(exp, a);
	    }
	    else{
		System.out.println("ERROR: unbound variable: "
				   + exp.atom);
		System.exit(0);
		return null;
	    }	    
	}
	else {
	    String temp;
	    //System.out.println("B:eval:!isLeaf:");
	    if (car(exp).isLeaf){
		temp = new String(car(exp).atom);
	    //else {
	    //System.out.println("INTERNAL ERROR: eval2");
		//System.exit(0);
	    //temp = new String(car(exp).atom);
	    //}
	    //System.out.println("B:eval:compareQUOTE");
		if (temp.compareTo("QUOTE") == 0){
		    //System.out.print("Bobo:eval:QUOTE:");
		    //car(cdr(exp)).output();
		    //error check
		    //cdr(exp) should be a list with a single element
		    if(exp.se2.isLeaf){
			if(exp.se2.atom.compareTo("NIL") != 0){
			    System.out.println("ERROR: quote argument incorrect");
			    System.exit(0);
			}
		    }
		    else if( !(exp.se2.se2.isLeaf == true &&  exp.se2.se2.atom.compareTo("NIL") == 0)){
			System.out.println("ERROR: quote argument incorrect");
			System.exit(0);
		    }
		    
		    return car(cdr(exp));
		}
		else if (temp.compareTo("COND") == 0) return evcon(cdr(exp), a, d);
		else if (temp.compareTo("DEFUN") == 0){ 
		    //System.out.println("Bobo:eval:DEFUN");
		    //error check
		    //cdr(exp) should be a list with exactly three elements
		    // a Literal atom (function name)
		    if (cdr(exp).se1.isLeaf == false 
			|| cdr(exp).se1.isNum == true){
			System.out.println("ERROR: function name is incorret");
			System.exit(0);
		    }
		    else if(cdr(exp).se1.atom.compareTo("T") == 0 ||
			    cdr(exp).se1.atom.compareTo("NIL") == 0){
			System.out.println("ERROR: function name is incorret");
			System.exit(0);
		    }
		    
		    // a List of distinct literal atoms (params)
		    SE params = car(cdr(cdr(exp)));
		    if (!params.isLitList()){
			System.out.println("ERROR: function params are not legal parameter literal atom list");
			System.exit(0);
		    }
		    else if (!isUnique(params)){
			System.out.println("ERROR: function params have duplicates");
			System.exit(0);
		    }
		    
		    SE varList = new SE();
		    varList.se1 = car(cdr(exp));
		    varList.se2 = new SE("NIL");
		    SE t1 = new SE(cdr(cdr(exp)));
		    SE valueList = new SE();
		    
		    t1.se2 = t1.se2.se1;
		    t1.se2.isLeft = false;
		    
		    valueList.se1 = t1;
		    valueList.se1.isLeft = true;
		    valueList.se2 = new SE("NIL");
		    //System.out.print("bobo:eval:beforeaddpairs:varList=");
		    //varList.output();
		    //System.out.print("valueList=");
		    //valueList.output();
		    //t1.output();
		    this.d = addpairs(varList, valueList, d);		
		    //System.out.print("Bobo:eval:afterAssign:myFuns.d=");
		    //this.d.output();
		    //System.out.println("after:eval:afteraddpairs");
		    SE temSe = new SE(car(cdr(exp)).atom);
		    return temSe;
		}
		else
		    return apply(car(exp), evlist(cdr(exp), a, d), a, d);
	    }
	    else{
		return apply(car(exp), evlist(cdr(exp), a, d), a, d);
	    }
	}
    }
    
    private boolean mem (SE x, SE list){
	if (null_my(list).atom.compareTo("T") == 0){
	    return false;
	}
	else if (eq(x, car(list)).atom.compareTo("T") == 0){
	    return true;
	}
	else{
	    return mem(x, cdr(list));
	}
		
    }

    private boolean isUnique(SE list){
	// assume list is Literal List; isLitList() = true
	if(null_my(list).atom.compareTo("T") == 0){
	    return true;
	}
	else {
	    if (mem(car(list), cdr(list))){
		return false;
	    }
	    else {
		return isUnique (cdr(list));
	    }
	}
    }


    private SE apply(SE f, SE x, SE a, SE d){
	/*
	System.out.print("B:apply:f=");
	f.output();
	System.out.print("B:apply:x=");
	x.output();
	System.out.print("B:apply:a=");
	a.output();
	System.out.print("B:apply:d=");
	d.output();
	*/
	if (f.isLeaf){
	    String str = new String(f.atom);
	    switch(f.atom){
	    case "CAR":
		return car(car(x));
	    case "CDR":
		return cdr(car(x));
	    case "CONS":
		return cons(car(x), car(cdr(x)));
	    case "ATOM":
		return atom(car(x));
	    case "EQ":
		return eq(car(x), car(cdr(x)));
	    case "NULL":
		return null_my(car(x));
	    case "INT":
		return int_my(car(x));
	    case "PLUS":
		return plus(car(x), car(cdr(x)));
	    case "MINUS":
		return minus(car(x), car(cdr(x)));
	    case "TIMES":
		return times(car(x), car(cdr(x)));
	    case "QUOTIENT":
		return quotient(car(x), car(cdr(x)));
	    case "REMAINDER":
		return remainder(car(x), car(cdr(x)));
	    case "LESS":
		return less(car(x), car(cdr(x)));
	    case "GREATER":
		return greater(car(x), car(cdr(x)));
	    default:
		//System.out.print("apply:default:d=");
		//d.output();
		return eval(cdr(getval(f, d)),
			    addpairs(car(getval(f, d)), x, a), d);
	    }
	}
	else {
	    System.out.println("ERROR: function name is not an atom");
	    System.exit(0);
	    return null;
	}
    }
    private SE evcon(SE x, SE a, SE d){/*
	System.out.print("B:evcon:x=");
	x.output();
	System.out.print("B:evcon:a=");
	a.output();
	System.out.print("B:evcon:d=");
	d.output();
				       */
	if (null_my(x).atom.compareTo("T") == 0){
	    System.out.println("ERROR: no argument found");
	    System.exit(0);
	    return null;
	}
	else if (eval(car(car(x)), a, d).atom.compareTo("T") == 0){
	    return eval(car(cdr(car(x))), a, d);
	}
	else {
	    return evcon(cdr(x), a, d);
	}
    }

    private SE evlist(SE x, SE a, SE d){
	/*
	System.out.print("B:evlist:x=");
	x.output();
	System.out.print("B:evlist:a=");
	a.output();
	System.out.print("B:evlist:d=");
	d.output();
	*/
	if(null_my(x).atom.compareTo("T") == 0){
	    SE temp = new SE("NIL");
	    return temp;
	}
	else {
	    return cons(eval(car(x), a, d), evlist(cdr(x), a, d));
	}
    }

    private boolean bound(SE exp, SE a){
	/*
	System.out.print("bobo:bound:exp=");
	exp.output();
	System.out.print("bobo:bound:a=");
	a.output();
	*/
	if (null_my(a).atom.compareTo("T") == 0){
	    //System.out.println("bobo:bound:null");
	    return false;
	}
	else if (car(car(a)).atom.compareTo(exp.atom) == 0){
	    return true;
	}
	else{
	    //System.out.println("bobo:bound:loop");
	    return bound(exp, cdr(a));
	}
    }
    private SE getval(SE exp, SE a){
	/*
	System.out.print("bobo:getval:exp=");
	exp.output();
	System.out.print("bobo:getval:a=");
	a.output();
	*/
	if (!bound(exp, a)){
	    System.out.println("ERROR: " + exp.atom +
			       " is not a function name");
	    System.exit(0);
	    return null;
	}
	if (car(car(a)).atom.compareTo(exp.atom) == 0){
	    return cdr(car(a));
	}
	else{
	    return getval(exp, cdr(a));
	}
    }
    private SE addpairs(SE varList, SE valueList, SE oldList){
	/*
	System.out.print("bobo:addpairs:varList=");
	varList.output();
	System.out.print("bobo:addpairs:valueList=");
	valueList.output();
	*/
	if(varList.isLeaf && varList.atom.compareTo("NIL") == 0)
	    return oldList;
	else{
	    return cons(cons(car(varList), car(valueList)), 
			addpairs(cdr(varList), cdr(valueList), oldList));
	}
    }


}
