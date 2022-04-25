import java.util.ArrayList;

public class Weight {

    ArrayList<Double> atrs;

    public Weight(ArrayList<Double> atrs) {
        this.atrs = atrs;
    }

    @Override
    public String toString() {
        return "" + atrs;
    }

}
