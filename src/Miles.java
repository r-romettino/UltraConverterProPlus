public class Miles implements IUnite {
    @Override
    public String toString() {
        return "Miles";
    }

    @Override
    public float convertToIS(float distance) {
        return (distance*1.60934f)*1000;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*0.000621371f;
    }
}
