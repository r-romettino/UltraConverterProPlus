package volumes;

import outils.IUnite;

public class ImperialPint implements IUnite {

    public String toString() {
        return "Imperial Pint";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/1.76f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*1.76f;
    }
}
