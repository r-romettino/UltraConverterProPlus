public class Reaumur implements IUnite{
    @Override
    public String toString() {
        return "Réaumur";
    }

    @Override
    public float convertToIS(float distance) {
        return (float) (distance * 1.25);
    }

    @Override
    public float convertFromIS(float distance) {
        return (float) (distance * 0.8);
    }
}
