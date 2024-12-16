package volumes;

import outils.IUnite;

public class AmericanLiquidOnce implements IUnite {
    public String toString() {
        return "American Liquid Once";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/33.814f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*33.814f;
    }
}
