/**
 * Created by Abhishek on 3/20/17.
 */


public class UTCs {

  /**
   * GIVEN: the hour and minute parts of a time in UTC
   * RETURNS: the UTC time determined by the arguments
   * WHERE: the hour is less than 24 and the minute is less than 60
   * EXAMPLES:
   *
   * PATTERN: Static factory pattern
   * @param hour
   * @param minute
   * @return UTC
   */
  public static UTC make(int hour, int minute) {
    return UTCImpl.makeUTC(hour, minute);
  }

}
