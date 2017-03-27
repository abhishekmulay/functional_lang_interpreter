import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

///////////////////////////////////////////////////////////////////////////////
//                      DATA DEFINITION                                      //
//////////////////////////////////////////////////////////////////////////////

/**
 *
 * A RacketListImpl is a:
 * CONSTRUCTOR TEMPLATE:
 * =====================
 *    new RacketListImpl()
 *
 * Interpretation:
 * ===============
 *  A RacketListImpl represents an instance of RacketList which is an immutable
 *  (and possibly empty) sequence of E values, with operations analogous to
 *  those we've been using in Racket.
 *
 */
public class RacketListImpl<E> implements RacketList<E> {

    // internal list which holds the data, this list is immutable
    private List<E> dataList =
    Collections.unmodifiableList(new ArrayList<E>());

    //__________________________________________________________________________
    /**
     * RETURNS:
     *  @return implicit empty RacketListImpl
     *
     * EXAMPLE:
     *  RacketListImpl test = new RacketListImpl();
     */
    public RacketListImpl() {}
  
    //__________________________________________________________________________
    /**
     * RETURNS:
     *  @return true iff the list is empty, false otherwise
     *
     * EXAMPLE: 
     *  RacketList.makeRacketList().isEmpty() => True
     *  
     *  RacketList.makeRacketList().cons(new AirportNode("BOS", 8)).isEmpty()
     *  => false
     */
    @Override
    public boolean isEmpty() {
        return dataList.size() == 0;
    }

    //__________________________________________________________________________
    /**
     * RETURNS:
     *  @return first element of the list
     *
     * WHERE:
     *  the list is not empty
     *
     * EXAMPLE:
     * RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
     *  						      .cons(new AirportNode("LAX", 5))
     *  						      .cons(new AirportNode("MSP", 10))
     *  						      .first();
     *  => new AirportNode("BOS", 8)
     */
    @Override
    public E first() {
        return this.dataList.get(0);
    }

    //__________________________________________________________________________
    /**
     * RETURNS:
     *  @return an immutable RacketList which contains all the elements from
     *  this list except the first element.
     *
     * WHERE:
     * the list is not empty
     *
     * EXAMPLE:
     *  RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
     *  						   	   .cons(new AirportNode("LAX", 5))
     *  						       .cons(new AirportNode("MSP", 10))
     *  						       .rest();
     *  => 
     *  RacketListImpl.makeRacketList().cons(new AirportNode("LAX", 5))
     *  						       .cons(new AirportNode("MSP", 10))
     */
    @Override
    public RacketList<E> rest() {
    	ArrayList<Object> temp = new ArrayList<Object>(this.dataList);
    	temp.remove(0);
      return new RacketListImpl(temp);
    }

    //_________________________________________________________________________
    /**
     * GIVEN:
     * 	@param x represents the object of type E to be added to the list
     *
     * RETURNS:
     *  @return new immutable RacketList with previous data and new data added
     *  to this list.
     *
     * EXAMPLE:
     *  RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
     *  						   	   .cons(new AirportNode("LAX", 5))
     *                                 .toString()
     *  =>
     *  "RacketList: {\n BOS:8\n LAX:5\n}"
     */

    @Override
    public RacketList<E> cons(E x) {
        ArrayList<E> temp = new ArrayList<E>();
        temp.addAll(this.dataList);
    	  temp.add(0, x);
    	return new RacketListImpl(temp);
    }

  //___________________________________________________________________________
    /**
     * GIVEN:
     * No implicit parameter, the method can access caller object as "this"
     *
     * RETURN:
     *  @return string representation of RacketListImpl
     *
     * EXAMPLE:
     *  ((RacketListImpl) RacketListImpl.makeRacketList()
     *  	.cons(new AirportNode("BOS", 8))
     *  	.cons(new AirportNode("LAX", 5)))
     *  	.toString()
     *  => "RacketList: {\n BOS:8\n LAX:5\n}"
     */
    @Override
    public String toString() {
        String str = "";
        for (Object o : this.dataList) {
            str += o.toString() + "\n";
        }
        return "RacketList: {\n" + str + "}";
    }

  //___________________________________________________________________________

    /**
     * This is a copy constructor
     *
     * RETURNS:
     *  @return implicit empty RacketListImpl
     *
     * EXAMPLE:
     *  RacketListImpl test = new RacketListImpl(this.dataList);
     */
    private RacketListImpl(List<E> dataList ) {
      this.dataList = dataList;
    }
  //__________________________________________________________________________

}