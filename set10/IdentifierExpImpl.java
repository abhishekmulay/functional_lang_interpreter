import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class IdentifierExpImpl implements IdentifierExp {
    private String identifier;

    public IdentifierExpImpl(String id) {
        this.identifier = id;
    }

    @Override
    public String name() {
        return this.identifier;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public boolean isIdentifier() {
        return true;
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
        return this;
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
        if (env.containsKey(this.identifier)) {
            return env.get(this.identifier);
        } else{
            System.out.println("Identifier not found in environment. "+ this.identifier + " environment: "+ env);
            throw new IllegalStateException();
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
        return "IdentifierExpImpl{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}
