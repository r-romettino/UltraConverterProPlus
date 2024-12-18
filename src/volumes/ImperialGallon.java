package volumes;

import outils.IUnite;

public class ImperialGallon implements IUnite {

    public String toString()
    {
        return "Imperial Gallon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*4.546f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/4.546f;
    }
}
