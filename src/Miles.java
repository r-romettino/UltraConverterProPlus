public class Miles implements IUnite {
    @Override
    public float convertToIS(float distance) {
        return (distance*1.609f)*1000;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*0.000621371f;
    }
}
