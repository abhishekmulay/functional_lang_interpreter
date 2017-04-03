/**
 * Created by Abhishek Mulay on 4/3/17.
 */
public class ExpValBoolean extends BaseExpVal implements ExpVal {

    // value of this expression
    boolean value;

    // constructor
    public ExpValBoolean(boolean value) {
        this.value = value;
    }
    //-----------------------------------------------------------------------------------------------------------

    //Returns: true if this is a boolean, false otherwise
    @Override
    public boolean isBoolean() {
        return true;
    }
    //-----------------------------------------------------------------------------------------------------------

    //Returns: boolean value of this expression
    @Override
    public boolean asBoolean() {
        return this.value;
    }
    //-----------------------------------------------------------------------------------------------------------
}
