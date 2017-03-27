import java.util.*;

public class Benchmark {

  //___________________________________________________________________________
  // main method for running benchmarks
  public static void main(String[] args) {
    //run all benchmarks in a loop
    for (int i = 8; i < 101; i = i + 2) {
      System.out.println(i + "," + benchmark0(i));
    }
  }

  //___________________________________________________________________________

  /**
   * GIVEN: a non-negative integer parameter n
   * RETURNS: a list of length n^2 showing the travel time for every pair of
   *          airports in a stress test of size Theta(n)
   * @param n
   */
  public static long benchmark0(int n) {
    long beforeRun = System.currentTimeMillis();
    RacketList<Flight> flights = stressTest(n);
    ArrayList<String> airports = ListConverter
        .getArrayListFromRacketList(allAirports(flights));
    airports.forEach((ap1) -> {
      airports.forEach((ap2) -> {
        Boolean reachable = Schedules.canGetThere(ap1, ap2, flights);
        if (reachable) {
          int travelTime = Schedules.travelTime(ap1, ap2, flights);
        }
      });
    });
    return System.currentTimeMillis() - beforeRun;
  }

  //___________________________________________________________________________

  /**
   * GIVEN: an non-negative integer n
   * RETURNS: a list of 2n flights connecting n+1 airports
   * @param n
   */
  private static RacketList<Flight> stressTest(int n) {
    RacketList<Flight> flightList = RacketLists.empty();
    while (n > 0) {
      flightList =
          flightList
              .cons(Flights.make("NoWays " + (n + n),
                  "AP" + n,
                  "AP" + (n + 1),
                  UTCs.make(
                      (107 * n) % 24,
                      (223 * n) % 60),
                  UTCs.make(
                      (151 * n) % 24,
                      (223 * n) % 60)))
              .cons(Flights.make("NoWays " + (n + n + 1),
                  "AP" + n,
                  "AP" + (n + 1),
                  UTCs.make(
                      (163 * n) % 24,
                      (201 * n) % 60),
                  UTCs.make(
                      (295 * n) % 24,
                      (183 * n) % 60)));
      n--;
    }
    return flightList;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   *  a racket list of flights
   *
   * RETURNS:
   * A list of all unique airports served by some flight in the given list.
   *
   * @param flights
   */
  private static RacketList<String> allAirports(RacketList<Flight> flights) {
    ArrayList<String> airports = new ArrayList<String>();
    ListConverter.getArrayListFromRacketList(flights).forEach((f) -> {
      if (!airports.contains(f.arrives())) {
        airports.add(f.arrives());
      }
      if (!airports.contains(f.departs())) {
        airports.add(f.departs());
      }
    });
    return ListConverter.getRacketListFromArrayList(airports);
  }

 //___________________________________________________________________________
}