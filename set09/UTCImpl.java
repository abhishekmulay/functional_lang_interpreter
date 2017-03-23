/**
 * Created by Abhishek Mulay on 3/22/17.
 */
public class UTCImpl implements UTC {

    private int hour;
    private int minute;
    public static final int MINUTES_PER_HOUR = 60;

    private UTCImpl(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static UTC makeUTC(int hour, int minute) {
        return new UTCImpl(hour, minute);
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
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        UTC utc = (UTC) o;
        return hour == utc.hour() && minute == utc.minute();
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }
}

// Unit tests for UTCImpl.
class UTCImplTest {

    public static void main (String[] args) {
        UTC t1 = UTCs.make(15, 31);
        UTC t2 = UTCs.make(14, 31);
        UTC t3 = UTCs.make(15, 32);
        UTC t4 = UTCs.make(15, 31);

        assert t1.hour() == 15 : "wrong hour for t1";
        assert t1.minute() == 31 : "wrong minute for t1";

        assert t1.isEqual (t1) : "isEqual says this doesn't equal this";
        assert t1.isEqual (t4) : "isEqual says this doesn't equal that";
        assert ! (t1.isEqual (t2)) : "isEqual true but hour different";
        assert ! (t1.isEqual (t3)) : "isEqual true but minute different";

        System.out.println ("All unit tests of UTCImpl passed.");
    }
}

