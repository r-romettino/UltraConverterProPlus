package unit.distance;

import unit.IUnite;

public class Metres implements IUnite {
    @Override
    public String getFullName() {
        return "mètres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance;
    }
}
