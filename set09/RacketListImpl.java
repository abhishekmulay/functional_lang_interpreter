import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/22/17.
 */
public class RacketListImpl<E> implements RacketList {

//    private List<Object> dataList = new ArrayList<Object>();
    private List<Object> dataList = Collections.unmodifiableList(new ArrayList<Object>());

    // making constructor private so that only way to create a racket list is empty() and cons()
    private RacketListImpl() {
    }

    public static RacketList makeRacketList() {
        return new RacketListImpl();
    }

    @Override
    public boolean isEmpty() {
        return dataList.size() == 0;
    }

    @Override
    public Object first() {
        return this.dataList.get(0);
    }

    @Override
    public RacketList rest() {
        this.dataList.remove(0);
        return this;
    }

    @Override
    public RacketList cons(Object x) {
    	ArrayList<Object> temp = new ArrayList<Object>();
    	temp.addAll(this.dataList);
    	temp.add(x);
    	this.dataList = temp;
    	return this;
    }

    public Object get(int index) {
        return this.dataList.get(index);
    }

    public List<Object> getDataList() {
        return this.dataList;
    }

    public int size() {
        return this.dataList.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (Object o : this.dataList) {
            str += o.toString() + "\n";
        }
        return "RacketList: {\n" + str + "}";
    }
}


class RacketListImplTest {

    private static Flight flt(String s, String ap1, String ap2, int t1, int t2) {
        UTC lv = UTCs.make(t1 / 100, t1 % 100);
        UTC ar = UTCs.make(t2 / 100, t2 % 100);
        return Flights.make(s, ap1, ap2, lv, ar);
    }

    public static void main(String[] args) {
        Flight f1 = flt("Delta 0121", "LGA", "MSP", 1100, 1409);
        Flight f2 = flt("Delta 0121", "LGA", "MSP", 1100, 1409);

        RacketList<Flight> racketList = RacketLists.empty();
        racketList.cons(f1).cons(f2);

        System.out.printf("\nbefore rest racketList : " + racketList);
        System.out.printf("\nrest racketList : " + racketList.rest());
        System.out.printf("\nafter rest racketList : " + racketList);

        assert f1.isEqual(f1) : " should be equal";
        assert f1.isEqual(f2) : " should NOT be equal";

        System.out.println("\nAll unit tests of UTCImpl passed.");
    }
}