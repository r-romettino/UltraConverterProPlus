package temps;

import outils.IUnite;

public class Heures implements IUnite {
    @Override
    public String toString() {
        return "heures";
    }
    @Override
    public float convertToIS(float hours) {
        return 60 * 60 * hours;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds / (60 * 60);
    }
}
