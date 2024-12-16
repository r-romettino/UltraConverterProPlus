package com.ceri.ultraConverterProPlus.temperatures;

import com.ceri.ultraConverterProPlus.outils.IUnite;

public class Newton implements IUnite {
    @Override
    public String toString() {
        return "Newton";
    }

    @Override
    public float convertToIS(float distance) {
        return (float) (distance / 0.33);
    }

    @Override
    public float convertFromIS(float distance) {
        return (float) (distance * 0.33);
    }
}
