package sports_day;

import java.util.Calendar;

/**
 * @author Alex Lockwood
 * @purpose Used as the Event object to store data being transferred to and from the database
 * @inputs Object orientation setters
 * @outputs Object orientation getters
 */

public class event
{
    private eventType etEvent;
    private boolean bElite;
    private int iYearOfEvent;
    private int iYearOfEntry;

    private boolean bCliffTeam;
    private boolean bDownsTeam;
    private boolean bFleetTeam;
    private boolean bHillTeam;
    private boolean bSchoolTeam;
    private boolean bTownTeam;

    //constructors
    public event()
    {
        reset();
    }

    public event(eventType et, boolean b, int i, int iY, boolean bC, boolean bD
            , boolean bF, boolean bH, boolean bS, boolean bT)
    {
        this();
        setEventType(et);
        setIfElite(b);
        setYearOfEvent(i);
        setYearOfEntry(iY);
        setCliffTeam(bC);
        setDownsTeam(bD);
        setFleetTeam(bF);
        setHillTeam(bH);
        setSchoolTeam(bS);
        setTownTeam(bT);
    }

    //setters
    public void setEventType(eventType et)
    {
        this.etEvent = et;
    }

    public void setIfElite(boolean b)
    {
        this.bElite = b;
    }

    public void setYearOfEvent(int i)
    {
        if(i <= Calendar.getInstance().get(Calendar.YEAR))
        {
            this.iYearOfEvent = i;
        }
    }

    public void setYearOfEntry(int i)
    {
        if(i <= Calendar.getInstance().get(Calendar.YEAR))
        {
            this.iYearOfEntry = i;
        }
    }

    public void setCliffTeam(boolean b) { this.bCliffTeam = b; }

    public void setDownsTeam(boolean b) { this.bDownsTeam = b; }

    public void setFleetTeam(boolean b) { this.bFleetTeam = b; }

    public void setHillTeam(boolean b) { this.bHillTeam = b; }

    public void setSchoolTeam(boolean b) { this.bSchoolTeam = b; }

    public void setTownTeam(boolean b) { this.bTownTeam = b; }

    //getters
    public eventType getEventType() { return etEvent; }

    public boolean isElite(){ return bElite; }

    public int getYearOfEvent() { return iYearOfEvent; }

    public int getYearOfEntry() { return iYearOfEntry; }

    public boolean getCliffTeam() { return bCliffTeam; }

    public boolean getDownsTeam() { return bDownsTeam; }

    public boolean getFleetTeam() { return bFleetTeam; }

    public boolean getHillTeam() { return bHillTeam; }

    public boolean getSchoolTeam() { return bSchoolTeam; }

    public boolean getTownTeam() { return bTownTeam; }

    //methods
    public void reset()
    {
        setEventType(eventType.HUNDRED_METRE);
        setIfElite(false);
        setYearOfEvent(1894);
        setYearOfEntry(1893);
        setCliffTeam(true);
        setDownsTeam(true);
        setFleetTeam(true);
        setHillTeam(true);
        setSchoolTeam(true);
        setTownTeam(true);
    }
}
