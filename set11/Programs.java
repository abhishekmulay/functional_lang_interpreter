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

    private static Set<String> freeVariables = new HashSet<>();
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
        System.out.println("[undefined] reading file: "+ filename);
        Set<String> undefinedVars = programAllDefined(filename);
        System.out.println("undefinedVars: "+ undefinedVars);
        return undefinedVars;
    }

    private static Set<String> programAllDefined(String filename) {
        String pgm = Scanner.readPgm(filename);
        List<Def> defs = Scanner.parsePgm(pgm);



        HashSet<String> empty = new HashSet<>();
        findFreeVariables(defs, empty);
        return lodAllDefined(defs, empty);
    }

    private static void findFreeVariables(List<Def> defs, HashSet<String> variables) {
        for (Def definition: defs) {
            variables.add(definition.lhs());
            expAllDefined(definition.rhs(), variables);
        }
    }

    private static Set<String> lodAllDefined(List<Def> defs, HashSet<String> variables) {
        if (defs.size() == 0) {
            return freeVariables;
        } else {
            // take first
            Def firstDef = defs.get(0);
            // make rest
            defs.remove(0);
            variables.add(firstDef.lhs());
            Set<String> strings = defAllDefined(firstDef, variables);
            Set<String> stringSet = lodAllDefined(defs, variables);
            stringSet.addAll(strings);
            return stringSet;
        }
    }

    private static Set<String> defAllDefined(Def singleDef, HashSet<String> variables) {
        List<String> formals = new ArrayList<>();
//        if (singleDef.rhs().isLambda()) {
//            formals = singleDef.asExp().asLambda().formals();
//            variables.addAll(formals);
//        }
        // variables.add(singleDef.rhs().asConstant().value(null));
        // is it already present in variables?
        return expAllDefined(singleDef.rhs(), variables);
    }

    private static Set<String> expAllDefined(Exp exp, HashSet<String> variables) {
        Set<String> encounteredVariables = new HashSet<>();

        if (exp.isLambda()) {
            List<String> formals = exp.asLambda().formals();
            variables.addAll(formals);

        } else if (exp.isIdentifier()) {
            variables.add(exp.asIdentifier().name());

        } else if (exp.isConstant()) {
            encounteredVariables.addAll(expAllDefined(exp.asConstant(), variables));

        } else if (exp.isCall()) {
            expAllDefined(exp.asCall().operator(), variables);

            for (Exp argument : exp.asCall().arguments()) {
                encounteredVariables.addAll(expAllDefined(argument, variables));
            }

        } else if (exp.isArithmetic()) {
            freeVariables.addAll(expAllDefined(exp.asArithmetic().leftOperand(), variables));

        } else if (exp.isIf()) {
            encounteredVariables.addAll(expAllDefined(exp.asIf().testPart(), variables));
            encounteredVariables.addAll(expAllDefined(exp.asIf().thenPart(), variables));
            encounteredVariables.addAll(expAllDefined(exp.asIf().elsePart(), variables));
        }

        // remove all elements that are valid
        variables.removeAll(encounteredVariables);

        // this now contains undefined variables.
        freeVariables.addAll(variables);
        return freeVariables;
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
                "/Users/abhishek/NEU/spring-17/PDP/pdp-abhishekmulay-nbreuer/set11/church.ps11";
//        Programs.undefined (CHURCH_PATH);

        final String BAD_PATH = "/Users/abhishek/NEU/spring-17/PDP/pdp-abhishekmulay-nbreuer/set11/bad.ps11";
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

