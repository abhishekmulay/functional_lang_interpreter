import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class LambdaExpImpl implements LambdaExp {

    private List<String> formals;
    private Exp body;

    public LambdaExpImpl(List<String> formals, Exp body) {
        this.formals = formals;
        this.body = body;
    }

    @Override
    public List<String> formals() {
        return this.formals;
    }

    @Override
    public Exp body() {
        return this.body;
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
        return true;
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
        return null;
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
        return null;
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
        return "LambdaExpImpl{" +
                "formals=" + formals +
                ", body=" + body +
                '}';
    }
}