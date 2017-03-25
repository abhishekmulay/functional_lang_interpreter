
public class PriorityAirportQueueTests {
	public static void main (String[] args) {
		//test enqueue
	    PriorityAirportQueue queue = new PriorityAirportQueue();
	    queue.enqueue(new AirportNode("LGA", 10));
	    queue.enqueue(new AirportNode("MSP", 4));
	    assert queue.toString() ==  "PQ { [ MSP:4,   LGA:10]}";
	       
		//test dequeue
	    queue = new PriorityAirportQueue();
	    queue.enqueue(new AirportNode("LGA", 10));
	    queue.enqueue(new AirportNode("MSP", 4));
	    assert queue.dequeue() == new AirportNode("MSP", 4);
	    
		//test isEmpty
	    assert (new PriorityAirportQueue()).isEmpty() == true;

	    queue = new PriorityAirportQueue();
	    queue.enqueue(new AirportNode("LGA", 10));
	    assert queue.isEmpty() == false;
		
		//test toString
	    assert (new PriorityAirportQueue()).toString() == "PQ { []}";

	    queue = new PriorityAirportQueue();
	    queue.enqueue(new AirportNode("LGA", 10));
	    assert queue.toString() == "PQ { [ LGA:10]}";
	    
	    //test changePriority
	    queue = new PriorityAirportQueue();
	    queue.enqueue(new AirportNode("LGA", 10));
	    queue.enqueue(new AirportNode("MSP", 4));
	    queue.changePriority("LGA", 2);
	    assert queue.toString() == "PQ { [ LGA:2,   MSP:4]}";
		
	}
}
