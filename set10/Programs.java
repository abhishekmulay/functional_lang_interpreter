import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class Programs {

    // Given: pgm representing a number of definitions in a program, the first
    //    being the entryPoint, and inputs representing Expressions to be
    //    passed into the first definition's right hand side
    // Returns: the final ExpressionVal of the program evaluated in
    //    terms of the definitions
    // Strategy: Combine simpler functions
    public static ExpVal run(List<Def> pgm, List<ExpVal> inputs) {
        Map<String, ExpVal> env = new HashMap<>();

        // program execution starts from here
        Def entryPoint = pgm.get(0);

        addInputsToEnvironment(env, entryPoint, inputs);
        addDefinitionsToEnvironment(env, pgm);

        return entryPoint.rhs().value(env);
    }

    //-----------------------------------------------------------------------------------------------------------
    // Given: env representing the environment before this method, entry point 
    //   representing the definition that will be run for results, and the inputs
    //   that will be passed into the entry point
    // Returns: the environment with the inputs mapped to the variable names
    //   requested by the entry point
    // Pattern: Functional visitor pattern
    public static void addInputsToEnvironment(Map<String, ExpVal> env,
                                              Def entryPoint, List<ExpVal> inputs) {
        if (entryPoint.rhs().isLambda()) {
            List<String> formals = entryPoint.rhs().asLambda().formals();

            // add all input variables to environment.
            for (int index = 0; index < inputs.size(); index++) {
                String key = formals.get(index);
                env.put(key, inputs.get(index));
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    // Given: env representing the environment before this method, and a list
    //   of definitions to be added to the environment
    // Returns: the environment with the definitions added
    // Strategy: use simpler functions
    public static void addDefinitionsToEnvironment(Map<String, ExpVal> env,
                                                   List<Def> defs) {

        for (int index = 0; index < defs.size(); index++) {
            Def definition = defs.get(index);

            // rhs expression is a lambda expression
            if (definition.rhs().isLambda()) {
                FunVal funVal = Asts.expVal(definition.rhs().asLambda(), env);
                env.put(definition.lhs(), funVal);
            } else if (definition.rhs().isConstant()) {
                env.put(definition.lhs(), definition.rhs().asConstant().value());
            } else {
                env.put(definition.lhs(), Asts.expVal(Asts.lambdaExp(null, definition.rhs()), env));
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    //Runs tests
    public static void main(String[] args) {
        Tests.testArithmeticOperations();
        Tests.testIfOperations();
        Tests.testProgram();
        Tests.testDef();
    }

    //-----------------------------------------------------------------------------------------------------------
}
