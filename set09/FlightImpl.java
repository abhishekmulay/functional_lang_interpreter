///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

/**
 *
 * A FlightImpl is a:
 * CONSTRUCTOR TEMPLATE:
 * =====================
 *    new FlightImpl(String String String UTC UTC)
 *
 *    OR
 *
 *    makeFlight(String, String, String, UTC, UTC)
 *
 * Interpretation:
 * ===============
 *   A FlightImpl represents a Flight instance which has following public
 *   members:
 *
 *    a name representing the name of the flight
 *    a source representing the departure city of the flight
 *    a destination representing the arrival city of the flight
 *    a departureTime representing the departure time of the flight
 *    a arrivalTime representing the arrival time of the flight
 */
public class FlightImpl implements Flight {
  // name of flight
  private String name;
  // flight leaves from this airport
  private String source;
  // flight reaches this airport
  private String destination;
  // time of departure in UTC format
  private UTC departureTime;
  // time of arrival in UTC format
  private UTC arrivalTime;

  //___________________________________________________________________________
  // making constructor private so that using new FlightImpl is discouraged.
  /**
   * GIVEN:
   * @param name          representing the name of the flight
   * @param source        representing the departure city of the flight
   * @param destination   representing the arrival city of the flight
   * @param departureTime representing the departure time of the flight
   * @param arrivalTime   representing the arrival time of the flight
   *                      RETURNS:
   * @returns Instance of FlightImpl which represnts a Flight with given data
   */
  private FlightImpl(String name, String source, String destination,
                     UTC departureTime, UTC arrivalTime) {
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }

  //___________________________________________________________________________
  /**
   * GIVEN
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
   *
   * makeFlight("Delta 0689", "LAX", "PDX", makeUTC(13, 0), makeUTC(15, 35)) =>
   * FlightImpl("Delta 0689", "LAX", "PDX", makeUTC(13, 0), makeUTC(15, 35))
   *
   * PATTERN: Static factory method pattern
   */
  public static Flight makeFlight(String name, String source,
                                  String destination, UTC departureTime,
                                  UTC arrivalTime) {
    return new FlightImpl(name, source, destination,
        departureTime, arrivalTime);
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns the name of this flight
   *
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

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns the departure location of this flight
   *
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

  //___________________________________________________________________________
  /**
   *
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns the arrival location of this flight
   *
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

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns the departure time of this flight
   *
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

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns the arrival time of this flight
   *
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

  //___________________________________________________________________________
  /**
   * GIVEN
   * @flight the flight to compare this flight to
   *
   * RETURNS:
   * @returns true iff all fields in flight are equal
   * EXAMPLE:
   *
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).isEqual(Flights.make("Delta 0689", "LAX", "PDX",
   * UTCs.make(13, 0), UTCs.make(15, 35)))
   * => false
   *
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).isEqual(Flights.make("Delta 0689", "LAX", "PDX",
   * UTCs.make(13, 0), UTCs.make(15, 35)))
   * => true
   *
   * DESIGN STRATEGY: Combine simpler functions
   */
  @Override
  public boolean isEqual(Flight flight) {
    return this.equals(flight);
  }

  //___________________________________________________________________________

  /**
   * GIVEN: the object to compare this flight to
   *
   * RETURNS:
   * @returns true if the flights are equal, false otherwise
   *
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(null)
   * => false
   *
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(UTCs.make(13, 16))
   * => false
   *
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).equals(Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make( 5, 24), UTCs.make(6, 58)))
   * => true
   *
   * DESIGN STRATEGY: Combine simpler functions
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

  //___________________________________________________________________________
  /**
   * GIVEN: none
   *
   * RETURNS:
   * @returns an Integer HashCode representation of this Flight
   *
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).hashCode()
   * => -235678037;
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).hashCode()
   * => 1996434897;
   *
   * DESIGN STRATEGY: Combine simpler functions
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

  //___________________________________________________________________________
  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * RETURNS:
   * @returns a String representation of this flight
   *
   * EXAMPLE:
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)).toString()
   * => "Flight: { Delta 1234 | BOS ==> LGA | departsAt: 5:24 | arrivesAt: 06:58 }"
   *
   * Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0),
   * UTCs.make(15, 35)).toString()
   * =>
   * "Flight: { Delta 0689 | LAX ==> PDX | departsAt: 13:00 | arrivesAt: 16:35 }"
   *
   * DESIGN STRATEGY: Combine simpler functions
   */
  public String toString() {
    return "Flight: { " + this.name() + " | " + this.departs() + " ==> " +
        this.arrives() + " | departsAt: "
        + this.departsAt().toString() + " | arrivesAt: " +
        this.arrivesAt().toString() + " }";
  }

  //___________________________________________________________________________
}
