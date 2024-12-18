package outils;

public class AffichageMenu {
    public static void afficher(String[] a)
    {
        System.out.println("\t0. Quitter");
        System.out.println("\t1. Conversion multiple par CSV");
        int idx = 2;
        for(String e : a)
        {
            String[] e_u = e.split("\\.");
            System.out.println("\t" + idx + ". "+e_u[1]);
            idx++;
        }
    }

    public static void afficherMonnaie(String[] a)
    {
        System.out.println("\t0. Quitter");
        int idx = 1;
        for(String e : a)
        {
            System.out.println("\t" + idx + ". "+e);
            idx++;
        }
    }
}
