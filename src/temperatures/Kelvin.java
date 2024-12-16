package temperatures;

import outils.IUnite;

public class Kelvin implements IUnite {
    @Override
    public String toString() {
        return "Kelvin";
    }

    @Override
    public float convertToIS(float distance) {
        if(distance >= 0) {
            return (float) (distance - 273.15);
        }
        else
            return -1;//Cas d'erreur
    }

    @Override
    public float convertFromIS(float distance) {
        if(distance + 273.15 >= 0) {
            return (float) (distance + 273.15);
        }
        else
            return -1;//Cas d'erreur
    }
}
