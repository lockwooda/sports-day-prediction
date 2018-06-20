package sports_day;

/**
 * @author Alex Lockwood
 * @purpose Used to represents all the houses of the school
 * @inputs N/A
 * @outputs getValue(), fromInt()
 */

public enum house
{
    CLIFF(1), DOWNS(2), FLEET(3), HILL(4), SCHOOL(5), TOWN(6);
    private int iHouse;

    private house(int iHouse){
        this.iHouse = iHouse;
    }

    public int getValue()
    {
        return iHouse;
    }

    public static house fromInt(int i)
    {
        for(house b : house.values())
        {
            if(b.getValue() == i)
            {
                return b;
            }
        }
        return null;
    }
}
