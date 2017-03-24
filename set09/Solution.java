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
        prettyPrintMap(this.table);
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

    private void prettyPrintMap(Map map) {
        String str = "======== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key).toString() + "\n";
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

        System.out.println("Before starting Dijikstra from root=" + source);
        System.out.println("DistanceMap:");
        prettyPrintMap(distanceMap);
        System.out.println("\nflightsTakenMap");
        prettyPrintMap(flightsTakenMap);
        System.out.println("______________________________");
        while (!vertexQueue.isEmpty()) {
            // Node with the least distance will be selected first
            // u -> currentNode
            // v -> neighbour
            AirportNode currentNode = vertexQueue.dequeue();
            System.out.println("Current: "+ currentNode.getName() +" \t | "+ vertexQueue);

            //  for each neighbor v of u: where v is still in Q.
            for( Flight flightOut : graph.get(currentNode.getName())){
                String neighbour = flightOut.arrives();
                System.out.println("\t\t\t==> Neighbour: " + neighbour);

                ArrayList<Flight> previouslyTakenFlights = flightsTakenMap.get(currentNode.getName());
                previouslyTakenFlights.add(flightOut);

                int alt = totalTravelTime(previouslyTakenFlights);
                int previousDistance = distanceMap.get(neighbour);
                System.out.println("\t\t\t  alt: " + alt + " previousDistance: " + previousDistance);
                // found shorter path
                if (alt < previousDistance ) {
                    distanceMap.put(neighbour, alt);
                    flightsTakenMap.put(neighbour, previouslyTakenFlights);
                    vertexQueue.changePriority(neighbour, alt);
                }
            }
        }
        System.out.println("distances: ");
        prettyPrintMap(distanceMap);
        System.out.println("flightsTakenMap: ");
        prettyPrintMap(flightsTakenMap);
    }

    private Integer totalTravelTime(List<Flight> flights) {
        TravelTimeCalculator calculator = new TravelTimeCalculator();
        return calculator.getTravelTimeForItinerary(flights);
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
//        Solution solution = new Solution(FlightExamples.smallDeltaFlights);
//        Solution solution = new Solution(FlightExamples.deltaFlights);
        Solution solution = new Solution(FlightExamples.deltaCycle);
        solution.dijikstraCaller("BNA");
    }
}