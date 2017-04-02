import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class ConstantExpImpl implements ConstantExp {

    private ExpVal value;

    ConstantExpImpl(ExpVal val) {
        this.value = val;
    }

    @Override
    public ExpVal value() {
        return this.value;
    }

    @Override
    public boolean isConstant() {
        return true;
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
        return false;
    }

    @Override
    public ConstantExp asConstant() {
        return this;
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
    	throw new UnsupportedOperationException();
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
        return this.value;
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
        return "ConstantExpImpl: {value=" + value + '}';
    }

}
