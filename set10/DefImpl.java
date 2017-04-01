import java.util.List;

/**
 * Created by Abhishek Mulay on 3/31/17.
 */
public class DefImpl implements Def {

    private String lhs;
    private Exp rhs;

    public DefImpl(String lhs, Exp rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String lhs() {
        return this.lhs;
    }

    @Override
    public Exp rhs() {
        return this.rhs;
    }

    @Override
    public boolean isPgm() {
        return false;
    }

    @Override
    public boolean isDef() {
        return true;
    }

    @Override
    public boolean isExp() {
        return false;
    }

    @Override
    public List<Def> asPgm() {
        return null;
    }

    @Override
    public Def asDef() {
        return this;
    }

    @Override
    public Exp asExp() {
        return null;
    }

    @Override
    public String toString() {
        return "DefImpl: {" +
                "lhs='" + lhs + '\'' +
                ", rhs=" + rhs +
                '}';
    }
}
