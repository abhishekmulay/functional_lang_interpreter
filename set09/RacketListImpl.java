import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/22/17.
 */
public class RacketListImpl<E> implements RacketList {

    private List<Object> dataList = new ArrayList<Object>();

    // making constructor private so that only way to create a racket list is empty() and cons()
    private RacketListImpl() {}

    public static RacketList makeRacketList() {
        return new RacketListImpl();
    }

    @Override
    public boolean isEmpty() {
        return dataList.size() == 0;
    }

    @Override
    public Object first() {
        if (this.dataList.size() == 0) {
            throw new IllegalStateException("Called first() on an empty RacketList. " + this);
        }
        return this.dataList.get(0);
    }

    @Override
    public RacketList rest() {
        System.out.println("Items remaining: " + this.dataList.size());
        if (this.dataList.size() == 0) {
            throw new IllegalStateException("Called rest() on an empty RacketList. " + this);
        }
        this.dataList.remove(0);
        return this;
    }

    @Override
    public RacketList cons(Object x) {
        this.dataList.add(0, x);
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
