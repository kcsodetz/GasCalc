import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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


    public APIDriver(){

    }

    //https://apis.solarialabs.com/shine/v1/vehicle-stats/specs?make={value}&model={value}&year={value}&full-data={value}&apikey={value}

    /**
     * Connects to API using an http request
     */
    public void connectToAPI(String url, String make, String model, String year, String key){
        String charset = "UTF-8";
        InputStream response = null;
        URLConnection connection = null;
        try {
            connection = new URL(url+"make="+make+"&model="+model+"&year="+year+"&apikey="+key).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Scanner scanner = new Scanner(response)){
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        APIDriver apiDriver = new APIDriver();
        String url = "https://apis.solarialabs.com/shine/v1/vehicle-stats/specs?";
        String make = "Honda";
        String model = "CR-V";
        String year = "2012";
        String APIKey = "A8874x8oBWR0GdYXGccI2tYFFULXur7a";
        apiDriver.connectToAPI(url, make, model, year, APIKey);
    }
}
