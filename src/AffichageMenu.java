import java.util.ArrayList;

public class AffichageMenu {
    public static void afficher(String[] a)
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
