package poids;

import outils.IUnite;

public class Gramme implements IUnite {

    @Override
    public String toString() {
        return "Gramme";
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
