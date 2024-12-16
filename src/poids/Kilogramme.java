package poids;

import outils.IUnite;

public class Kilogramme implements IUnite {

    @Override
    public String toString() {
        return "Kilogramme";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*1000;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/1000;
    }
}
