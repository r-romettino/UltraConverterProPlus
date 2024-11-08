package outils;

public class Convertisseur {

    static public float convert(IUnite u1, IUnite u2, float distance)
    {
        return u2.convertFromIS(u1.convertToIS(distance));
    }

}
