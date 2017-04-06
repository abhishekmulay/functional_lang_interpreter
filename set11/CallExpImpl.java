import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */

//////////////////////////////////////////////////////////////////////////
//                             DATA DEFINITION                         //
////////////////////////////////////////////////////////////////////////

//Constructor template for CallExpImpl:
//    new CallExpImpl(Exp, List<Exp>)
//
//Interpretation:
//    This class represents a call expression that applies the given operator
//    on given list of parameters

public class CallExpImpl extends BaseExp implements CallExp {

    private Exp operator;
    private List<Exp> arguments;

    public CallExpImpl(Exp operator, List<Exp> arguments) {
        this.operator = operator;
        this.arguments = arguments;
    }

    //-----------------------------------------------------------------------------------------------------------
    // Returns: the Expression operator of this Call
    @Override
    public Exp operator() {
        return this.operator;
    }

    //-----------------------------------------------------------------------------------------------------------
    // Returns: the list of Expressions representing the arguments of this call
    @Override
    public List<Exp> arguments() {
        return this.arguments;
    }

    //-----------------------------------------------------------------------------------------------------------
    // Returns: true because this is a CallExp
    @Override
    public boolean isCall() {
        return true;
    }

    //-----------------------------------------------------------------------------------------------------------
    // Returns: this as this is a already a CallExp
    @Override
    public CallExp asCall() {
        return this;
    }

    //-----------------------------------------------------------------------------------------------------------
    // Given: env representing the current environment of this call
    // Returns: the result of this call as an ExpVal within the provided environment
    // Strategy: use simpler functions
    @Override
    public ExpVal value(Map<String, ExpVal> env) {
        if (env == null) {
            throw new UnsupportedOperationException();

        } else {

            // arguments of lambda function
            List<String> formals = this.operator.value(env).asFunction().code().formals();

            // create a new environment for this lambda expression so that scope is different
            Map<String, ExpVal> envCopy = new HashMap<>();
            envCopy.putAll(env);

            // map parameters of lambda functions to arguments received in this callExp
            for (int index = 0; index < formals.size(); index++) {
                String param = formals.get(index);
                Exp argument = this.arguments.get(index);
                envCopy.put(param, argument.value(envCopy));
            }

            // return value of this lambda expression.
            return this.operator.value(envCopy).asFunction().code().value(envCopy);
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    // Returns: the representation of this object as a string
    @Override
    public String toString() {
        return "CallExpImpl: {operator=" + operator + ", arguments=" + arguments + '}';
    }

    //-----------------------------------------------------------------------------------------------------------
}
