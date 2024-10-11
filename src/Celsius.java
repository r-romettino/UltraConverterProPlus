public class Celsius implements IUnite{

    @Override
    public String toString() {
        return "Celsius";
    }

    @Override
    public float convertToIS(float distance) {
        return distance;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance;
    }
}
