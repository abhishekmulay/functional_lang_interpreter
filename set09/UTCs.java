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

    // making constructor private so that only way to create an UTC object is make()
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
        return this.equals(t2);
    }

    @Override
    public String toString() {
        //  example: 15:00 or 19:02
        return  String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        UTCs utCs = (UTCs) o;
        return hour == utCs.hour && minute == utCs.minute;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }
}
