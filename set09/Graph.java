
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishek on 3/20/17.
 */
public class Graph {

    private Map<String, ArrayList <Flight>> graph = new HashMap<String, ArrayList <Flight>>();

    public Graph createGraph(ArrayList <Flight> flights) {
        String source ="", destination = "";

        for(Flight flight : flights) {
            source = flight.departs();
            graph.put(source, getFlightsLeavingFromAirport(source, flights));
        }

        return this;
    }

    private ArrayList <Flight> getFlightsLeavingFromAirport(String airportName, ArrayList <Flight> allFlights) {
        ArrayList <Flight>  result = null;
        for (Flight flight : allFlights) {
            if (flight.departs().equals(airportName)) {
                result.add(flight);
            }
        }
        return result;
    }
}
