import java.util.Scanner;

//Takes input while removing white spaces and checking for errors

class Input
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Make: ");
        String make0 = input.nextLine();
        String make = make0.trim();

        System.out.print("Model: ");
        String model0 = input.nextLine();
        String model = model0.trim();

        int i=0;
        boolean yearNum = true;

        String year0, year;

        System.out.print("Year: ");
        year0 = input.nextLine();
        year = year0.trim();

        while(yearNum)
        {
            for(i=0; i<year.length(); i++)
            {
                if(Character.isDigit(year.charAt(i)))
                {
                    i++;
                }
                else
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
    }
}
