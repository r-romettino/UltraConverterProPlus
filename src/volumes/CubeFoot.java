package volumes;

import outils.IUnite;

public class CubeFoot implements IUnite {

    @Override
    public String toString() {
        return "Cube Foot";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*28.317f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/28.317f;
    }
}
