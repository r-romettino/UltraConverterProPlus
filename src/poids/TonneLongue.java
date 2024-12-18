package poids;

import outils.IUnite;

public class TonneLongue implements IUnite {

    @Override
    public String toString() {
        return "Tonne longue";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*1016000;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/1016000;
    }
}
