import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/23/17.
 */

public class PriorityAirportQueue {
    List<AirportNode> dataList = new ArrayList<>();

    PriorityAirportQueue() {
    }

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

    public void enqueue(AirportNode ap) {
        this.dataList.add(ap);
        this.dataList.sort(comparator);
    }

    public AirportNode dequeue() {
        return this.dataList.remove(0);
    }

    public boolean isEmpty(){
        return this.dataList.isEmpty();
    }

    @Override
    public String toString() {
        return "PQ { " + dataList + '}';
    }

    public void changePriority(String neighbour, int alt) {
        for (AirportNode item : this.dataList){
            if (item.getName().equals(neighbour)){
                item.setPriority(alt);
            }
        }
        this.dataList.sort(comparator);
    }

    public static void main(String[] args) {
        AirportNode ap1 = new AirportNode("LGA", 10);
        AirportNode ap2 = new AirportNode("MSP", 4);
        AirportNode ap3 = new AirportNode("PDX", 1);
        AirportNode ap4 = new AirportNode("DEN", 2);
        AirportNode ap5 = new AirportNode("LAX", 9);

        PriorityAirportQueue queue = new PriorityAirportQueue();
        queue.enqueue(ap1);
        queue.enqueue(ap2);
        queue.enqueue(ap3);
        queue.enqueue(ap4);
        queue.enqueue(ap5);
        System.out.println(queue);
        queue.changePriority("MSP", 0);
        System.out.println(queue);
    }
}