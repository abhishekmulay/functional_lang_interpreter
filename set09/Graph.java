
import java.util.*;

/**
 * Created by abhishek on 3/20/17.
 */
public class Graph {

    private Map<String, RacketList<Flight>> graph = new HashMap<String, RacketList<Flight>>();

    public Map<String, RacketList<Flight>> createGraph(RacketList flights){
        return this.createGraph(flights, flights).graph;
    }

    private Graph createGraph(RacketList flights, RacketList allFlights) {
//        System.out.println("[createGraph] flights" + flights);
        if (flights.isEmpty()) {
            System.out.println(this.graph);
            return this;

        } else {
            Flight flight;
            String departureAirport, arrivalAirport;
            flight = (Flight) flights.first();
            departureAirport = flight.departs();
            if (!this.graph.containsKey(departureAirport)) {
                this.graph.put(departureAirport, getFlightsLeavingFromAirport(departureAirport, flights, allFlights));
            }
            return createGraph(flights.rest(), allFlights);
        }
    }

    private RacketList<Flight> getFlightsLeavingFromAirport(String airportName, RacketList flights, RacketList allFlights) {
//        System.out.println("[getFlightsLeavingFromAirport] flights: " + flights);
        if (flights.isEmpty()) {
            return RacketLists.empty();
        } else {
            Flight flight = (Flight) flights.first();
            if (flight.departs().equals(airportName))
                return this.getFlightsLeavingFromAirport(airportName, flights.rest(), allFlights).cons(flight);
            else
                return this.getFlightsLeavingFromAirport(airportName, flights.rest(), allFlights);
        }
    }

    @Override
    public String toString() {
        return "Graph { \n" + this.graph.toString();
    }
}
