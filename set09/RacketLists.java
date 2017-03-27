import java.util.*;
/**
 * A Flights is a Factory for making Flights, it has no public constructor
 */
public class RacketLists {

    private RacketLists() {}

    // empty flight list is a null object
    public static <E> RacketList<E> empty() {
    	return RacketListImpl.makeRacketList();
    }


}
