import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class ArithmeticExpImpl implements ArithmeticExp {

    private Exp lhs;
    private String operator;
    private Exp rhs;

    public ArithmeticExpImpl(Exp lhs, String operator, Exp rhs) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public boolean isIdentifier() {
        return false;
    }

    @Override
    public boolean isLambda() {
        return false;
    }

    @Override
    public boolean isArithmetic() {
        return true;
    }

    @Override
    public boolean isCall() {
        return false;
    }

    @Override
    public boolean isIf() {
        return false;
    }

    @Override
    public ConstantExp asConstant() {
        return null;
    }

    @Override
    public IdentifierExp asIdentifier() {
        return null;
    }

    @Override
    public LambdaExp asLambda() {
        return null;
    }

    @Override
    public ArithmeticExp asArithmetic() {
        return this;
    }

    @Override
    public CallExp asCall() {
        return null;
    }

    @Override
    public IfExp asIf() {
        return null;
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
        System.out.println("[ArithmeticExpImpl] value " + prettyPrintMap(env));
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

        System.out.println("Something bad happened. " + this.toString());
        return null;
    }

    @Override
    public Exp leftOperand() {
        return this.lhs;
    }

    @Override
    public Exp rightOperand() {
        return this.rhs;
    }

    @Override
    public String operation() {
        return this.operator;
    }

    @Override
    public boolean isPgm() {
        return false;
    }

    @Override
    public boolean isDef() {
        return false;
    }

    @Override
    public boolean isExp() {
        return false;
    }

    @Override
    public List<Def> asPgm() {
        return null;
    }

    @Override
    public Def asDef() {
        return null;
    }

    @Override
    public Exp asExp() {
        return null;
    }

    @Override
    public String toString() {
        return "ArithmeticExpImpl: {" +
                "lhs=" + lhs +
                ", operator='" + operator + '\'' +
                ", rhs=" + rhs +
                '}';
    }
    private String prettyPrintMap(Map map) {
        if (map == null) {
            System.out.println(this.getClass().getSimpleName() + " Env is null ");
        }
        String str = "\n========ENV========== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key) + "\n";
        }
//        System.out.printf(str);
        return str;
    }
}
