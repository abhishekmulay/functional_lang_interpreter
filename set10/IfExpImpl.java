import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class IfExpImpl implements IfExp {
    private Exp testPart;
    private Exp thenPart;
    private Exp elsePart;

    public IfExpImpl(Exp testPart, Exp thenPart, Exp elsePart) {
        this.testPart = testPart;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    @Override
    public Exp testPart() {
        return this.testPart;
    }

    @Override
    public Exp thenPart() {
        return this.thenPart;
    }

    @Override
    public Exp elsePart() {
        return this.elsePart;
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
        return false;
    }

    @Override
    public boolean isCall() {
        return false;
    }

    @Override
    public boolean isIf() {
        return true;
    }

    @Override
    public ConstantExp asConstant() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public IdentifierExp asIdentifier() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public LambdaExp asLambda() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public ArithmeticExp asArithmetic() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public CallExp asCall() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public IfExp asIf() {
        return this;
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
        System.out.println("[IfExpImpl] value " + prettyPrintMap(env));
        if (testPart.value(env).asBoolean()) {
            return thenPart.value(env);
        } else {
            return elsePart.value(env);
        }
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
    	throw new UnsupportedOperationException();
    }

    @Override
    public Def asDef() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public Exp asExp() {
        return this;
    }

    @Override
    public String toString() {
        return "IfExpImpl{" +
                "testPart=" + testPart +
                ", thenPart=" + thenPart +
                ", elsePart=" + elsePart +
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
