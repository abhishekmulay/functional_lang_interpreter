import java.util.Arrays;

public class RacketListsTests {
	public static void main (String[] args) {
		//test isEmpty

		
		assert RacketListImpl.makeRacketList().isEmpty() == true;

		assert RacketLists.empty().cons(
				new AirportNode("BOS", 8)).isEmpty() == false;
		
		//test first
		assert RacketLists.empty().cons(new AirportNode("BOS", 8))
	       						   .cons(new AirportNode("LAX", 5))
	      						   .cons(new AirportNode("MSP", 10))
	       						   .first()
	       						   .toString()
	       	   .equals(" BOS:8");
		
		//test rest
        assert RacketLists.empty()
        				  .cons(new AirportNode("BOS", 8))
        				  .cons(new AirportNode("LAX", 5))
        				  .cons(new AirportNode("MSP", 10))
        				  .rest()
        				  .toString()
        				  .equals
        	  ( "RacketList: {\n LAX:5\n MSP:10\n}");
		
		//test cons and toString
		assert RacketLists.empty().cons(new AirportNode("BOS", 8))
	      						   	   .cons(new AirportNode("LAX", 5))
	                                   .toString().equals(
	           "RacketList: {\n BOS:8\n LAX:5\n}");


    RacketList<Flight> empty = RacketLists.empty();
    RacketList<Flight> racketList = empty.cons(Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24),
        UTCs.make(6, 58)));

    RacketList<Flight> empty1 = RacketLists.empty();
    RacketList<Flight> EQUAL_LIST = empty1.cons(Flights.make("Delta 1234",
        "BOS", "LGA", UTCs.make( 5, 24),
        UTCs.make(6, 58)));

    RacketList<Flight> empty2 = RacketLists.empty();
    RacketList<Flight> UNEQUAL_LIST = empty1.cons(Flights.make("Delta 1234",
        "BOS", "LGA", UTCs.make( 5, 24),
        UTCs.make(6, 58))).cons(Flights.make("Delta 1234",
        "BOS", "LGA", UTCs.make( 5, 24),
        UTCs.make(6, 58)));


    System.out.println("racketList.equals(EQUAL_LIST) " + RacketLists
        .isFlightRacketListEqual(racketList, EQUAL_LIST));

    System.out.println("racketList.equals(UNEQUAL_LIST): " + racketList.equals(UNEQUAL_LIST));
    assert RacketLists
        .isFlightRacketListEqual(racketList, EQUAL_LIST) : "should be equal";
    assert !RacketLists
        .isFlightRacketListEqual(racketList, UNEQUAL_LIST) : "should NOT be " +
        "equal";

    System.out.println("All unit tests of RacketListsTests passed");
	}
}
