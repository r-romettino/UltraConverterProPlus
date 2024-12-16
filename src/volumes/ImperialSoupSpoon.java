package volumes;

import outils.IUnite;

public class ImperialSoupSpoon implements IUnite {

    @Override
    public String toString() {
        return "Imperial Soup Spoon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/56.312f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*56.312f;
    }
}
