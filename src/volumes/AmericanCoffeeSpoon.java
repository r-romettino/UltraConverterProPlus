package volumes;

import outils.IUnite;

public class AmericanCoffeeSpoon implements IUnite {

    public String toString() {
        return "American Coffee Spoon";
    }

    @Override
    public float convertToIS(float valeur) {
        return valeur/202.9f;
    }

    @Override
    public float convertFromIS(float valeur) {
        return valeur*202.9f;
    }
}
