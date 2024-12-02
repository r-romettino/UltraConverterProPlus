package outils;

import distances.*;
import temperatures.*;
import temps.*;

/**
 * Take CSV content and convert each lines
 */
public class ConvertisseurCSV {
    /**
     * Take a CSV line and convert it
     * @param ligne The CSV line must be in format : unit1, valueUnit1, unit2
     * @return The CSV line return will be in format : unit1, valueUnit1, unit2, valueUnit2
     */
    static public String convertir(String ligne)
    {
        String[] donneesEntrees = ligne.split(",");
        IUnite u1=null;
        IUnite u2=null;
        u1 = Factory.transformStringToClass("temps."+donneesEntrees[0], false);
        if(u1==null)
        {
            u1 = Factory.transformStringToClass("temperatures."+donneesEntrees[0], false);
        }
        if(u1==null)
        {
            u1 = Factory.transformStringToClass("distances."+donneesEntrees[0], false);
        }

        u2 = Factory.transformStringToClass("temps."+donneesEntrees[2], false);
        if(u2==null)
        {
            u2 = Factory.transformStringToClass("temperatures."+donneesEntrees[2], false);
        }
        if(u2==null)
        {
            u2 = Factory.transformStringToClass("distances."+donneesEntrees[2], false);
        }
        float valueUnit1 = Float.parseFloat(donneesEntrees[1]);
        float valueUnit2 = Convertisseur.convert(u1, u2, valueUnit1);
        return u1.toString()+","+valueUnit1+","+u2.toString()+","+valueUnit2;
    }

    /**
     * Take a CSV content, convert each line create a new CSV content and return
     * @param content the content from the original CSV
     * @return the new content generated
     */
    static public String convertirCSV(String content)
    {
        String[] lignes = content.split(";");
        String CSVToReturn = "";
        for(String ligne : lignes)
        {
            CSVToReturn += convertir(ligne)+"\n";
        }
        return CSVToReturn;
    }
}
