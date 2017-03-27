/**
 * Created by Abhishek Mulay on 3/22/17.
 */
///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

/**
 *
 * A UTCImpl is a:
 * CONSTRUCTOR TEMPLATE:
 * =====================
 *    new UTCImpl(int, int)
 *
 *    OR
 *
 *    makeUTC(int, int)
 *
 * Interpretation:
 * ==============
 * A UTCImpl represents a UTC instance which has following public members:
 *  hour: an int representing hour part of UTC time
 *  minute: an int representing minute part of UTC time
 *
 *  The hour is less than 24 and the minute is less than 60
 *
 */
public class UTCImpl implements UTC {

  private int hour;
  private int minute;
  public static final int MINUTES_PER_HOUR = 60;

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param hour   represents the number of hours passed in the day
   * @param minute represents the number of minutes passed in the day
   *
   * RETURNS:
   * @return an instance of UTCImpl which represents given time
   *
   * EXAMPLE:
   * new UTCImpl(5, 20) => UTCImpl.makeUTC(5, 20)
   */
  private UTCImpl(int hour, int minute) {
    this.hour = hour;
    this.minute = minute;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param hour   represents the number of hours passed in the day
   * @param minute represents the number of minutes passed in the day
   *
   * RETURN:
   * @return a UTC object with the provided properties
   *
   * EXAMPLE:
   * UTCImpl.makeUTC(5, 20).toString() => "05:20"
   *
   * PATTERN: Static factory method pattern
   */
  public static UTC makeUTC(int hour, int minute) {
    return new UTCImpl(hour, minute);
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURN:
   * @return the number of hours in this UTC
   *
   * EXAMPLE:
   * UTCs.make(5, 20).hour() => 5
   */
  @Override
  public int hour() {
    return this.hour;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURN:
   * @return the number of minutes in this UTC
   *
   * EXAMPLE:
   * UTCs.make(5, 20).minute() => 20
   */
  @Override
  public int minute() {
    return this.minute;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param t2 represents the UTC to compare to
   *
   * RETURN:
   * true if the UTC are equal, false otherwise
   *
   * EXAMPLE:
   * UTCs.make(5, 20).isEqual(UTCs.make(15, 4)) => false
   */
  @Override
  public boolean isEqual(UTC t2) {
    return this.equals(t2);
  }

  //___________________________________________________________________________
  /**
   * RETURN:
   * @return a string representation of this UTC
   *
   * EXAMPLE:
   * UTCs.make(15, 4).toString() => "15:04"
   */
  @Override
  public String toString() {
    //  example: 15:00 or 19:02
    return String.format("%02d", hour) + ":" + String.format("%02d", minute);
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param o represents the UTC to compare to
   *
   * RETURNS:
   * true iff o equals this UTC, false otherwise
   *
   * EXAMPLE:
   *   UTCs.make(15, 4).equals(UTCs.make(15, 4)) => true
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    UTC utc = (UTC) o;
    return hour == utc.hour() && minute == utc.minute();
  }

  //___________________________________________________________________________
  /**
   *
   * RETURNS:
   * @return a hashCode representation of this UTC
   *
   * EXAMPLES:
   * UTCs.make(15, 4).hashCode() => 469
   */
  @Override
  public int hashCode() {
    int result = hour;
    result = 31 * result + minute;
    return result;
  }
  //___________________________________________________________________________
}
