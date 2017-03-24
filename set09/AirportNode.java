/**
 * Created by Abhishek Mulay on 3/23/17.
 */

public class AirportNode {
    private String name;
    private int priority;

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    AirportNode(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
//        return "AirportNode{" +
//                "name='" + name + '\'' +
//                ", priority=" + priority +
//                '}';

        return  " " + name + ":" + priority;

    }

}



