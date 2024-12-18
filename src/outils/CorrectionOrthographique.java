package outils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CorrectionOrthographique {

    static String[] uniteTemps = {"temps.Secondes", "temps.Minutes", "temps.Heures", "temps.Jours", "temps.Semaines"};
    static String[] uniteTemperatures = {"temperatures.Celsius", "temperatures.Delisle", "temperatures.Fahrenheit", "temperatures.Kelvin", "temperatures.Newton", "temperatures.Rankine", "temperatures.Reaumur"};
    static String[] uniteDistances = {"distances.Mile", "distances.Metre", "distances.Pouce", "distances.MileNautique", "distances.Yard", "distances.Kilometre", "distances.Centimetre", "distances.Millimetre", "distances.Micrometre", "distances.Nanometre", "distances.Pied"};
    static String[] unitePoids = {"poids.Gramme","poids.Kilogramme","poids.Livre","poids.Microgramme","poids.Milligramme","poids.Once","poids.Stone","poids.Tonne","poids.TonneCourte","poids.TonneLongue"};
    static String[] uniteVolumes = {"volumes.AmericanCoffeeSpoon","volumes.AmericanGallon","volumes.AmericanLiquidOnce","volumes.AmericanLiquidPint","volumes.AmericanMug","volumes.AmericanQuarter","volumes.AmericanSoupSpoon","volumes.CubeFoot","volumes.CubeInch","volumes.CubicMeter","volumes.ImperialCoffeeSpoon","volumes.ImperialGallon","volumes.ImperialLiquidOnce","volumes.ImperialMug","volumes.ImperialPint","volumes.ImperialQuarter","volumes.ImperialSoupSpoon","volumes.Litre","volumes.Millilitre"};

    Set<String> unites;

    public CorrectionOrthographique() {
        unites = new HashSet<>();
        unites.addAll(Arrays.asList(uniteTemps));
        unites.addAll(Arrays.asList(uniteTemperatures));
        unites.addAll(Arrays.asList(uniteDistances));
        unites.addAll(Arrays.asList(unitePoids));
        unites.addAll(Arrays.asList(uniteVolumes));
    }

    /**
     * Allows us to find the second word in a csv file
     * @param entree
     * @return
     */
    public String trouverCorrection2(String entree, String unite1)
    {
        String meilleureCorrection = null;
        int distanceMinimale = Integer.MAX_VALUE;
        String type = trouverType(unite1);
        for (String unite : unites) {
            int distance = calculerDistanceLevenshtein(type+"."+entree, unite);
            if (distance < distanceMinimale) {
                distanceMinimale = distance;
                meilleureCorrection = unite;
            }
        }

        return meilleureCorrection != null && distanceMinimale <= 3 ? meilleureCorrection : null; // tolérance de 3 erreurs
    }

    public String trouverType(String entree)
    {
        for(String unite : unites)
        {
            if(unite.split("\\.")[1].equals(entree))
            {
                return unite.split("\\.")[0];
            }
        }
        return null;
    }

    public String trouverCorrection(String entree) {
        String meilleureCorrection = null;
        int distanceMinimale = Integer.MAX_VALUE;

        for (String unite : unites) {
            int distance = calculerDistanceLevenshtein(entree, unite.split("\\.")[1]);
            if (distance < distanceMinimale) {
                distanceMinimale = distance;
                meilleureCorrection = unite;
            }
        }

        return meilleureCorrection != null && distanceMinimale <= 3 ? meilleureCorrection : null; // tolérance de 3 erreurs
    }



    public int calculerDistanceLevenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int substitutionCost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + substitutionCost);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
