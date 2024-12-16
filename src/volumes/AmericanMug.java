package volumes;

import outils.IUnite;

public class AmericanMug implements IUnite {
    public String toString() {
        return "American Mug";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/4.167f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*4.167f;
    }
}
