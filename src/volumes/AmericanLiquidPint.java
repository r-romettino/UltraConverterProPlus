package volumes;

import outils.IUnite;

public class AmericanLiquidPint implements IUnite {
    public String toString() {
        return "American Liquid Pint";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/2.113f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*2.113f;
    }
}
