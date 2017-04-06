import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */

//////////////////////////////////////////////////////////////////////////
//                             DATA DEFINITION                         //
////////////////////////////////////////////////////////////////////////

//Constructor template for Programs:
//    new Programs()

//Interpretation:
//    This class is the entry point to start program execution.

public class Programs {

    // Given: pgm representing a number of definitions in a program, the first
    //        being the entryPoint, and inputs representing Expressions to be
    //        passed into the first definition's right hand side
    // Returns: the final ExpressionVal of the program evaluated in
    //          terms of the definitions
    // Strategy: Combine simpler functions
    public static ExpVal run(List<Def> pgm, List<ExpVal> inputs) {
        Map<String, ExpVal> env = new HashMap<>();

        Exp entryPoint = pgm.get(0).rhs();
        if (entryPoint.isConstant()) {
            return entryPoint.value(env);
        }

        if (entryPoint.isLambda()) {
            return handleLambda(pgm, entryPoint, env, inputs);
        }

        throw new RuntimeException("Invalid parameters passed to run. pgm:" + pgm + " inputs:" + inputs);
    }
    //-----------------------------------------------------------------------------------------------------------

    // Given: pgm representing a number of definitions in a program, first
    //        entryPoint definition which is a LambdaExp, runtime environment env, and inputs representing Expressions
    //        to be passed into the first definition's right hand side.
    // Returns: the final ExpressionVal of the program evaluated in
    //          terms of the definitions
    public static ExpVal handleLambda(List<Def> pgm, Exp entryPoint, Map<String, ExpVal> env, List<ExpVal> inputs) {

        for (int index=0; index < pgm.size(); index++){
            if(pgm.get(index).rhs().isLambda()) {
                FunVal funVal = Asts.expVal(pgm.get(index).rhs().asLambda(), env);
                env.put(pgm.get(index).lhs(), funVal);
            } else {
                env.put(pgm.get(index).lhs(), pgm.get(index).rhs().value(env));
            }
        }

        List<String> formals = entryPoint.asLambda().formals();
        for (int index=0; index < formals.size(); index++) {
            env.put(formals.get(index), inputs.get(index));
        }

        return entryPoint.asLambda().body().value(env);
    }

    //-----------------------------------------------------------------------------------------------------------
    //Runs all tests
    public static void main(String[] args) {
        Tests.testArithmeticOperations();
        Tests.testIfOperations();
        Tests.testProgram();
        Tests.testDef();
    }
    //-----------------------------------------------------------------------------------------------------------
}
