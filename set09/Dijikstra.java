import java.util.*;

/**
 * Created by Abhishek Mulay on 3/24/17.
 */

///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

// A Dijikstra is :
//
//  CONSTRUCTOR TEMPLATE:
//  =====================
//          new Dijikstra()
//
//  Interpretation:
//  ===============
//   A Dijikstra provides methods for calculating shortest possible path from
//  source to destination airport using given set of flights
//
// CITATION:
// ===========
// Used Dijikstra's algorithm mentioned here:
// https://en.wikipedia.org/wiki/Dijkstra's_algorithm
//
//////////////////////////////////////////////////////////////////////////////

public class Dijikstra {

  // this contains map of name of airport and time taken to get to that airport
  // from source
  Map<String, Integer> timeTakenMap = new HashMap<>();
  // this is a map of name of airport and flights taken to reach that airport
  // from start
  Map<String, ArrayList<Flight>> flightsTakenMap = new HashMap<>();
  // this is a priority queue which contains all airports and the priority
  // associated with it is the shortest time to get to that airport starting
  // from source.
  PriorityAirportQueue vertexQueue = new PriorityAirportQueue();
  // constant for infinity
  private static final int INFINITY = 9999999;

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param graph       representing airport and flights contained in map
   * @param source      representing the name of the source airport
   * @param destination representing the name of the destination airport
   *
   * WHERE:
   *        in graph, airport is treated as vertex and flights connecting
   *        vertex are edges
   * RETURNS:
   * @return best itinerary for reaching destination from source in least time
   *
   * EXAMPLE:
   * getShortestPath(new Map({"BOS" : Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make(5, 0), UTCs.make(6,0)).departs().equals("BOS")}), "BOS", "PDX")
   * => new ArrayList()
   *
   * DESIGN STRATEGY: Combine simpler functions
   */
  public ArrayList<Flight> getShortestPath(Map<String,
      List<Flight>> graph,
                                           String source,
                                           String destination) {
    return this.dijikstraCaller(graph, source, destination);
  }

  //___________________________________________________________________________
  /**
   *  Problem: When Dijikstra starts from source, we can not directly choose a
   *  flight as best from list of flights leaving from
   *  source based on its flight time, because a flight which has higher flight
   *  time starting from source might be a part of path which is
   *  shorter than path we will get with first flight as shortest traveler time
   *  among flights leaving from source.
   *
   *  Solution: What we do is, if there are x flights starting from source, we
   *  call dijikstra() x times
   *  with graph which has each one flight leaving from source, so that we get
   *  all possible shortest paths from source
   *  then finally we choose the shortest among those itineraries returned.
   */
  /**
   * GIVEN:
   * @param graph       representation of airport and flights contained in map
   * @param source      representation of source airport name
   * @param destination representation of destination airport name
   *
   * WHERE:
   * in graph, airport is treated as vertex and flights connecting vertex are
   * edges.
   *
   * RETURNS:
   * @return best itinerary for reaching destination from
   * source in least time
   *
   * EXAMPLE:
   * dijikstraCaller(new Map({"BOS" : Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make(5, 0), UTCs.make(6,0)).departs().equals("BOS")}), "BOS", "PDX")
   * => new ArrayList()
   *
   * DESIGN STRATEGY: Combine simpler functions
   */
  public ArrayList<Flight> dijikstraCaller(Map<String, List<Flight>> graph,
                                           String source, String destination) {

    // Source and Destination airports can not be reached by given flights
    if (!graph.containsKey(source) || !graph.containsKey(destination)) {
      // return empty itinerary.
      return new ArrayList<Flight>(0);
    }

    ArrayList<Flight> flightsOutFromSource =
        (ArrayList<Flight>) graph.get(source);
    ArrayList<Flight> flightsOutFromSourceCopy =
        getCopy(flightsOutFromSource);
    // remove all fligths leaving from source
    graph.put(source, new ArrayList<Flight>());
    ArrayList<Flight> emptyList = new ArrayList<>();
    ArrayList<ArrayList<Flight>> bestItinerariesToDestination =
        new ArrayList<>();

    for (Flight f : flightsOutFromSourceCopy) {
      emptyList.add(f);
      ArrayList<Flight> singleFlightFromSource = emptyList;
      graph.put(source, singleFlightFromSource);
      bestItinerariesToDestination.add(dijikstra(graph, source, destination));
      emptyList.clear();
    }

    // best itinerary is at start
    if (sortItineraries(bestItinerariesToDestination).isEmpty()) {
      return new ArrayList<Flight>(0);
    }
    return bestItinerariesToDestination.get(0);
  }

  //___________________________________________________________________________
  // sort all itineraries on travel time, the one with least travel time
  // comes first.

  /**
   * GIVEN: array list of flights
   * @param bestItinerariesToDestination
   *
   * EXAMPLE:
   * ArrayList<Flight> flightList = new ArrayList<Flight>();
   * flightList.add(Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make(5, 0), UTCs.make(6,0)).departs().equals("BOS"));
   *
   * sortItineraries(flightList) =>
   * ArrayList(Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make(5, 0), UTCs.make(6,0)).departs().equals("BOS"));
   *
   * RETURNS: same list of flights sorted by the time it takes to get to
   * destination from start of itinerary
   * @return
   */
  private ArrayList<ArrayList<Flight>>
  sortItineraries(ArrayList<ArrayList<Flight>>  bestItinerariesToDestination) {
    Collections.sort(bestItinerariesToDestination,
        new Comparator<ArrayList<Flight>>() {
          TravelTimeCalculator calculator = new TravelTimeCalculator();

          @Override
          public int
          compare(ArrayList<Flight> itinerary1, ArrayList<Flight> itinerary2) {
            if (calculator.getTravelTimeForItinerary(itinerary1)
                >
                calculator.getTravelTimeForItinerary(itinerary2)) {
              return 1;
            } else if (calculator.getTravelTimeForItinerary(itinerary1)
                <
                calculator.getTravelTimeForItinerary(itinerary2)) {
              return -1;
            } else {
              return 0;
            }
          }
        });
    return bestItinerariesToDestination;
  }

  //___________________________________________________________________________

  /**
   * GIVEN: map of airport name and flights leaving from that airport,
   *        name of start airport
   * RETURNS: no return value
   * DETAILS: initialize components required for Dijikstras algorithm
   * @param graph
   * @param source
   */
  private void initializeData(Map<String, List<Flight>> graph, String source) {
    // initialization
    for (String airportVertex : graph.keySet()) {
      if (airportVertex.equals(source)) {
        // Distance from source to source is 0, because we are already here.
        timeTakenMap.put(airportVertex, 0);
        // source should be first in queue.
        vertexQueue.enqueue(new AirportNode(airportVertex, 0));
      } else {
        timeTakenMap.put(airportVertex, INFINITY);
        vertexQueue.enqueue(new AirportNode(airportVertex, INFINITY));
      }
      // initialize with empty list of flights to get to corresponding
      // airport from source
      flightsTakenMap.put(airportVertex, new ArrayList<Flight>());
    }
  }
  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param graph       representation of airport and flights contained in map
   * @param source      representation of source airport name
   * @param destination representation of destination airport name
   *
   * RETURNS:
   * @return best itinerary for reaching destination from source in least time
   * WHERE: in graph, airport is treated as vertex and flights connecting vertex
   * are edges
   *
   * DESIGN STRATEGY: Use Dijikstras algorithm and combine simpler functions
   */
  private ArrayList<Flight> dijikstra(Map<String, List<Flight>> graph,
                                      String source, String destination) {
    initializeData(graph, source);

    while (!vertexQueue.isEmpty()) {
      // Node with the least distance will be selected first.
      // u -> currentNode, v -> neighbour
      AirportNode currentNode = vertexQueue.dequeue();

      //  for each neighbor v of u: where v is still in Q.
      for (Flight flightOut : graph.get(currentNode.getName())) {
        String neighbour = flightOut.arrives();

        ArrayList<Flight> previouslyTakenFlights =
            flightsTakenMap.get(currentNode.getName());
        ArrayList<Flight> FlightsToConsider = getCopy(previouslyTakenFlights);

        // flights needed to reach this neighbour node including current flight
        FlightsToConsider.add(flightOut);

        // time it will take to reach this neighbour from source
        int alt = totalTravelTime(FlightsToConsider);

        // previous shortest path to this neighbour from start
        int previousShortestTime = timeTakenMap.get(neighbour);

        // found shorter path
        if (alt < previousShortestTime) {
          // update shortest path to newly found shortest path
          timeTakenMap.put(neighbour, alt);
          // add the this flight to flights taken to reach this neighbour
          flightsTakenMap.put(neighbour, FlightsToConsider);
          // change priority of this node so that vertex queue is ordered with
          // shortest path node first
          vertexQueue.changePriority(neighbour, alt);
        }
      }
    }

    // return shortest path to get to destination from source
    return flightsTakenMap.get(destination);
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param flights representing an itinerary to find the travel time of
   *
   * RETURNS:
   * @return total travel time taken if traveler takes flights from given
   * list in given order
   *
   * EXAMPLE:
   * ArrayList<Flight> flightList = new ArrayList<Flight>();
   * flightList.add(Flights.make("Delta 1234", "BOS", "LGA",
   * UTCs.make(5, 0), UTCs.make(6,0)).departs().equals("BOS"));
   *
   * totalTravelTime(flightList) => 60
   * DESIGN STRATEGY: combine simpler functions
   */
  private Integer totalTravelTime(ArrayList<Flight> flights) {
    TravelTimeCalculator calculator = new TravelTimeCalculator();
    return calculator.getTravelTimeForItinerary(flights);
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param list representing any list of objects
   *
   * RETURNS:
   * @return a new list with the same content as given list
   *
   * EXAMPLE:
   * new ArrayList().getCopy(0) = new ArrayList(0);
   *
   * DESIGN STRATEGY: combine simpler functions
   */
  private <T> ArrayList<T> getCopy(ArrayList<T> list) {
    ArrayList<T> result = new ArrayList<T>();
    for (T item : list) {
      result.add(item);
    }
    return result;
  }

  //___________________________________________________________________________

  /**
   * GIVEN: an instance of Map
   * RETURNS:
   *        no return value, void.
   * EFFECT: Prints the string representation of the map instance to stdout
   * @param map
   */
  private void prettyPrintMap(Map map) {
    String str = "================== \t\t\n";
    for (Object key : map.keySet()) {
      str += key + " : " + map.get(key).toString() + "\n";
    }
    System.out.printf(str);
  }

  //___________________________________________________________________________
}
