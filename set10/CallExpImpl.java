import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class CallExpImpl implements CallExp {

    private Exp operator;
    private List<Exp> arguments;

    public CallExpImpl(Exp operator, List<Exp> arguments) {
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Exp operator() {
        return this.operator;
    }

    @Override
    public List<Exp> arguments() {
        return this.arguments;
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
        return true;
    }

    @Override
    public boolean isIf() {
        return false;
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
        return this;
    }

    @Override
    public IfExp asIf() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
    	//TODO
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
        return true;
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
        return "CallExpImpl: {" +
                "operator=" + operator +
                ", arguments=" + arguments +
                '}';
    }
}
