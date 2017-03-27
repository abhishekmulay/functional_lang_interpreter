/**
 * A Flights is a Factory for making Flights
 */
public class Flights {
  /**
   * GIVEN:
   * @param name          represents the name of the flight
   * @param source        represents the city the flight departs from
   * @param destination   represents the city the flight arrives in
   * @param departureTime represents the time of departure
   * @param arrivalTime   represents the time of arrival
   *
   * RETURNS:
   * @return returns a Flight with given properties

   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "DET", UTCs.make(5, 23), UTCs.make(10,
   * 0)) =>
   *
   * new FlightImpl("Delta 1234", "BOS", "LGA", makeUTC( 5, 24), makeUTC(6,
   * 58));
   */
  public static Flight make(String name, String source, String destination,
                            UTC departureTime, UTC arrivalTime) {
    return FlightImpl.makeFlight(name, source, destination, departureTime,
        arrivalTime);
  }
}