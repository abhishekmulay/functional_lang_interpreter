import java.util.*;

/**
 * Created by Abhishek Mulay on 3/23/17.
 */

///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

// A TravelTimeCalculator is :
//
//  CONSTRUCTOR TEMPLATE:
//  =====================
//          new TravelTimeCalculator()
//
//  Interpretation:
//  ===============
//   A TravelTimeCalculator provides methods for calculating travel time of
//   given itineraries which are represented as an ArrayList of flights.
//

public class TravelTimeCalculator {

  // constant, number of minutes in a day
  private final int MINUTES_PER_DAY = 1440;

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param flights a list of flights to get the travel time of
   *
   * RETURNS:
   * @return time this itinerary will take in mintues
   *
   * EXAMPLES:
   * getTravelTimeForItinerary(new ArrayList<Flight>(){{
   * add(Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30),
   * UTCs.make(5, 20)));
   * add(Flights.make("Delta 1", "MSP", "LGA", UTCs.make(7, 10),
   * UTCs.make(11, 45)));}});
   * =>
   * 615
   *
   * PATTERN: Functional visitor pattern, visit each flight in the list
   */
  public int getTravelTimeForItinerary(ArrayList<Flight> flights) {
    int airTime = 0;
    int layoverTime = 0;
    for (int index = 0; index < flights.size(); index++) {
      Flight f = flights.get(index);
      airTime += getAirTime(f);
      if (index == flights.size() - 1) {
        break;
      }
      Flight nextFlight = flights.get(index + 1);
      layoverTime += getLayoverTime(f, nextFlight);
    }
    return airTime + layoverTime;
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   *
   * @param f1 represents the first flight
   * @param f2 represents the second flight
   *           RETURNS:
   * @return length of layover between flights in minutes
   * STRATEGY:
   * use simpler functions
   * EXAMPLES:
   * getLayoverTime(
   * Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30), UTCs.make(5, 20)),
   * Flights.make("Delta 1", "MSP", "LGA", UTCs.make(7, 10), UTCs.make(11, 45)))
   * => 110
   */
  private int getLayoverTime(Flight f1, Flight f2) {
    if (getMinutes(f1.arrivesAt()) > getMinutes(f2.departsAt())) {
      return overnightLayoverTime(f1, f2);
    } else {
      return sameDayLayover(f1, f2);
    }
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   *
   * @param f1 represents the first flight
   * @param f2 represents the second flight
   *           RETURNS:
   * @return length of layover between flights in minutes
   * WHERE:
   * the layover is not overnight
   * STRATEGY:
   * use simpler functions
   * EXAMPLES:
   * getLayoverTime(
   * Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30), UTCs.make(5, 20)),
   * Flights.make("Delta 1", "MSP", "LGA", UTCs.make(7, 10), UTCs.make(11, 45)))
   * => 110
   */
  private int sameDayLayover(Flight f1, Flight f2) {
    return getMinutes(f2.departsAt()) - getMinutes(f1.arrivesAt());
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   *
   * @param f1 represents the first flight
   * @param f2 represents the second flight
   *           RETURNS:
   * @return length of layover between flights in minutes
   * WHERE:
   * the layover is not overnight
   * STRATEGY:
   * use simpler functions
   * EXAMPLES:
   * getLayoverTime(
   * Flights.make("Delta 1", "BOS", "MSP", UTCs.make(18, 30),UTCs.make(20, 20)),
   * Flights.make("Delta 1", "MSP", "LGA", UTCs.make(4, 10), UTCs.make(6, 45)))
   * => 470
   */
  private int overnightLayoverTime(Flight f1, Flight f2) {
    return MINUTES_PER_DAY - getMinutes(f1.arrivesAt()) +
        getMinutes(f2.departsAt());
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param f represents the flight
   *
   * RETURNS:
   * @return length of flight time in minutes
   *
   * EXAMPLES:
   * getAirTime(
   * Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30), UTCs.make(5, 20)))
   * => 230
   */
  public int getAirTime(Flight f) {
    // Time of arrival is > departure then we crossed 12 AM
    if (getMinutes(f.arrivesAt()) > getMinutes(f.departsAt())) {
      return getMinutes(f.arrivesAt()) - getMinutes(f.departsAt());
    } else {
      return (MINUTES_PER_DAY - getMinutes(f.departsAt())) +
          getMinutes(f.arrivesAt());
    }
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param utc a time in the format of a UTC object
   *
   * RETURNS:
   * @return number of minutes that have passed in a day
   *
   * EXAMPLES:
   * getMinutes(UTCs.make(5, 20))
   * => 330
   */
  public int getMinutes(UTC utc) {
    return UTCImpl.MINUTES_PER_HOUR * utc.hour() + utc.minute();
  }
  //___________________________________________________________________________
}
