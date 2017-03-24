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
    private Map<String, List<Flight>> createAirportGraph(List<Flight> flights, List<String> allAirports) {
        for (String airport : allAirports) {
            graph.put(airport, this.getFlightsLeavingFrom(airport));
        }
        return this.graph;
    }

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

    private List<Flight> getFlightsLeavingFrom(String airportName) {
        List<Flight> flightsLeavingFromAirport = new ArrayList<Flight>();
        for (Flight f : this.flights) {
            if (f.departs().equals(airportName)) {
                flightsLeavingFromAirport.add(f);
            }
        }
        return flightsLeavingFromAirport;
    }

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