import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class Programs {
    public static ExpVal run(List<Def> pgm, List<ExpVal> inputs) {
        System.out.println("[Programs] run \npgm: "+ pgm + " \ninputs: "+ inputs);

        Def definition  = pgm.get(0);
        System.out.println("definition: "+ definition);
        return null;
    }


    public static void main(String[] args) {
//        System.out.println("Asts.expVal(1): " + Asts.expVal(1));
//        ConstantExp constantExp = Asts.constantExp(Asts.expVal(1));
//        System.out.println("constantExp: " + constantExp);
//        Exp exp1 = Asts.arithmeticExp(Asts.identifierExp("n"), "MINUS", Asts.constantExp(Asts.expVal(1)));
//        System.out.println("exp1: " + exp1);
//
//        Exp call1 = Asts.callExp (Asts.identifierExp ("fact"), Asts.list (exp1));
//        System.out.println("call1: "+ call1);
//
//        Exp testPart = Asts.arithmeticExp (Asts.identifierExp ("n"),  "EQ", Asts.constantExp (Asts.expVal (0)));
//        Exp thenPart = Asts.constantExp (Asts.expVal (1));
//        Exp elsePart  = Asts.arithmeticExp (Asts.identifierExp ("n"),  "TIMES", call1);
//
//        Def def1  = Asts.def ("fact",
//                            Asts.lambdaExp (Asts.list ("n"),
//                                            Asts.ifExp (testPart, thenPart, elsePart)));
//        System.out.println("Def1: " + def1);
//
//        ExpVal result = Programs.run (Asts.list (def1), Asts.list (Asts.expVal (5)));


//        Def def2 = Asts.def("mult", twoTimesThree);
//        List<Def> defList = new ArrayList<Def>();
//        defList.add(def2);
//        List<ExpVal> inputs = new ArrayList<>();
//        inputs.add(new ExpValImpl(new Long(2)));
//        inputs.add(new ExpValImpl( new Long(3)));
//        ExpVal result = Programs.run(defList, inputs);
//
//        System.out.println("result: " + result.asInteger());

        Programs.testArithmeticOperations();
    }

    public static void testArithmeticOperations() {
        Exp two = Asts.constantExp(Asts.expVal(2));
        Exp three = Asts.constantExp(Asts.expVal(3));
        Exp twoTimesThree = Asts.arithmeticExp(two, "TIMES", three);
        Exp twoPlusThree = Asts.arithmeticExp(two, "PLUS", three);
        Exp twoMinusThree = Asts.arithmeticExp(two, "MINUS", three);
        Exp threeMinusTwo = Asts.arithmeticExp(three, "MINUS", two);
        Exp threeGreaterThanTwo = Asts.arithmeticExp(three, "GT", two);
        Exp twoLesserThanThree = Asts.arithmeticExp(two, "LT", three);
        Exp twoEqualsTwo = Asts.arithmeticExp(two, "EQ", two);
        Exp twoEqualsThree = Asts.arithmeticExp(two, "EQ", three);
        Map<String, ExpVal> DEFAULT_ENV = null;

        assert twoTimesThree.value(DEFAULT_ENV).asInteger() == 6 : " TIMES does not work.";
        assert twoPlusThree.value(DEFAULT_ENV).asInteger() == 5 : " PLUS does not work.";
        assert twoMinusThree.value(DEFAULT_ENV).asInteger() == -1 : " MINUS does not work.";
        assert threeMinusTwo.value(DEFAULT_ENV).asInteger() == 1 : " MINUS does not work.";
        assert threeGreaterThanTwo.value(DEFAULT_ENV).asBoolean() == true : " GT does not work.";
        assert twoLesserThanThree.value(DEFAULT_ENV).asBoolean() == true : " LT does not work.";
        assert twoEqualsTwo.value(DEFAULT_ENV).asBoolean() == true : " EQ does not work.";
        assert twoEqualsThree.value(DEFAULT_ENV).asBoolean() == false : " EQ does not work.";
    }
}
