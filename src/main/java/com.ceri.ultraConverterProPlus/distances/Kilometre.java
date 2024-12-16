package com.ceri.ultraConverterProPlus.distances;

import com.ceri.ultraConverterProPlus.outils.IUnite;

/**
 * Classe gérant les kilomètres
 */
public class Kilometre implements IUnite {
    @Override
    public String toString() {
        return "Kilometres";
    }

    @Override
    public float convertToIS(float distance) {
        return distance*1000;
    }

    @Override
    public float convertFromIS(float distance) {
        return distance/1000;
    }

}
