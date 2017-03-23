/**
 * Created by Abhishek on 3/20/17.
 */

public class Flights {
    private String name;
    private String source;
    private String destination;
    private UTC departureTime;
    private UTC arrivalTime;

    // making constructor private so that make is the only way to create a Flight instance
    private Flights(String name, String source, String destination, UTC departureTime, UTC arrivalTime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static Flight make(String name, String source, String destination, UTC departureTime, UTC arrivalTime) {
        return FlightImpl.makeFlight(name, source, destination, departureTime, arrivalTime);
    }
}