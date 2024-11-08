package temperatures;

import outils.IUnite;

public class Delisle implements IUnite {
    @Override
    public String toString() {
        return "Delisle";
    }

    @Override
    public float convertToIS(float distance) {
        return 100 - ((distance * 2) / 3);
    }

    @Override
    public float convertFromIS(float distance) {
        return (float) ((100 - distance) * 1.5);
    }
}
