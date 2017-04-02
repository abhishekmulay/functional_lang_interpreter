import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class IfExpImpl extends BaseExp implements IfExp {
    private Exp testPart;
    private Exp thenPart;
    private Exp elsePart;

    public IfExpImpl(Exp testPart, Exp thenPart, Exp elsePart) {
        this.testPart = testPart;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    @Override
    public Exp testPart() {
        return this.testPart;
    }

    @Override
    public Exp thenPart() {
        return this.thenPart;
    }

    @Override
    public Exp elsePart() {
        return this.elsePart;
    }

    @Override
    public boolean isIf() {
        return true;
    }

    @Override
    public IfExp asIf() {
        return this;
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
//        System.out.println("[IfExpImpl] value " + prettyPrintMap(env));
        if (testPart.value(env).asBoolean()) {
            return thenPart.value(env);
        } else {
            return elsePart.value(env);
        }
    }

    @Override
    public String toString() {
        return "IfExpImpl{" +
                "testPart=" + testPart +
                ", thenPart=" + thenPart +
                ", elsePart=" + elsePart +
                '}';
    }

    private String prettyPrintMap(Map map) {
        if (map == null) {
            System.out.println(this.getClass().getSimpleName() + " Env is null ");
        }
        String str = "\n========ENV========== \t\t\n";
        for (Object key : map.keySet()) {
            str += key + " : " + map.get(key) + "\n";
        }
//        System.out.printf(str);
        return str;
    }
}
