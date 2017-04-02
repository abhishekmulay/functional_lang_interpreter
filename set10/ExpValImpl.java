
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

  //Returns: true if this is a boolean, false otherwise
  @Override
  public boolean isBoolean() {
    return this.value instanceof Boolean;
  }

  //Returns: true if this is an Integer, false otherwise
  @Override
  public boolean isInteger() {
    return (this.value instanceof Integer || this.value instanceof Long);
  }

  //Returns: true if this is a FunVal, false otherwise
  @Override
  public boolean isFunction() {
	  return this.value instanceof FunVal;
  }

  //Returns: this cast to a Boolean if this is a Boolean
  @Override
  public boolean asBoolean() {
    if (this.isBoolean()) {
      return (boolean) this.value;
    } else {
    	throw new UnsupportedOperationException();
    }
  }

  //Returns: this cast to an Integer if this is an Integer
  @Override
  public long asInteger() {
    if (this.isInteger()) {
      return (long) this.value;
    } else {
    	throw new UnsupportedOperationException();
    }
  }

  //Returns: this cast to a FunVal if this is an Integer
  @Override
  public FunVal asFunction() {
	  if (this.isInteger()) {
		  return (FunVal)this.value;
	} else {
		throw new UnsupportedOperationException();
	}
  }

  //string representation of this object
  @Override
  public String toString() {
    return "ExpValImpl: {value=" + value + '}';
  }
}
