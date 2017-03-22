import java.util.ArrayList;
import java.util.Map;

/**
 * Created by abhishek on 3/20/17.
 */

public class PS09 {

    public static void main(String[] args) {
        System.out.println("PS09 class for testing. Remove this class before final submission.");
        Graph graphMaker = new Graph();

        RacketList<Flight> deltaFlights = FlightExamples.deltaFlights;
        Map<String, RacketList<Flight>> graph = graphMaker.createGraph(deltaFlights);
        System.out.println(graph);
    }


    private static Flight flt(String s, String ap1, String ap2,
                              int t1, int t2) {
        UTC lv = UTCs.make(t1 / 100, t1 % 100);
        UTC ar = UTCs.make(t2 / 100, t2 % 100);
        return Flights.make(s, ap1, ap2, lv, ar);
    }
}
