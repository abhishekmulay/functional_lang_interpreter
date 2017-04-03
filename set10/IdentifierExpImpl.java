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

    // Returns: the name of this IdentifierExp
    @Override
    public String name() {
        return this.identifier;
    }

    // Returns: true because this is an IdentifierExp
    @Override
    public boolean isIdentifier() {
        return true;
    }

    // Returns: this as this is already an IdentifierExp
    @Override
    public IdentifierExp asIdentifier() {
        return this;
    }

    // Given: env representing the current environment of this call
    // Returns: the result of this call as an ExpVal within the provided environment
    @Override
    public ExpVal value(Map<String, ExpVal> env) {
        if (env!=null && env.containsKey(this.identifier)) {
            return env.get(this.identifier);
        } else{
            throw new IllegalArgumentException();
        }
    }

    // Returns: the representation of this object as a string
    @Override
    public String toString() {
        return "IdentifierExpImpl{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}
