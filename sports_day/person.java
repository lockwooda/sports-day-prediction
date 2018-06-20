package sports_day;

/**
 * @author Alex Lockwood
 * @purpose Used in order to hold the first name and surname of participants
 * @inputs Object orientation setters
 * @outputs Object orientation getters
 */

public class person
{
    private String szFirstName;
    private String szLastName;

    public person()
    {
        reset();
    }

    public person(String szFirstName, String szLastName)
    {
        this();
        setFirstName(szFirstName);
        setLastName(szLastName);
    }

    //setters
    public void setFirstName(String szFirstName)
    {
        this.szFirstName = szFirstName;
    }

    public void setLastName(String szLastName)
    {
        this.szLastName = szLastName;
    }

    //getters
    public String getFirstName()
    {
        return this.szFirstName;
    }

    public String getLastName()
    {
        return this.szLastName;
    }

    //other methods
    public void reset()
    {
        setFirstName("");
        setLastName("");
    }
}

