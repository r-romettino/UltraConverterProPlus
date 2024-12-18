package poids;

import outils.IUnite;

public class Milligramme implements IUnite {

    @Override
    public String toString() {
        return "Milligramme";
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
