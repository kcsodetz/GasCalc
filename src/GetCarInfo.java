package Gastimator.src;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Gets info on a specific car using Shine API
 */

public class GetCarInfo
{


    public final String APIKEY = "A8874x8oBWR0GdYXGccI2tYFFULXur7a";

    /**
     * Gets and parses the make of the vehicle
     * @return make of the vehicle
     */

    public  String getMake()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Make: ");
        String make0 = input.nextLine();
        return make0.trim();
    }

    /**
     * Gets and parses the model of the car
     * @return the mode of the car, minus any spaces
     */
    public String getModel()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Model: ");
        String model0 = input.nextLine();
        return model0.trim();
    }

    /**
     * Gets and checks the year from user input
     * @return year string
     */
    public String getYear()
    {
        Scanner input = new Scanner(System.in);
        String year0, year;
        boolean yearNum = true;
        int i;
        System.out.print("Year: ");
        year0 = input.nextLine();
        year = year0.trim();
        while(true)
        {
            for(i=0; i<year.length(); i++)
            {
                if(!Character.isDigit(year.charAt(i)))
                {
                    yearNum=false;
                    break;
                }
            }
            if(!yearNum)
            {
                System.out.print("Year: ");
                year0 = input.nextLine();
                year = year0.trim();
                yearNum = true;
            }
            else
            {
                break;
            }
        }
        return year;
    }

    /**
     * Sends the HTML request to get the vehicle data based on given parameters
     * @param make, make of the vehicle
     * @param model, model of the vehicle
     * @param year, year of the vehicle
     * @return responseBody, string response of the API call
     */
    public String shineConnect(String make, String model, String year)
    {
        String charset = "UTF-8";
        InputStream response = null;
        URLConnection connection;
        String url = "https://apis.solarialabs.com/shine/v1/vehicle-stats/specs?";
        try
        {
            connection = new URL(url+"make="+make+"&model="+model+"&year="+year+"&apikey="+APIKEY).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        {
            try(Scanner scanner = new Scanner(response))
            {
                if (scanner.useDelimiter("\\A").next().equals("[]"))
                {
                    String err = "The " + year + " " + make + " " + model + " doesn't exist";
                    return err;
                }
                else
                {
                    return scanner.useDelimiter("\\A").next();
                }
            }
        }
    }


    /**
     * Parses City MPG from api call
     * @param apiCallString api string to be parsed
     * @return city mpg as a double
     */
    public double getCityMPG(String apiCallString)
    {
        String response = apiCallString.substring(apiCallString.indexOf("City_Unadj_Conventional_Fuel"), apiCallString.indexOf("Hwy_Unadj_Conventional_Fuel"));
        response = response.substring(response.indexOf(":") + 1, response.indexOf(","));
        return Double.parseDouble(response);
    }

    /**
     * Parses Highway MPG from api call
     * @param apiCallString api string to be parsed
     * @return highway mpg as a double
     */
    public double getHighwayMPG(String apiCallString)
    {
        String response = apiCallString.substring(apiCallString.indexOf("Hwy_Unadj_Conventional_Fuel"), apiCallString.indexOf("Air_AspirMethod"));
        response = response.substring(response.indexOf(":") + 1, response.indexOf(","));
        return Double.parseDouble(response);
    }

}
