package outils;

/**
 * Interface commune a toutes nos unités qu'elles soient distances ou temps ou autre
 */
public interface IUnite {

    @Override
    /**
     * Transforme en texte lisible une unité
     */
    String toString();

    /**
     * Transforme notre unité en systeme internationnal (unite de reference)
     * Par exemple pour les distance notre systeme internationnal sont les mètres
     * @param valeur notre valeur d'unité a convertir (par exemple 5 miles)
     * @return la valeur convertie en mètres
     */
    float convertToIS(float valeur);

    /**
     * Convertie une valeur du systeme internationnal (unite de reference) en unite de la classe
     * Par exemple pour les distance notre systeme internationnal sont les mètres
     * @param valeur notre valeur dans le systeme internationnal (par exemple 5 mètres)
     * @return la valeur convertie en miles
     */
    float convertFromIS(float valeur);
}
