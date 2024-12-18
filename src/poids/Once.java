package poids;

import outils.IUnite;

public class Once implements IUnite {

    @Override
    public String toString() {
        return "Once";
    }

    @Override
    public float convertToIS(float valeur) {
        return (float) (valeur*28.35);
    }

    @Override
    public float convertFromIS(float valeur) {
        return (float) (valeur/28.35);
    }
}
