import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import static java.lang.System.exit;

public class Main
{
    /**
     * Entry point of our CLI program, asks the user for the unit type, and the original and output unit, then the value of the original unit
     * Print the result of the conversion
     * @param args
     */
    public static void main(String[] args)
    {
        float res = -1;

        String[] types = {"Distances", "Temps", "Températures"};;//To fill with all the unit types

        // TODO: Make this a hashmap
        String[] uniteTemps = {"Secondes", "Minutes", "Heures", "Jours", "Semaines"};
        String[] uniteTemperatures = {"Celsius", "Delisle", "Fahrenheit", "Kelvin", "Newton", "Rankine", "Reaumur"};
        String[] uniteDistances = {"Miles", "Metre", "Pouce", "MileNautique", "Yard", "Kilometre", "Centimetre", "Millimetre", "Micrometre", "Nanometre", "Pied"};//To fill with all the different distance units
        //while (1) {
	        System.out.println("Selectionnez un des choix suivants : \n");
	        AffichageMenu.afficher(types);
	
	        System.out.println("\n Votre choix : ");
	
	        Scanner sc = new Scanner(System.in);//initialize the scanner to get the user's choices
	        String entree = sc.nextLine();
	
	        // TODO: refactor to get rid of the else if
	        if(Objects.equals(entree, "0"))
	        {
	            exit(0);
	        }
	        else if(Objects.equals(entree, "1"))//Distances
	        {
	        	Distances D = new Distances(uniteDistances);
	        	D.convertor(res, entree, sc);
	        }
	        else if(Objects.equals(entree, "2"))//Temps
	        {
	        	Temps T = new Temps(uniteTemps);
	        	T.convertor(res, entree, sc);
	        }
	        else if(Objects.equals(entree, "3"))//Températures
	        {
	        	Temperatures Tem = new Temperatures(uniteTemperatures);
	        	Tem.convertor(res, entree, sc);
	        }
	        else
	        {
	            System.out.println("Erreur! \n Votre choix est impossible ("+entree+")");
	        }
        //}

    }
}