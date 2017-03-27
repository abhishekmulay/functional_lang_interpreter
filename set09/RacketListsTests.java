import java.util.Arrays;

public class RacketListsTests {
  public static void main(String[] args) {

    ////////////////////////////////////////////////////////////////////////////
    //    test isEmpty
    ////////////////////////////////////////////////////////////////////////////
    RacketList<Object> emptyRacketList = RacketLists.empty();
    assert emptyRacketList.isEmpty() : "should be empty";

    assert !RacketLists.empty().cons(
        new AirportNode("BOS", 8)).isEmpty() : "List is not empty";

    ////////////////////////////////////////////////////////////////////////////
    //    test cons
    ////////////////////////////////////////////////////////////////////////////
    assert RacketLists.empty().cons("A").cons("B").cons("C").first().equals
        ("C") :
        "cons() should add element 'C' at start of list";

    ////////////////////////////////////////////////////////////////////////////
    // test rest
    ////////////////////////////////////////////////////////////////////////////
    RacketList<Flight> empty3 = RacketLists.empty();
    RacketList<Flight>list = empty3.cons(
        Flights.make("Delta 1234", "BOS", "LGA",
        UTCs.make(5, 24), UTCs.make(6, 58)))
        .cons(Flights.make("Delta 0689", "LAX", "PDX",
            UTCs.make(13, 0), UTCs.make(15, 35)));

    RacketList<Flight> empty4 = RacketLists.empty();
    RacketList<Flight> restList = empty4.cons(Flights.make
        ("Delta 1234", "BOS", "LGA",
            UTCs.make(5, 24), UTCs.make(6, 58)));

    assert RacketLists.isFlightRacketListEqual(list.rest(), restList) :
        "should remove first element from list";


    ////////////////////////////////////////////////////////////////////////////
    // test immutability
    ////////////////////////////////////////////////////////////////////////////
    RacketList<Flight> empty5 = RacketLists.empty();
    RacketList<Flight> flightList5 = empty5.cons(Flights.make("Delta 1234",
        "BOS", "LGA",
        UTCs.make(5, 24), UTCs.make(6, 58)))
        .cons(Flights.make("Delta 0689", "LAX", "PDX",
            UTCs.make(13, 0), UTCs.make(15, 35)));

    RacketList<Flight> empty6 = RacketLists.empty();
    RacketList<Flight> restList5 = empty6.cons(Flights.make
        ("Delta 1234", "BOS", "LGA",
            UTCs.make(5, 24), UTCs.make(6, 58)));

    assert RacketLists.isFlightRacketListEqual(flightList5.rest(), restList5);
    assert flightList5.first().isEqual(Flights.
        make("Delta 0689", "LAX", "PDX",
        UTCs.make(13, 0), UTCs.make(15, 35)));


    RacketList<String> strList = RacketLists.empty();
    RacketList<String> a = strList.cons("A");
    a.cons("B");
    assert a.first().equals("A") : "List is immutable, B should not be added";

    ////////////////////////////////////////////////////////////////////////////
    // test racketList equality
    ////////////////////////////////////////////////////////////////////////////

    RacketList<Flight> empty = RacketLists.empty();
    RacketList<Flight> racketList = empty.
        cons(Flights.make("Delta 1234", "BOS", "LGA",
            UTCs.make(5, 24), UTCs.make(6, 58)));

    RacketList<Flight> empty1 = RacketLists.empty();
    RacketList<Flight> EQUAL_LIST = empty1.
        cons(Flights.make("Delta 1234", "BOS", "LGA",
            UTCs.make(5, 24), UTCs.make(6, 58)));

    RacketList<Flight> empty2 = RacketLists.empty();
    RacketList<Flight> UNEQUAL_LIST = empty1.cons(Flights.make("Delta 1234",
        "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58))).cons(Flights.make("Delta 1234",
        "BOS", "LGA", UTCs.make(5, 24),
        UTCs.make(6, 58)));

    assert RacketLists
        .isFlightRacketListEqual(racketList, EQUAL_LIST) : "should be equal";
    assert !RacketLists
        .isFlightRacketListEqual(racketList, UNEQUAL_LIST) : "should NOT be " +
        "equal";

    System.out.println("[RacketListsTests] All unit tests passed.");
  }
}
