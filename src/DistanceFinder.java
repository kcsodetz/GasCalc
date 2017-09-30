import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author Shiv Paul
 * @since 9/30/2017
 */

public class DistanceFinder
{

    private static final String APIKEY = "AIzaSyD7GjH80EBchoi53fNvVRWGhBrWaPGP_iw";
    public static final String LINK = "https://maps.googleapis" +
            ".com/maps/api/distancematrix/json?units=imperial&";

    private DistanceFinder()
    {

    }

    /**
     * Connects to the api and gets the response string
     * @param link link url to the api call
     * @param origin starting point string
     * @param dest destination string
     * @return responseBody, string response of the API call
     */
    public static String connectToAPI(String link, String origin, String dest) {
        InputStream response = null;
        URLConnection connection;
        try
        {
            connection = new URL(link+"origins="+origin+"&destinations="+dest+"&key="+APIKEY).openConnection();
            response = connection.getInputStream();
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        assert response != null;
        try(Scanner scanner = new Scanner(response)) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    /**
     * Main method
     * @param args program arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter origin: ");
        String origin0 = input.nextLine();
        String origin = origin0.replace(" ","+");
        System.out.print("Enter destination: ");
        String dest0 = input.nextLine();
        String dest = dest0.replace(" ","+");
        String apiReturn = connectToAPI(LINK, origin, dest);
        System.out.println(apiReturn);
    }
}
