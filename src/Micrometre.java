public class Micrometre implements IUnite{
    @Override
    public String toString() {
        return "Micrometres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.000001f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*1000000;
    }

}
