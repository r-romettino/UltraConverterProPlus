package outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvFileHelper {

    private final static String FILE_NAME = "ressources/test.csv";

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

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.append(line).append(";");
        }

        br.close();
        fr.close();

        return result.toString();
    }

    public static void main(String[] args)
    {

        final File file = CsvFileHelper.getResource(FILE_NAME);

        try {
            String result = readFile(file);
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
