package com.ceri.ultraConverterProPlus.distances;

import com.ceri.ultraConverterProPlus.outils.IUnite;

/**
 * Classe gérant les micromètres
 */
public class Micrometre implements IUnite {
    @Override
    public String toString() {
        return "Micrometres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*0.000001f;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance*1000000;
    }

}
