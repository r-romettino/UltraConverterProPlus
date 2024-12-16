package com.ceri.ultraConverterProPlus.temps;

import com.ceri.ultraConverterProPlus.outils.IUnite;

public class Secondes implements IUnite {
    @Override
    public String toString() {
        return "secondes";
    }
    @Override
    public float convertToIS(float seconds) {
        return seconds;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds;
    }
}
