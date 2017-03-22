import java.util.*;

/**
 * Created by Abhishek Mulay on 3/20/17.
 */
public class RacketLists<E> implements RacketList {

    private List<Object> dataList = new ArrayList<Object>();

    // making constructor private so that only way to create a racket list is empty() and cons()
    private RacketLists() {}

    // empty flight list is a null object
    public static <E> RacketList<E> empty() {
        return new RacketLists<E>();
    }

    @Override
    public boolean isEmpty() {
        return this.dataList == null || dataList.size() == 0;
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
        System.out.println("Items remaining: "+ this.dataList.size());
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

    public Object get(int index){
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
