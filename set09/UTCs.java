/**
 * Created by Abhishek on 3/20/17.
 */

///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

/**
 *  An UTCs is a:
 * CONSTRUCTOR TEMPLATE:
 * =====================
 *    new UTCs()
 *
 * Interpretation:
 * ===============
 *    This is a class which provides a static factory method for creating
 *    objects of type UTC.
 *
 */

public class UTCs {

  /**
   * GIVEN: the hour and minute parts of a time in UTC
   *
   * RETURNS: the UTC time determined by the arguments
   *
   * WHERE: the hour is less than 24 and the minute is less than 60
   *
   * EXAMPLES:
   * UTCs.make(5, 20) => UTCImpl.makeUTC(5, 20)
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
