package temperatures;

import outils.IUnite;

public class Rankine implements IUnite {
    @Override
    public String toString() {
        return "Rankine";
    }

    @Override
    public float convertToIS(float distance) {
        return (float) (distance - 491.67) * 5/9;
    }

    @Override
    public float convertFromIS(float distance) {
        return (float) ((distance * ((float)9/5)) + 491.67);
    }
}
