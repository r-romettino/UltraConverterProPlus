package poids;

import outils.IUnite;

public class Stone implements IUnite {

    @Override
    public String toString() {
        return "Stone";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/6350;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*6350;
    }
}
