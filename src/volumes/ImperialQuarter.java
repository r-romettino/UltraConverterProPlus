package volumes;

import outils.IUnite;

public class ImperialQuarter implements IUnite {

    public String toString() {
        return "Imperial Quarter";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur*1.137f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur/1.137f;
    }
}
