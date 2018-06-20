package sports_day;

/**
 * @author Alex Lockwood
 * @purpose Used to represent the possible events that can occur on sport's day
 * @inputs N/A
 * @outputs getValue(), fromInt()
 */

public enum eventType
{
    HUNDRED_METRE(1)
        , TWO_HUNDRED_METRE(2)
        , FOUR_HUNDRED_METRE(3)
        , EIGHT_HUNDRED_METRE(4)
        , FIFTEEN_HUNDRED_METRE(5)
        , EIGHT_BY_HUNDRED_METRE(6)
        , FOUR_BY_TWO_HUNDRED_METRE(7)
        , LONG_JUMP(8)
        , HIGH_JUMP(9)
        , JAVELIN(10)
        , SHOT_PUTT(11)
        , FOUR_HUNDRED_METRE_RELAY(12)
    ;
    private int iEventType;

    eventType(int iEventType)
    {
        this.iEventType = iEventType;
    }

    public int getValue()
    {
        return iEventType;
    }

    public static eventType fromInt(int i)
    {
        for(eventType b : eventType.values())
        {
            if(b.getValue() == i)
            {
                return b;
            }
        }
        return null;
    }

}
