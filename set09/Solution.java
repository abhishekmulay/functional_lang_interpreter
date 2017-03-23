import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.*;

//https://en.wikipedia.org/wiki/Dijkstra's_algorithm

/**
 * Created by Abhishek Mulay on 3/23/17.
 */
public class Solution {

    private Map<String, List<Flight>> table = new HashMap<String, List<Flight>>();
    private List<Flight> flights = new ArrayList<Flight>();
    private static final int INFINITY = 9999999;

    public Solution(RacketList<Flight> racketList) {
        this.flights = ListConverter.getArrayListFromRacketList(racketList);
//        System.out.println("Received flights : " + this.flights);
        this.table = createAirportGraph(this.flights, this.getAllAirports(this.flights));
//        System.out.println("Created airport table " + table);
    }

    private Map<String, List<Flight>> createAirportGraph(List<Flight> flights, List<String> allAirports) {
        for (String airport : allAirports) {
            table.put(airport, this.getFlightsLeavingFrom(airport));
        }
//        prettyPrintGraph(this.table);
        return this.table;
    }

    public ArrayList<String> getAllAirports(List<Flight> flights) {
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

    private void prettyPrintGraph(Map<String, List<Flight>> graph) {
        String str = "\t\t\t GRAPH \t\t\n";
        for (String airportName : graph.keySet()) {
            str += airportName + " : " + graph.get(airportName).toString() + "\n";
        }
        System.out.printf(str);
    }

    /////////////////////////////////////////////////////////////////////
    //                Dijikstra's Algorithm                           //
    ///////////////////////////////////////////////////////////////////

    public void dijikstraCaller(String source) {
        this.dijikstra(this.table, source);
    }

    Map<String, Integer> distanceMap = new HashMap<>();
    Map<String, ArrayList<Flight>> flightsTakenMap = new HashMap<>();
    PriorityAirportQueue vertexQueue = new PriorityAirportQueue();

    private void dijikstra(Map<String, List<Flight>> graph, String source) {

        // initialization
        for (String airportVertex : graph.keySet()) {
            if (airportVertex.equals(source)) {
                distanceMap.put(airportVertex, 0);
                vertexQueue.enqueue(new AirportNode(airportVertex, 0));
            } else {
                distanceMap.put(airportVertex, INFINITY);
                vertexQueue.enqueue(new AirportNode(airportVertex, INFINITY));
            }
            flightsTakenMap.put(airportVertex, new ArrayList<Flight>());
        }

//        while (!vertexQueue.isEmpty()) {
//
//        }

        System.out.println("queue " + vertexQueue);
        System.out.println("flightsTakenMap " + flightsTakenMap);
        System.out.println("distanceMap " + distanceMap);
    }

    /////////////////////////////////////////////////////////////////////
    //                public methods                                  //
    ///////////////////////////////////////////////////////////////////
    public boolean canGetThere(String ap1, String ap2) {
        return false;
    }

    public RacketList<Flight> fastestItinerary(String ap1, String ap2) {
        return null;
    }

    public int travelTime(String ap1, String ap2) {
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(FlightExamples.smallDeltaFlights);
        solution.dijikstraCaller("LGA");
    }
}

class AirportNode {
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
        return "AirportNode{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}


