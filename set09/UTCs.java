/**
 * Created by abhishek on 3/20/17.
 */


public class UTCs implements UTC {

    private int hour;
    private int minute;
    public static final int MINUTES_PER_HOUR = 60;

    private UTCs(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static UTC make(int hour, int minute) {
        return new UTCs(hour, minute);
    }

    @Override
    public int hour() {
        return this.hour;
    }

    @Override
    public int minute() {
        return this.minute;
    }

    @Override
    public boolean isEqual(UTC t2) {
        return this.hour() == t2.hour() && this.minute() == t2.minute();
    }

    @Override
    public String toString() {
        return "UTCs {" + "hour=" + hour + ", minute=" + minute + '}';
    }

}
