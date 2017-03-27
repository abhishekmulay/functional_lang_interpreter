import java.util.*;


///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

// A ListConverter is :
//
// CONSTRUCTOR TEMPLATE:
// =====================
//          new ListConverter()
//
// Interpretation:
// ===============
//   A ListConverter provides methods for converting list of type RacketList and
//   ArrayList into each other.
//

public class ListConverter {

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param racketList of items of type E
   * @param <E>        type of objects in RacketList
   *
   * RETURNS:
   * @return an ArrayList of same items
   *
   * EXAMPLE:
   * ListConverter.getArrayListFromRacketList(RacketLists.empty()
   * .cons(new AirportNode("BOS", 2))
   * .cons(new AirportNode("DET", 10)))
   * =>
   * new ArrayList<AirportNode>(){{ add(new AirportNode("BOS", 2))
   * add(new AirportNode("DET", 10))}}
   */
  public static <E> ArrayList<E> getArrayListFromRacketList(RacketList<E> racketList) {
    return getArrayListFromRacketListHelper(racketList, new ArrayList<E>());
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param racketList of items of type E
   * @param arrayList  to put items into
   * @param <E>        type of objects in RacketList and ArrayList
   *
   * RETURNS:
   * @return ArrayList of same items
   *
   * EXAMPLE:
   * ListConverter.getArrayListFromRacketList(RacketLists.empty()
   * .cons(new AirportNode("BOS", 2))
   * .cons(new AirportNode("DET", 10)),
   * new ArrayList<AirportNode>)
   * =>
   * new ArrayList<AirportNode>(){{ add(new AirportNode("BOS", 2))
   * add(new AirportNode("DET", 10))}}
   *
   * HALTING MEASURE: length of racketList
   * DESIGN STRATEGY: Recurse on racketList
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
  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param arrayList of items of type E
   * @param <E>       Type of items in ArrayList
   *
   * RETURNS:
   * @return RacketList of same items
   *
   * EXAMPLE:
   * ListConverter.getRacketListFromArrayList(new ArrayList<AirportNode>(){{
   * add(new AirportNode("BOS", 2))
   * add(new AirportNode("DET", 10))}})
   * =>
   * RacketLists.empty()
   * .cons(new AirportNode("BOS", 2))
   * .cons(new AirportNode("DET", 10))
   */
  public static <E> RacketList<E> getRacketListFromArrayList(ArrayList<E> arrayList) {
    return getRacketListFromArrayListHelper(arrayList, RacketLists.empty());
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param arrayList  of data of type E
   * @param racketList of data of type E
   * @param <E>        type of objects in arrayList and racketList
   *
   * RETURNS:
   * @return RacketList with all items from arrayList copied into that RacketList
   *
   * EXAMPLE:
   * ListConverter.getRacketListFromArrayList(new ArrayList<AirportNode>(){{
   * add(new AirportNode("BOS", 2))
   * add(new AirportNode("DET", 10))}},
   * empty)
   * =>
   * RacketLists.empty()
   * .cons(new AirportNode("BOS", 2))
   * .cons(new AirportNode("DET", 10))
   *
   * HALTING MEASURE: length of arrayList
   * DESIGN STRATEGY: Recurse on arrayList
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
  //___________________________________________________________________________



}