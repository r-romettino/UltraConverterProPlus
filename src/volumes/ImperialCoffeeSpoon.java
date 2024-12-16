package volumes;

import outils.IUnite;

public class ImperialCoffeeSpoon implements IUnite {

    @Override
    public String toString() {
        return "Imperial Coffee Spoon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/168.9f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*168.9f;
    }
}
