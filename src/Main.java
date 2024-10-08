import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static <Unite> void main(String[] args) {
        float res = -1;

        String[] types = {"Distances"};

        String[] uniteDistances = {"Miles", "Metre"};

        System.out.println("Selectionnez un des choix suivants : \n");
        AffichageMenu.afficher(types);

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
            AffichageMenu.afficher(uniteDistances);
            entree = sc.nextLine();

            if(Objects.equals(entree, "0"))//On quitte
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

            res = Convertisseur.convert(u1,u2, distance);
            Formatter format = new Formatter();
            format.format("Résultat de la conversion de %.2f %s vers %s = %.2f", distance, u1, u2, res);
            System.out.println(format);
        }
        else
        {
            System.out.println("Erreur! \n Votre choix est impossible ("+entree+")");
        }

    }
}