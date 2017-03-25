import java.util.*;

import static com.sun.tools.internal.xjc.reader.Ring.add;

//https://en.wikipedia.org/wiki/Dijkstra's_algorithm

/**
 * Created by Abhishek Mulay on 3/23/17.
 */
public class Solution {

    private Map<String, List<Flight>> graph = new HashMap<String, List<Flight>>();
    private List<Flight> flights = new ArrayList<Flight>();

    public Solution(RacketList<Flight> racketList) {
        this.flights = ListConverter.getArrayListFromRacketList(racketList);
        this.graph = createAirportGraph(this.flights, this.getAllAirports(this.flights));
    }

    /////////////////////////////////////////////////////////////////////
    //                public methods                                  //
    ///////////////////////////////////////////////////////////////////
    public boolean canGetThere(String source, String destination) {
        if (source.equals(destination)) {return true;}

        Dijikstra dijikstra = new Dijikstra();
        ArrayList<Flight> bestItinerary = dijikstra.getShortestPath(this.graph, source, destination);
        return bestItinerary.size() > 0;
    }

    public RacketList<Flight> fastestItinerary(String source, String destination) {
        if (source.equals(destination)) {return RacketLists.empty();}

        Dijikstra dijikstra = new Dijikstra();
        ArrayList<Flight> bestItinerary = dijikstra.getShortestPath(this.graph, source, destination);
        return ListConverter.getRacketListFromArrayList(bestItinerary);
    }

    public int travelTime(String source, String destination) {
        if (source.equals(destination)) {return 0;}

        Dijikstra dijikstra = new Dijikstra();
        ArrayList<Flight> bestItinerary = dijikstra.getShortestPath(this.graph, source, destination);
        TravelTimeCalculator calculator = new TravelTimeCalculator();
        return calculator.getTravelTimeForItinerary(bestItinerary);
    }


    /////////////////////////////////////////////////////////////////////
    //                helper methods                                  //
    ///////////////////////////////////////////////////////////////////

    /**
     * createAirportGraph: List<Flight> List<String> -> Map<String, List<Flight>>
     * GIVEN: a list of flights and a list of airport names
     * RETURNS: a map where keys are names of string and its corresponding value is list of flights leaving that airport
     * DESIGN STRATEGY: Combine simpler functions
     * @param flights
     * @param allAirports
     * @return
     */
    private Map<String, List<Flight>> createAirportGraph(List<Flight> flights, List<String> allAirports) {
        for (String airport : allAirports) {
            graph.put(airport, this.getFlightsLeavingFrom(airport));
        }
        return this.graph;
    }

    /**
     * getAllAirports: List<Flight> -> ArrayList<String>
     * GIVEN: a list of flights
     * RETURNS: a list of unique airport names
     * DESIGN STRATEGY: Combine simpler functions
     * @param flights
     * @return
     */
    private ArrayList<String> getAllAirports(List<Flight> flights) {
        Set<String> airportSet = new TreeSet();
        int totalFlights = flights.size();
        Flight flight;
        for (int index = 0; index < totalFlights; index++) {
            flight = flights.get(index);
            airportSet.add(flight.departs());
            airportSet.add(flight.arrives());
        }
        return new ArrayList<String>(airportSet);
    }

    /**
     * getFlightsLeavingFrom: String -> List<Flight>
     * GIVEN: name of airport
     * RETURNS: a list of flights leaving from that airport
     * DESIGN STRATEGY: Combine simpler functions
     * @param airportName
     * @return
     */
    private List<Flight> getFlightsLeavingFrom(String airportName) {
        List<Flight> flightsLeavingFromAirport = new ArrayList<Flight>();
        for (Flight f : this.flights) {
            if (f.departs().equals(airportName)) {
                flightsLeavingFromAirport.add(f);
            }
        }
        return flightsLeavingFromAirport;
    }

    /**
     * prettyPrintMap : Map -> Void
     * GIVEN: a Map which contains <K, V> pairs
     * RETURNS: no return value
     * EFFECT: Prints the contents of map to stdout in a readable format
     * DESIGN STRATEGY: Combine simpler functions
     * @param map
     */
    private void prettyPrintMap(Map map) {
        String str = "================== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key).toString() + "\n";
        }
        System.out.printf(str);
    }


    /////////////////////////////////////////////////////////////////////
    //                For testing, remove before submission           //
    ///////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
//        Solution solution = new Solution(FlightExamples.smallDeltaFlights);
        Solution solution = new Solution(FlightExamples.deltaFlights);
        System.out.printf(" " + solution.fastestItinerary("PDX", "LHR"));
    }
}