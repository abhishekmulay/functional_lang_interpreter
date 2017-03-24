import java.util.*;

/**
 * Created by Abhishek Mulay on 3/24/17.
 */
public class Dijikstra {

    private static final int INFINITY = 9999999;

    public ArrayList<Flight> dijikstraCaller(Map<String, List<Flight>> graph, String source, String destination) {
        ArrayList<Flight> flightsOutFromSource = (ArrayList<Flight>) graph.get(source);
        ArrayList<Flight> flightsOutFromSourceCopy = getCopy(flightsOutFromSource);
        // remove all fligths leaving from source
        graph.put(source, new ArrayList<Flight>());
        ArrayList<Flight> emptyList = new ArrayList<>();
        ArrayList<ArrayList<Flight>> bestItinerariesToDestination = new ArrayList<>();

        for (Flight f : flightsOutFromSourceCopy) {
            emptyList.add(f);
            ArrayList<Flight> singleFlightFromSource = emptyList;
            graph.put(source, singleFlightFromSource);
            bestItinerariesToDestination.add(dijikstra(graph, source, destination));
            emptyList.clear();
        }

        Collections.sort(bestItinerariesToDestination, new Comparator<ArrayList<Flight>>() {
            TravelTimeCalculator calculator = new TravelTimeCalculator();

            @Override
            public int compare(ArrayList<Flight> itinerary1, ArrayList<Flight> itinerary2) {
                if (calculator.getTravelTimeForItinerary(itinerary1) > calculator.getTravelTimeForItinerary(itinerary2)) {
                    return 1;
                } else if (calculator.getTravelTimeForItinerary(itinerary1) < calculator.getTravelTimeForItinerary(itinerary2)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        // best itinerary is at start
        return bestItinerariesToDestination.get(0);
    }

    Map<String, Integer> distanceMap = new HashMap<>();
    Map<String, ArrayList<Flight>> flightsTakenMap = new HashMap<>();
    PriorityAirportQueue vertexQueue = new PriorityAirportQueue();

    private ArrayList<Flight> dijikstra(Map<String, List<Flight>> graph, String source, String destination) {

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

//        System.out.println("Before starting Dijikstra from root=" + source);
//        System.out.println("DistanceMap:");
//        prettyPrintMap(distanceMap);
//        System.out.println("\nflightsTakenMap");
//        prettyPrintMap(flightsTakenMap);
        System.out.println("______________________________");
        while (!vertexQueue.isEmpty()) {
            // Node with the least distance will be selected first
            // u -> currentNode
            // v -> neighbour
            AirportNode currentNode = vertexQueue.dequeue();
//            System.out.println("\nCurrent: "+ currentNode.getName() +" \t | "+ vertexQueue);

            //  for each neighbor v of u: where v is still in Q.
            for (Flight flightOut : graph.get(currentNode.getName())) {
                String neighbour = flightOut.arrives();
//                System.out.println("\n\t\t==> Neighbour: " + neighbour);

                ArrayList<Flight> previouslyTakenFlights = flightsTakenMap.get(currentNode.getName());
                ArrayList<Flight> FlightsToConsider = getCopy(previouslyTakenFlights);
                FlightsToConsider.add(flightOut);

                int alt = totalTravelTime(FlightsToConsider);
                int previousDistance = distanceMap.get(neighbour);
//                System.out.println("\t\t\talt: " + alt + " previousDistance: " + previousDistance);
                // found shorter path
                if (alt < previousDistance) {
//                    System.out.println("\t\t\tFound shortest path for "+ neighbour + " previouslyTakenFlights: "+ previouslyTakenFlights + " alt: " + alt);
                    distanceMap.put(neighbour, alt);
                    flightsTakenMap.put(neighbour, FlightsToConsider);
                    vertexQueue.changePriority(neighbour, alt);
                }
//                FlightsToConsider.clear();
            }
        }
//        System.out.println("distances: ");
//        prettyPrintMap(distanceMap);
//        System.out.println("flightsTakenMap: ");
//        prettyPrintMap(flightsTakenMap);
        System.out.println("Flights to destination" + flightsTakenMap.get(destination));
        return flightsTakenMap.get(destination);
    }

    private Integer totalTravelTime(List<Flight> flights) {
        TravelTimeCalculator calculator = new TravelTimeCalculator();
        return calculator.getTravelTimeForItinerary(flights);
    }

    private <T> ArrayList<T> getCopy(ArrayList<T> list) {
        ArrayList<T> result = new ArrayList<T>();
        for (T item : list) {
            result.add(item);
        }
        return result;
    }

    public ArrayList<Flight> getShortestPath(Map<String, List<Flight>> graph, String source, String destination) {
        return this.dijikstraCaller(graph, source, destination);
    }
}
