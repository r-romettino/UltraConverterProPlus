package volumes;

import outils.IUnite;

public class AmericanSoupSpoon implements IUnite {
    public String toString()
    {
        return "American Soup Spoon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/67.628f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*67.628f;
    }
}
