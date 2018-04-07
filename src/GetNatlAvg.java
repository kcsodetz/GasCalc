import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Gets the national average for unleaded and diesel fuel
 *
 * @author Ken Sodetz
 * @since 10/9/2017
 */
public class GetNatlAvg {

    private double diesel, regular, premium, midgrade, electric, e85;

    /**
     * Default constructor
     */
    public GetNatlAvg() {
    }

    /**
     * Use fueleconomy.gov to get the national average for some different fuel sources
     * @return collection of prices
     */
    public HashMap requestNatlAvg() {
        String charset = "UTF-8";
        InputStream response = null;
        URLConnection connection;
        String url = "https://www.fueleconomy.gov/ws/rest/fuelprices";
        try {
            connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        try(Scanner scanner = new Scanner(response)) {
            String parsed = scanner.useDelimiter("\\A").next();
            return parseNatlAvg(parsed);
        }
    }

    /**
     * Gets the national average for unleaded gasoline, diesel, electric, etc
     * @param response String to be parsed
     * @return collection of the types
     */
    private HashMap parseNatlAvg(String response) {
        HashMap<String, Double> map = new HashMap<>();
        System.out.println(response);
        diesel = Double.parseDouble(response.substring(response.indexOf("diesel") + 7, response.indexOf("</diesel>")));
        map.put("Diesel", diesel);
        e85 = Double.parseDouble(response.substring(response.indexOf("e85") + 4, response.indexOf("</e85>")));
        map.put("E85", e85);
        electric = Double.parseDouble(response.substring(response.indexOf("electric") + 9, response.indexOf("</electric>")));
        map.put("Electric", electric);
        midgrade = Double.parseDouble(response.substring(response.indexOf("midgrade") + 9, response.indexOf("</midgrade>")));
        map.put("Midgrade", midgrade);
        regular = Double.parseDouble(response.substring(response.indexOf("regular") + 8, response.indexOf("</regular>")));
        map.put("Regular", regular);
        premium = Double.parseDouble(response.substring(response.indexOf("premium") + 8, response.indexOf("</premium>")));
        map.put("Premium", premium);
        return map;
    }

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        GetNatlAvg getNatlAvg = new GetNatlAvg();
        System.out.println(getNatlAvg.requestNatlAvg());
    }
}
