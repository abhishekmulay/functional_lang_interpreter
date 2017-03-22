import java.util.ArrayList;

/**
 * Created by abhishek on 3/20/17.
 */

public class PS09 {

    public static void main(String[] args) {
        System.out.println("Main class for testing");
        Graph graph = new Graph();

        RacketList<Flight> deltaFlights = FlightExamples.deltaFlights;
//        graph.createGraph(deltaFlights);

        Flight flightInstance = Flights.make("Delta 2163",
                "MSP", "PDX", UTCs.make(15, 0), UTCs.make(19, 2));

        System.out.println(flightInstance);
        System.out.println(deltaFlights);
    }


}
