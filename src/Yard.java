/**
 * Classe gérant les yard
 */
public class Yard implements IUnite{
    @Override
    public String toString() {
        return "Yard";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.9144f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*1.09361f;
    }
}
