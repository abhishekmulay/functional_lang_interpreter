import java.util.*;

/**
 * Created by Abhishek Mulay on 3/24/17.
 */
public class Dijikstra {

    Map<String, Integer> distanceMap = new HashMap<>();
    Map<String, ArrayList<Flight>> flightsTakenMap = new HashMap<>();
    PriorityAirportQueue vertexQueue = new PriorityAirportQueue();
    private static final int INFINITY = 9999999;

    public ArrayList<Flight> getShortestPath(Map<String, List<Flight>> graph, String source, String destination) {
        return this.dijikstraCaller(graph, source, destination);
    }

    /**
     *  Problem: When Dijikstra starts from source, we can not directly choose a flight as best from list of flights leaving from
     *  source based on its flight time, because a flight which has higher flight time starting from source might be a part of path which is
     *  shorter than path we will get with first flight as shortest traverl time among flights leaving from source.
     *
     *  Solution: What we do is, if there are x flights starting from source, we call dijikstra() x times
     *  with graph which has each one flight leaving from source, so that we get all possible shortest paths from source
     *  then finally we choose the shortest among those itineraries returned.
     */
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


    private ArrayList<Flight> dijikstra(Map<String, List<Flight>> graph, String source, String destination) {
        // initialization
        for (String airportVertex : graph.keySet()) {
            if (airportVertex.equals(source)) {
                distanceMap.put(airportVertex, 0); // Distance from source to source is 0, because we are already here.
                vertexQueue.enqueue(new AirportNode(airportVertex, 0)); // source should be first in queue.
            } else {
                distanceMap.put(airportVertex, INFINITY);
                vertexQueue.enqueue(new AirportNode(airportVertex, INFINITY));
            }
            // initialize with empty list of flights to get to corresponding airport from source
            flightsTakenMap.put(airportVertex, new ArrayList<Flight>());
        }

        while (!vertexQueue.isEmpty()) {
            // Node with the least distance will be selected first. u -> currentNode, v -> neighbour
            AirportNode currentNode = vertexQueue.dequeue();

            //  for each neighbor v of u: where v is still in Q.
            for (Flight flightOut : graph.get(currentNode.getName())) {
                String neighbour = flightOut.arrives();

                ArrayList<Flight> previouslyTakenFlights = flightsTakenMap.get(currentNode.getName());
                ArrayList<Flight> FlightsToConsider = getCopy(previouslyTakenFlights);

                // flights needed to reach this neighbour node including current flight flightOut
                FlightsToConsider.add(flightOut);

                // time it will take to reach this neighbour from source
                int alt = totalTravelTime(FlightsToConsider);

                // previous shortest path to this neighbour from start
                int previousDistance = distanceMap.get(neighbour);

                // found shorter path
                if (alt < previousDistance) {
                    // update shortest path to newly found shortest path
                    distanceMap.put(neighbour, alt);
                    // add the this flight to flights taken to reach this neighbour
                    flightsTakenMap.put(neighbour, FlightsToConsider);
                    // change priority of this node so that vertex queue is ordered with shortest path node first
                    vertexQueue.changePriority(neighbour, alt);
                }
            }
        }

        // return shortest path to get to destination from source
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

}
