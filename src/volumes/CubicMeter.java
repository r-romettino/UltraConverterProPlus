package volumes;

import outils.IUnite;

public class CubicMeter implements IUnite {

    public String toString()
    {
        return "Cubic Meter";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*1000;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/1000;
    }
}
