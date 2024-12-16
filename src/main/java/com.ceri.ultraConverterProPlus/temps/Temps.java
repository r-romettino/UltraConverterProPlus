package com.ceri.ultraConverterProPlus.temps;

import java.util.*;
import static java.lang.System.exit;
import com.ceri.ultraConverterProPlus.outils.AffichageMenu;
import com.ceri.ultraConverterProPlus.outils.IUnite;
import com.ceri.ultraConverterProPlus.outils.Factory;
import com.ceri.ultraConverterProPlus.outils.Convertisseur;

public class Temps {
	
	String[] uniteTemps;
	
	public Temps(String[] toutesUnites) {
		this.uniteTemps = toutesUnites;
	}

	public void convertor(float res, String entree, Scanner sc) {
				
		System.out.println("Selectionnez l'unité d'origine : \n");
        AffichageMenu.afficher(uniteTemps);
        entree = sc.nextLine();

        if(Objects.equals(entree, "0"))//On quitte
            exit(0);

        IUnite u1=null;
        IUnite u2=null;

        u1 = Factory.transformStringToClass(uniteTemps[Integer.parseInt(entree)-1]);

        System.out.println("Vers quelle unité?\n");
        AffichageMenu.afficher(uniteTemps);
        entree = sc.nextLine();

        u2 = Factory.transformStringToClass(uniteTemps[Integer.parseInt(entree)-1]);

        System.out.println("Qu'elle distance voulez vous convertir?");
        float distance = Float.parseFloat(sc.nextLine());

        res = Convertisseur.convert(u1,u2, distance);
        Formatter format = new Formatter();
        format.format("Résultat de la conversion de %.2f %s vers %s = %.2f", distance, u1, u2, res);
        System.out.println(format);
	}
}
