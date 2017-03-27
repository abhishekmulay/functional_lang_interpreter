/**
 * An FlightImpl is a
 *    new FlightImpl(String String String UTC UTC)
 * Interpretation:
 *   A FlightImpl represents an flight with:
 *    a name representing the name of the flight
 *    a source representing the departure city of the flight
 *    a destination representing the arrival city of the flight
 *    a departureTime representing the departure time of the flight
 *    a arrivalTime representing the arrival time of the flight
 * Template: 
 *  public ?? FlightImpl-fn() {
 *    (...(name())
 *    	  (departs())
 *    	  (arrives())
 *    	  (departsAt())
 *    	  (arrivesAt())
 *  }
 */
public class FlightImpl implements Flight {
  private String name;
  private String source;
  private String destination;
  private UTC departureTime;
  private UTC arrivalTime;

  // making constructor private so that make is the only way to
  //create a Flight instance
  /**
   * GIVEN:
   *
   * @param name          representing the name of the flight
   * @param source        representing the departure city of the flight
   * @param destination   representing the arrival city of the flight
   * @param departureTime representing the departure time of the flight
   * @param arrivalTime   representing the arrival time of the flight
   *                      RETURNS:
   * @returns implicitly returns this Flight with given properties
   */
  private FlightImpl(String name, String source, String destination,
                     UTC departureTime, UTC arrivalTime) {
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }

  /**
   * GIVEN
   *
   * @param name          representing the name of the flight
   * @param source        representing the departure city of the flight
   * @param destination   representing the arrival city of the flight
   * @param departureTime representing the departure time of the flight
   * @param arrivalTime   representing the arrival time of the flight
   *                      RETURNS:
   * @returns a Flight with given properties
   * STRATEGY: Combine simpler functions
   * EXAMPLE:
   * makeFlight("Delta 1234", "BOS", "LGA", makeUTC( 5, 24), makeUTC(6, 58)) =>
   * FlightImpl("Delta 1234", "BOS", "LGA", makeUTC( 5, 24), makeUTC(6, 58))
   * makeFlight("Delta 0689", "LAX", "PDX", makeUTC(13, 0), makeUTC(15, 35)) =>
   * FlightImpl("Delta 0689", "LAX", "PDX", makeUTC(13, 0), makeUTC(15, 35))
   */
  public static Flight makeFlight(String name, String source,
                                  String destination, UTC departureTime,
                                  UTC arrivalTime) {
    return new FlightImpl(name, source, destination,
        departureTime, arrivalTime);
  }

  /**
   * RETURNS:
   *
   * @returns the name of this flight
   * STRATEGY:
   * use private variable
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).name()
   * => "Delta 1234"
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
   * UTCs.make(15, 35)).name()
   * => "Delta 0689"
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * RETURNS:
   *
   * @returns the departure location of this flight
   * STRATEGY:
   * use private variable
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).departs()
   * => "BOS"
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
   * UTCs.make(15, 35)).departs()
   * => "LAX"
   */
  @Override
  public String departs() {
    return this.source;
  }

  /**
   * RETURNS:
   *
   * @returns the arrival location of this flight
   * STRATEGY:
   * use private variable
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).arrives()
   * => "LGA"
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
   * UTCs.make(15, 35)).arrives()
   * => "LGA"
   */
  @Override
  public String arrives() {
    return this.destination;
  }

  /**
   * RETURNS:
   *
   * @returns the departure time of this flight
   * STRATEGY:
   * use private variable
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).departsAt()
   * => UTCs.make( 5, 24)
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
   * UTCs.make(15, 35)).departsAt()
   * => UTCs.make( 5, 24)
   */
  @Override
  public UTC departsAt() {
    return this.departureTime;
  }

  /**
   * RETURNS:
   *
   * @returns the arrival time of this flight
   * STRATEGY:
   * use private variable
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).arrivesAt()
   * => UTCs.make(6, 58))
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
   * UTCs.make(15, 35)).arrivesAt()
   * => UTCs.make(6, 58))
   */
  @Override
  public UTC arrivesAt() {
    return this.arrivalTime;
  }

  /**
   * GIVEN
   *
   * @flight the flight to compare this flight to
   * RETURNS:
   * @returns true if the flights are equal, false otherwise
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).isEqual(Flights.make("Delta 0689", "LAX", "PDX",
   * UTCs.make(13, 0), UTCs.make(15, 35)))
   * => false
   * <p>
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).isEqual(Flights.make("Delta 0689", "LAX", "PDX",
   * UTCs.make(13, 0), UTCs.make(15, 35)))
   * => true
   */
  @Override
  public boolean isEqual(Flight flight) {
    return this.equals(flight);
  }

  /**
   * RETURNS: the object to compare this flight to
   *
   * @returns true if the flights are equal, false otherwise
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(null)
   * => false
   * <p>
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(UTCs.make(13, 16))
   * => false
   * <p>
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make( 5, 24), UTCs.make(6, 58)))
   * => true
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    FlightImpl f = (FlightImpl) o;
    return this.name.equals(f.name())
        && this.departs().equals(f.departs())
        && this.arrives().equals(f.arrives())
        && this.departsAt().isEqual(f.departsAt())
        && this.arrivesAt().isEqual(f.arrivesAt());
  }

  /**
   * RETURNS:
   *
   * @returns an Integer HashCode representation of this Flight
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).hashCode()
   * => -235678037;
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).hashCode()
   * => 1996434897;
   */
  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (source != null ? source.hashCode() : 0);
    result = 31 * result + (destination != null ? destination.hashCode() : 0);
    result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
    result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
    return result;
  }

  /**
   * RETURNS:
   *
   * @returns a String representation of this flight
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).toString()
   * => "Flight: { Delta 1234 | BOS ==> LGA | departsAt: 5:24 | arrivesAt: 06:58 }"
   * <p>
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).toString()
   * =>
   * "Flight: { Delta 0689 | LAX ==> PDX | departsAt: 13:00 | arrivesAt: 16:35 }"
   */
  public String toString() {
    return "Flight: { " + this.name() + " | " + this.departs() + " ==> " +
        this.arrives() + " | departsAt: "
        + this.departsAt().toString() + " | arrivesAt: " +
        this.arrivesAt().toString() + " }";
  }
}
