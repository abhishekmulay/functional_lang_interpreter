import java.util.HashMap;
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
        System.out.println("[CallExpImpl] value" + prettyPrintMap(env));
        if (env == null) {
            System.out.println("[CallExpImpl] .value() Environment is null." + this.toString());
            return null;
        } else{

            // arguments of lambda function
            List<String> formals = this.operator.value(env).asFunction().code().formals();

            Map<String, ExpVal> envCopy = new HashMap<>();
            envCopy.putAll(env);

            // map parameters of lambda functions to arguments received in this callExp
            for (int index=0; index< formals.size(); index++) {
                String param = formals.get(index);
                Exp argument = this.arguments.get(index);
                envCopy.put(param, argument.value(envCopy));
            }

            // return value of this lambda expression.
            return this.operator.value(envCopy).asFunction().code().value(envCopy);
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
        return "CallExpImpl: {operator=" + operator +  ", arguments=" + arguments + '}';
    }


    public static void main(String[] args) {

        ArithmeticExp nTimesTen = Asts.arithmeticExp(Asts.identifierExp("n"), "TIMES", Asts.constantExp(Asts.expVal(10)));
        System.out.println("result: "+ nTimesTen);

        Map<String, ExpVal> testEnv = new HashMap<>();
        testEnv.put("n", new ExpValImpl( new Long(10)));

        System.out.println("testEnv" + testEnv);

        System.out.println("result value : " +  nTimesTen.value(testEnv).asInteger());

    }

    private String prettyPrintMap(Map map) {
        if (map == null) {
            System.out.println(this.getClass().getSimpleName() + " Env is null ");
        }
        String str = "\n========ENV========== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key) + "\n";
        }
//        System.out.printf(str);
        return str;
    }
}
