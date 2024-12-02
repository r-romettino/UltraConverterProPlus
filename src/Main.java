import outils.AffichageMenu;
import outils.Convertisseur;
import outils.Factory;
import outils.IUnite;
import outils.convertHistory;

import java.util.*;
import static java.lang.System.exit;
import distances.*;
import outils.ConvertisseurCSV;
import temperatures.*;
import temps.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main
{
	
	private static List<convertHistory> history = new ArrayList<>();  // Initialisation de l'historique
    /**
     * Entry point of our CLI program, asks the user for the unit type, and the original and output unit, then the value of the original unit
     * Print the result of the conversion
     * @param args
     */
	public static void main(String[] args) {
        float res = -1;

        String[] types = {"u.Distances", "u.Temps", "u.Températures", "u.History"}; // To fill with all the unit types

        // TODO: Make this a hashmap
        String[] uniteTemps = {"temps.Secondes", "temps.Minutes", "temps.Heures", "temps.Jours", "temps.Semaines"};
        String[] uniteTemperatures = {"temperatures.Celsius", "temperatures.Delisle", "temperatures.Fahrenheit", "temperatures.Kelvin", "temperatures.Newton", "temperatures.Rankine", "temperatures.Reaumur"};
        String[] uniteDistances = {"distances.Miles", "distances.Metre", "distances.Pouce", "distances.MileNautique", "distances.Yard", "distances.Kilometre", "distances.Centimetre", "distances.Millimetre", "distances.Micrometre", "distances.Nanometre", "distances.Pied"}; // To fill with all the different distance units
        
        Scanner sc = new Scanner(System.in); // Initialize the scanner to get the user's choices
        
        // Charger l'historique depuis le fichier JSON
        loadHistoryFromJson();

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
                convertor(res, entree, sc, uniteDistances, "Distances");
            } 
            else if (Objects.equals(entree, "2")) { // Temps
                convertor(res, entree, sc, uniteTemps, "Temps");
            }
            else if (Objects.equals(entree, "3")) { // Températures
                convertor(res, entree, sc, uniteTemperatures, "Températures");
            }
            else if (Objects.equals(entree, "4")) { // Températures
                afficheHistory(history);
            }
            else {
                System.out.println("Erreur! \n Votre choix est impossible (" + entree + ")");
            }
        }
        
        sc.close(); // Fermer le scanner à la fin

    }
	
	public static void convertor(float res, String entree, Scanner sc, String[]listeUnite, String selectedType) {
		    
		    System.out.println("Selectionnez l'unité d'origine : \n");
		    AffichageMenu.afficher(listeUnite);
		    entree = sc.nextLine();
		
		    if(Objects.equals(entree, "0"))//On quitte si 0
		        exit(0);
		
		    IUnite u1=null;
		    IUnite u2=null;
		    		    
		    u1 = Factory.transformStringToClass(listeUnite[Integer.parseInt(entree)-1]);
		    String result = listeUnite[Integer.parseInt(entree)-1];
		    String toUnit = result.substring(result.indexOf(".") + 1);
		    
		    System.out.println("Vers quelle unité ?\n");
		    AffichageMenu.afficher(listeUnite);
		    entree = sc.nextLine();
		
		    u2 = Factory.transformStringToClass(listeUnite[Integer.parseInt(entree)-1]);
		    String result1 = listeUnite[Integer.parseInt(entree)-1];
		    String fromUnit = result1.substring(result.indexOf(".") + 1);
		    
		    System.out.println("Qu'elle distance voulez vous convertir ?");
		    float distance = Float.parseFloat(sc.nextLine());
		
		    //Avoid to convert if miles to miles or meters to meters etc...
		    if(u1.getClass()==u2.getClass())
		        res = distance;
		    else
		        res = Convertisseur.convert(u1,u2, distance);
		    
		    Formatter format = new Formatter();
		    format.format("Résultat de la conversion : %s %s = %s %s", distance, u1, res, u2);
		    System.out.println(format);
		    
		    convertHistory lastConvert = new convertHistory(res, fromUnit, toUnit, distance, selectedType);
	    	history.add(lastConvert);
	    	
	    	// Enregistrer l'historique dans un fichier JSON avant de quitter
	        saveHistoryToJson(history);
		}
	
	public static void afficheHistory(List<convertHistory> history) {
		if (history.isEmpty()) {
			System.out.println("L'historique est vide");
		} else {
			for (convertHistory e : history) {
				System.out.println(e.toString());
			}
		}
	}
	
	// Méthode pour enregistrer l'historique dans un fichier JSON
    public static void saveHistoryToJson(List<convertHistory> history) {
        ObjectMapper objectMapper = new ObjectMapper(); // Création d'un objet ObjectMapper de Jackson

        try {
            // Enregistrer l'historique dans un fichier JSON
            objectMapper.writeValue(new File("history.json"), history);
            System.out.println("Historique enregistré dans history.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour charger l'historique à partir d'un fichier JSON
    public static void loadHistoryFromJson() {
        ObjectMapper objectMapper = new ObjectMapper(); // Création d'un objet ObjectMapper de Jackson
        File file = new File("history.json"); // Chemin du fichier JSON

        try {
            // Vérifier si le fichier existe avant de tenter de le lire
            if (file.exists() && !file.isDirectory()) {
                // Lire le contenu du fichier JSON et le convertir en une liste d'objets convertHistory
                List<convertHistory> loadedHistory = Arrays.asList(
                        objectMapper.readValue(file, convertHistory[].class)
                );

                // Ajouter les entrées chargées à l'historique actuel
                history.addAll(loadedHistory);
                System.out.println("Historique chargé depuis history.json");
            } else {
                System.out.println("Aucun fichier history.json trouvé. L'historique commence vide.");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de l'historique depuis le fichier JSON.");
            e.printStackTrace();
        }
    }
    
}