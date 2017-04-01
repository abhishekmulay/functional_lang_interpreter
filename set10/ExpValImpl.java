
/**
 * Created by Abhishek Mulay on 3/30/17.
 */
public class ExpValImpl implements ExpVal {

  // value of expression
  private Object value;

  ExpValImpl(boolean booleanVal) {
    this.value = (Boolean) booleanVal;
  }

  ExpValImpl(Long integerVal) {
    this.value = integerVal;
  }

  @Override
  public boolean isBoolean() {
    return this.value instanceof Boolean;
  }

  @Override
  public boolean isInteger() {
    return (this.value instanceof Integer || this.value instanceof Long);
  }

  @Override
  public boolean isFunction() {
    System.out.println("[ExpValImpl] asFunction: not implemented yet." );
    return false;
  }

  @Override
  public boolean asBoolean() {
    if (this.isBoolean()) {
      return (boolean) this.value;
    } else {
      System.out.println("[ExpValImpl] asBoolean: not a boolean value: " +this.value);
      return false;
    }
  }

  @Override
  public long asInteger() {
    if (this.isInteger()) {
      return (long) this.value;
    } else {
      System.out.println("[ExpValImpl] asInteger: not a long value: value= " + this.value);
      return -1;
    }
  }

  @Override
  public FunVal asFunction() {
    System.out.println("[ExpValImpl] asFunction: not implemented yet." );
    return null;
  }

  @Override
  public String toString() {
    return "ExpValImpl: {value=" + value + '}';
  }
}
