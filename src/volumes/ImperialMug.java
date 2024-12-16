package volumes;

import outils.IUnite;

public class ImperialMug implements IUnite {

    @Override
    public String toString() {
        return "Imperial Mug";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/3.52f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*3.52f;
    }
}
