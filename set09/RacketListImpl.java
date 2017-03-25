import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/22/17.
 */
public class RacketListImpl<E> implements RacketList {

//    private List<Object> dataList = new ArrayList<Object>();
    private List<Object> dataList = Collections.unmodifiableList(new ArrayList<Object>());

    // making constructor private so that only way to create a racket list is empty() and cons()
    /**
     * RETURNS:
     *  @return implicit empty RacketListImpl
     * STRATEGY:
     *  none
     * EXAMPLE:
     *  RacketListImpl test = new RacketListImpl();
     */
    private RacketListImpl() {
    }

    /**
     * RETURNS:
     *  @return an empty RacketListImpl
     * STRATEGY:
     *  use simpler function
     * EXAMPLE:
     *  RacketListImpl test = RacketListImpl.makeRacketList() 
     *  => RacketListImpl.makeRacketList()
     */
    public static RacketList makeRacketList() {
        return new RacketListImpl();
    }

    /**
     * RETURNS:
     *  @return true if the list is empty, false otherwise
     * STRATEGY:
     *  use simpler function
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

    /**
     * RETURNS:
     *  @return first element of the list
     *  WHERE:
     *  the list is not empty
     * STRATEGY:
     *  use simpler function
     * EXAMPLE:
     * RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
     *  						      .cons(new AirportNode("LAX", 5))
     *  						      .cons(new AirportNode("MSP", 10))
     *  						      .first();
     *  => new AirportNode("BOS", 8)
     */
    @Override
    public Object first() {
        return this.dataList.get(0);
    }

    /**
     * RETURNS:
     *  @return all but the first element of the list
     * STRATEGY: 
     *  use simpler function
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
    public RacketList rest() {
        this.dataList.remove(0);
        return this;
    }

    /**
     * GIVEN:
     * 	@param x represents the object to add to the list
     * RETURN:
     *  @return this RacketList
     * STRATEGY:
     *  use simpler function
     * EXAMPLE:
     *  RacketListImpl.makeRacketList().cons(new AirportNode("BOS", 8))
     *  						   	   .cons(new AirportNode("LAX", 5))
     *                                 .toString()
     *  =>
     *  "RacketList: {\n BOS:8\n LAX:5\n}"
     */
    @Override
    public RacketList cons(Object x) {
    	ArrayList<Object> temp = new ArrayList<Object>();
    	temp.addAll(this.dataList);
    	temp.add(x);
    	this.dataList = temp;
    	return this;
    }

    /**
     * GIVEN:
     *  @param index represents the location in the list to return
     * RETURN:
     *  @return the element at location defined by index
     * STRATEGY:
     *  use simpler function
     * EXAMPLE:
     *  ((RacketListImpl) RacketListImpl.makeRacketList()
     *  	.cons(new AirportNode("BOS", 8))
     *  	.cons(new AirportNode("LAX", 5))
     *  	.cons(new AirportNode("MSP", 10)))
     *      .get(2)
     *  =>
     *  new AirportNode("LAX", 5)
     */
    public Object get(int index) {
        return this.dataList.get(index);
    }

    /**
     * RETURN:
     *  @return a list of all elements in the RacketList
     * STRATEGY:
     *  use private variable
     * EXAMPLE:
     *  ((RacketListImpl) RacketListImpl.makeRacketList()
     *   	       	.cons(new AirportNode("BOS", 8))
     *   	         	.cons(new AirportNode("LAX", 5)))
     *   	         	.getDataList();
     *   =>
     *   Arrays.asList(new AirportNode("BOS", 8),
     *   			  new AirportNode("LAX", 5));
     */
    public List<Object> getDataList() {
        return this.dataList;
    }

    /**
     * RETURN:
     *  @return number of elements in list
     * STRATEGY:
     *  use simpler function
     * EXAMPLE:
     *  ((RacketListImpl) RacketListImpl.makeRacketList()).size()
     *  => 0
     *  
     *   ((RacketListImpl) RacketListImpl.makeRacketList()
     *  	.cons(new AirportNode("BOS", 8))
     *  	.cons(new AirportNode("LAX", 5)))
     *  	.size()
     *  => 2
     */
    public int size() {
        return this.dataList.size();
    }

    /**
     * RETURN:
     *  @return string representation of RacketListImpl
     * STRATEGY:
     *  use simpler function
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
}