import java.util.Arrays;

public class RacketListsImplTests {
	public static void main (String[] args) {
		//test isEmpty
		assert RacketListImpl.makeRacketList().isEmpty() == true;

		assert RacketListImpl.makeRacketList().cons(
				new AirportNode("BOS", 8)).isEmpty() == false;
		
		//test first
		assert RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
	       						   .cons(new AirportNode("LAX", 5))
	      						   .cons(new AirportNode("MSP", 10))
	       						   .first()
	           == new AirportNode("BOS", 8);
		
		//test rest
        RacketList<AirportNode> emtpyList = RacketLists.empty();
        assert emtpyList.cons(new AirportNode("BOS", 8))
	       						   .cons(new AirportNode("LAX", 5))
	      						   .cons(new AirportNode("MSP", 10))
	      						   .rest()
               ==
	    	   RacketListImpl.makeRacketList().cons(new AirportNode("LAX", 5))
	       						   			  .cons(new AirportNode("MSP", 10));
		
		//test cons and toString
		assert RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
	      						   	   .cons(new AirportNode("LAX", 5))
	                                   .toString()
	           == "RacketList: {\n BOS:8\n LAX:5\n}";
		
		//test get
		assert ((RacketListImpl) RacketListImpl
				    .makeRacketList()
					.cons(new AirportNode("BOS", 8))
	       			.cons(new AirportNode("LAX", 5))
	       			.cons(new AirportNode("MSP", 10)))
	                .get(2)
               == new AirportNode("LAX", 5);
		
		//test getDataList
		assert ((RacketListImpl) RacketListImpl.makeRacketList()
    	       	.cons(new AirportNode("BOS", 8))
    	         	.cons(new AirportNode("LAX", 5)))
    	         	.getDataList()
				==
			    Arrays.asList(new AirportNode("BOS", 8),
    			  	  		  new AirportNode("LAX", 5));
		
		//test size
		assert ((RacketListImpl) RacketListImpl.makeRacketList()).size() == 0;
	      
	    assert ((RacketListImpl) RacketListImpl.makeRacketList()
	    		.cons(new AirportNode("BOS", 8))
	     		.cons(new AirportNode("LAX", 5)))
	     		.size() 
	     		== 2;
	    
	    System.out.println("All unit tests of RacketListsImplTests passed");
	}
}
