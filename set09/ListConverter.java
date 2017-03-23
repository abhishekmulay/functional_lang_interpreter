import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/23/17.
 */
public class ListConverter {

    //making constructor private so that we do not make un-necessary instances of this class.
    private ListConverter() {}

    /**
     * getArrayListFromRacketList: RacketList<E> ArrayList<E> -> ArrayList<E>
     * GIVEN: a racket list of items of type E
     * RETURNS: an array list of same items
     *
     * @param racketList
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> getArrayListFromRacketList(RacketList<E> racketList) {
        return getArrayListFromRacketListHelper(racketList, new ArrayList<E>());
    }

    /**
     * getArrayListFromRacketListHelper: RacketList<E> ArrayList<E> -> ArrayList<E>
     * GIVEN: a racket list of items of type E
     * RETURNS: an array list of same items
     *
     * @param racketList
     * @param arrayList
     * @param <E>
     * @return ArrayList<E>
     */
    private static <E> ArrayList<E> getArrayListFromRacketListHelper(RacketList<E> racketList, ArrayList<E> arrayList) {
        if (racketList.isEmpty()) {
            return arrayList;
        } else {
            E item = racketList.first();
            arrayList.add(item);
            return getArrayListFromRacketListHelper(racketList.rest(), arrayList);
        }
    }

    /**
     * getRacketListFromArrayList: ArrayList<E> -> RacketList<E>
     * GIVEN: an arrayList of items of type E
     * RETURNS: a racketList of same items
     *
     * @param arrayList
     * @param <E>
     * @return RacketList<E>
     */
    public static <E> RacketList<E> getRacketListFromArrayList(ArrayList<E> arrayList) {
        return getRacketListFromArrayListHelper(arrayList, RacketLists.empty());
    }

    /**
     * getRacketListFromArrayListHelper : ArrayList<E> RacketList -> RacketList
     * GIVEN: An arrayList of data of type E and a racketList
     * RETURNS: A RacketList with all items from arrayList copied into that RacketList
     * DESIGN STRATEGY: Use template for ArrayList on arrayList
     *
     * @param arrayList
     * @param racketList
     * @param <E>
     * @return
     */
    private static <E> RacketList<E> getRacketListFromArrayListHelper(ArrayList<E> arrayList, RacketList<E> racketList) {
        if (arrayList.isEmpty()) {
            return racketList;
        } else {
            E item = arrayList.get(0);
            arrayList.remove(0);
            return getRacketListFromArrayListHelper(arrayList, racketList.cons(item));
        }
    }


    private static Flight flt(String s, String ap1, String ap2,  int t1, int t2) {
        UTC lv = UTCs.make(t1 / 100, t1 % 100);
        UTC ar = UTCs.make(t2 / 100, t2 % 100);
        return Flights.make(s, ap1, ap2, lv, ar);
    }

    public static void main(String[] args) {
        Flight TEST_FLIGHT = flt("Delta 0121", "LGA", "MSP", 1100, 1409);
        ArrayList<Flight> ARRAY_LIST_OF_FLIGHT = new ArrayList<Flight>();
        ARRAY_LIST_OF_FLIGHT.add(TEST_FLIGHT);
        RacketList<Flight> RACKET_LIST_OF_FLIGHTS =  RacketLists.empty();
        RACKET_LIST_OF_FLIGHTS.cons(TEST_FLIGHT);

//        assert ListConverter.getArrayListFromRacketList(RACKET_LIST_OF_FLIGHTS).listEquals(ARRAY_LIST_OF_FLIGHT);

    }

}
