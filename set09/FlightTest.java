
public class FlightTest {
  public static void main(String[] args) {

    //test name
    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)).name().equals("Delta 1234");
    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35)).name().equals("Delta 0689");

    //test departs
    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)).departs().equals("BOS");
    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35)).departs().equals("LAX");

    //test arrives
    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)).arrives().equals("LGA");
    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35)).arrives().equals("PDX");

    //test departsAt
    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)).departsAt().equals(UTCs.make(5, 24));
    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35)).departsAt().equals(UTCs.make(13, 0));

    //test arrivesAt
    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)).arrivesAt().equals(UTCs.make(6, 58));
    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35)).arrivesAt().equals(UTCs.make(15, 35));

    // test isEqual
    Flight testFlight = Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0),
        UTCs.make(15, 35));

    Flight equalTestFlight = Flights.make("Delta 0689", "LAX", "PDX", UTCs
            .make
            (13, 0),
        UTCs.make(15, 35));

    Flight testFlightNameNotEqual = Flights.make("Delta A", "LAX", "PDX",
        UTCs
            .make(13, 0),
        UTCs.make(15, 35));

    Flight testFlightDepartsNotEqual = Flights.make("Delta A", "BOM", "PDX",
        UTCs
            .make(13, 0),
        UTCs.make(15, 35));

    Flight testFlightArrivesNotEqual = Flights.make("Delta A", "BOM", "PDX",
        UTCs
            .make(13, 0),
        UTCs.make(15, 35));

    Flight testFlightDepartsAtNotEqual = Flights.make("Delta A", "BOM",
        "PDX",
        UTCs
            .make(10, 0),
        UTCs.make(15, 35));

    Flight testFlightArrivesAtNotEqual = Flights.make("Delta A", "BOM",
        "PDX",
        UTCs
            .make(13, 0),
        UTCs.make(10, 35));

    assert testFlight.isEqual(equalTestFlight) :
        "The flights are same.";
    assert testFlight.isEqual(testFlightNameNotEqual) == false:
        "Flights are not equal";
    assert testFlight.isEqual(testFlightDepartsNotEqual) == false:
        "Flights are not equal";
    assert testFlight.isEqual(testFlightArrivesNotEqual)== false :
        "Flights are not equal";
    assert testFlight.isEqual(testFlightDepartsAtNotEqual) == false:
        "Flights are not equal";
    assert testFlight.isEqual(testFlightArrivesAtNotEqual) == false :
        "Flights are not equal";

    System.out.println("[FlightTest] All unit tests passed.");
  }
}
