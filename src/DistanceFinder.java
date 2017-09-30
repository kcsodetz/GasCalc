import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/*
 * @author Shiv Paul
 * @since 9/30/2017
 */

public class DistanceFinder
{

    private static final String APIKEY = "AIzaSyD7GjH80EBchoi53fNvVRWGhBrWaPGP_iw";

    private DistanceFinder()
    {

    }

    /*
     * @param key API key
     * @return responseBody, string response of the API call
     */

    public static String connectToAPI(String link, String origin, String dest, String key)
    {
        InputStream response = null;
        URLConnection connection;
        try
        {
            connection = new URL(link+"origins="+origin+"&destinations="+dest+"&key="+key).openConnection();
            response = connection.getInputStream();
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        assert response != null;
        try(Scanner scanner = new Scanner(response))
        {
            String apiReturn = scanner.useDelimiter("\\A").next();
            return apiReturn;
        }
    }

    public static void main(String[] args)
    {
        String link = "https://maps.googleapis" +
                ".com/maps/api/distancematrix/json?units=imperial&";

        Scanner input = new Scanner(System.in);

        System.out.print("Enter origin: ");
        String origin0 = input.nextLine();
        String origin = origin0.replace(" ","+");

        System.out.print("Enter destination: ");
        String dest0 = input.nextLine();
        String dest = dest0.replace(" ","+");

        String apiReturn = connectToAPI(link, origin, dest, APIKEY);

        System.out.println(apiReturn);
    }
}
