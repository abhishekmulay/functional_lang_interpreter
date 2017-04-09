import java.util.*;

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


// program-all-defined:     Program->                       Boolean
// lod-all-defined?:        ListOfDefinition SetOfVariable->Boolean
// def-all-defined?:        Definition SetOfVariable->      Boolean
// exp-all-defined?:        Exp SetOfVariable->             Boolean

// (define-struct def (name args body))       definition
// (define-struct varexp (name))              identifier
// (define-struct appexp (fn args))           lambda


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

        for (int index = 0; index < pgm.size(); index++) {
            if (pgm.get(index).rhs().isLambda()) {
                FunVal funVal = Asts.expVal(pgm.get(index).rhs().asLambda(), env);
                env.put(pgm.get(index).lhs(), funVal);
            } else {
                env.put(pgm.get(index).lhs(), pgm.get(index).rhs().value(env));
            }
        }

        List<String> formals = entryPoint.asLambda().formals();
        for (int index = 0; index < formals.size(); index++) {
            env.put(formals.get(index), inputs.get(index));
        }

        return entryPoint.asLambda().body().value(env);
    }


    //////////////////////////////////////////////////////////////////////////
    //                             Added for Set11                         //
    ////////////////////////////////////////////////////////////////////////

    // Reads the ps11 program found in the file named by the given string
    // and returns the set of all variable names that occur free within
    // the program.
    //
    // Examples:
    //     Programs.undefined ("church.ps11")    // returns an empty set
    //     Programs.undefined ("bad.ps11")       // returns { "x", "z" }
    //
    //   where bad.ps11 is a file containing:
    //
    //     f (x, y) g (x, y) (y, z);
    //     g (z, y) if 3 > 4 then x else f

    public static Set<String> undefined(String filename) {
        //System.out.println("[undefined] reading file: "+ filename);
        Set<String> undefinedVars = programAllUndefined(filename);
        System.out.println("undefinedVars: "+ undefinedVars);
        return undefinedVars;
    }

    private static Set<String> programAllUndefined(String filename) {
        String pgm = Scanner.readPgm(filename);
        List<Def> defs = Scanner.parsePgm(pgm);
        HashSet<String> globalVariables = new HashSet<>();
        HashSet<String> undefinedVariables = new HashSet<>();
        
        //build global variable definitions
        for (Def definition: defs) {
            globalVariables.add(definition.lhs());
        }
        
        //evaluate individual definition variables
        for (Def definition: defs) {
            undefinedVariables.addAll(
            		expAllUndefined(definition.rhs(), globalVariables, 0));
        }
        
        return undefinedVariables;
    }

    private static Set<String> expAllUndefined(Exp exp, Set<String> variables, int level) {
    	Set<String> undefinedVariables = new HashSet<>();
    	//definedVariables are copied as we are in a new scope and need to be immutable
    	Set<String> definedVariables = new HashSet<>(variables);
        Set<String> encounteredVariables = new HashSet<>();
        
        if (exp.isLambda()) {
            List<String> formals = exp.asLambda().formals();
            //lambda only adds to env
            definedVariables.addAll(formals);
            //recurse on the body of the lambda
            undefinedVariables.addAll(expAllUndefined(exp.asLambda().body(), definedVariables, level + 1));

        } else if (exp.isIdentifier()) {
        	//identifier represents an encountered variable
        	encounteredVariables.add(exp.asIdentifier().name());
        } else if (exp.isConstant()) {
        	//env has no effect on Constants
        } else if (exp.isCall()) {
        	//recurse on the operator
            undefinedVariables.addAll(expAllUndefined(exp.asCall().operator(), definedVariables, level + 1));

            //recurse on each argument
            for (Exp argument : exp.asCall().arguments()) {
            	undefinedVariables.addAll(expAllUndefined(argument, definedVariables, level + 1));
            }

        } else if (exp.isArithmetic()) {
        	//recurse on left and right side of exp
            undefinedVariables.addAll(expAllUndefined(exp.asArithmetic().leftOperand(), definedVariables, level +1));
            undefinedVariables.addAll(expAllUndefined(exp.asArithmetic().rightOperand(), definedVariables, level +1));

        } else if (exp.isIf()) {
        	//recurse on test, then, and else of exp
        	undefinedVariables.addAll(expAllUndefined(exp.asIf().testPart(), definedVariables, level + 1));
            undefinedVariables.addAll(expAllUndefined(exp.asIf().thenPart(), definedVariables, level + 1));
            undefinedVariables.addAll(expAllUndefined(exp.asIf().elsePart(), definedVariables, level + 1));
        }

        System.out.print("defined: " + definedVariables);
        System.out.println("used: " + encounteredVariables);
        
        // remove all elements that are valid
        encounteredVariables.removeAll(definedVariables);
        
        undefinedVariables.addAll(encounteredVariables);
        return undefinedVariables;
    }


    //-----------------------------------------------------------------------------------------------------------
    //Runs all tests
    // Runs the ps11 program found in the file named on the command line
    // on the integer inputs that follow its name on the command line,
    // printing the result computed by the program.
    //
    // Example:
    //
    //     % java Programs sieve.ps11 2 100
    //     25
    public static void main(String[] args) {

        final String CHURCH_PATH =
                "church.ps11";
        Programs.undefined (CHURCH_PATH);

        final String BAD_PATH = "bad.ps11";
        Programs.undefined(BAD_PATH);
//        if (args.length >= 2) {
//            String filename = args[0];
//            String pgm = Scanner.readPgm(filename);
//            List<ExpVal> inputs = new ArrayList<ExpVal>();
//            for (int i = 1; i < args.length; i = i + 1) {
//                long input = Long.parseLong(args[i]);
//                inputs.add(Asts.expVal(input));
//            }
//            ExpVal result = Scanner.runPgm(pgm, inputs);
//
//            Object val = null;
//            if (result.isBoolean()) {
//                val = result.asBoolean();
//            } else if (result.isInteger()) {
//                val = result.asInteger();
//
//            } else if (result.isFunction()) {
//                val = result.asFunction();
//            }
//            System.out.println(val);
//
//        } else {
//            System.out.println(usageMsg);
//        }
    }


    public static final String usageMsg = "Usage: java Programs <filename> <input> ...";
    //-----------------------------------------------------------------------------------------------------------

}

