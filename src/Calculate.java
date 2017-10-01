package Gastimator.src;

/**
 * @author Shiv Paul
 * @since 9/30/2017
 */

public class Calculate
{
    public static void main(String[] args)
    {
        double gasReq;

        GetCarInfo car = new GetCarInfo();
        GetDistance trip = new GetDistance();

        String make = car.getMake();
        String model = car.getModel();
        String year = car.getYear();

        String shineResponse = car.shineConnect(make, model, year);

        double CityMPG = car.getCityMPG(shineResponse);
        double HighwayMPG = car.getHighwayMPG(shineResponse);

        String origin = trip.getOrigin();
        String destination = trip.getDestination();

        String googleResponse = trip.googleMapsConnect(origin, destination);

        double distance = trip.parseAPIReturn(googleResponse);

        System.out.println("This trip is " + distance + " miles long!");

        if(distance>40)
        {
            gasReq = distance/CityMPG;
        }
        else
        {
            gasReq = distance/HighwayMPG;
        }

        System.out.println("You need " + gasReq + " gallons of gas for the trip!!");

    }
}
