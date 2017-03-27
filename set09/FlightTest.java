
public class FlightTest {
	public static void main (String[] args){
		
		//test name
		assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
				UTCs.make(6, 58)).name().equals("Delta 1234");
	    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0), 
	    		UTCs.make(15, 35)).name().equals("Delta 0689");	 
	    
	    //test departs
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		UTCs.make(6, 58)).departs().equals("BOS");
		assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0), 
				UTCs.make(15, 35)).departs().equals("LAX");
		
		//test arrives
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		UTCs.make(6, 58)).arrives().equals("LGA");
		assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0), 
				UTCs.make(15, 35)).arrives().equals("PDX");
		
		//test departsAt
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		UTCs.make(6, 58)).departsAt().equals(UTCs.make( 5, 24));
		assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0), 
				UTCs.make(15, 35)).departsAt().equals(UTCs.make(13, 0));
		
		//test arrivesAt
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		UTCs.make(6, 58)).arrivesAt().equals(UTCs.make( 6, 58));
		assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make(13, 0), 
				UTCs.make(15, 35)).arrivesAt().equals(UTCs.make(15, 35));
		
		//test isEqual
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		   UTCs.make(6, 58)).isEqual(Flights.make("Delta 0689", "LAX", "PDX", 
	    	         							  UTCs.make(13, 0), UTCs.make(15, 35)))
	    	   == false;
	    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0), 
	    	         UTCs.make(15, 35)).isEqual(Flights.make("Delta 0689", "LAX", "PDX", 
	    	         							  UTCs.make(13, 0), UTCs.make(15, 35)))
	    	   == true;
	    
	    //test equals
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		   UTCs.make(6, 58)).equals(Flights.make("Delta 0689", "LAX", "PDX", 
	    	         							  UTCs.make(13, 0), UTCs.make(15, 35)))
	    	   == false;
	    
	    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0), 
   	         UTCs.make(15, 35)).equals(Flights.make("Delta 0689", "LAX", "PDX", 
   	         							  UTCs.make(13, 0), UTCs.make(15, 35)));
	    	     
	    	     
		assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
			         UTCs.make(6, 58)).equals(Flights.make("Delta 1234", "BOS", "LGA",
			    	          UTCs.make( 5, 24), UTCs.make(6, 58)))
				== true;
		
		//test hashCode
		assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
		         UTCs.make(6, 58)).hashCode() == -235678037;
		assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0), 
   	         UTCs.make(15, 35)).hashCode() == 1996434897;
		
		//test toString
	    assert Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), 
	    		UTCs.make(6, 58)).toString().equals(
	    		"Flight: { Delta 1234 | BOS ==> LGA | departsAt: 05:24 | arrivesAt: 06:58 }");
	    		    
	    assert Flights.make("Delta 0689", "LAX", "PDX", UTCs.make( 13, 0), 
	    	    UTCs.make(15, 35)).toString().equals(
	    		"Flight: { Delta 0689 | LAX ==> PDX | departsAt: 13:00 | arrivesAt: 15:35 }");
		
		System.out.println("All unit tests of FlightTest passed");
	}
}
