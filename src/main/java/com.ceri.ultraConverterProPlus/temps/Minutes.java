package com.ceri.ultraConverterProPlus.temps;

import com.ceri.ultraConverterProPlus.outils.IUnite;

public class Minutes implements IUnite {
    @Override
    public String toString() {
        return "minutes";
    }
    @Override
    public float convertToIS(float minutes) {
        return 60 * minutes;
    }

    @Override
    public float convertFromIS(float seconds) {
        return seconds / 60;
    }
}
