package sports_day;

/**
 * @author Alex Lockwood
 * @purpose Used to represent the possible positions made in sport's day
 * @inputs N/A
 * @outputs getValue(), fromInt()
 */
public enum position
{
    DID_NOT_FINISH(0)
        , FIRST(1)
        , SECOND(2)
        , THIRD(3)
        , FOURTH(4)
        , FIFTH(5)
        , SIXTH(6)
    ;
    private int iPosition;

    position(int p)
    {
        this.iPosition = p;
    }

    public int getValue()
    {
        return iPosition;
    }

    public static position fromInt(int i)
    {
        for(position b : position.values())
        {
            if(b.getValue() == i)
            {
                return b;
            }
        }
        return null;
    }
}
