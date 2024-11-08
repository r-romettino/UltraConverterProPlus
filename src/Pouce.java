/**
 * Classe gérant les pouces
 */
public class Pouce implements IUnite{

    @Override
    public String toString() {
        return "Pouces";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.0254f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*0.3937f;
    }
}
