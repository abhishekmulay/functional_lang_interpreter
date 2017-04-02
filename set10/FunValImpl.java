import java.util.Map;

/**
 * Created by Abhishek Mulay on 4/1/17.
 */
public class FunValImpl implements FunVal {

    private LambdaExp exp;
    private Map<String, ExpVal> env;

    public FunValImpl(LambdaExp exp, Map<String, ExpVal> env) {
        this.exp = exp;
        this.env = env;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public boolean asBoolean() {
        return this.exp.value(this.env).asBoolean();
    }

    @Override
    public long asInteger() {
        return this.exp.value(this.env).asInteger();
    }

    @Override
    public FunVal asFunction() {
        return this;
    }

    @Override
    public LambdaExp code() {
        return this.exp;
    }

    @Override
    public Map<String, ExpVal> environment() {
        return this.env;
    }

}
