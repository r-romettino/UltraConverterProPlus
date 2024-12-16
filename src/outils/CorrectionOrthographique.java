package outils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CorrectionOrthographique {

    static String[] uniteTemps = {"temps.Secondes", "temps.Minutes", "temps.Heures", "temps.Jours", "temps.Semaines"};
    static String[] uniteTemperatures = {"temperatures.Celsius", "temperatures.Delisle", "temperatures.Fahrenheit", "temperatures.Kelvin", "temperatures.Newton", "temperatures.Rankine", "temperatures.Reaumur"};
    static String[] uniteDistances = {"distances.Miles", "distances.Metre", "distances.Pouce", "distances.MileNautique", "distances.Yard", "distances.Kilometre", "distances.Centimetre", "distances.Millimetre", "distances.Micrometre", "distances.Nanometre", "distances.Pied"};

    Set<String> unites;

    public CorrectionOrthographique() {
        unites = new HashSet<>();
        unites.addAll(Arrays.asList(uniteTemps));
        unites.addAll(Arrays.asList(uniteTemperatures));
        unites.addAll(Arrays.asList(uniteDistances));
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
