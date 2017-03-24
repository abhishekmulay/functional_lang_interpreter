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
        return "PriorityAirportQueue { " + dataList + '}';
    }
}