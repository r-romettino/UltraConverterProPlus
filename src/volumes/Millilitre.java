package volumes;

import outils.IUnite;

public class Millilitre implements IUnite {

    public String toString() {
        return "Millilitre";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/1000;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*1000;
    }
}
