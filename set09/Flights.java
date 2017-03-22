/**
 * Created by Abhishek on 3/20/17.
 */

public class Flights implements Flight {
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
        return this.equals(flight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flights)) return false;

        Flights f = (Flights) o;

        return this.name.equals(f.name()) && this.departs().equals(f.departs()) && this.arrives().equals(f.arrives())
                && this.departsAt().isEqual(f.departsAt()) && this.arrivesAt().isEqual(f.arrivesAt());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Flight: { " + this.name() + " | " + this.departs() + " ==> " + this.arrives() + " | departsAt: "
                + this.departsAt() + " | arrivesAt: " + this.arrivesAt() + " }";
    }

}