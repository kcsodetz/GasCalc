import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * The APIDriver class gets user input and calls the API to get the statistics
 *
 * @author Ken Sodetz
 * @since 9/29/2017
 */
public class APIDriver {

    private static final String APIKEY = "A8874x8oBWR0GdYXGccI2tYFFULXur7a";

    /**
     * Default constructor
     */
    private APIDriver(){

    }

    /**
     * Connects the API to git
     * @param url url for the http call
     * @param make make of the vehicle
     * @param model model of the vehicle
     * @param year year of the vehicle
     * @param key API key
     * @return responseBody, string response of the API call
     */
    public String connectToAPI(String url, String make, String model, String year, String key) {
        String charset = "UTF-8";
        InputStream response = null;
        URLConnection connection;
        try {
            connection = new URL(url+"make="+make+"&model="+model+"&year="+year+"&apikey="+key).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        try(Scanner scanner = new Scanner(response)){
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
            return responseBody;
        }
    }

    public double getCityMPG(String apiCallString) {

        String response = apiCallString.substring(apiCallString.indexOf("City_Unadj_Conventional_Fuel"), apiCallString.indexOf("Hwy_Unadj_Conventional_Fuel"));
        response = response.substring(response.indexOf(":") + 1, response.indexOf(","));
        return Double.parseDouble(response);
    }

    public double getHighwayMPG(String apiCallString) {
        String response = apiCallString.substring(apiCallString.indexOf("Hwy_Unadj_Conventional_Fuel"), apiCallString.indexOf("Air_AspirMethod"));
        response = response.substring(response.indexOf(":") + 1, response.indexOf(","));
        return Double.parseDouble(response);
    }

    /**
     * Main Method
     * @param args default arguments
     */
    public static void main(String[] args) {
        APIDriver apiDriver = new APIDriver();
        String url = "https://apis.solarialabs.com/shine/v1/vehicle-stats/specs?";
        String make = "honda";
        String model = "cr-v";
        String year = "2012";
        double cityMPG;
        double highwayMPG;
        String apiCallString = apiDriver.connectToAPI(url, make, model, year, APIKEY);
        cityMPG = apiDriver.getCityMPG(apiCallString);
        highwayMPG = apiDriver.getHighwayMPG(apiCallString);
        System.out.println("City MPG: "+cityMPG+"\n"+"Highway MPG: "+highwayMPG);
    }
}
