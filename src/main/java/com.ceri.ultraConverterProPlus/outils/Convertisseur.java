package com.ceri.ultraConverterProPlus.outils;

public class Convertisseur {

    static public float convert(IUnite u1, IUnite u2, float distance)
    {
        return u2.convertFromIS(u1.convertToIS(distance));
    }
    
    static public float convertString(String fromUnit, String toUnit, float value, String[] listeUnite) {
    	IUnite u1=null;
	    IUnite u2=null;
	    
	    u1 = Factory.transformStringToClass(fromUnit);
	    u2 = Factory.transformStringToClass(toUnit);
		
		return u2.convertFromIS(u1.convertToIS(value));
    }

}
