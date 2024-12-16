package com.ceri.ultraConverterProPlus.temperatures;

import com.ceri.ultraConverterProPlus.outils.IUnite;

public class Fahrenheit implements IUnite {
    @Override
    public String toString() {
        return "Fahrenheit";
    }

    @Override
    public float convertToIS(float distance) {
        return (distance - 32) * ((float) 5/9);
    }

    @Override
    public float convertFromIS(float distance) {
        return (distance * (float) 9/5) + 32;
    }
}
