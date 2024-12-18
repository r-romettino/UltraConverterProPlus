package outils;

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
        IUnite u1;
        IUnite u2;
        CorrectionOrthographique co = new CorrectionOrthographique();

        CorrectionOrthographique co1 = new CorrectionOrthographique();
        String m1 = co1.trouverCorrectionMonnaie(donneesEntrees[0]);
        String m2 = co1.trouverCorrectionMonnaie(donneesEntrees[2]);
        CurrencyConverter cc = new CurrencyConverter();

        if(m1 == null || m2 == null)
        {
            u1 = Factory.transformStringToClass(co.trouverCorrection(donneesEntrees[0]));
            u2 = Factory.transformStringToClass(co.trouverCorrection2(donneesEntrees[2], u1.toString()));
            float valueUnit1 = Float.parseFloat(donneesEntrees[1]);
            float valueUnit2 = Convertisseur.convert(u1, u2, valueUnit1);
            return u1+","+valueUnit1+","+u2+","+valueUnit2;
        }
        return Float.toString(cc.convert(m1, m2, Float.parseFloat(donneesEntrees[1])));
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
