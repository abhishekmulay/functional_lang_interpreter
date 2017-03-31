import java.util.List;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class Programs {
    public static ExpVal run(List<Def> pgm, List<ExpVal> inputs) {
        System.out.println("Programs: run: inputs=" + inputs);
        System.out.println("Programs: run: pgm" + pgm);
//    return new ExpValImpl();
        return null;
    }


    public static void main(String[] args) {
        System.out.println("Asts.expVal(1): " + Asts.expVal(1));
        ConstantExp constantExp = Asts.constantExp(Asts.expVal(1));
        System.out.println("constantExp: " + constantExp);

        Exp exp1 = Asts.arithmeticExp(Asts.identifierExp("n"), "MINUS", Asts.constantExp(Asts.expVal(1)));

        System.out.println("exp1: " + exp1);
    }
}
