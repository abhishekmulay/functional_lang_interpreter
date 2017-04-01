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
        System.out.println("All arithmetic operations tests passed.");
    }
}
