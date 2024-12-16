package com.ceri.ultraConverterProPlus.distances;

import com.ceri.ultraConverterProPlus.outils.IUnite;

/**
 * Classe gérant les centimètres
 */
public class Centimetre implements IUnite {
    @Override
    public String toString() {
        return "Centimetres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance/100;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*100;
    }
}
