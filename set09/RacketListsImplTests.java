import java.util.Arrays;

public class RacketListsImplTests {
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
	           == new AirportNode("BOS", 8);
		
		//test rest
        RacketList<AirportNode> emtpyList = RacketLists.empty();
        assert RacketLists.empty().cons(new AirportNode("BOS", 8))
	       						   .cons(new AirportNode("LAX", 5))
	      						   .cons(new AirportNode("MSP", 10))
	      						   .rest()
               ==
            		   RacketLists.empty().cons(new AirportNode("LAX", 5))
	       						   			  .cons(new AirportNode("MSP", 10));
		
		//test cons and toString
		assert RacketLists.empty().cons(new AirportNode("BOS", 8))
	      						   	   .cons(new AirportNode("LAX", 5))
	                                   .toString()
	           == "RacketList: {\n BOS:8\n LAX:5\n}";
		
		//TODO: potentially delete these implementation specific tests:
		
		//test get
		assert ((RacketListImpl) RacketLists.empty()
					.cons(new AirportNode("BOS", 8))
	       			.cons(new AirportNode("LAX", 5))
	       			.cons(new AirportNode("MSP", 10)))
	                .get(2)
               == new AirportNode("LAX", 5);
		
		//test getDataList
		assert ((RacketListImpl) RacketLists.empty()
    	       	.cons(new AirportNode("BOS", 8))
    	         	.cons(new AirportNode("LAX", 5)))
    	         	.getDataList()
				==
			    Arrays.asList(new AirportNode("BOS", 8),
    			  	  		  new AirportNode("LAX", 5));
		
		//test size
		assert ((RacketListImpl) RacketLists.empty()).size() == 0;
	      
	    assert ((RacketListImpl) RacketLists.empty()
	    		.cons(new AirportNode("BOS", 8))
	     		.cons(new AirportNode("LAX", 5)))
	     		.size() 
	     		== 2;
	    
	    System.out.println("All unit tests of RacketListsImplTests passed");
	}
}
