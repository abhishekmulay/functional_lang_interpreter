import java.util.HashMap;
import java.util.Map;

public class PrrogramsTests {
	
	//IfOperations tests
	public static void testIfOperations() {
        Exp two = Asts.constantExp(Asts.expVal(2));
        Exp three = Asts.constantExp(Asts.expVal(3));
        Exp testPart1 = Asts.arithmeticExp (two, "EQ", three);
        Exp thenPart1 = Asts.constantExp (Asts.expVal (false));
        Exp elsePart1 = Asts.constantExp (Asts.expVal (true));
        IfExp result1 = Asts.ifExp(testPart1, thenPart1, elsePart1);
        assert result1.isIf() == true : "result should be an IF expression.";
        assert result1.thenPart().value(null).asBoolean() == false : " then part should be false, because 2 != 3";

        Exp testPart2 = Asts.arithmeticExp (two, "LT", three);
        Exp thenPart2 = Asts.constantExp (Asts.expVal (true));
        Exp elsePart2 = Asts.constantExp (Asts.expVal (false));
        IfExp result2 = Asts.ifExp(testPart2, thenPart2, elsePart2);

        assert result2.isIf() == true : "result should be an IF expression.";
        System.out.println("All IF operation tests passed.");
    }

	//Arithmetic tests
    public static void testArithmeticOperations() {
        Exp two = Asts.constantExp(Asts.expVal(2));
        Exp three = Asts.constantExp(Asts.expVal(3));
        Exp trueExp = Asts.constantExp(Asts.expVal(true));
        Exp falseExp = Asts.constantExp(Asts.expVal(false));

        Exp twoTimesThree = Asts.arithmeticExp(two, "TIMES", three);
        Exp twoPlusThree = Asts.arithmeticExp(two, "PLUS", three);
        Exp twoMinusThree = Asts.arithmeticExp(two, "MINUS", three);
        Exp threeMinusTwo = Asts.arithmeticExp(three, "MINUS", two);
        Exp threeGreaterThanTwo = Asts.arithmeticExp(three, "GT", two);
        Exp twoLesserThanThree = Asts.arithmeticExp(two, "LT", three);
        Exp twoEqualsTwo = Asts.arithmeticExp(two, "EQ", two);
        Exp twoEqualsThree = Asts.arithmeticExp(two, "EQ", three);
        Exp trueEqualsTrue = Asts.arithmeticExp(trueExp, "EQ", trueExp);
        Exp trueEqualsFalse = Asts.arithmeticExp(trueExp, "EQ", falseExp);
        Map<String, ExpVal> DEFAULT_ENV = new HashMap<>();

        assert twoTimesThree.value(DEFAULT_ENV).asInteger() == 6 : " TIMES does not work.";
        assert twoPlusThree.value(DEFAULT_ENV).asInteger() == 5 : " PLUS does not work.";
        assert twoMinusThree.value(DEFAULT_ENV).asInteger() == -1 : " MINUS does not work.";
        assert threeMinusTwo.value(DEFAULT_ENV).asInteger() == 1 : " MINUS does not work.";
        assert threeGreaterThanTwo.value(DEFAULT_ENV).asBoolean() == true : " GT does not work.";
        assert twoLesserThanThree.value(DEFAULT_ENV).asBoolean() == true : " LT does not work.";
        assert twoEqualsTwo.value(DEFAULT_ENV).asBoolean() == true : " EQ does not work.";
        assert twoEqualsThree.value(DEFAULT_ENV).asBoolean() == false : " EQ does not work.";
        assert trueEqualsTrue.value(DEFAULT_ENV).asBoolean() == true : "EQ does not work for boolean";
        assert trueEqualsFalse.value(DEFAULT_ENV).asBoolean() == false : "EQ does not work for boolean";
        System.out.println("All arithmetic operations tests passed.");
    }
    
    //Def tests (also coverage for Calls/Identifier)
    public static void testDef() {
    	Def main = Asts.def("main", Asts.arithmeticExp(Asts.identifierExp("a"), "PLUS", Asts.identifierExp("b")));
    	Def defa = Asts.def("a", Asts.constantExp(Asts.expVal(1)));
    	Def defb = Asts.def("b", Asts.constantExp(Asts.expVal(2)));
    	ExpVal result = Programs.run(Asts.list(main, defa, defb), Asts.list(Asts.expVal(2)));
        assert result.asInteger() == 3;
    	System.out.println("All Def operations tests passed.");
    }

    //Program tests (also coverage for Calls/Lambda/Identifier)
    public static void testProgram() {
        Exp exp1  = Asts.arithmeticExp (Asts.identifierExp ("n"), "MINUS",  Asts.constantExp (Asts.expVal (1)));
        Exp call1 = Asts.callExp (Asts.identifierExp ("fact"), Asts.list (exp1));
        Exp testPart  = Asts.arithmeticExp (Asts.identifierExp ("n"), "EQ", Asts.constantExp (Asts.expVal (0)));
        Exp thenPart = Asts.constantExp (Asts.expVal (1));
        Exp elsePart = Asts.arithmeticExp (Asts.identifierExp ("n"), "TIMES", call1);
        Def def1 = Asts.def ("fact", Asts.lambdaExp (Asts.list ("n"), Asts.ifExp (testPart, thenPart, elsePart)));
        ExpVal result = Programs.run (Asts.list (def1), Asts.list (Asts.expVal (5)));
        assert result.asInteger() == 120 : "should be 120";
        System.out.println("All Program operation tests passed.");
    }
}
