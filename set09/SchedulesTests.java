public class SchedulesTests {
  public static void main(String[] args) {
    //test canGetThere
    assert Schedules.canGetThere("abhishek", "JFK",
        FlightExamples.panAmFlights) == false;
    assert Schedules.canGetThere("JFK", "JFK",
        FlightExamples.panAmFlights) == true;
    assert Schedules.canGetThere("06N", "LAX",
        FlightExamples.deltaFlights) == false;
    assert Schedules.canGetThere("LAX", "06N",
        FlightExamples.deltaFlights) == false;
    assert Schedules.canGetThere("LGA", "PDX",
        FlightExamples.deltaFlights) == true;


    //test fastestItinerary
    assert RacketLists.isFlightRacketListEqual(Schedules.
        fastestItinerary("JFK", "JFK", FlightExamples.panAmFlights),
        RacketLists.empty()) : "Should return empty list";

    assert RacketLists.isFlightRacketListEqual(Schedules.
            fastestItinerary("JFK", "LGA", FlightExamples.panAmFlights),
        RacketLists.empty()) : "Should return empty list";

    RacketList<Flight> fastestItineraryfromLHRtoPDXviaDeltaFlights =
        FlightExamples.fastestItineraryfromLHRtoPDXviaDeltaFlights;

    assert RacketLists.isFlightRacketListEqual
        (fastestItineraryfromLHRtoPDXviaDeltaFlights, Schedules
            .fastestItinerary
                ("PDX", "LHR", FlightExamples.deltaFlights)) :
        "wrong itinerary for PDX to LHR via Delta";

    //test travelTime
    assert Schedules.travelTime("JFK", "JFK",
        FlightExamples.panAmFlights) == 0;
    assert Schedules.travelTime("LGA", "PDX",
        FlightExamples.deltaFlights) == 482;
    assert Schedules.travelTime("PDX", "LHR",
        FlightExamples.deltaFlights) == 1167 : "Travel time is 1167";

    System.out.println("[SchedulesTests] All unit tests passed.");
  }
}
