import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 4/2/17.
 */
abstract public class BaseExp implements Exp {

    @Override
    abstract public ExpVal value(Map<String, ExpVal> env);

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
        throw new UnsupportedOperationException();
    }

    @Override
    public IfExp asIf() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPgm() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDef() {
        throw new UnsupportedOperationException();
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
}
