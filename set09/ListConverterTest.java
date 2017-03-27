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
    assert ListConverter.getRacketListFromArrayList(
        new ArrayList<AirportNode>() {{
          add(new AirportNode("BOS", 2));
          add(new AirportNode("DET", 10));
        }})
        .toString().equals(
            RacketLists.empty()
                .cons(new AirportNode("BOS", 2))
                .cons(new AirportNode("DET", 10))
                .toString());

    System.out.println("[ListConverterTest] All unit tests passed.");
  }
}
