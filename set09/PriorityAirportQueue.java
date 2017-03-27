import java.util.*;

public class PriorityAirportQueue {

  List<AirportNode> dataList = new ArrayList<>();
  /**
   * Sort the list of airport nodes so that the node with least priority comes first
   */
  Comparator<AirportNode> comparator = new Comparator<AirportNode>() {
    @Override
    public int compare(AirportNode ap1, AirportNode ap2) {
      if (ap1.getPriority() > ap2.getPriority()) {
        return 1;
      } else if (ap1.getPriority() < ap2.getPriority()) {
        return -1;
      } else {
        return 0;
      }
    }
  };

  //___________________________________________________________________________
  /**
   * RETURNS:
   *
   * @returns implicitly returns this PriorityAirportQueue
   * STRATEGY:
   * default constructor
   * EXAMPLE:
   * new PriorityAirportQueue() => new PriorityAirportQueue()
   */
  public PriorityAirportQueue() {
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   *
   * @param ap an AirportNode representing an element to add to the dataList
   *           RETURNS:
   * @return this PriorityAirportQueue
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * PriorityAirportQueue queue = new PriorityAirportQueue();
   * queue.enqueue(new AirportNode("LGA", 10));
   * queue.enqueue(new AirportNode("MSP", 4));
   * queue.toString() => "PQ { [ MSP:4,   LGA:10]}"
   */
  public PriorityAirportQueue enqueue(AirportNode ap) {
    this.dataList.add(ap);
    this.dataList.sort(comparator);
    return this;
  }

  //___________________________________________________________________________
  /**
   * RETURNS:
   *
   * @return next element of this PriorityAirportQueue and removes it
   * STRATEGY:
   * use simpler functions
   * WHERE:
   * Queue is not empty
   * EXAMPLE:
   * PriorityAirportQueue queue = new PriorityAirportQueue();
   * queue.enqueue(new AirportNode("LGA", 10));
   * queue.enqueue(new AirportNode("MSP", 4));
   * queue.dequeue() => new AirportNode("MSP", 4)
   */
  public AirportNode dequeue() {
    return this.dataList.remove(0);
  }

  //___________________________________________________________________________
  /**
   * RETURNS:
   *
   * @return Boolean indicating if this PriorityAirportQueue is empty
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * (new PriorityAirportQueue()).isEmpty() => true
   * <p>
   * PriorityAirportQueue queue = new PriorityAirportQueue();
   * queue.enqueue(new AirportNode("LGA", 10));
   * queue.isEmpty() => false
   */
  public boolean isEmpty() {
    return this.dataList.isEmpty();
  }

  //___________________________________________________________________________
  /**
   * RETURNS:
   *
   * @return String representation of this PriorityAirportQueue
   * STRATEGY:
   * use simpler functions
   * EXAMPLE:
   * (new PriorityAirportQueue()).toString() => "PQ { []}"
   * <p>
   * PriorityAirportQueue queue = new PriorityAirportQueue();
   * queue.enqueue(new AirportNode("LGA", 10));
   * queue.toString() => "PQ { [ LGA:10]}"
   */
  @Override
  public String toString() {
    return "PQ { " + dataList + '}';
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param neighbour representing the element to change the priority of
   * @param alt       representing the new priority of the element in the queue
   *
   * RETURNS:
   * @return this PriorityAirportQueue

   * EXAMPLE:
   * PriorityAirportQueue queue = new PriorityAirportQueue();
   * queue.enqueue(new AirportNode("LGA", 10));
   * queue.enqueue(new AirportNode("MSP", 4));
   * queue.changePriority("LGA", 2);
   * queue.toString() => "PQ { [ LGA:2,   MSP:4]}"
   */
  public PriorityAirportQueue changePriority(String neighbour, int alt) {
    for (AirportNode item : this.dataList) {
      if (item.getName().equals(neighbour)) {
        item.setPriority(alt);
      }
    }
    this.dataList.sort(comparator);
    return this;
  }
  //___________________________________________________________________________

}