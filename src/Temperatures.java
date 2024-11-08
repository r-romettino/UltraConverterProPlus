import java.util.*;
import static java.lang.System.exit;

public class Temperatures {
	
	String[] uniteTemperatures;
	
	public Temperatures(String[] toutesUnites) {
		this.uniteTemperatures = toutesUnites;
	}

	public void convertor(float res, String entree, Scanner sc) {
		
		System.out.println("Selectionnez l'unité d'origine : \n");
        AffichageMenu.afficher(uniteTemperatures);
        entree = sc.nextLine();

        if(Objects.equals(entree, "0"))//On quitte
            exit(0);

        IUnite u1=null;
        IUnite u2=null;

        u1 = Factory.transformStringToClass(uniteTemperatures[Integer.parseInt(entree)-1]);

        System.out.println("Vers quelle unité?\n");
        AffichageMenu.afficher(uniteTemperatures);
        entree = sc.nextLine();

        u2 = Factory.transformStringToClass(uniteTemperatures[Integer.parseInt(entree)-1]);

        System.out.println("Qu'elle distance voulez vous convertir?");
        float temperature = Float.parseFloat(sc.nextLine());

        res = Convertisseur.convert(u1,u2, temperature);
        Formatter format = new Formatter();
        format.format("Résultat de la conversion de %.2f %s vers %s = %.2f", temperature, u1, u2, res);
        System.out.println(format);
	}
}
