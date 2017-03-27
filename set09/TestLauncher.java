/**
 * Created by Abhishek Mulay on 3/27/17.
 */

///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

//  A TestLauncher is a :
//
//  CONSTRUCTOR TEMPLATE:
//  =====================
//      new TestLauncher()
//
//  Interpretation:
//  ===============
//     This is a entry point for testing entire application, it has a main()
//     method which calls all other test suites.

public class TestLauncher {

  // main launcher method
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

    ////////////////////////////////////
    //   TESTS FOR HELPER CLASSES
    ///////////////////////////////////
    TravelTimeCalculatorTests.main(args);
    ListConverterTest.main(args);
    PriorityAirportQueueTests.main(args);
    AirportNodeTests.main(args);

  }

}
