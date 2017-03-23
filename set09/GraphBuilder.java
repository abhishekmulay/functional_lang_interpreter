
import java.util.*;

/**
 * Created by abhishek on 3/20/17.
 */
public class GraphBuilder {

    private Map<String, RacketList<Flight>> graph = new HashMap<String, RacketList<Flight>>();

    public Map<String, RacketList<Flight>> createGraph(RacketList flights){
        return this.createGraph(flights, flights).graph;
    }

    private GraphBuilder createGraph(RacketList flights, RacketList allFlights) {
//        System.out.println("[createGraph] flights" + flights);
        if (flights.isEmpty()) {
            System.out.println("Flights empty now " + this.graph);
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

    public RacketList<Flight> getFlightsLeavingFromAirport(String airportName, RacketList flights, RacketList allFlights) {
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
        return "GraphBuilder { \n" + this.graph.toString();
    }
}

class GraphTest {

    private static Flight flt(String s, String ap1, String ap2,  int t1, int t2) {
        UTC lv = UTCs.make(t1 / 100, t1 % 100);
        UTC ar = UTCs.make(t2 / 100, t2 % 100);
        return Flights.make(s, ap1, ap2, lv, ar);
    }

    public static void main(String[] args) {
        RacketList<Flight> fs00 = RacketLists.empty();
        RacketList<Flight> fs01
                = fs00.cons(flt("Delta 0121", "LGA", "MSP", 1100, 1409));
        RacketList<Flight> fs02
                = fs01.cons(flt("Delta 0121", "LGA", "MSP", 1100, 1409));
        RacketList<Flight> fs03
                = fs02.cons(flt("Delta 2163", "MSP", "PDX", 1500, 1902));

        RacketList<Flight> EMPTY_LIST = RacketLists.empty();
        RacketList<Flight> FLIGHTS_LEAVING_FROM_LGA =  EMPTY_LIST
                .cons(flt("Delta 0121", "LGA", "MSP", 1100, 1409))
                .cons(flt("Delta 0121", "LGA", "MSP", 1100, 1409));

        GraphBuilder g = new GraphBuilder();


        assert FLIGHTS_LEAVING_FROM_LGA.equals(g.getFlightsLeavingFromAirport("LGA", fs00, fs00));
        System.out.println("[GraphTest] All unit tests passed.");

        UTC u1 = UTCs.make(10, 20);
        RacketList<UTC> utcList = RacketLists.empty();
        System.out.println(utcList.cons(u1));
    }
}
