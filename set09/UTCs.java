/**
 * Created by abhishek on 3/20/17.
 */


public class UTCs {

    // making constructor private so that only way to create an UTC object is make()
    public static UTC make(int hour, int minute) {
        return UTCImpl.makeUTC(hour, minute);
    }

}
