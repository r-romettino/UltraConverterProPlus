import outils.AffichageMenu;
import outils.Convertisseur;
import outils.Factory;
import outils.IUnite;

import static java.lang.System.exit;

import java.util.*;

public class Main
{
    /**
     * Entry point of our CLI program, asks the user for the unit type, and the original and output unit, then the value of the original unit
     * Print the result of the conversion
     * @param args
     */
	public static void main(String[] args) {
        float res = -1;

        String[] types = {"u.Distances", "u.Temps", "u.TempÃ©ratures"}; // To fill with all the unit types

        // TODO: Make this a hashmap
        String[] uniteTemps = {"temps.Secondes", "temps.Minutes", "temps.Heures", "temps.Jours", "temps.Semaines"};
        String[] uniteTemperatures = {"temperatures.Celsius", "temperatures.Delisle", "temperatures.Fahrenheit", "temperatures.Kelvin", "temperatures.Newton", "temperatures.Rankine", "temperatures.Reaumur"};
        String[] uniteDistances = {"distances.Miles", "distances.Metre", "distances.Pouce", "distances.MileNautique", "distances.Yard", "distances.Kilometre", "distances.Centimetre", "distances.Millimetre", "distances.Micrometre", "distances.Nanometre", "distances.Pied"}; // To fill with all the different distance units

        Scanner sc = new Scanner(System.in); // Initialize the scanner to get the user's choices

        while (true) { // Infinite loop to keep the user interacting until they choose to quit
            System.out.println("Selectionnez un des choix suivants : \n");
            AffichageMenu.afficher(types);

            System.out.println("\n Votre choix : ");
            String entree = sc.nextLine();

            // Vérifier si l'utilisateur veut quitter
            if (Objects.equals(entree, "0")) {
                System.out.println("Vous avez choisi de quitter. À bientôt!");
                break; // Sortir de la boucle
            }

            // Gestion des différentes options
            else if (Objects.equals(entree, "1")) { // Distances
                convertor(res, entree, sc, uniteDistances);
            } 
            else if (Objects.equals(entree, "2")) { // Temps
                convertor(res, entree, sc, uniteTemps);
            }
            else if (Objects.equals(entree, "3")) { // Températures
                convertor(res, entree, sc, uniteTemperatures);
            }
            else {
                System.out.println("Erreur! \n Votre choix est impossible (" + entree + ")");
            }
        }
        
        sc.close(); // Fermer le scanner à la fin
    }
	
	public static void convertor(float res, String entree, Scanner sc, String[]listeUnite) {
		    
		    System.out.println("Selectionnez l'unitÃ© d'origine : \n");
		    AffichageMenu.afficher(listeUnite);
		    entree = sc.nextLine();
		
		    if(Objects.equals(entree, "0"))//On quitte si 0
		        exit(0);
		
		    IUnite u1=null;
		    IUnite u2=null;
		    
		    u1 = Factory.transformStringToClass(listeUnite[Integer.parseInt(entree)-1]);
		
		    System.out.println("Vers quelle unitÃ©?\n");
		    AffichageMenu.afficher(listeUnite);
		    entree = sc.nextLine();
		
		    u2 = Factory.transformStringToClass(listeUnite[Integer.parseInt(entree)-1]);
		
		    System.out.println("Qu'elle distance voulez vous convertir?");
		    float distance = Float.parseFloat(sc.nextLine());
		
		    //Avoid to convert if miles to miles or meters to meters etc...
		    if(u1.getClass()==u2.getClass())
		        res = distance;
		    else
		        res = Convertisseur.convert(u1,u2, distance);
		    Formatter format = new Formatter();
		    format.format("RÃ©sultat de la conversion : %s %s = %s %s", distance, u1, res, u2);
		    System.out.println(format);
		}
}