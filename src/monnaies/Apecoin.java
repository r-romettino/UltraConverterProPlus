package monnaies;

import outils.IUnite;

/**
 * Classe gérant les centimètres
 */
public class Apecoin implements IUnite {
    @Override
    public String toString() {
        return "Apecoin";
    }

    @Override
    public float convertToIS(float distance) {
        return distance/100;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*100;
    }
}
