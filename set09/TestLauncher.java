/**
 * Created by Abhishek Mulay on 3/27/17.
 */
public class TestLauncher {

  public static void main(String[] args) {

    ///////////////////////////////////////
    //    TESTS FOR ADT
    //////////////////////////////////////
    FlightTest.main(args);
    UTCTests.main(args);
    RacketListsTests.main(args);

    //////////////////////////////////////
    //   TESTS FOR LAUNCHER CLASSES
    /////////////////////////////////////
    SchedulesTests.main(args);
    DijikstraTests.main(args);

    ////////////////////////////////////
    //   TESTS FOR HELPER CLASSES
    ///////////////////////////////////
    TravelTimeCalculatorTests.main(args);
    ListConverterTest.main(args);
    PriorityAirportQueueTests.main(args);
    AirportNodeTests.main(args);

  }

}
