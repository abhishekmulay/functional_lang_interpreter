class AirportNodetests {
	public static void main (String[] args){
		//test getName
		assert (new AirportNode("BOS", 2)).getName() == "BOS";
		assert (new AirportNode("LAX", 20)).getName() == "LAX";
		
		//test getPriority
		assert (new AirportNode("BOS", 2)).getPriority() == 2;
		assert (new AirportNode("LAX", 20)).getPriority() == 20;
		
		//test setPriority
		assert (new AirportNode("BOS", 2)).setPriority(18).getPriority() == 18;
		assert (new AirportNode("LAX", 20)).setPriority(4).getPriority() == 4;
		
		//test setName
		assert (new AirportNode("BOS", 2)).setName("DET").getName() == "DET";
		assert (new AirportNode("LAX", 20)).setName("LGA").getName() == "LGA";
		
		//test toString
		assert (new AirportNode("BOS", 2)).toString() == " BOS:2";
	    assert (new AirportNode("LAX", 20)).toString() == " LAX:20";
	    
	    System.out.println("All unit tests of AirportNode passed");
	}
}
