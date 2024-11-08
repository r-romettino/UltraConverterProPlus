package distances;

import outils.IUnite;

/**
 * Classe gérant les millimètres
 */
public class Millimetre implements IUnite {
    @Override
    public String toString() {
        return "Millimètres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance/1000;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*1000;
    }
}
