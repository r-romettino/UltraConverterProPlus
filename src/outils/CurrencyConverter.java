package outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyConverter {
    private HashMap<String, String> nameToSymbol = new HashMap<>();

    private String getFromAPI(String symbolFrom) {
        URL url;
        HttpURLConnection con;

        try {
            url = new URL("https://latest.currency-api.pages.dev/v1/currencies/" + symbolFrom + ".json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String inputLine;
        StringBuilder content = new StringBuilder();
        try {
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }

    public CurrencyConverter() {
        nameToSymbol.put("Euro", "eur");
        nameToSymbol.put("Dirham", "mad");
        nameToSymbol.put("LivreTurque", "try");
        nameToSymbol.put("FrancFrançais", "frf");
        nameToSymbol.put("DollarAméricain", "usd");
        nameToSymbol.put("Bitcoin", "btc");
        nameToSymbol.put("Apecoin", "ape");
    }
    public float convert(String fromCurrency, String toCurrency, float fromValue) {
        String symbolFrom = nameToSymbol.get(fromCurrency);
        String symbolTo = nameToSymbol.get(toCurrency);

        String conversionTable = getFromAPI(symbolFrom);

        Pattern pattern = Pattern.compile("\"" + symbolTo + "\": ([0-9]+\\.[0-9]+e?-?[0-9]?)");

        Matcher matcher = pattern.matcher(conversionTable);
        matcher.find();

        float coeff = Float.parseFloat(matcher.group(1));

        return coeff * fromValue;
    }
}
