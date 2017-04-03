/**
 * Created by Abhishek Mulay on 4/3/17.
 */
 abstract public class BaseExpVal implements ExpVal {

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isFunction() {
        return false;
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long asInteger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FunVal asFunction() {
        throw new UnsupportedOperationException();
    }
}
