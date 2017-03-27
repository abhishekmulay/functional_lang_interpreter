import java.util.*;

public class Benchmark {
	public static long benchmark0(int n){
		long beforeRun = System.currentTimeMillis();
		RacketList<Flight> flights = stressTest(n);
		ArrayList<String> airports = ListConverter.getArrayListFromRacketList(allAirports(flights));
		airports.forEach((ap1) -> {
			airports.forEach((ap2) ->{
				System.out.print("canGetThere("+ap1+", "+ap2+", "+flights+")");
				Boolean reachable = Schedules.canGetThere(ap1, ap2, flights);
				System.out.println(" = "+reachable);
				if(reachable){
					System.out.print("travelTime("+ap1+", "+ap2+", "+flights+")");
					int travelTime = Schedules.travelTime(ap1, ap2, flights);
					System.out.println(" = "+travelTime);
				}
			});
		});
		return System.currentTimeMillis() - beforeRun;
	}
	
	private static RacketList<Flight> stressTest(int n){
		RacketList<Flight> flightList = RacketLists.empty();
		while(n > 0){
			flightList =
					flightList
						.cons(Flights.make("NoWays "+(n+n), 
							"AP"+n, 
							"AP"+(n+1), 
							UTCs.make(
									(107 * n) % 24, 
									(223 * n) % 60), 
							UTCs.make(
									(151 * n) % 24, 
									(223 * n) % 60)))
						.cons(Flights.make("NoWays "+(n+n+1), 
								"AP"+n, 
								"AP"+(n+1), 
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
	
	private static RacketList<String> allAirports(RacketList<Flight> flights){
		ArrayList<String> airports = new ArrayList<String>();
		ListConverter.getArrayListFromRacketList(flights).forEach((f) ->{
			if(!airports.contains(f.arrives())){
				airports.add(f.arrives());
			}
			if(!airports.contains(f.departs())){
				airports.add(f.departs());
			}
		});
		return ListConverter.getRacketListFromArrayList(airports);
	}
	
	 public static void main(String[] args) {
		 //equivalent to (make-stress-test0 3)
		 //System.out.println(stressTest(3)); 
		 //equivalent to (all-airports (make-stress-test0 3))...just a different order
		 //System.out.println(allAirports(stressTest(3)));
		 //equivalent to benchmark0, it calls canGetThere, and travelTime (when valid) on every airport combination
		 System.out.println(benchmark0(3));
   } 
}