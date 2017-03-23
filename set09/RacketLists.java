import java.util.*;

/**
 * Created by Abhishek Mulay on 3/20/17.
 */
public class RacketLists {

    private RacketLists() {
    }

    // empty flight list is a null object
    public static <E> RacketList<E> empty() {
        return RacketListImpl.makeRacketList();
    }

}
