package Gastimator.src;

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
        double CityMPG = getCityMPG();
        double HwMPG = getHwMPG();
        double distance = getDistance();
        double gasReq;

        if(distance>40)
        {
            gasReq = distance/HwMPG;
        }
        else
        {
            gasReq = distance/CityMPG;
        }

        System.out.println("Gas required is " + gasReq+ " gallons.");
    }
}
