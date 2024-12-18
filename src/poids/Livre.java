package poids;

import outils.IUnite;

public class Livre implements IUnite {

    @Override
    public String toString() {
        return "Livre";
    }

    @Override
    public float convertToIS(float valeur) {
        return (float) (valeur*453.6);
    }

    @Override
    public float convertFromIS(float valeur) {
        return (float) (valeur/453.6);
    }
}
