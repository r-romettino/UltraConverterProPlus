package outils;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static outils.ConvertisseurCSV.convertirCSV;

public class CsvFileHelper {

    private final static String FILE_NAME = "ressources/test.csv";
    private final static String FILE_Conversion = "ressources/Conversion.csv";


    public static String getResourcePath(String fileName) {
        final File f = new File("");
        return f.getAbsolutePath() + File.separator + fileName;
    }

    public static File getResource(String fileName) {
        final String completeFileName = getResourcePath(fileName);
        return new File(completeFileName);
    }

    public static String readFile(File file) throws IOException {

      StringBuilder result = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result.append(line.replace(";", ",")).append(";");
            }
        }

        return result.toString();
    }

    public static boolean writeFile(String conversion) throws IOException {

        String filePath = FILE_Conversion;

        String[] rows = conversion.split(";");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.ISO_8859_1))) {

            for (String row : rows) {
                /*String[] cols = row.split(",");
                for (String col : cols) {
                    writer.write(col +";");
                }*/
                writer.write(row);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {

        final File file = CsvFileHelper.getResource(FILE_NAME);

        try
        {
            String result = readFile(file);
            writeFile(convertirCSV(result));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

       /* String conversion = "Unité de base,Valeur,Unité de conversion;metre,2,miles;";
        try {
            boolean result = writeFile(conversion);
            System.out.println("Fichier écrit avec succès : " + result);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'appel de writeFile : " + e.getMessage());
        }*/

    }
}
