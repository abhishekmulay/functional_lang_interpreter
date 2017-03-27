import java.util.*;

public class ListConverterTest {
  public static void main(String[] args) {
    //test getArrayListFromRacketList
//	    assert ListConverter.getArrayListFromRacketList(
//	    		 	 RacketLists.empty()
//	       						.cons(new AirportNode("BOS", 2))
//	    	        			.cons(new AirportNode("DET", 10)))
//	    						.toString().equals(
//	    	         new ArrayList<AirportNode>(){{ add(new AirportNode("BOS", 2));
//	    	       							       add(new AirportNode("DET", 10));}}
//	    	         							.toString());

    //test getRacketListFromArrayList
//    assert ListConverter.getRacketListFromArrayList(
//        new ArrayList<AirportNode>() {{
//          add(new AirportNode("BOS", 2));
//          add(new AirportNode("DET", 10));
//        }})
//        .toString().equals(
//            RacketLists.empty()
//                .cons(new AirportNode("BOS", 2))
//                .cons(new AirportNode("DET", 10))
//                .toString());


    /////////////////////////////////////////
    //   test order of items
    ///////////////////////////////////////

    RacketList<Flight> empty7 = RacketLists.empty();
    RacketList<Flight> f1 = empty7.cons(
        Flights.make("Delta 0010",
            "DEN", "LHR", UTCs.make(20, 30),
            UTCs.make(9, 45)));
    RacketList<Flight> f2 = f1.cons(
        Flights.make("Delta 4574",
            "LAX", "DEN", UTCs.make(17, 35),
            UTCs.make(20, 7)));
    RacketList<Flight> racketListOfFlights = f2.cons(
        Flights.make("Delta 0950",
            "PDX", "LAX", UTCs.make(14, 18),
            UTCs.make(16, 55)));

    ArrayList<Flight> arrayListOfFlights = new ArrayList<>();
    arrayListOfFlights.add(Flights.make("Delta 0950",
        "PDX", "LAX", UTCs.make(14, 18),
        UTCs.make(16, 55)));
    arrayListOfFlights.add(Flights.make("Delta 4574",
        "LAX", "DEN", UTCs.make(17, 35),
        UTCs.make(20, 7)));
    arrayListOfFlights.add(Flights.make("Delta 0010",
        "DEN", "LHR", UTCs.make(20, 30),
        UTCs.make(9, 45)));

    RacketList<Flight> racketListFromArrayList = ListConverter.getRacketListFromArrayList(arrayListOfFlights);

    assert RacketLists.isFlightRacketListEqual(racketListOfFlights,
        racketListFromArrayList) == true;

    System.out.println("[ListConverterTest] All unit tests passed.");
  }
}
