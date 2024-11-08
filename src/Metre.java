/**
 * Classe gérant les mètres
 * Système internationnal des Distances
 */
public class Metre implements IUnite {

    @Override
    public String toString() {
        return "Metres";
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
