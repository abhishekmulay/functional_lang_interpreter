import java.util.*;

public class ListConverter {

    /**
     * GIVEN: 
     *  @param a RacketList of items of type E
     *  @param <E> type of objects in RacketList
     * RETURNS: 
     *  @return an ArrayList of same items
     * STRATEGY: 
     *   use private function
     * EXAMPLE:
     *   ListConverter.getArrayListFromRacketList(RacketLists.empty()
     *   													 .cons(new AirportNode("BOS", 2))
     *   													 .cons(new AirportNode("DET", 10)))
     *   =>
     *   new ArrayList<AirportNode>(){{ add(new AirportNode("BOS", 2))
     *   							   add(new AirportNode("DET", 10))}}
     */
    public static <E> ArrayList<E> getArrayListFromRacketList(RacketList<E> racketList) {
        return getArrayListFromRacketListHelper(racketList, new ArrayList<E>());
    }

    /**
     * GIVEN:
     *  @param racketList of items of type E
     *  @param arrayList to put items into
     *  @param <E> type of objects in RacketList and ArrayList
     * RETURNS:
     *  @return ArrayList of same items
     * STRATEGY:
     *  use template of RacketList on racketList
     * HAULTING MEASURE:
     *  length of racketList
     * EXAMPLE:
     *   EXAMPLE:
     *   ListConverter.getArrayListFromRacketList(RacketLists.empty()
     *   													 .cons(new AirportNode("BOS", 2))
     *   													 .cons(new AirportNode("DET", 10)),
     *   										  new ArrayList<AirportNode>)
     *   =>
     *   new ArrayList<AirportNode>(){{ add(new AirportNode("BOS", 2))
     *   							   add(new AirportNode("DET", 10))}}
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
     * GIVEN:
     *  @param arrayList of items of type E
     *  @param <E> Type of items in ArrayList
     * RETURNS:
     *  @return RacketList of same items
     * STRATEGY:
     *  use private function
     * EXAMPLE:
     *   ListConverter.getRacketListFromArrayList(new ArrayList<AirportNode>(){{ 
     *   							   add(new AirportNode("BOS", 2))
     *   							   add(new AirportNode("DET", 10))}})
     *   =>
     *   RacketLists.empty()
     *   			.cons(new AirportNode("BOS", 2))
     *   			.cons(new AirportNode("DET", 10))
     */
    public static <E> RacketList<E> getRacketListFromArrayList(ArrayList<E> arrayList) {
    	ArrayList<E> reversed = new ArrayList<E>( arrayList);
    	Collections.reverse(reversed);
        return getRacketListFromArrayListHelper(reversed, RacketLists.empty());
    }

    /**
     * getRacketListFromArrayListHelper : ArrayList<E> RacketList -> RacketList
     * GIVEN: An arrayList of data of type E and a racketList
     * RETURNS: A RacketList with all items from arrayList copied into that RacketList
     * DESIGN STRATEGY: Use template for ArrayList on arrayList
     *
     * GIVEN:
     *  @param arrayList of data of type E
     *  @param racketList of data of type E
     *  @param <E> type of objects in arrayList and racketList
     * RETURNS:
     *  @return RacketList with all items from arrayList copied into that RacketList
     * DESIGN STRATEGY: Use template for ArrayList on arrayList
     * HAULTING MEASURE: length of arrayList
     * EXAMPLE: 
     *   ListConverter.getRacketListFromArrayList(new ArrayList<AirportNode>(){{ 
     *   											add(new AirportNode("BOS", 2))
     *   							   				add(new AirportNode("DET", 10))}},
     *   										empty)
     *   =>
     *   RacketLists.empty()
     *   			.cons(new AirportNode("BOS", 2))
     *   			.cons(new AirportNode("DET", 10))
     */
    private static <E> RacketList<E> getRacketListFromArrayListHelper(ArrayList<E> arrayList, RacketList<E> racketList) {
        if (arrayList.isEmpty()) {
            return racketList;
        } else {
            // get last element
            E item = arrayList.get(arrayList.size() - 1);
            arrayList.remove(item);
            return getRacketListFromArrayListHelper(arrayList, racketList.cons(item));
        }
    }
}