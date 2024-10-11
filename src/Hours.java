public class Hours implements IUnite {
    @Override
    public String toString() {
        return "heures";
    }
    @Override
    public float convertToIS(float hours) {
        return 3600 * hours;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds / 3600;
    }
}
