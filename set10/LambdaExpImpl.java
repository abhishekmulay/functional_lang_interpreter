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
    	throw new UnsupportedOperationException();
    }

    @Override
    public IdentifierExp asIdentifier() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public LambdaExp asLambda() {
        return this;
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
    	//lambda does not depend on current environment, it depends on
    	//compile time environment calculated initially.
    	//So I think we dont support this method, but we may have to
    	//implement it for the compile time env update...
    	//TODO
    	throw new UnsupportedOperationException();
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
        return "LambdaExpImpl{" +
                "formals=" + formals +
                ", body=" + body +
                '}';
    }
}
