import java.util.*;

/**
 * A RacketLists is a Factory for making Flights.
 */
public class RacketLists {

  // making constructor private so that programmers are prohibited from
  // creating un-necessary instances of this class which has all static
  // methods.
  private RacketLists() {
  }

  /**
   * GIVEN: no input required
   * RETURNS: an instance of RacketList which represnts an empty list
   * EXAMPLES:
   * <p>
   * RacketList<Flight> empty = RacketLists.empty();
   * empty.isEmpty() => true;
   * <p>
   * PATTERN: Static factory method pattern
   *
   * @param <E>
   * @return RacketList<E>
   */
  public static <E> RacketList<E> empty() {
    return RacketListImpl.makeRacketList();
  }


  /**
   * GIVEN: two racket lists which contain objects of type Flight
   * RETURNS: true if both lists are equal, i.e. they have same objects and in
   * same order
   * EXAMPLE:
   * <p>
   * RacketList<Flight> empty = RacketLists.empty();
   * RacketList<Flight> racketList = empty.cons(
   * Flights.make("Delta 1234", "BOS", "LGA", UTCs.make( 5, 24), UTCs.make(6,
   * 58)));
   * <p>
   * RacketList<Flight> empty1 = RacketLists.empty();
   * RacketList<Flight> EQUAL_LIST = empty1.cons(Flights.make("Delta 1234",
   * "BOS", "LGA", UTCs.make( 5, 24),
   * UTCs.make(6, 58)));
   * <p>
   * RacketLists.isFlightRacketListEqual(racketList, EQUAL_LIST) => true
   * <p>
   * HALTING MEASURE: length of list1 or list2
   * DESIGN STRATEGY: Recurse over both lists and check corresponding items
   *
   * @param list1
   * @param list2
   * @return boolean
   */
  public static boolean isFlightRacketListEqual(RacketList<Flight> list1,
                                                RacketList<Flight>
                                                    list2) {
    if (list1.isEmpty() && !list2.isEmpty()) {
      return false;
    }
    if (!list1.isEmpty() && list2.isEmpty()) {
      return false;
    }
    if (list1.isEmpty() && list2.isEmpty()) {
      return true;
    }

    // check element of list with corresponding element in other list
    if (!list1.first().isEqual(list2.first())) {
      return false;
    } else {
      // all elements are equal till here, continue recursively.
      return isFlightRacketListEqual(list1.rest(), list2.rest());
    }
  }

}
