package volumes;

import outils.IUnite;

public class AmericanQuarter implements IUnite {
    @Override
    public String toString() {
        return "American Quarter";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/1.057f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*1.057f;
    }
}
