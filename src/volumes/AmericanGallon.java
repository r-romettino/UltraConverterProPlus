package volumes;

import outils.IUnite;

public class AmericanGallon implements IUnite {
    public String toString()
    {
        return "American Gallon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*3.78541f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/3.78541f;
    }
}
