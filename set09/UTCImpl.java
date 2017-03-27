/**
 * Created by Abhishek Mulay on 3/22/17.
 */
public class UTCImpl implements UTC {

    private int hour;
    private int minute;
    public static final int MINUTES_PER_HOUR = 60;

    /**
     * GIVEN:
     *  @param hour represents the number of hours passed in the day
     *  @param minute represents the number of minutes passed in the day
     * RETURN
     *  @return implicit return of this UTCImpl
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  new UTCImpl(5, 20) => UTCImpl.makeUTC(5, 20)
     */
    private UTCImpl(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * GIVEN:
     *  @param hour represents the number of hours passed in the day
     *  @param minute represents the number of minutes passed in the day
     * RETURN:
     *  @return a UTC object with the provided properties
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCImpl.makeUTC(5, 20).toString() => "05:20"
     */
    public static UTC makeUTC(int hour, int minute) {
        return new UTCImpl(hour, minute);
    }

    /**
     * RETURN: 
     *  @return the number of hours in this UTC
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(5, 20).hour() => 5
     */
    @Override
    public int hour() {
        return this.hour;
    }

    /**
     * RETURN: 
     *  @return the number of minutes in this UTC
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(5, 20).minute() => 20
     */
    @Override
    public int minute() {
        return this.minute;
    }

    /**
     * GIVEN:
     *  @param t2 represents the UTC to compare to
     * RETURN:
     * 	True if t2 equals this, false otherwise
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(5, 20).isEqual(UTCs.make(15, 4))
     *  => false
     */
    @Override
    public boolean isEqual(UTC t2) {
        return this.equals(t2);
    }

    /**
     * RETURN: 
     *  @return a string representation of this UTC
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(15, 4).toString() => "15:04"
     */
    @Override
    public String toString() {
        //  example: 15:00 or 19:02
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    /**
     * GIVEN:
     *  @param o represents the UTC to compare to
     * RETURN:
     * 	True if o equals this, false otherwise
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(15, 4).equals(UTCs.make(15, 4))
     *  => true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        UTC utc = (UTC) o;
        return hour == utc.hour() && minute == utc.minute();
    }

    /**
     * RETURN: 
     *  @return a hashCode representation of this UTC
     * STRATEGY:
     *  use simpler functions
     * EXAMPLE:
     *  UTCs.make(15, 4).hashCode() => 469
     */
    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }
}
