package poids;

import outils.IUnite;

public class Tonne implements IUnite {

    @Override
    public String toString() {
        return "Tonne";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*1000000;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/1000000;
    }
}
