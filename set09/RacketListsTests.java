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
		
	    System.out.println("All unit tests of RacketListsTests passed");
	}
}
