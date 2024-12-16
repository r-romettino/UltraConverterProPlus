package volumes;

import outils.IUnite;

public class ImperialLiquidOnce implements IUnite {

    @Override
    public String toString() {
        return "Imperial Liquid Once";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/35.195f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*35.195f;
    }
}
