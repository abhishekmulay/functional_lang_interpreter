
public class UTCTests {
	public static void main (String[] args){
		//test makeUTC
		assert UTCImpl.makeUTC(5, 20).toString().equals("05:20");
		
		//test hour
		assert UTCs.make(5, 20).hour() == 5;
		
		//test minute
		assert UTCs.make(5, 20).minute() == 20;
		
		//test isEqual
		assert UTCs.make(5, 20).isEqual(UTCs.make(15, 4)) == false;
		
		//test toString
		assert UTCs.make(15, 4).toString().equals("15:04");
		
		//test equals
		assert UTCs.make(15, 4).equals(UTCs.make(15, 4)) == true;
		
		//test hashCode
		assert UTCs.make(15, 4).hashCode() == 469;
		
		System.out.println("All unit tests of UTCTests passed");
	}
}
