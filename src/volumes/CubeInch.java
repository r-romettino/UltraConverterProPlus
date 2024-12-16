package volumes;

import outils.IUnite;

public class CubeInch implements IUnite {

    @Override
    public String toString() {
        return "Cube Inch";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/61.024f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*61.024f;
    }
}
