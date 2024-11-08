package distances;

import outils.IUnite;

/**
 * Classe gérant les mile nautique
 */
public class MileNautique implements IUnite {
    @Override
    public String toString() {
        return "Miles Nautique";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*1852;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*0.000539957f;
    }
}
