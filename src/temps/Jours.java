package temps;

import outils.IUnite;

public class Jours implements IUnite {
    @Override
    public String toString() {
        return "jours";
    }
    @Override
    public float convertToIS(float days) {
        return 60 * 60 * 24 * days;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds / (60 * 60 * 24);
    }
}
