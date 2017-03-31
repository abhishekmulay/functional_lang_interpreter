import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class Asts {
  // Static factory methods for Def

  // Returns a Def with the given left and right hand sides.

  public static Def def (String id1, Exp rhs) { ... }

  // Static factory methods for Exp

  // Returns an ArithmeticExp representing e1 op e2.

  public static ArithmeticExp arithmeticExp (Exp e1, String op, Exp e2) { ... }

  // Returns a CallExp with the given operator and operand expressions.

  public static CallExp callExp (Exp operator, List<Exp> operands) { ... }

  // Returns a ConstantExp with the given value.

  public static ConstantExp constantExp (ExpVal value) { ... }

  // Returns an IdentifierExp with the given identifier name.

  public static IdentifierExp identifierExp (String id) { ... }

  // Returns an IfExp with the given components.

  public static IfExp ifExp (Exp testPart, Exp thenPart, Exp elsePart) { ... }

  // Returns a LambdaExp with the given formals and body.

  public static LambdaExp lambdaExp (List<String> formals, Exp body) { ... }

  // Static factory methods for ExpVal

  // Returns a value encapsulating the given boolean.

  public static ExpVal expVal (boolean b) { ... }

  // Returns a value encapsulating the given (long) integer.

  public static ExpVal expVal (long n) { ... }

  // Returns a value encapsulating the given lambda expression
  // and environment.

  public static FunVal expVal (LambdaExp exp, Map<String,ExpVal> env) { ... }

  // Static methods for creating short lists

  public static <X> List<X> list (X x1) { ... }

  public static <X> List<X> list (X x1, X x2) { ... }

  public static <X> List<X> list (X x1, X x2, X x3) { ... }

  public static <X> List<X> list (X x1, X x2, X x3, X x4) { ... }
}
