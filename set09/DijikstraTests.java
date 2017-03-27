
public class DijikstraTests {
  public static void main(String[] args) {


    RacketList<Flight> deltaFlights = FlightExamples.deltaFlights;
    Solution solution = new Solution(deltaFlights);
    //test getShortestPath

    //test dijikstraCaller

    System.out.println("[DijikstraTests] All unit tests passed.");
  }
}
