package volumes;

import outils.IUnite;

public class Litre implements IUnite {

    public String toString() {
        return "Litre";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur;
    }
}
