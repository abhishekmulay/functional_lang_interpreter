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

    // Returns: true if this function returns a Boolean in its
    //    environment
    @Override
    public boolean isBoolean() {
    	return this.exp.value(this.env).isBoolean();
    }

    // Returns: true if this function returns an Integer in its
    //    environment
    @Override
    public boolean isInteger() {
    	return this.exp.value(this.env).isInteger();
    }

    // Returns: true if this function returns an Integer in its
    //    environment
    @Override
    public boolean isFunction() {
        return this.exp.value(this.env).isFunction();
    }

    // Returns: the value of this function in its environment 
    //    cast to a Boolean if the value is a Boolean
    @Override
    public boolean asBoolean() {
        return this.exp.value(this.env).asBoolean();
    }

    // Returns: the value of this function in its environment 
    //    cast to an Integer if the value is an Integer
    @Override
    public long asInteger() {
        return this.exp.value(this.env).asInteger();
    }

    // Returns: the value of this function in its environment 
    //    cast to a Function if the value is a Function
    @Override
    public FunVal asFunction() {
    	return this.exp.value(this.env).asFunction();
    }

    // Returns: the LambdaExp of this FunVal
    @Override
    public LambdaExp code() {
        return this.exp;
    }

    // Returns: the environment of this FunVal
    @Override
    public Map<String, ExpVal> environment() {
        return this.env;
    }

}
