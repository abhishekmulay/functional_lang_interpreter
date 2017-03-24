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
    private final int MINUTES_PER_DAY = 1440;

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
                distanceMap.put(airportVertex, 0); // Distance from source to source
                vertexQueue.enqueue(new AirportNode(airportVertex, 0));
            } else {
                distanceMap.put(airportVertex, INFINITY);
                vertexQueue.enqueue(new AirportNode(airportVertex, INFINITY));
            }
            flightsTakenMap.put(airportVertex, new ArrayList<Flight>());
        }

        System.out.println("queue " + vertexQueue);
        System.out.println("flightsTakenMap " + flightsTakenMap);
        System.out.println("distanceMap " + distanceMap);

        System.out.println("Starting dijikstras now...");

        while (!vertexQueue.isEmpty()) {
            // Node with the least distance will be selected first
            AirportNode currentNode = vertexQueue.dequeue();

            //  for each neighbor v of u: where v is still in Q.
            for( Flight flightOut : graph.get(currentNode.getName())){
                String neighbour = flightOut.arrives();

                int alt = distanceMap.get(currentNode) + edgeLength(currentNode, neighbour);
                alt = 0;
                int previousDistance = distanceMap.get(neighbour);

                // found shorter path
                if (alt > previousDistance ) {
                    distanceMap.put(neighbour, alt);
                    ArrayList previouslyTakenFlights = flightsTakenMap.get(neighbour);
                    previouslyTakenFlights.add(flightOut);
                    flightsTakenMap.put(neighbour, previouslyTakenFlights);
                }

            }
        }

    }

    private Integer edgeLength(AirportNode currentNode, String neighbour) {
        return 0;
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

    /////////////////////////////////////////////////////////////////////
    //                Travel Time                                     //
    ///////////////////////////////////////////////////////////////////



    public static void main(String[] args) {
        Solution solution = new Solution(FlightExamples.smallDeltaFlights);
//        solution.dijikstraCaller("LGA");

        Flight leg1 = Flights.make("Delta 2734", "BOS", "DTW", UTCImpl.makeUTC(10, 35), UTCImpl.makeUTC(12, 59));
        Flight leg2 = Flights.make("Delta 1511", "DTW", "DEN", UTCImpl.makeUTC(13, 30), UTCImpl.makeUTC(16, 51));
        Flight leg3 = Flights.make("Delta 0010", "DEN", "LHR", UTCImpl.makeUTC(20, 30), UTCImpl.makeUTC(9, 45));

        ArrayList<Flight> TRIP1 = new ArrayList<>();
        TRIP1.add(leg1);
        TRIP1.add(leg2);
        TRIP1.add(leg3);

    }
}




////////////////////////////////////////////////////
