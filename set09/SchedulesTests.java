
public class SchedulesTests {
	public static void main (String[] args) {
		//test canGetThere
		assert Schedules.canGetThere("abhishek", "JFK", FlightExamples
				.panAmFlights)  ==  false;
		assert Schedules.canGetThere("JFK", "JFK", FlightExamples.panAmFlights)  ==  true;
		assert Schedules.canGetThere("06N", "LAX", FlightExamples.deltaFlights)  ==  false;
		assert Schedules.canGetThere("LAX", "06N", FlightExamples.deltaFlights)  ==  false;
		assert Schedules.canGetThere("LGA", "PDX", FlightExamples.deltaFlights)  ==  true;
		
		
		//test fastestItinerary
		assert Schedules.fastestItinerary("JFK", "JFK", FlightExamples.panAmFlights).toString().equals(  
				"RacketList: {\n}");
		
		RacketList<Flight> racketList = RacketLists.empty();
		assert Schedules.fastestItinerary("LGA", "PDX", FlightExamples.deltaFlights).toString().equals(
					"RacketList: {\n"+
					"Flight: { Delta 0121 | LGA ==> MSP | departsAt: 11:00 | arrivesAt: 14:09 }\n"+
					"Flight: { Delta 2163 | MSP ==> PDX | departsAt: 15:00 | arrivesAt: 19:02 }\n}"
					);
		
		//test travelTime
	    assert Schedules.travelTime("JFK", "JFK", FlightExamples.panAmFlights)  ==  0;
	    assert Schedules.travelTime("LGA", "PDX", FlightExamples.deltaFlights)  ==  482;
	    
	    System.out.println("All unit tests of SchedulesTests passed");
	}
}
