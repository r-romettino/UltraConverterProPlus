package distances;

import outils.IUnite;

/**
 * Classe gérant les nanomètre
 */
public class Nanometre implements IUnite {
    @Override
    public String toString() {
        return "Nanometres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.000000001f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*1000000000;
    }


}
