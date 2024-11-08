package temps;

import outils.IUnite;

public class Semaines implements IUnite {
    @Override
    public String toString() {
        return "semaines";
    }
    @Override
    public float convertToIS(float weeks) {
        return 60 * 60 * 24 * 7 * weeks;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds / (60 * 60 * 24 * 7);
    }
}
