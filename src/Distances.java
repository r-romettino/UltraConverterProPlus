import java.util.*;
import static java.lang.System.exit;

public class Distances {
	
	String[] uniteDistances;
	
	public Distances(String[] toutesUnites) {
		this.uniteDistances = toutesUnites;
	}
	
	public void convertor(float res, String entree, Scanner sc) {
	    
	    System.out.println("Selectionnez l'unité d'origine : \n");
	    AffichageMenu.afficher(uniteDistances);
	    entree = sc.nextLine();
	
	    if(Objects.equals(entree, "0"))//On quitte si 0
	        exit(0);
	
	    IUnite u1=null;
	    IUnite u2=null;
	
	    u1 = Factory.transformStringToClass(uniteDistances[Integer.parseInt(entree)-1]);
	
	    System.out.println("Vers quelle unité?\n");
	    AffichageMenu.afficher(uniteDistances);
	    entree = sc.nextLine();
	
	    u2 = Factory.transformStringToClass(uniteDistances[Integer.parseInt(entree)-1]);
	
	    System.out.println("Qu'elle distance voulez vous convertir?");
	    float distance = Float.parseFloat(sc.nextLine());
	
	    //Avoid to convert if miles to miles or meters to meters etc...
	    if(u1.getClass()==u2.getClass())
	        res = distance;
	    else
	        res = Convertisseur.convert(u1,u2, distance);
	    Formatter format = new Formatter();
	    format.format("Résultat de la conversion : %s %s = %s %s", distance, u1, res, u2);
	    System.out.println(format);
	}
}
