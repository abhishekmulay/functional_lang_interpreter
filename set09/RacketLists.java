import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek Mulay on 3/20/17.
 */
public class RacketLists<E> implements RacketList{

    private  List<Object> dataList = new ArrayList<Object>();
    // making constructor private so that only way to create a racket list is empty() and cons()
    private RacketLists(){}

    // empty flight list is a null object
    public static <E> RacketList<E> empty () {
        return new RacketLists<E>();
    }

    @Override
    public boolean isEmpty() {
        return this.dataList != null && dataList.size() > 0;
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
        this.dataList.add(x);
        return this;
    }

    @Override
    public String toString() {
        String str = "";
        for(Object o : this.dataList) {
            str += o.toString() + "\n";
        }
        return "RacketList: {\n" + str + "}";
    }
}
