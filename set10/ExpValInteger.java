import java.util.Map;

/**
 * Created by Abhishek Mulay on 4/3/17.
 */
public class ExpValInteger extends BaseExpVal implements ExpVal {

    // value of this expression
    long value;

    // constructor
    public ExpValInteger(long value) {
        this.value = value;
    }
    //-----------------------------------------------------------------------------------------------------------

    //Returns: true if this is an Integer, false otherwise
    @Override
    public boolean isInteger() {
        return true;
    }
    //-----------------------------------------------------------------------------------------------------------

    // returns integer value of this expression
    @Override
    public long asInteger() {
        return this.value;
    }
    //-----------------------------------------------------------------------------------------------------------

}
