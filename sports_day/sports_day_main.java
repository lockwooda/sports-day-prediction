package sports_day;

/**
 * @author Alex Lockwood
 * @purpose The main class for the Sports Day Data Handler. Creates the necessary objects and boots it up.
 */

public class sports_day_main
{
    public static void main(String args[])
    {
        view theView = new view();
        model theModel = new model();

        controller theController = new controller(theModel, theView);
        theView.mainScreen();
    }
}