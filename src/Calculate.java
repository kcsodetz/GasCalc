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

//        String make = "audi";
//        String model = "a5";
//        String year = "2015";

        String shineResponse = car.shineConnect(make, model, year);

        double CityMPG = car.getCityMPG(shineResponse);
        double HighwayMPG = car.getHighwayMPG(shineResponse);

        String origin = trip.getOrigin();
        String destination = trip.getDestination();

        String googleResponse = trip.googleMapsConnect(origin, destination);

        double distance = trip.parseDistance(googleResponse);

        System.out.println("This trip is " + distance + " miles!");

        if(distance>40)
        {
            gasReq = distance/HighwayMPG;
        }
        else
        {
            gasReq = distance/CityMPG;
        }

        double gasPrice = trip.getGasPrice(gasReq);

        System.out.printf("\nYou need %.2f gallons of gas for the trip\n", gasReq);
        System.out.printf("Estimated cost of gas for the trip will be $%.2f\n", gasPrice);
    }
}
