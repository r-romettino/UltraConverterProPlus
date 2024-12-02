package outils;


import java.io.*;
import java.nio.charset.StandardCharsets;

import static outils.ConvertisseurCSV.convertirCSV;

public class CsvFileHelper {

    private static String FILE_NAME = "ressources/test.csv";
    private static String FILE_Conversion = "ressources/Conversion.csv";

    public static String getFILE_Conversion() {
        return FILE_Conversion;
    }

    public static String getFileName() {
        return FILE_NAME;
    }

    public static void setFILE_Conversion(String FILE_Conversion) {
        CsvFileHelper.FILE_Conversion = FILE_Conversion;
    }

    public static void setFileName(String fileName) {
        FILE_NAME = fileName;
    }

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
                //System.out.println(line);
                result.append(line.replace(";", ",")).append(";");
            }
        }

        return result.toString();
    }

    public static boolean writeFile(String conversion) throws IOException {

        String[] rows = conversion.split(";");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_Conversion), StandardCharsets.ISO_8859_1))) {

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

    public static void IOCSV(String pathIn, String pathOut)
    {
        setFileName(pathIn);
        setFILE_Conversion(pathOut);
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

    }
}
