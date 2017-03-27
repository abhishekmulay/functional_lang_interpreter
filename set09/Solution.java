import java.util.*;

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
	/**
	 * GIVEN: 
	 *  @param source the name of the starting airport
	 *  @param destination the name of the destination airport
	 * RETURNS:
	 *  @return true if and only if it is possible to fly from the
	     first airport (ap1) to the second airport (ap2) using
	     only the given flights
	 * STRATEGY:
	 *  combine simpler functions
	 * EXAMPLES:
	 *  Schedules.canGetThere("06N", "JFK", FlightExamples.panAmFlights)  
	 *  =>  false
	 *  Schedules.canGetThere("JFK", "JFK", FlightExamples.panAmFlights)  
	 *  =>  true
	 *  Schedules.canGetThere("06N", "LAX", FlightExamples.deltaFlights) 
	 *  =>  false
	 *  Schedules.canGetThere("LAX", "06N", FlightExamples.deltaFlights)  
	 *  =>  false
	 *  Schedules.canGetThere("LGA", "PDX", FlightExamples.deltaFlights)  
	 *  =>  true
	 */
    public boolean canGetThere(String source, String destination) {
        if (source.equals(destination)) {return true;}

        Dijikstra dijikstra = new Dijikstra();
        ArrayList<Flight> bestItinerary = dijikstra.getShortestPath(this.graph, source, destination);
        return bestItinerary.size() > 0;
    }
   
    /**
     * GIVEN:
     *  @param source the name of the starting airport
	 *  @param destination the name of the destination airport
     * RETURNS:
     *  @return  a RacketList of flights that tells how to fly from the
     *   first airport (ap1) to the second airport (ap2) in the
     *   least possible time, using only the given flights
     * STRATEGY:
	 *  combine simpler functions
     * EXAMPLES:
     *  fastestItinerary ("JFK", "JFK", FlightExamples.panAmFlights)
     *       =>  RacketListImpl.makeRacketList()
	 *  
     *   Schedules.fastestItinerary("LGA", "PDX", FlightExamples.deltaFlights)
     *   =>
     *	RacketListImpl
	 *		.makeRacketList()
	 *		.cons(FlightImpl.makeFlight("Delta 2163", "MSP", "PDX", 
	 *							UTCs.make (15, 0), UTCs.make (19, 2)))
	 *		.cons(FlightImpl.makeFlight("Delta 0121", "LGA", "MSP",
	 *		                   UTCs.make (11, 0), UTCs.make (14, 9)))
     */
    public RacketList<Flight> fastestItinerary(String source, String destination) {
        if (source.equals(destination)) {return RacketLists.empty();}

        Dijikstra dijikstra = new Dijikstra();
        ArrayList<Flight> bestItinerary = dijikstra.getShortestPath(this.graph, source, destination);
        return ListConverter.getRacketListFromArrayList(bestItinerary);
    }
    
    /**
     * GIVEN:
     *  @param source the name of the starting airport
	 *  @param destination the name of the destination airport
     * RETURNS:
     *  @return the number of minutes it takes to fly from the first
     *	airport (ap1) to the second airport (ap2), including any
     *	layovers, by the fastest possible route that uses only
     *	the given flights
     * STRATEGY:
	 *  combine simpler functions
     * EXAMPLES:
     *	Schedules.travelTime("JFK", "JFK", FlightExamples.panAmFlights)  =>  0
     *	Schedules.travelTime("LGA", "PDX", FlightExamples.deltaFlights)  =>  482
     */
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
     * GIVEN: 
     *  @param flights a list of flights 
	 *  @param allAirports list of airport names
     * RETURNS: 
     *  @return a map where keys are names of string and its corresponding 
     *  value is list of flights leaving that airport
     * DESIGN STRATEGY: 
     *  Combine simpler functions
     * EXAMPLES:
     *  TODO
     */
    private Map<String, List<Flight>> createAirportGraph(List<Flight> flights, List<String> allAirports) {
        for (String airport : allAirports) {
            graph.put(airport, this.getFlightsLeavingFrom(airport));
        }
        return this.graph;
    }

    /**
     * GIVEN: 
     *  @param flights a list of flights
     * RETURNS: 
     *  @return a list of unique airport names
     * DESIGN STRATEGY: 
     *  Combine simpler functions
     * EXAMPLES:
     *  TODO
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
     * GIVEN: 
     *  @param airportName name of airport
     * RETURNS: 
     *  @return a list of flights leaving from that airport
     * DESIGN STRATEGY: 
     *  Combine simpler functions
     * EXAMPLES:
     *  TODO
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
     * GIVEN: 
     *  @param map a Map which contains <K, V> pairs
     * RETURNS: 
     *  no return value
     * EFFECT: 
     *  Prints the contents of map to stdout in a readable format
     * DESIGN STRATEGY: 
     *  Combine simpler functions
     * EXAMPLES:
     *  TODO 
     */
    private void prettyPrintMap(Map map) {
        String str = "================== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key).toString() + "\n";
        }
        System.out.printf(str);
    }
}