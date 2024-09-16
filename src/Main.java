import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static <Unite> void main(String[] args) {
        //System.out.println("100 metres en miles");
        //System.out.println(Convertisseur.convert("ClassesUnites.Metre", "ClassesUnites.Miles", 100));
        float res = -1;
        System.out.println("Selectionnez un des choix suivants : \n" +
                        "\t0. Quitter\n"+
                    "\t1. Distances\n");
        Scanner sc = new Scanner(System.in);
        String entree = sc.nextLine();

        String[] tabDistance = {"Metre", "Miles"};

        /*try
        {
            Integer entreeInt = entree.to
        }*/

        if(Objects.equals(entree, "0"))
        {
            exit(0);
        }
        else if(Objects.equals(entree, "1"))
        {
            System.out.println("Selectionnez l'unité d'origine : \n" +
                    "\t0. Quitter\n"+
                    "\t1. Metres\n" +
                    "\t2. ClassesUnites.Miles\n");
            entree = sc.nextLine();

            if(Objects.equals(entree, "0"))//On quitte
                exit(0);

            System.out.println("Qu'elle distance voulez vous convertir?");
            float distance = Float.parseFloat(sc.nextLine());

            IUnite u1=null;
            IUnite u2=null;
            if (Objects.equals(entree, "1"))//Metres vers ClassesUnites.Miles
            {
                u1 = Factory.transformStringToClass("ClassesUnites.Metre");
                u2 = Factory.transformStringToClass("ClassesUnites.Miles");
            }
            else if (Objects.equals(entree, "2"))//ClassesUnites.Miles vers Metres
            {
                u1 = Factory.transformStringToClass("ClassesUnites.Miles");
                u2 = Factory.transformStringToClass("ClassesUnites.Metre");

            }
            else
            {
                System.out.println("Erreur dans l'entrée");
                exit(0);
            }
            res = Convertisseur.convert(u1,u2, distance);
        }
        System.out.println(res);
    }
}