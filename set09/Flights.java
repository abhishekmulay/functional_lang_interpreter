/**
 * Created by Abhishek on 3/20/17.
 */

public class Flights implements Flight {
    private String name;
    private String source;
    private String destination;
    private UTC departureTime;
    private UTC arrivalTime;

    // making constructor private so that makeFlight is the only way to create a Flight instance
    private Flights(String name, String source, String destination, UTC departureTime, UTC arrivalTime) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static Flight make(String name, String source, String destination, UTC departureTime, UTC arrivalTime) {
        return new Flights(name, source, destination, departureTime, arrivalTime);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String departs() {
        return this.source;
    }

    @Override
    public String arrives() {
        return this.destination;
    }

    @Override
    public UTC departsAt() {
        return this.departureTime;
    }

    @Override
    public UTC arrivesAt() {
        return this.arrivalTime;
    }

    @Override
    public boolean isEqual(Flight flight) {
        return this.name.equals(flight.name()) &&
                this.departs().equals(flight.departs()) &&
                this.arrives().equals(flight.arrives()) &&
                this.departsAt().isEqual(flight.departsAt()) &&
                this.arrivesAt().isEqual(flight.arrivesAt());
    }

    public String toString() {
        return this.name() + " from: " + this.departs() + " to: " + this.arrives() + " departsAt: "
                + this.departsAt() + " arrivesAt: " + this.arrivesAt();
    }

    public int hashCode() {
        return toString().hashCode();
    }
}