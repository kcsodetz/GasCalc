package Gastimator.src;


/**
 *
 * @author Shiv Paul
 * @since 9/30/2017
 */

public class Calculate
{
    static double getCityMPG()      //from APIDriver
    {
        return 30.0;
    }

    static double getHwMPG()        //from APIDriver
    {
        return 50.0;
    }

    static double getDistance()     //from DistanceFinder
    {
        return 1000.0;
    }

    public static void main(String[] args)
    {
        getCarInfo car;

        String make = car.getMake();
        String model = car.getModel();
        String year = car.getYear();

        String apiResponse = car.shineConnect(make, model, year);

        double CityMPG = car.getCityMPG(apiResponse);
        double HwMPG = car.getHighwayMPG(apiResponse);


    }
}
