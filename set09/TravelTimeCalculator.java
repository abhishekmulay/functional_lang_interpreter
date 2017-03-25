import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/23/17.
 */
public class TravelTimeCalculator {

    private final int MINUTES_PER_DAY = 1440;

    public int getTravelTimeForItinerary(List<Flight> flights) {
        int airTime = 0;
        int layoverTime = 0;
        for (int index=0; index < flights.size(); index++) {
            Flight f = flights.get(index);
            airTime += getAirTime(f);
            if (index == flights.size() - 1 ) {
                break;
            }
            Flight nextFlight = flights.get(index+1);
            layoverTime += getLayoverTime(f, nextFlight);
        }
        return airTime + layoverTime;
    }

    private int getLayoverTime(Flight f1, Flight f2) {
        if (getMinutes(f1.arrivesAt()) > getMinutes(f2.departsAt())) {
            return overnightLayoverTime(f1, f2);
        } else {
            return sameDayLayover(f1, f2);
        }
    }

    private int sameDayLayover(Flight f1, Flight f2) {
        return getMinutes(f2.departsAt()) - getMinutes(f1.arrivesAt());
    }

    private int overnightLayoverTime(Flight f1, Flight f2) {
        return MINUTES_PER_DAY - getMinutes(f1.arrivesAt()) + getMinutes(f2.departsAt());
    }

    public int getAirTime(Flight f) {
        // Time of arrival is > departure then we crossed 12 AM
        if ( getMinutes(f.arrivesAt()) > getMinutes(f.departsAt())) {
            return getSameDayFlightAirTime(f);
        } else {
            return overnightFlightAirTime(f);
        }
    }

    private int overnightFlightAirTime(Flight f) {
        return (MINUTES_PER_DAY - getMinutes(f.departsAt())) + getMinutes(f.arrivesAt());
    }

    private int getSameDayFlightAirTime(Flight f) {
        return getMinutes(f.arrivesAt()) - getMinutes(f.departsAt());
    }
    
    public int getMinutes(UTC utc) {
        return UTCImpl.MINUTES_PER_HOUR * utc.hour() + utc.minute();
    }

    public static void main(String[] args) {
        Flight leg1 = Flights.make("Delta 2734", "BOS", "DTW", UTCImpl.makeUTC(10, 35), UTCImpl.makeUTC(12, 59));
        Flight leg2 = Flights.make("Delta 1511", "DTW", "DEN", UTCImpl.makeUTC(13, 30), UTCImpl.makeUTC(16, 51));
        Flight leg3 = Flights.make("Delta 0010", "DEN", "LHR", UTCImpl.makeUTC(20, 30), UTCImpl.makeUTC(9, 45));

        ArrayList<Flight> TRIP1 = new ArrayList<>();
        TRIP1.add(leg1);
        TRIP1.add(leg2);
        TRIP1.add(leg3);

        TravelTimeCalculator instance = new TravelTimeCalculator();
        assert instance.getTravelTimeForItinerary(TRIP1) == 1390
                : " Travel time is 1390";
    }
}
