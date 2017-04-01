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
        return null;
    }

    @Override
    public CallExp asCall() {
        return null;
    }

    @Override
    public IfExp asIf() {
        return this;
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
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
        return "IfExpImpl{" +
                "testPart=" + testPart +
                ", thenPart=" + thenPart +
                ", elsePart=" + elsePart +
                '}';
    }
}
