import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class ArithmeticExpImpl extends BaseExp implements ArithmeticExp {

    private Exp lhs;
    private String operator;
    private Exp rhs;

    public ArithmeticExpImpl(Exp lhs, String operator, Exp rhs) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    // Returns: True as this object is an ArithmeticExp.
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.isArithmetic()
    //   		=> true
    @Override
    public boolean isArithmetic() {
        return true;
    }


    // Returns: this, as it is already an ArithmeticExp
    //			for this object type
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.asArithmetic()
    //		 => 
    //  		(new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))   
    @Override
    public ArithmeticExp asArithmetic() {
        return this;
    }

    // Returns: the result of the operation on the lhs and rhs of this object
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.value(TODO)
    //			=> Asts.expVal(3)  
    @Override
    public ExpVal value(Map<String, ExpVal> env) {
//        System.out.println("[ArithmeticExpImpl] value " + prettyPrintMap(env));
        // multiplication
        if (operator.equals("TIMES")) {
            long result  = this.lhs.value(env).asInteger() * this.rhs.value(env).asInteger();
            return Asts.expVal(result);

        } else if (operator.equals("PLUS")) {
            long result  = this.lhs.value(env).asInteger() + this.rhs.value(env).asInteger();
            return Asts.expVal(result);

        } else if (operator.equals("MINUS")) {
            long result  = this.lhs.value(env).asInteger() - this.rhs.value(env).asInteger();
            return Asts.expVal(result);

        } else if (operator.equals("EQ")) {
            boolean result = this.lhs.value(env).asInteger() == this.rhs.value(env).asInteger();
            return Asts.expVal(result);

        } else if (operator.equals("GT")) {
            boolean result = this.lhs.value(env).asInteger() > this.rhs.value(env).asInteger();
            return Asts.expVal(result);

        } else if (operator.equals("LT")) {
            boolean result = this.lhs.value(env).asInteger() < this.rhs.value(env).asInteger();
            return Asts.expVal(result);
        }

        System.out.println("Something bad happened. " + this.toString()); //TODO:remove this before we turn it in
        return null;
    }

    // Returns: the lhs of this ArithmeticExp
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.leftOperand()
    //   		=> Asts.constantExp (Asts.expVal (1))
    @Override
    public Exp leftOperand() {
        return this.lhs;
    }

    // Returns: the rhs of this ArithmeticExp
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.rightOperand()
    //   		=> Asts.constantExp (Asts.expVal (2))
    @Override
    public Exp rightOperand() {
        return this.rhs;
    }

    // Returns: the operation of this ArithmeticExp
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.operation()
    //   		=> "PLUS"
    @Override
    public String operation() {
        return this.operator;
    }

    // Returns: a string representation of this object
    // Example: (new Asts.arithmeticExp(Asts.constantExp (Asts.expVal (1)),
    //								    "PLUS",
    //									Asts.constantExp (Asts.expVal (2))))
    //			.toString()
    //			=>  "ArithmeticExpImpl: {lhs=1, operator='PLUS', rhs=2}"		
    @Override
    public String toString() {
        return "ArithmeticExpImpl: {" +
                "lhs=" + lhs +
                ", operator='" + operator + '\'' +
                ", rhs=" + rhs +
                '}';
    }
    private String prettyPrintMap(Map map) {
    	String str = "\n========ENV========== \t\t\n";
        if (map == null) {
            System.out.println(this.getClass().getSimpleName() + " Env is null ");
        }
        else{
	        for (Object key : map.keySet()) {
	            str += key + " : " + map.get(key) + "\n";
	        }
        }
//        System.out.printf(str);
        return str;
    }
}
