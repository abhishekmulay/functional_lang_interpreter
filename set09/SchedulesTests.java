
public class SchedulesTests {
	public static void main (String[] args) {
		//test canGetThere
		assert Schedules.canGetThere("06N", "JFK", FlightExamples.panAmFlights)  ==  false;
		assert Schedules.canGetThere("JFK", "JFK", FlightExamples.panAmFlights)  ==  true;
		assert Schedules.canGetThere("06N", "LAX", FlightExamples.deltaFlights)  ==  false;
		assert Schedules.canGetThere("LAX", "06N", FlightExamples.deltaFlights)  ==  false;
		assert Schedules.canGetThere("LGA", "PDX", FlightExamples.deltaFlights)  ==  true;
		
		
		//test fastestItinerary
		assert Schedules.fastestItinerary("JFK", "JFK", FlightExamples.panAmFlights) ==  
				RacketListImpl.makeRacketList();
		
		RacketList<Flight> racketList = RacketLists.empty();
		assert Schedules.fastestItinerary("LGA", "PDX", FlightExamples.deltaFlights) ==
					racketList
					.cons(FlightImpl.makeFlight("Delta 2163", "MSP", "PDX", 
										UTCs.make (15, 0), UTCs.make (19, 2)))
					.cons(FlightImpl.makeFlight("Delta 0121", "LGA", "MSP",
			                           UTCs.make (11, 0), UTCs.make (14, 9)));
		
		//test travelTime
	    assert Schedules.travelTime("JFK", "JFK", FlightExamples.panAmFlights)  ==  0;
	    assert Schedules.travelTime("LGA", "PDX", FlightExamples.deltaFlights)  ==  482;
	    
	    System.out.println("All unit tests of SchedulesTests passed");
	}
}
