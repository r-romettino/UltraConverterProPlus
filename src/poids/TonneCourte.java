package poids;

import outils.IUnite;

public class TonneCourte implements IUnite {

    @Override
    public String toString() {
        return "Tonne courte";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*907200;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/907200;
    }
}
