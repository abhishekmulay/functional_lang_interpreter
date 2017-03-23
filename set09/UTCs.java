/**
 * Created by abhishek on 3/20/17.
 */


public class UTCs {

    private int hour;
    private int minute;
    public static final int MINUTES_PER_HOUR = 60;

    private UTCs(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // making constructor private so that only way to create an UTC object is make()
    public static UTC make(int hour, int minute) {
        return UTCImpl.makeUTC(hour, minute);
    }

}
