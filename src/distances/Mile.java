package distances;

import outils.IUnite;

/**
 * Classe gérant les miles
 */
public class Mile implements IUnite {
    @Override
    public String toString() {
        return "Miles";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*1609.344f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*0.000621371f;
    }
}
