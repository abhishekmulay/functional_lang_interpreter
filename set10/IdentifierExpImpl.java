import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class IdentifierExpImpl extends BaseExp implements IdentifierExp {
    private String identifier;

    public IdentifierExpImpl(String id) {
        this.identifier = id;
    }

    @Override
    public String name() {
        return this.identifier;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public IdentifierExp asIdentifier() {
        return this;
    }

    @Override
    public ExpVal value(Map<String, ExpVal> env) {
//        System.out.println("[IdentifierExpImpl] value " + prettyPrintMap(env));
        if (env!=null && env.containsKey(this.identifier)) {
            return env.get(this.identifier);
        } else{
            System.out.println("Identifier not found in environment. identifier: "
                    + this.identifier + " environment: "+ env);
            return null;
        }
    }

    @Override
    public String toString() {
        return "IdentifierExpImpl{" +
                "identifier='" + identifier + '\'' +
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
