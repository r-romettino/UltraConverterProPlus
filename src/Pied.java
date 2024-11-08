/**
 * Classe gérant les pieds
 */
public class Pied implements IUnite{

    @Override
    public String toString() {
        return "Pieds";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.3047f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*3.2809f;
    }
}
