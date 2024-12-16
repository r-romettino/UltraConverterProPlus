package com.ceri.ultraConverterProPlus.temperatures;

import com.ceri.ultraConverterProPlus.outils.IUnite;

public class Kelvin implements IUnite {
    @Override
    public String toString() {
        return "Kelvin";
    }

    @Override
    public float convertToIS(float distance) {
        return (float) (distance - 273.15);
    }

    @Override
    public float convertFromIS(float distance) {
        return (float) (distance + 273.15);
    }
}
