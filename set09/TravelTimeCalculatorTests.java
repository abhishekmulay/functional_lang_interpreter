import java.util.*;

public class TravelTimeCalculatorTests {
	public static void main (String[] args) {
		TravelTimeCalculator calc = new TravelTimeCalculator();
		
		//test getTravelTimeForItinerary
		assert calc.getTravelTimeForItinerary(
			    new ArrayList<Flight>(){{
			      add(Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30), UTCs.make(5, 20)));
			      add(Flights.make("Delta 1", "MSP", "LGA", UTCs.make(7, 10), UTCs.make(11, 45)));}}) 
				== 615;
		
		//test getAirTime
		assert calc.getAirTime(
			     Flights.make("Delta 1", "BOS", "MSP", UTCs.make(1, 30), UTCs.make(5, 20)))
				== 230;
		
		//test getMinutes
		assert calc.getMinutes(UTCs.make(5, 20)) == 320;
		
		System.out.println("[TravelTimeCalculatorTests] All unit tests passed.");
	}
}
