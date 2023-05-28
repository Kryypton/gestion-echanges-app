import java.io.Serializable;
import java.util.ArrayList;

public class Association implements Serializable {
    private Teenager teenager1;
    private Teenager teenager2;

    public Association(Teenager teenager1, Teenager teenager2) {
        this.teenager1 = teenager1;
        this.teenager2 =teenager2;
    }

    public Teenager getTeenager1() {
        return teenager1;
    }

    public Teenager getTeenager2() {
        return teenager2 ;
    }
}