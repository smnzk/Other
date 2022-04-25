import java.util.ArrayList;

public class Thing {

    double first;
    ArrayList<Double> atrs;

    String type;

    public Thing(double first, ArrayList<Double> atrs, String type) {
        this.first = first;
        this.atrs = atrs;
        this.type = type;
    }

    @Override
    public String toString() {
        return atrs + " " + type;
    }
}
