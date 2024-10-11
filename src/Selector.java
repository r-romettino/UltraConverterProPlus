public class Selector {
    public static void afficher(String[] classList)
    {
        System.out.println("\t0. Quitter");
        int idx = 1;
        for(String classPath : classList)
        {

            System.out.println("\t" + idx + ". "+classPath);
            idx++;
        }
    }
}
