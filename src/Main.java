import unit.IUnite;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static <Unite> void main(String[] args) {
        float res = -1;

        System.out.println("Selectionnez un des choix suivants : \n");
        Selector.afficher(types);

        System.out.println("\n Votre choix : ");

        Scanner sc = new Scanner(System.in);
        String entree = sc.nextLine();

        if(Objects.equals(entree, "0"))
        {
            exit(0);
        }
        else if(Objects.equals(entree, "1"))//Distances
        {
            System.out.println("Selectionnez l'unité d'origine : \n");
            Selector.afficher(uniteDistances);
            entree = sc.nextLine();

            if(Objects.equals(entree, "0"))//On quitte
                exit(0);

            IUnite u1=null;
            IUnite u2=null;

            u1 = Factory.transformStringToClass(uniteDistances[Integer.parseInt(entree)-1]);

            System.out.println("Vers quelle unité?\n");
            Selector.afficher(uniteDistances);
            entree = sc.nextLine();

            u2 = Factory.transformStringToClass(uniteDistances[Integer.parseInt(entree)-1]);

            System.out.println("Quelle distance voulez vous convertir?");
            float distance = Float.parseFloat(sc.nextLine());

            res = Convertisseur.convert(u1,u2, distance);
            Formatter format = new Formatter();
            format.format("Résultat de la conversion de %.2f %s vers %s = %.2f", distance, u1.getFullName(), u2.getFullName(), res);
            System.out.println(format);
        }
        else
        {
            System.out.println("Erreur! \n Votre choix est impossible ("+entree+")");
        }

    }
}