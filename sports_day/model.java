package sports_day;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Calendar;
import java.time.Duration;

/**
 * @author Alex Lockwood
 * @purpose Acts as a model that links the objects in Java to the tables in the MySQL database
 */
public class model
{
    /**
     * ======================================================================
     * CLASS VARIABLES
     * ======================================================================
     */

    private session dbSession;
    private static String sql;

    private static Statement sqlStatement = null;
    private static ResultSet resultSet = null;

    private LinkedList<person> pCliffTeam = new LinkedList<person>();
    private LinkedList<person> pDownsTeam = new LinkedList<person>();
    private LinkedList<person> pFleetTeam = new LinkedList<person>();
    private LinkedList<person> pHillTeam = new LinkedList<person>();
    private LinkedList<person> pSchoolTeam = new LinkedList<person>();
    private LinkedList<person> pTownTeam = new LinkedList<person>();

    private String szFirstResult;
    private String szSecondResult;
    private String szThirdResult;

    /**
     * ======================================================================
     * CONSTRUCTOR
     * ======================================================================
     */

    public model()
    {
        dbSession = new session();
    }

    /**
     * ======================================================================
     * METHODS, GETTERS, SETTERS
     * ======================================================================
     */

    /**
     * ======================================================================
     * ADD VIEW SELECTION TO RESULT OBJECT
     * Inputs: iEvent, iElite, iYear, iCliffPosition to iTownPosition (all integers)
     * Outputs: r (result)
     *          null if any input not accepted
     * Comments: Converts the choices made by the user into a result.
     * ======================================================================
     */
    public result addViewSelectionToResult(
            int iEvent
            , int iElite
            , int iYear
            , int iCliffPosition
            , int iDownsPosition
            , int iFleetPosition
            , int iHillPosition
            , int iSchoolPosition
            , int iTownPosition
    )
    {
        int i, iCurrentYear, iYearOfEntry = 1970;
        result r = new result();
        event e = new event();

        e.setEventType(eventType.fromInt(iEvent + 1));
        switch (iElite)
        {
            case 0:
                e.setIfElite(false);
                break;
            case 1:
                e.setIfElite(true);
                break;
            default:
                e.setIfElite(false);
                break;
        }
        iCurrentYear = Calendar.getInstance().get(Calendar.YEAR);

        //assumes that sports day is always at the end of the academic year in the summer
        switch (iYear)
        {
            //year 7
            case 0:
                iYearOfEntry = iCurrentYear - 1;
                break;
            //year 8
            case 1:
                iYearOfEntry = iCurrentYear - 2;
                break;
            //year 9
            case 2:
                iYearOfEntry = iCurrentYear - 3;
                break;
            //year 10
            case 3:
                iYearOfEntry = iCurrentYear - 4;
                break;
            //year 12
            case 4:
                iYearOfEntry = iCurrentYear - 6;
                break;
            default:
                iYearOfEntry = iCurrentYear - 1;
                break;
        }

        e.setYearOfEvent(iCurrentYear);
        e.setYearOfEntry(iYearOfEntry);

        //set cliff position
        if((iCliffPosition - 1) >= 0)
        {
            e.setCliffTeam(true);
            r.setCliffPosition(position.fromInt(iCliffPosition - 1));
        }
        if(iCliffPosition == 0)
        {
            e.setCliffTeam(false);
            r.setCliffPosition(position.DID_NOT_FINISH);
        }

        //set downs position
        if((iDownsPosition - 1) >= 0)
        {
            e.setDownsTeam(true);
            r.setDownsPosition(position.fromInt(iDownsPosition - 1));
        }
        if(iDownsPosition == 0)
        {
            e.setDownsTeam(false);
            r.setDownsPosition(position.DID_NOT_FINISH);
        }

        //set fleet position
        if((iFleetPosition - 1) >= 0)
        {
            e.setFleetTeam(true);
            r.setFleetPosition(position.fromInt(iFleetPosition - 1));
        }
        if(iFleetPosition == 0)
        {
            e.setFleetTeam(false);
            r.setFleetPosition(position.DID_NOT_FINISH);
        }

        //set hill position
        if((iHillPosition - 1) >= 0)
        {
            e.setHillTeam(true);
            r.setHillPosition(position.fromInt(iHillPosition - 1));
        }
        if(iHillPosition == 0)
        {
            e.setHillTeam(false);
            r.setHillPosition(position.DID_NOT_FINISH);
        }

        //set school position
        if((iSchoolPosition - 1) >= 0)
        {
            e.setSchoolTeam(true);
            r.setSchoolPosition(position.fromInt(iSchoolPosition - 1));
        }
        if(iSchoolPosition == 0)
        {
            e.setSchoolTeam(false);
            r.setSchoolPosition(position.DID_NOT_FINISH);
        }

        //set town position
        if((iTownPosition - 1) >= 0)
        {
            e.setTownTeam(true);
            r.setTownPosition(position.fromInt(iTownPosition - 1));
        }
        if(iTownPosition == 0)
        {
            e.setTownTeam(false);
            r.setTownPosition(position.DID_NOT_FINISH);
        }

        r.setEvent(e);

        r.setFirstResult(this.getFirstPlaceResult());
        r.setSecondResult(this.getSecondPlaceResult());
        r.setThirdResult(this.getThirdPlaceResult());

        //ADD IN TEAMS FROM PREVIOUSLY ADDED TEAMS
        switch(r.getCliffPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pCliffTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pCliffTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pCliffTeam.get(i));
                }
                break;
        }

        switch(r.getDownsPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pDownsTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pDownsTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pDownsTeam.get(i));
                }
                break;
        }

        switch(r.getFleetPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pFleetTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pFleetTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pFleetTeam.get(i));
                }
                break;
        }

        switch(r.getHillPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pHillTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pHillTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pHillTeam.get(i));
                }
                break;
        }

        switch(r.getSchoolPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pSchoolTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pSchoolTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pSchoolTeam.get(i));
                }
                break;
        }

        switch(r.getTownPosition())
        {
            case FIRST:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToFirstPlace(pTownTeam.get(i));
                }
                break;
            case SECOND:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToSecondPlace(pTownTeam.get(i));
                }
                break;
            case THIRD:
                for (i = 0; i < getParticipantCount(r.getEvent().getEventType()) ; i++)
                {
                    r.addToThirdPlace(pTownTeam.get(i));
                }
                break;
        }

        return r;
    }

    /**
     * ======================================================================
     * SET TEAMS
     * Inputs: szF (String), szS (String)
     * Outputs: N/A
     * Comments: Converts the two names inputted into people that are added to a LinkedList
     * ======================================================================
     */

    public void addToTeam(house h, String szF, String szS)
    {
        person p = new person(szF, szS);

        switch(h)
        {
            case CLIFF:
                this.pCliffTeam.add(p);
                break;
            case DOWNS:
                this.pDownsTeam.add(p);
                break;
            case FLEET:
                this.pFleetTeam.add(p);
                break;
            case HILL:
                this.pHillTeam.add(p);
                break;
            case SCHOOL:
                this.pSchoolTeam.add(p);
                break;
            case TOWN:
                this.pTownTeam.add(p);
                break;
            default:
                return;
        }
    }

    /**
     * ======================================================================
     * CLEAR TEAMS
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Clears all teams
     * ======================================================================
     */
    public void clearCliffTeam()
    {
        pCliffTeam.clear();
    }

    public void clearDownsTeam()
    {
        pDownsTeam.clear();
    }

    public void clearFleetTeam()
    {
        pFleetTeam.clear();
    }

    public void clearHillTeam()
    {
        pHillTeam.clear();
    }

    public void clearSchoolTeam()
    {
        pSchoolTeam.clear();
    }

    public void clearTownTeam()
    {
        pTownTeam.clear();
    }

    /**
     * ======================================================================
     * SET TIMINGS
     * Inputs: szM (String), szs (String), szms (String)
     * Outputs: N/A
     * Comments: Takes in the user's input for timings and formats it into a timing
     * ======================================================================
     */
    public void setFirstPlace(String szM, String szs, String szms)
    {
        this.szFirstResult = szM + ":" + szs + ":" + szms;
    }

    public void setSecondPlace(String szM, String szs, String szms)
    {
        this.szSecondResult = szM + ":" + szs + ":" + szms;
    }

    public void setThirdPlace(String szM, String szs, String szms)
    {
        this.szThirdResult = szM + ":" + szs + ":" + szms;
    }

    /**
     * ======================================================================
     * SET DISTANCES
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Takes in the user's input for distances and formats it into a distance
     * ======================================================================
     */
    public void setFirstPlace(String szm, String szcm)
    {
        this.szFirstResult = szm + "." + szcm;
    }

    public void setSecondPlace(String szm, String szcm)
    {
        this.szSecondResult = szm + "." + szcm;
    }

    public void setThirdPlace(String szm, String szcm)
    {
        this.szThirdResult = szm + "." + szcm;
    }

    /**
     * ======================================================================
     * CLEAR RESULTS
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Clears any distances or timings set by the user
     * ======================================================================
     */
    public void clearFirstPlace()
    {
        this.szFirstResult = "";
    }

    public void clearSecondPlace()
    {
        this.szSecondResult = "";
    }

    public void clearThirdPlace()
    {
        this.szThirdResult = "";
    }

    /**
     * ======================================================================
     * GET RESULTS
     * Inputs: N/A
     * Outputs: Result (String)
     * Comments: Gets the distance/timing of the position in question
     * ======================================================================
     */

    public String getFirstPlaceResult()
    {
        return this.szFirstResult;
    }

    public String getSecondPlaceResult()
    {
        return this.szSecondResult;
    }

    public String getThirdPlaceResult()
    {
        return this.szThirdResult;
    }

    /**
     * ======================================================================
     * GET PARTICIPANT COUNT
     * Inputs: et (eventType)
     * Outputs: int
     * Comments: Gets the amount of people per team based upon the eventType
     * ======================================================================
     */
    public int getParticipantCount(eventType et)
    {
        int iParticipants = 0;
        switch(et)
        {
            case HUNDRED_METRE:
            case TWO_HUNDRED_METRE:
            case FOUR_HUNDRED_METRE:
            case EIGHT_HUNDRED_METRE:
            case FIFTEEN_HUNDRED_METRE:
            case LONG_JUMP:
            case HIGH_JUMP:
            case JAVELIN:
            case SHOT_PUTT:
                iParticipants = 1;
                break;
            case FOUR_BY_TWO_HUNDRED_METRE:
                iParticipants = 4;
                break;
            case EIGHT_BY_HUNDRED_METRE:
                iParticipants = 8;
                break;
            //will ignore participants
            case FOUR_HUNDRED_METRE_RELAY:
                iParticipants = 0;
                break;
            default:
                System.err.println(this.getClass().getName() + ".java :: Event Type not found");
                break;
        }

        return iParticipants;
    }

    /**
     * ======================================================================
     * ADD FINISHED EVENT
     * Inputs: r (result)
     * Outputs: N/A
     * Comments: Adds a finished result to the database
     * ======================================================================
     */
    public void addFinishedEvent(result r)
    {
        int iParticipants = 0;
        int i;
        LinkedList<Integer> iTeamID = new LinkedList<Integer>();

        getSession().connect();
        if(!getSession().isConnected())
        {
            System.err.println(this.getClass().getName() + ".java :: Failed to connect to database.");
            return;
        }

        try
        {
            sql = "INSERT INTO event "
                    + "(competition, elite, eventYear, yearOfEntry, cliffTeam"
                    + ", downsTeam, fleetTeam, hillTeam, schoolTeam, townTeam) "
                    + "VALUES ("
                    + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEvent()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + r.getEvent().getCliffTeam()
                    + ", " + r.getEvent().getDownsTeam()
                    + ", " + r.getEvent().getFleetTeam()
                    + ", " + r.getEvent().getHillTeam()
                    + ", " + r.getEvent().getSchoolTeam()
                    + ", " + r.getEvent().getTownTeam()
                    + ")"
                    + " ON DUPLICATE KEY UPDATE"
                    + " cliffTeam = " + r.getEvent().getCliffTeam()
                    + ", downsTeam = " + r.getEvent().getDownsTeam()
                    + ", fleetTeam = " + r.getEvent().getFleetTeam()
                    + ", hillTeam = " + r.getEvent().getHillTeam()
                    + ", schoolTeam = " + r.getEvent().getSchoolTeam()
                    + ", townTeam = " + r.getEvent().getTownTeam()
                    + ";"
            ;

            sqlStatement = getSession().getConnection().createStatement();
            sqlStatement.executeUpdate(sql);


            iParticipants = getParticipantCount(r.getEvent().getEventType());

            //if the event is not the sixth form 400m relay, must have participants
            if(iParticipants != 0)
            {
                sql = "INSERT INTO people "
                        + "(firstname, surname) VALUES ";

                //if one person per team
                if(iParticipants == 1)
                {
                    sql += "(\""
                            + r.getFirstPlacePerson().getFirstName() + "\", \""
                            + r.getFirstPlacePerson().getLastName() + "\"), ";

                    //if tied, add another person to first place
                    if(r.getSecondPlacePerson() == null)
                    {
                        sql += "(\"" + r.getFirstPlacePerson(1).getFirstName() + "\", \""
                              + r.getFirstPlacePerson(1).getLastName() + "\")";
                    }
                    //if not tied, add person to second place
                    if(r.getSecondPlacePerson() != null)
                    {
                        sql += "(\"" + r.getSecondPlacePerson().getFirstName() + "\", \""
                                + r.getSecondPlacePerson().getLastName() + "\")";
                    }

                    if(r.getThirdPlacePerson() != null)
                    {
                        //add third place person
                        sql += ", (\"" + r.getThirdPlacePerson().getFirstName() + "\", \""
                                + r.getThirdPlacePerson().getLastName() + "\")";
                    }

                    //if a tie occurs on third add to third place
                    if(r.getThirdPlacePerson(1) != null)
                    {
                        sql += ", (\"" + r.getThirdPlacePerson(1).getFirstName() + "\", \""
                                + r.getThirdPlacePerson(1).getLastName() + "\")";
                    }
                }
                //if more than one person per team
                if(iParticipants > 1)
                {
                    for (i = 0; i < (iParticipants); i++)
                    {
                        sql += "(\"" + r.getFirstPlacePerson(i).getFirstName()
                                + "\", \"" + r.getFirstPlacePerson(i).getLastName() + "\"), ";
                    }
                    if(r.getSecondPlacePerson() == null)
                    {
                        for (i = iParticipants; i < (iParticipants * 2); i++)
                        {
                            sql += "(\"" + r.getFirstPlacePerson(i).getFirstName()
                                    + "\", \"" + r.getFirstPlacePerson(i).getLastName() + "\"), ";
                        }
                    }
                    if(r.getSecondPlacePerson() != null)
                    {
                        for (i = 0; i < (iParticipants); i++)
                        {
                            sql += "(\"" + r.getSecondPlacePerson(i).getFirstName()
                                    + "\", \"" + r.getSecondPlacePerson(i).getLastName() + "\")";
                            if(i != iParticipants - 1)
                            {
                                sql += ", ";
                            }
                        }
                    }
                    if(r.getThirdPlacePerson() != null)
                    {
                        sql += ", ";
                        for (i = 0; i < (iParticipants); i++)
                        {
                            sql += "(\"" + r.getThirdPlacePerson(i).getFirstName()
                                    + "\", \"" + r.getThirdPlacePerson(i).getLastName() + "\")";
                            if (i != iParticipants - 1)
                            {
                                sql += ", ";
                            }
                        }
                    }
                    if(r.getThirdPlacePerson(iParticipants) != null)
                    {
                        sql += ", ";
                        for (i = iParticipants; i < (iParticipants * 2); i++)
                        {
                            sql += "(\"" + r.getThirdPlacePerson(i).getFirstName()
                                    + "\", \"" + r.getThirdPlacePerson(i).getLastName() + "\")";
                            if(i != iParticipants - 1)
                            {
                                sql += ", ";
                            }
                        }
                    }

                }
                sql += " ON DUPLICATE KEY UPDATE "
                        + "firstname = VALUES(firstname)"
                        + ", surname = VALUES(surname)";

                System.err.println(sql);
                sqlStatement = getSession().getConnection().createStatement();
                sqlStatement.executeUpdate(sql);

                //insert student team into database
                sql = "INSERT INTO studentTeam (peopleID1";
                if(iParticipants > 3)
                {
                    sql += ", peopleID2, peopleID3, peopleID4";
                    if(iParticipants > 7)
                    {
                        sql += ", peopleID5, peopleID6, peopleID7, peopleID8";
                    }
                }

                sql += ") VALUES (";

                //if second place present
                if(r.getSecondPlacePerson() != null)
                {
                    for (i = 0; i < iParticipants; i++)
                    {
                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getFirstPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getFirstPlacePerson(i).getLastName() + "\")";

                        if(i != (iParticipants - 1))
                        {
                            sql += ", ";
                        }
                        if(i == (iParticipants - 1))
                        {
                            sql += "), (";
                        }
                    }

                    for (i = 0; i < iParticipants; i++)
                    {
                        sql = "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getSecondPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getSecondPlacePerson(i).getLastName() + "\")";

                        if(i != (iParticipants - 1))
                        {
                            sql += ", ";
                        }
                        if(i == (iParticipants - 1))
                        {
                            sql += "), (";
                        }
                    }
                }

                //if first place tied
                if(r.getSecondPlacePerson() == null)
                {
                    for (i = 0; i < iParticipants * 2; i++)
                    {
                        sql = "(SELECT peopleID FROM people WHERE firstName = \"" +
                                r.getFirstPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getFirstPlacePerson(i).getLastName() + "\")";

                        if(i != (iParticipants - 1))
                        {
                            sql += ", ";
                        }
                        if(i == (iParticipants - 1) || i == ((iParticipants * 2) - 1))
                        {
                            sql += "), (";
                        }
                    }
                }

                //if third place is present
                if(r.getThirdPlacePerson() != null)
                {
                    for (i = 0; i < iParticipants; i++)
                    {
                        sql = "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getThirdPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getThirdPlacePerson(i).getLastName() + "\")";

                        if(i != (iParticipants - 1))
                        {
                            sql += ", ";
                        }
                        if(i == (iParticipants - 1))
                        {
                            sql += ")";
                        }
                    }
                }

                //if third place is present and tied
                if(r.getThirdPlacePerson(iParticipants) != null)
                {
                    sql += ", (";

                    for(i = iParticipants ; i < (iParticipants * 2) ; i++)
                    {
                        sql = "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getThirdPlacePerson(i).getFirstName()
                                +  "\" AND surname = \""
                                + r.getThirdPlacePerson(i).getLastName() + "\")";

                        if(i != ((iParticipants * 2) - 1))
                        {
                            sql += ", ";
                        }
                        if(i == ((iParticipants * 2) - 1))
                        {
                            sql += ")";
                        }
                    }
                }

                sql += " ON DUPLICATE KEY UPDATE peopleID1 = VALUES(peopleID1)";
                if(iParticipants > 3)
                {
                    sql += ", peopleID2 = VALUES(peopleID2)" +
                            ", peopleID3 = VALUES(peopleID3)" +
                            ", peopleID4 = VALUES(peopleID4)";
                    if(iParticipants > 7)
                    {
                        sql += ", peopleID5 = VALUES(peopleID5)" +
                                ", peopleID6 = VALUES(peopleID6)" +
                                ", peopleID7 = VALUES(peopleID7)" +
                                ", peopleID8 = VALUES(peopleID8)";
                    }
                }

                sql += ";";

                //get team ids
                //if second place present
                if(r.getSecondPlacePerson() != null)
                {
                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = 0 ; i < iParticipants ; i++)
                    {
                        if(i != 0)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i+1) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getFirstPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getFirstPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }

                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = 0 ; i < iParticipants ; i++)
                    {
                        if(i != 0)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i+1) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getSecondPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getSecondPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }
                }

                //if first place tied
                if(r.getSecondPlacePerson() == null)
                {
                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = 0 ; i < iParticipants ; i++)
                    {
                        if(i != 0)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i+1) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getFirstPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getFirstPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }

                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = iParticipants ; i < iParticipants * 2 ; i++)
                    {
                        if(i != iParticipants)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i - (iParticipants - 1)) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getFirstPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getFirstPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }
                }

                if(r.getThirdPlacePerson() != null)
                {
                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = 0 ; i < iParticipants ; i++)
                    {
                        if(i != 0)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i+1) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getThirdPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getThirdPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }
                }

                if(r.getThirdPlacePerson(iParticipants) != null)
                {
                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = 0 ; i < iParticipants ; i++)
                    {
                        if(i != 0)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i+1) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getThirdPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getThirdPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }

                    sql = "SELECT studentTeamID FROM studentTeam WHERE ";
                    for(i = iParticipants ; i < iParticipants * 2 ; i++)
                    {
                        if(i != iParticipants)
                        {
                            sql += " AND ";
                        }

                        sql += "peopleID" + (i - (iParticipants - 1)) + " = ";

                        sql += "(SELECT peopleID FROM people WHERE firstName = \""
                                + r.getThirdPlacePerson(i).getFirstName()
                                + "\" AND surname = \""
                                + r.getThirdPlacePerson(i).getLastName() + "\")";
                    }

                    if(iParticipants == 1)
                    {
                        sql += " AND peopleID2 IS NULL";
                    }
                    if(iParticipants == 4)
                    {
                        sql += " AND peopleID5 IS NULL";
                    }

                    sql += " LIMIT 1;";
                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        do
                        {
                            iTeamID.add(resultSet.getInt("studentTeamID"));
                        }
                        while (resultSet.next());
                    }
                }
            }

            //add team to result table
            switch(iParticipants)
            {
                case 0:
                    sql = "INSERT INTO results "
                            + "(competition, elite, eventYear, yearOfEntry, cliffPosition, downsPosition" +
                            ", fleetPosition, hillPosition, schoolPosition, townPosition" +
                            ", firstPlace, secondPlace, thirdPlace)"
                            + " VALUES "
                            + "(" + r.getEvent().getEventType().getValue()
                            + ", " + r.getEvent().isElite()
                            + ", " + r.getEvent().getYearOfEvent()
                            + ", " + r.getEvent().getYearOfEntry()
                            + ", " + r.getCliffPosition().getValue()
                            + ", " + r.getDownsPosition().getValue()
                            + ", " + r.getFleetPosition().getValue()
                            + ", " + r.getHillPosition().getValue()
                            + ", " + r.getSchoolPosition().getValue()
                            + ", " + r.getTownPosition().getValue()
                            + ", \"" + r.getFirstResult() + "\""
                            + ", \"" + r.getSecondResult() + "\""
                            + ", \"" + r.getThirdResult() + "\""
                            + ")"
                            + " ON DUPLICATE KEY UPDATE"
                            + " cliffPosition = " + r.getCliffPosition().getValue()
                            + ", downsPosition = " + r.getDownsPosition().getValue()
                            + ", fleetPosition = " + r.getFleetPosition().getValue()
                            + ", hillPosition = " + r.getHillPosition().getValue()
                            + ", schoolPosition = " + r.getSchoolPosition().getValue()
                            + ", townPosition = " + r.getTownPosition().getValue()
                            + ", firstPlace = \"" + r.getFirstResult() + "\""
                            + ", secondPlace = \"" + r.getSecondResult() + "\""
                            + ", thirdPlace = \"" + r.getThirdResult() + "\""
                            + ";";
                    break;
                case 1:
                case 4:
                case 8:
                    sql = "INSERT INTO results (competition, elite, eventYear, yearOfEntry, cliffPosition" +
                            ", downsPosition, fleetPosition, hillPosition, schoolPosition" +
                            ", townPosition, firstTeam, secondTeam, ";
                    if(iTeamID.size() > 2)
                    {
                        sql += "thirdTeam, ";
                    }
                    if(iTeamID.size() > 3)
                    {
                        sql += "tiedTeam, ";
                    }
                    sql += "firstPlace, secondPlace, thirdPlace)"
                            + " VALUES "
                            + "(" + r.getEvent().getEventType().getValue()
                            + ", " + r.getEvent().isElite()
                            + ", " + r.getEvent().getYearOfEvent()
                            + ", " + r.getEvent().getYearOfEntry()
                            + ", " + r.getCliffPosition().getValue()
                            + ", " + r.getDownsPosition().getValue()
                            + ", " + r.getFleetPosition().getValue()
                            + ", " + r.getHillPosition().getValue()
                            + ", " + r.getSchoolPosition().getValue()
                            + ", " + r.getTownPosition().getValue()
                            + ", " + iTeamID.get(0)
                            + ", " + iTeamID.get(1);
                        if(iTeamID.size() > 2)
                        {
                            sql += ", " + iTeamID.get(2);
                        }
                        if(iTeamID.size() > 3)
                        {
                            sql += ", " + iTeamID.get(3);
                        }
                            sql += ", \"" + r.getFirstResult() + "\""
                            + ", \"" + r.getSecondResult() + "\""
                            + ", \"" + r.getThirdResult() + "\""
                            + ")"
                            + " ON DUPLICATE KEY UPDATE"
                            + " cliffPosition = " + r.getCliffPosition().getValue()
                            + ", downsPosition = " + r.getDownsPosition().getValue()
                            + ", fleetPosition = " + r.getFleetPosition().getValue()
                            + ", hillPosition = " + r.getHillPosition().getValue()
                            + ", schoolPosition = " + r.getSchoolPosition().getValue()
                            + ", townPosition = " + r.getTownPosition().getValue()
                            + ", firstTeam = " + iTeamID.get(0)
                            + ", secondTeam = " + iTeamID.get(1);
                        if(iTeamID.size() > 2)
                        {
                            sql += ", thirdTeam = " + iTeamID.get(2);
                        }
                        if(iTeamID.size() > 3)
                        {
                            sql += ", tiedTeam = " + iTeamID.get(3);
                        }
                            sql += ", firstPlace = \"" + r.getFirstResult() + "\""
                            + ", secondPlace = \"" + r.getSecondResult() + "\""
                            + ", thirdPlace = \"" + r.getThirdResult() + "\""
                            + ";";
                    break;
                default:
                    System.err.println(this.getClass().getName() + ".java :: Participant count not found.");
                    break;
            }

            System.err.println(sql);
            sqlStatement = getSession().getConnection().createStatement();
            sqlStatement.executeUpdate(sql);

            if(iParticipants != 0)
            {
                updateElo(r);
            }

            resultSet.close();
        }
        catch(SQLException se)
        {
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
        }
        catch(Exception e)
        {
            System.err.println(this.getClass().getName() + ".java :: Exception: " + e );
        }
    }

    /**
     * ======================================================================
     * CHECK FOR RECORD
     * Inputs: r (result)
     * Outputs: boolean
     * Comments: Checks to see if the result given is a record breaker from that year backwards
     * ======================================================================
     */

    public boolean checkForRecord(result r)
    {
        int iYear = r.getEvent().getYearOfEvent(), i = 0;
        boolean bYearPresent = true;
        boolean bRecordBreaker = true;
        result rCheck;
        String szCheck;
        Duration dCheckTime = Duration.ZERO;
        Duration dQueriedTime;

        float fCheckLength = 0;
        float fQueriedLength;

        if(r.getFirstResult().contains("."))
        {
            //convert directly into a float
            fCheckLength = Float.parseFloat(r.getFirstResult());
        }
        if(r.getFirstResult().contains(":"))
        {
            //format into a duration type
            dCheckTime = formatTiming(r.getFirstResult());
        }

        //check opposite participation of current year
        rCheck = this.getResultFromDB(r.getEvent().getEventType(), !r.getEvent().isElite()
                , iYear, r.getEvent().getYearOfEntry());
        //if this result is present, check to see if its faster
        if(rCheck != null)
        {
            szCheck = rCheck.getFirstResult();
            if(szCheck.contains(":"))
            {
                dQueriedTime = formatTiming(szCheck);

                if(dCheckTime.compareTo(dQueriedTime) < 0)
                {
                    bRecordBreaker = true;
                }
                if(dCheckTime.compareTo(dQueriedTime) >= 0)
                {
                    bRecordBreaker = false;
                    getSession().disconnect();
                    return bRecordBreaker;
                }
            }
            if(szCheck.contains("."))
            {
                fQueriedLength = Float.parseFloat(szCheck);

                if(fCheckLength > fQueriedLength)
                {
                    bRecordBreaker = true;
                }
                if(fCheckLength <= fQueriedLength)
                {
                    bRecordBreaker = false;
                    getSession().disconnect();
                    return bRecordBreaker;
                }
            }
        }

        while(bYearPresent)
        {
            iYear--;
            i++;

            rCheck = this.getResultFromDB(r.getEvent().getEventType()
                    , r.getEvent().isElite(), iYear, r.getEvent().getYearOfEntry() - i);
            if(rCheck == null)
            {
                bYearPresent = false;
                continue;
            }

            szCheck = rCheck.getFirstResult();
            if(szCheck.contains(":"))
            {
                dQueriedTime = formatTiming(szCheck);

                if(dCheckTime.compareTo(dQueriedTime) < 0)
                {
                    bRecordBreaker = true;
                }
                if(dCheckTime.compareTo(dQueriedTime) >= 0)
                {
                    bRecordBreaker = false;
                    bYearPresent = false;
                }
            }
            if(szCheck.contains("."))
            {
                fQueriedLength = Float.parseFloat(szCheck);

                if(fCheckLength > fQueriedLength)
                {
                    bRecordBreaker = true;
                }
                if(fCheckLength <= fQueriedLength)
                {
                    bRecordBreaker = false;
                    bYearPresent = false;
                }
            }

            rCheck = this.getResultFromDB(r.getEvent().getEventType(), !r.getEvent().isElite()
                    , iYear, r.getEvent().getYearOfEntry() - i);
            if(rCheck == null)
            {
                bYearPresent = false;
                continue;
            }

            szCheck = rCheck.getFirstResult();
            if(szCheck.contains(":"))
            {
                dQueriedTime = formatTiming(szCheck);

                if(dCheckTime.compareTo(dQueriedTime) < 0)
                {
                    bRecordBreaker = true;
                }
                if(dCheckTime.compareTo(dQueriedTime) >= 0)
                {
                    bRecordBreaker = false;
                    bYearPresent = false;
                    continue;
                }
            }
            if(szCheck.contains("."))
            {
                fQueriedLength = Float.parseFloat(szCheck);

                if(fCheckLength > fQueriedLength)
                {
                    bRecordBreaker = true;
                }
                if(fCheckLength <= fQueriedLength)
                {
                    bRecordBreaker = false;
                    bYearPresent = false;
                    continue;
                }
            }
        }

        getSession().disconnect();
        return bRecordBreaker;
    }

    public Duration formatTiming(String szTiming)
    {
        String [] szSplit;
        szSplit = szTiming.split(":");
        Duration dFormattedTime;

        if(szTiming.length() > 4)
        {
            dFormattedTime = Duration.parse("PT" + szSplit[0] + "M" + szSplit[1] + "." + szSplit[2] + "S");
        }
        else
        {
            dFormattedTime = Duration.parse("PT" + szSplit[0] + "." + szSplit[1] + "S");
        }

        return dFormattedTime;
    }

    /**
     * ======================================================================
     * GET RESULT FROM DB
     * Inputs: et (eventType), el (boolean), evY (int), yE (int)
     * Outputs: result
     *          null if not existant
     * Comments: Gets a result from the database
     * ======================================================================
     */

    public result getResultFromDB(eventType et, boolean el, int evY, int yE)
    {
        event evT = new event();
        result r = new result();

        //add already known variables in
        evT.setEventType(et);
        evT.setIfElite(el);
        evT.setYearOfEvent(evY);
        evT.setYearOfEntry(yE);

        int iFirstID = 0, iSecondID = 0, iThirdID = 0, iTiedID = 0, iParticipant, i;
        LinkedList<Integer> iFirstPeopleID = new LinkedList<Integer>();
        LinkedList<Integer> iSecondPeopleID = new LinkedList<Integer>();
        LinkedList<Integer> iThirdPeopleID = new LinkedList<Integer>();

        try
        {
            getSession().connect();
            if (!getSession().isConnected())
            {
                System.err.println(this.getClass().getName() + ".java :: Failed to connect to database.");
                getSession().disconnect();
                return null;
            }

            //get variables for event and result
            sql = "SELECT "
                    + "e1.cliffTeam AS cliffTeam"
                    + ", e1.downsTeam AS downsTeam"
                    + ", e1.fleetTeam AS fleetTeam"
                    + ", e1.hillTeam AS hillTeam"
                    + ", e1.schoolTeam AS schoolTeam"
                    + ", e1.townTeam AS townTeam"
                    + ", r.cliffPosition AS cliffPosition"
                    + ", r.downsPosition AS downsPosition"
                    + ", r.fleetPosition AS fleetPosition"
                    + ", r.hillPosition AS hillPosition"
                    + ", r.schoolPosition AS schoolPosition"
                    + ", r.townPosition AS townPosition"
                    + ", r.firstPlace AS firstPlace"
                    + ", r.secondPlace AS secondPlace"
                    + ", r.thirdPlace AS thirdPlace";
            if(et != eventType.FOUR_HUNDRED_METRE_RELAY)
            {
                sql += ", r.firstTeam AS firstTeam"
                        + ", r.secondTeam AS secondTeam"
                        + ", r.thirdTeam AS thirdTeam"
                        + ", r.tiedTeam AS tiedTeam";
            }

            sql += " FROM results AS r"
                    + " INNER JOIN"
                    + " event AS e1"
                    + " ON"
                    + " r.competition = e1.competition"
                    + " AND r.elite = e1.elite"
                    + " AND r.eventYear = e1.eventYear"
                    + " AND r.yearOfEntry = e1.yearOfEntry"
                    + " WHERE "
                    + "r.competition = " + et.getValue()
                    + " AND r.elite = " + el
                    + " AND r.eventYear = " + evY
                    + " AND r.yearOfEntry = " + yE
                    + ";";
            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            if(!resultSet.next())
            {
                getSession().disconnect();
                return null;
            }
            else
            {
                do
                {
                    evT.setCliffTeam(resultSet.getBoolean("cliffTeam"));
                    evT.setDownsTeam(resultSet.getBoolean("downsTeam"));
                    evT.setFleetTeam(resultSet.getBoolean("fleetTeam"));
                    evT.setHillTeam(resultSet.getBoolean("hillTeam"));
                    evT.setSchoolTeam(resultSet.getBoolean("schoolTeam"));
                    evT.setTownTeam(resultSet.getBoolean("townTeam"));

                    r.setCliffPosition(position.fromInt(resultSet.getInt("cliffPosition")));
                    r.setDownsPosition(position.fromInt(resultSet.getInt("downsPosition")));
                    r.setFleetPosition(position.fromInt(resultSet.getInt("fleetPosition")));
                    r.setHillPosition(position.fromInt(resultSet.getInt("hillPosition")));
                    r.setSchoolPosition(position.fromInt(resultSet.getInt("schoolPosition")));
                    r.setTownPosition(position.fromInt(resultSet.getInt("townPosition")));
                    r.setFirstResult(resultSet.getString("firstPlace"));
                    r.setSecondResult(resultSet.getString("secondPlace"));
                    r.setThirdResult(resultSet.getString("thirdPlace"));

                    if(et != eventType.FOUR_HUNDRED_METRE_RELAY)
                    {
                        iFirstID = resultSet.getInt("firstTeam");
                        iSecondID = resultSet.getInt("secondTeam");
                        iThirdID = resultSet.getInt("thirdTeam");
                        iTiedID = resultSet.getInt("tiedTeam");
                    }
                }
                while(resultSet.next());
            }

            r.setEvent(evT);

            //get student ids, then get their details and add them to an array.
            if(et != eventType.FOUR_HUNDRED_METRE_RELAY)
            {
                iParticipant = getParticipantCount(et);

                sql = "SELECT * FROM studentTeam WHERE "
                        + "studentTeamID = " + iFirstID + ";";

                resultSet = sqlStatement.executeQuery(sql);

                while(resultSet.next())
                {
                    switch (iParticipant)
                    {
                        case 8:
                            iFirstPeopleID.add(resultSet.getInt("peopleID5"));
                            iFirstPeopleID.add(resultSet.getInt("peopleID6"));
                            iFirstPeopleID.add(resultSet.getInt("peopleID7"));
                            iFirstPeopleID.add(resultSet.getInt("peopleID8"));
                        case 4:
                            iFirstPeopleID.add(resultSet.getInt("peopleID2"));
                            iFirstPeopleID.add(resultSet.getInt("peopleID3"));
                            iFirstPeopleID.add(resultSet.getInt("peopleID4"));
                        case 1:
                            iFirstPeopleID.add(resultSet.getInt("peopleID1"));
                            break;
                        default:

                    }
                }
                sql = "SELECT * FROM studentTeam WHERE "
                        + "studentTeamID = " + iSecondID + ";";

                resultSet = sqlStatement.executeQuery(sql);

                while(resultSet.next())
                {
                    switch (iParticipant)
                    {
                        case 8:
                            iSecondPeopleID.add(resultSet.getInt("peopleID5"));
                            iSecondPeopleID.add(resultSet.getInt("peopleID6"));
                            iSecondPeopleID.add(resultSet.getInt("peopleID7"));
                            iSecondPeopleID.add(resultSet.getInt("peopleID8"));
                        case 4:
                            iSecondPeopleID.add(resultSet.getInt("peopleID2"));
                            iSecondPeopleID.add(resultSet.getInt("peopleID3"));
                            iSecondPeopleID.add(resultSet.getInt("peopleID4"));
                        case 1:
                            iSecondPeopleID.add(resultSet.getInt("peopleID1"));
                            break;
                        default:
                            System.err.println(this.getClass().getName() + ".java :: Participant count not found.");
                            break;
                    }
                }

                sql = "SELECT * FROM studentTeam WHERE "
                        + "studentTeamID = " + iThirdID + ";";

                resultSet = sqlStatement.executeQuery(sql);

                while(resultSet.next())
                {
                    switch (iParticipant)
                    {
                        case 8:
                            iThirdPeopleID.add(resultSet.getInt("peopleID5"));
                            iThirdPeopleID.add(resultSet.getInt("peopleID6"));
                            iThirdPeopleID.add(resultSet.getInt("peopleID7"));
                            iThirdPeopleID.add(resultSet.getInt("peopleID8"));
                        case 4:
                            iThirdPeopleID.add(resultSet.getInt("peopleID2"));
                            iThirdPeopleID.add(resultSet.getInt("peopleID3"));
                            iThirdPeopleID.add(resultSet.getInt("peopleID4"));
                        case 1:
                            iThirdPeopleID.add(resultSet.getInt("peopleID1"));
                            break;
                        default:
                            System.err.println(this.getClass().getName() + ".java :: Participant count not found.");
                            break;
                    }
                }

                if(iTiedID != 0)
                {
                    sql = "SELECT * FROM studentTeam WHERE "
                            + "studentTeamID = " + iTiedID + ";";

                    resultSet = sqlStatement.executeQuery(sql);

                    while(resultSet.next())
                    {
                        switch (iParticipant)
                        {
                            case 8:
                                iThirdPeopleID.add(resultSet.getInt("peopleID5"));
                                iThirdPeopleID.add(resultSet.getInt("peopleID6"));
                                iThirdPeopleID.add(resultSet.getInt("peopleID7"));
                                iThirdPeopleID.add(resultSet.getInt("peopleID8"));
                            case 4:
                                iThirdPeopleID.add(resultSet.getInt("peopleID2"));
                                iThirdPeopleID.add(resultSet.getInt("peopleID3"));
                                iThirdPeopleID.add(resultSet.getInt("peopleID4"));
                            case 1:
                                iThirdPeopleID.add(resultSet.getInt("peopleID1"));
                                break;
                            default:
                                System.err.println(this.getClass().getName() + ".java :: Participant count not found.");
                                break;
                        }
                    }
                }

                person [] pFirst = new person[iParticipant];
                person [] pSecond = new person[iParticipant];
                person [] pThird = new person[iParticipant * 2];

                for(i = 0 ; i < iParticipant ; i++)
                {
                    sql = "SELECT * FROM people WHERE "
                            + "peopleID = " + iFirstPeopleID.get(i) + ";";

                    resultSet = sqlStatement.executeQuery(sql);
                    while (resultSet.next())
                    {
                        pFirst[i] = new person(resultSet.getString("firstName")
                                , resultSet.getString("surname"));
                    }
                }

                for(i = 0 ; i < iParticipant ; i++)
                {
                    sql = "SELECT * FROM people WHERE "
                            + "peopleID = " + iSecondPeopleID.get(i) + ";";

                    resultSet = sqlStatement.executeQuery(sql);
                    while (resultSet.next())
                    {
                        pSecond[i] = new person(resultSet.getString("firstName")
                                , resultSet.getString("surname"));
                    }
                }

                if(iThirdPeopleID.size() > 0)
                {
                    for (i = 0; i < iParticipant; i++)
                    {
                        sql = "SELECT * FROM people WHERE "
                                + "peopleID = " + iThirdPeopleID.get(i) + ";";

                        resultSet = sqlStatement.executeQuery(sql);
                        while (resultSet.next())
                        {
                            pThird[i] = new person(resultSet.getString("firstName")
                                    , resultSet.getString("surname"));
                        }
                    }
                }

                if(iTiedID != 0)
                {
                    for(i = iParticipant ; i < (iParticipant * 2) ; i++)
                    {
                        sql = "SELECT * FROM people WHERE "
                                + "peopleID = " + iThirdPeopleID.get(i) + ";";

                        resultSet = sqlStatement.executeQuery(sql);
                        while (resultSet.next())
                        {
                            pThird[i] = new person(resultSet.getString("firstName")
                                    , resultSet.getString("surname"));
                        }
                    }
                }

                r.addToFirstPlace(pFirst);
                r.addToSecondPlace(pSecond);
                r.addToThirdPlace(pThird);
            }

            resultSet.close();
        }
        catch(SQLException se)
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
        }
        catch(Exception e)
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: Exception: " + e);
        }

        getSession().disconnect();
        return r;
    }

    /**
     * ======================================================================
     * UPDATE ELO
     * Inputs: r (result)
     * Outputs: N/A
     * Comments: Updates the Elo rating for each team with respect to the event
     * ======================================================================
     */

    public void updateElo(result r)
    {
        int iTeams, i, iK;
        int iCliff = 0, iDowns = 0, iFleet = 0, iHill = 0, iSchool = 0, iTown = 0;
        LinkedList<Integer> iElo = new LinkedList<Integer>();

        if(r.getEvent().getFleetTeam())
        {
            iTeams = 6;
        }
        else
        {
            iTeams = 5;
        }

        //get the K mulitplier, based upon if fleet were present
        iK = (int)(32/(iTeams - 1));

        try
        {
            dbSession.connect();
            if (!getSession().isConnected())
            {
                System.err.println(this.getClass().getName() + ".java :: Failed to connect to database.");
                getSession().disconnect();
                return;
            }

            //initialise rows
            sql = "INSERT INTO elo (competition, elite, yearOfEntry, house) VALUES "
                    + "(" + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + house.CLIFF.getValue() + ")"
                    + ", (" + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + house.DOWNS.getValue() + ")"
                    + ", (" + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + house.HILL.getValue() + ")"
                    + ", (" + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + house.SCHOOL.getValue() + ")"
                    + ", (" + r.getEvent().getEventType().getValue()
                    + ", " + r.getEvent().isElite()
                    + ", " + r.getEvent().getYearOfEntry()
                    + ", " + house.TOWN.getValue() + ")";

            if(iTeams == 6)
            {
                sql += ", (" + r.getEvent().getEventType().getValue()
                        + ", " + r.getEvent().isElite()
                        + ", " + r.getEvent().getYearOfEntry()
                        + ", " + house.FLEET.getValue() + ")";
            }
            //if already present, ignore statement
            sql += " ON DUPLICATE KEY UPDATE competition = VALUES(competition)" +
                    ", elite = VALUES(elite), yearOfEntry = VALUES(yearOfEntry), house = VALUES(house);";

            sqlStatement = getSession().getConnection().createStatement();
            sqlStatement.executeUpdate(sql);


            //switch statement based upon house and number of participants
            //can infer that if five participants, fleet were not present in this match
            if (iTeams == 5)
            {
                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ")"
                        + " AND competition = " + r.getEvent().getEventType().getValue()
                        + " AND elite = " + r.getEvent().isElite()
                        + " AND yearOfEntry = " + r.getEvent().getYearOfEntry()
                        + " ORDER BY FIELD"
                        + "(house,\'" + house.CLIFF.name()
                        + "\',\'" + house.DOWNS.name()
                        + "\',\'" + house.HILL.name()
                        + "\',\'" + house.SCHOOL.name()
                        + "\',\'" + house.TOWN.name()
                        + "\')"
                        + ";"
                ;
            }
            if (iTeams == 6)
            {
                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ", " + house.FLEET.getValue()
                        + ")"
                        + " AND competition = " + r.getEvent().getEventType().getValue()
                        + " AND elite = " + r.getEvent().isElite()
                        + " AND yearOfEntry = " + r.getEvent().getYearOfEntry()
                        + " ORDER BY FIELD(house,"
                        + house.CLIFF.getValue()
                        + "," + house.DOWNS.getValue()
                        + "," + house.HILL.getValue()
                        + "," + house.SCHOOL.getValue()
                        + "," + house.TOWN.getValue()
                        + "," + house.FLEET.getValue()
                        + ");"
                ;
            }

            System.err.println(sql);
            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            while(resultSet.next())
            {
                iElo.add(resultSet.getInt("elo"));
            }

            //get the elo of teams based upon results
            iCliff = (int)(iElo.get(0)
                    + (iK * (performEloPostComparison(r.getCliffPosition().getValue()
                            , r.getDownsPosition().getValue(), iElo.get(0), iElo.get(1))))
                    + (iK * (performEloPostComparison(r.getCliffPosition().getValue()
                            , r.getHillPosition().getValue(), iElo.get(0), iElo.get(2))))
                    + (iK * (performEloPostComparison(r.getCliffPosition().getValue()
                            , r.getSchoolPosition().getValue(), iElo.get(0), iElo.get(3))))
                    + (iK * (performEloPostComparison(r.getCliffPosition().getValue()
                            , r.getTownPosition().getValue(), iElo.get(0), iElo.get(4))))
            );

            iDowns = (int)(iElo.get(1)
                    + (iK * (performEloPostComparison(r.getDownsPosition().getValue()
                            , r.getCliffPosition().getValue(), iElo.get(1), iElo.get(0))))
                    + (iK * (performEloPostComparison(r.getDownsPosition().getValue()
                            , r.getHillPosition().getValue(), iElo.get(1), iElo.get(2))))
                    + (iK * (performEloPostComparison(r.getDownsPosition().getValue()
                            , r.getSchoolPosition().getValue(), iElo.get(1), iElo.get(3))))
                    + (iK * (performEloPostComparison(r.getDownsPosition().getValue()
                            , r.getTownPosition().getValue(), iElo.get(1), iElo.get(4))))
            );

            iHill = (int)(iElo.get(2)
                    + (iK * (performEloPostComparison(r.getHillPosition().getValue()
                            , r.getCliffPosition().getValue(), iElo.get(2), iElo.get(0))))
                    + (iK * (performEloPostComparison(r.getHillPosition().getValue()
                            , r.getDownsPosition().getValue(), iElo.get(2), iElo.get(1))))
                    + (iK * (performEloPostComparison(r.getHillPosition().getValue()
                            , r.getSchoolPosition().getValue(), iElo.get(2), iElo.get(3))))
                    + (iK * (performEloPostComparison(r.getHillPosition().getValue()
                            , r.getTownPosition().getValue(), iElo.get(2), iElo.get(4))))
            );

            iSchool = (int)(iElo.get(3)
                    + (iK * (performEloPostComparison(r.getSchoolPosition().getValue()
                            , r.getCliffPosition().getValue(), iElo.get(3), iElo.get(0))))
                    + (iK * (performEloPostComparison(r.getSchoolPosition().getValue()
                            , r.getDownsPosition().getValue(), iElo.get(3), iElo.get(1))))
                    + (iK * (performEloPostComparison(r.getSchoolPosition().getValue()
                            , r.getHillPosition().getValue(), iElo.get(3), iElo.get(2))))
                    + (iK * (performEloPostComparison(r.getSchoolPosition().getValue()
                            , r.getTownPosition().getValue(), iElo.get(2), iElo.get(4))))
            );

            iTown = (int)(iElo.get(4)
                    + (iK * (performEloPostComparison(r.getTownPosition().getValue()
                            , r.getCliffPosition().getValue(), iElo.get(4), iElo.get(0))))
                    + (iK * (performEloPostComparison(r.getTownPosition().getValue()
                            , r.getDownsPosition().getValue(), iElo.get(4), iElo.get(1))))
                    + (iK * (performEloPostComparison(r.getTownPosition().getValue()
                            , r.getHillPosition().getValue(), iElo.get(4), iElo.get(2))))
                    + (iK * (performEloPostComparison(r.getTownPosition().getValue()
                            , r.getTownPosition().getValue(), iElo.get(4), iElo.get(3))))
            );

            if(iTeams == 6)
            {
                iCliff += (int)(iK * (performEloPostComparison(r.getCliffPosition().getValue()
                                    , r.getFleetPosition().getValue(), iElo.get(0), iElo.get(5))));
                iDowns += (int)(iK * (performEloPostComparison(r.getDownsPosition().getValue()
                                    , r.getFleetPosition().getValue(), iElo.get(1), iElo.get(5))));
                iHill += (int)(iK * (performEloPostComparison(r.getHillPosition().getValue()
                                    , r.getFleetPosition().getValue(), iElo.get(2), iElo.get(5))));
                iSchool += (int)(iK * (performEloPostComparison(r.getSchoolPosition().getValue()
                                    , r.getFleetPosition().getValue(), iElo.get(3), iElo.get(5))));
                iTown += (int)(iK * (performEloPostComparison(r.getTownPosition().getValue()
                                    , r.getFleetPosition().getValue(), iElo.get(4), iElo.get(5))));

                iFleet = (int)(iElo.get(5)
                        + (iK * (performEloPostComparison(r.getFleetPosition().getValue()
                                , r.getCliffPosition().getValue(), iElo.get(5), iElo.get(0))))
                        + (iK * (performEloPostComparison(r.getFleetPosition().getValue()
                                , r.getDownsPosition().getValue(), iElo.get(5), iElo.get(1))))
                        + (iK * (performEloPostComparison(r.getFleetPosition().getValue()
                                , r.getHillPosition().getValue(), iElo.get(5), iElo.get(2))))
                        + (iK * (performEloPostComparison(r.getFleetPosition().getValue()
                                , r.getTownPosition().getValue(), iElo.get(5), iElo.get(3))))
                        + (iK * (performEloPostComparison(r.getFleetPosition().getValue()
                                , r.getTownPosition().getValue(), iElo.get(5), iElo.get(4))))
                );
            }

            //update elo ratings
            sql = "UPDATE elo SET elo = " + iCliff + " WHERE "
                    + "house = " + house.CLIFF.getValue()
                    + " AND competition = " + r.getEvent().getEventType().getValue()
                    + " AND elite = " + r.getEvent().isElite()
                    + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

            sqlStatement.executeUpdate(sql);

            sql = "UPDATE elo SET elo = " + iDowns + " WHERE "
                    + "house = " + house.DOWNS.getValue()
                    + " AND competition = " + r.getEvent().getEventType().getValue()
                    + " AND elite = " + r.getEvent().isElite()
                    + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

            sqlStatement.executeUpdate(sql);

            sql = "UPDATE elo SET elo = " + iHill + " WHERE "
                    + "house = " + house.HILL.getValue()
                    + " AND competition = " + r.getEvent().getEventType().getValue()
                    + " AND elite = " + r.getEvent().isElite()
                    + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

            sqlStatement.executeUpdate(sql);

            sql = "UPDATE elo SET elo = " + iSchool + " WHERE "
                    + "house = " + house.SCHOOL.getValue()
                    + " AND competition = " + r.getEvent().getEventType().getValue()
                    + " AND elite = " + r.getEvent().isElite()
                    + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

            sqlStatement.executeUpdate(sql);

            sql = "UPDATE elo SET elo = " + iTown + " WHERE "
                    + "house = " + house.TOWN.getValue()
                    + " AND competition = " + r.getEvent().getEventType().getValue()
                    + " AND elite = " + r.getEvent().isElite()
                    + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

            sqlStatement.executeUpdate(sql);

            if(iTeams == 6)
            {
                sql = "UPDATE elo SET elo = " + iFleet + " WHERE "
                        + "house = " + house.FLEET.getValue()
                        + " AND competition = " + r.getEvent().getEventType().getValue()
                        + " AND elite = " + r.getEvent().isElite()
                        + " AND yearOfEntry = " + r.getEvent().getYearOfEntry();

                sqlStatement.executeUpdate(sql);
            }

            resultSet.close();
        }
        catch(SQLException se)
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
        }
        catch(Exception e)
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: Exception: " + e);
        }

        getSession().disconnect();
    }

    /**
     * ======================================================================
     * PERFORM ELO PRE-COMPARISON
     * Inputs: iMain (int), iRelative (int)
     * Outputs: double
     * Comments: Gets the probability pre-event that the team will win compared to another team
     * ======================================================================
     */

    public double performEloPreComparison(int iMain, int iRelative)
    {
        return (1/(1 + Math.pow(10,((iRelative - iMain)/400))));
    }

    /**
     * ======================================================================
     * PERFORM ELO POST COMPARISON
     * Inputs: iMain (int), iRelative (int), iMainElo (int), iRelativeElo (int)
     * Outputs: double
     * Comments: Returns the relative value based upon their finishing position and the pre-comparison
     * ======================================================================
     */

    public double performEloPostComparison(int iMain, int iRelative, int iMainElo, int iRelativeElo)
    {
        double dPosition = 0;

        if(iMain < iRelative)
        {
            dPosition = 1;
        }
        if(iMain == iRelative)
        {
            dPosition = 0.5;
        }
        if(iMain > iRelative)
        {
            dPosition = 0;
        }
        if(iMain == 0)
        {
            dPosition = 0;
        }

        return (dPosition - performEloPreComparison(iMainElo, iRelativeElo));
    }

    /**
     * ======================================================================
     * GET SCOREBOARD POINTS
     * Inputs: N/A
     * Outputs: int[]
     *          Indexes 0 to 5 represents Cliff to Town
     * Comments: Gets the current scores for sports day based upon current results
     * =====================================================================
     */

    public int[] getScoreboardPoints() throws IOException {
        int[] iScores = {0, 0, 0, 0, 0, 0};
        int[] iTempScores = {0, 0, 0, 0, 0, 0};
        int i, j, iYearOfEntry = 1970;
        result r;

        try
        {
            getSession().connect();
            if(!getSession().isConnected())
            {
                System.err.println(this.getClass().getName() + ".java :: SQL Error: No connection to database.");
            }

            for (i = 7; i < 11; i++)
            {
                //get year of entry based upon year group
                switch (i)
                {
                    //year 7
                    case 7:
                        iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 1;
                        break;
                    //year 8
                    case 8:
                        iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 2;
                        break;
                    //year 9
                    case 9:
                        iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 3;
                        break;
                    //year 10
                    case 10:
                        iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 4;
                        break;
                }

                //get each event for year 7 to 10, both mass and elite
                for (j = 1; j < 12; j++)
                {
                    //mass
                    r = getResultFromDB(eventType.fromInt(j), false
                            , Calendar.getInstance().get(Calendar.YEAR), iYearOfEntry);

                    if (r != null)
                    {
                        //cliff
                        if (r.getCliffPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[0] += 12 - (2 * (r.getCliffPosition().getValue() - 1));
                        }
                        //downs
                        if (r.getDownsPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[1] += 12 - (2 * (r.getDownsPosition().getValue() - 1));
                        }
                        //fleet
                        if (r.getFleetPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[2] += 12 - (2 * (r.getFleetPosition().getValue() - 1));
                        }
                        //hill
                        if (r.getHillPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[3] += 12 - (2 * (r.getHillPosition().getValue() - 1));
                        }
                        //school
                        if (r.getSchoolPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[4] += 12 - (2 * (r.getSchoolPosition().getValue() - 1));
                        }
                        //town
                        if (r.getTownPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[5] += 12 - (2 * (r.getTownPosition().getValue() - 1));
                        }

                    }
                    //elite
                    r = getResultFromDB(eventType.fromInt(j), true
                            , Calendar.getInstance().get(Calendar.YEAR), iYearOfEntry);

                    if (r != null)
                    {
                        //cliff
                        if (r.getCliffPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[0] += 24 - (2 * (r.getCliffPosition().getValue() - 1));
                        }
                        //downs
                        if (r.getDownsPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[1] += 24 - (2 * (r.getDownsPosition().getValue() - 1));
                        }
                        //fleet
                        if (r.getFleetPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[2] += 24 - (2 * (r.getFleetPosition().getValue() - 1));
                        }
                        //hill
                        if (r.getHillPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[3] += 24 - (2 * (r.getHillPosition().getValue() - 1));
                        }
                        //school
                        if (r.getSchoolPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[4] += 24 - (2 * (r.getSchoolPosition().getValue() - 1));
                        }
                        //town
                        if (r.getTownPosition() != position.DID_NOT_FINISH)
                        {
                            iScores[5] += 24 - (2 * (r.getTownPosition().getValue() - 1));
                        }
                    }
                }

                sql = "INSERT INTO house (house, yearOfEntry, ";
                switch(i)
                {
                    case 7:
                        sql += "yearSevenScore) ";
                        break;
                    case 8:
                        sql += "yearEightScore) ";
                        break;
                    case 9:
                        sql += "yearNineScore) ";
                        break;
                    case 10:
                        sql += "yearTenScore) ";
                        break;
                }
                sql += "VALUES ";

                for(j = 0 ; j < 6 ; j++)
                {
                    //get the year group's score.
                    //must do this as iScores is cumulative for all years, this finds difference between scores
                    iTempScores[j] = iScores[j] - iTempScores[j];
                    if(j == 2)
                    {
                        iTempScores[j] = getWeightedFleetScore(iTempScores[j]);
                    }
                    sql += "(" + (j+1) + ", " + iYearOfEntry + ", " + iTempScores[j] + ")";
                    if(j != 5)
                    {
                        sql += ", ";
                    }
                }

                sql += " ON DUPLICATE KEY UPDATE house = VALUES(house), yearOfEntry = VALUES(yearOfEntry),";
                switch(i)
                {
                    case 7:
                        sql += " yearSevenScore = VALUES(yearSevenScore);";
                        break;
                    case 8:
                        sql += " yearEightScore = VALUES(yearEightScore);";
                        break;
                    case 9:
                        sql += " yearNineScore = VALUES(yearNineScore);";
                        break;
                    case 10:
                        sql +=  "yearTenScore = VALUES(yearTenScore);";
                        break;
                }

                getSession().connect();
                sqlStatement = getSession().getConnection().createStatement();
                sqlStatement.executeUpdate(sql);
                getSession().disconnect();

            }

            i = 12;
            iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 6;
            for (j = 1; j < 3; j++)
            {
                r = getResultFromDB(eventType.fromInt(j), false
                        , Calendar.getInstance().get(Calendar.YEAR), iYearOfEntry);

                if (r != null)
                {
                    //cliff
                    if (r.getCliffPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[0] += 12 - (2 * (r.getCliffPosition().getValue() - 1));
                    }
                    //downs
                    if (r.getDownsPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[1] += 12 - (2 * (r.getDownsPosition().getValue() - 1));
                    }
                    //fleet
                    if (r.getFleetPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[2] += 12 - (2 * (r.getFleetPosition().getValue() - 1));
                    }
                    //hill
                    if (r.getHillPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[3] += 12 - (2 * (r.getHillPosition().getValue() - 1));
                    }
                    //school
                    if (r.getSchoolPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[4] += 12 - (2 * (r.getSchoolPosition().getValue() - 1));
                    }
                    //town
                    if (r.getTownPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[5] += 12 - (2 * (r.getTownPosition().getValue() - 1));
                    }
                }

                r = getResultFromDB(eventType.fromInt(j), true
                        , Calendar.getInstance().get(Calendar.YEAR), iYearOfEntry);

                if (r != null)
                {
                    //cliff
                    if (r.getCliffPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[0] += 24 - (2 * (r.getCliffPosition().getValue() - 1));
                    }
                    //downs
                    if (r.getDownsPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[1] += 24 - (2 * (r.getDownsPosition().getValue() - 1));
                    }
                    //fleet
                    if (r.getFleetPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[2] += 24 - (2 * (r.getFleetPosition().getValue() - 1));
                    }
                    //hill
                    if (r.getHillPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[3] += 24 - (2 * (r.getHillPosition().getValue() - 1));
                    }
                    //school
                    if (r.getSchoolPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[4] += 24 - (2 * (r.getSchoolPosition().getValue() - 1));
                    }
                    //town
                    if (r.getTownPosition() != position.DID_NOT_FINISH)
                    {
                        iScores[5] += 24 - (2 * (r.getTownPosition().getValue() - 1));
                    }
                }
            }

            sql = "INSERT INTO house (house, yearOfEntry, yearTwelveScore) VALUES ";

            for(j = 0 ; j < 6 ; j++)
            {
                //get the year group's score.
                //must do this as iScores is cumulative for all years, this finds difference between scores
                iTempScores[j] = iScores[j] - iTempScores[j];
                if(j == 2)
                {
                    iTempScores[j] = getWeightedFleetScore(iTempScores[j]);
                }
                sql += "(" + (j+1) + ", " + iYearOfEntry + ", " + iTempScores[j] + ")";
                if(j != 5)
                {
                    sql += ", ";
                }
            }


            sql += " ON DUPLICATE KEY UPDATE house = VALUES(house), yearOfEntry = VALUES(yearOfEntry)," +
                    " yearTwelveScore = VALUES(yearTwelveScore);";

            getSession().connect();
            sqlStatement = getSession().getConnection().createStatement();
            sqlStatement.executeUpdate(sql);
            getSession().disconnect();

        }
        catch(SQLException se)
        {
            System.err.println(sql);
            return null;
        }

        //weight fleet's score
        iScores[2] = getWeightedFleetScore(iScores[2]);


        return iScores;
    }

    /**
     * ======================================================================
     * OUTPUT RESULTS FOR YEAR
     * Inputs: iYear (int)
     * Outputs: boolean
     *          True if goes through
     *          False if db not connected or missing event
     *          year,event,participation,first_name,first_house,first_result,... etc
     *          Up to tied_name,tied_house (to represent a 3rd place tie)
     * Comments: Outputs the results for the year
     * ======================================================================
     */

    public boolean outputResultsForYear(int iYear) throws IOException
    {
        DateFormat df = new SimpleDateFormat("ddMMyy-HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String szPathName = iYear + "-YearResults-" + df.format(cal.getTime()) + ".csv";
        File fFile = new File(szPathName);
        fFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, true));
        int i, j, iYearOfEntry = 1970;
        house hFirst = null, hSecond = null, hThird = null, hTiedThird = null;

        result r;

        try
        {
            getSession().connect();
            if(!getSession().isConnected())
            {
                System.err.println(this.getClass().getName() + ".java :: SQL Error: Failed to connect to database.");
                return false;
            }

            bw.write("year,year_of_entry,event,participation" +
                    ",first_name,first_house,first_result" +
                    ",second_name,second_house_second_result" +
                    ",third_name,third_house,third_result," +
                    "tied_name,tied_house");

            //year groups from 7 to 10
            for (i = 7; i < 12; i++)
            {
                switch(i)
                {
                    //year 7
                    case 7:
                        iYearOfEntry = iYear - 1;
                        break;
                    //year 8
                    case 8:
                        iYearOfEntry = iYear - 2;
                        break;
                    //year 9
                    case 9:
                        iYearOfEntry = iYear - 3;
                        break;
                    //year 10
                    case 10:
                        iYearOfEntry = iYear - 4;
                        break;
                    //year 12
                    case 11:
                        iYearOfEntry = iYear - 6;
                        break;
                    default:
                        return false;
                }

                //all event types
                for (j = 1; j < 13; j++)
                {
                    if(j != eventType.EIGHT_BY_HUNDRED_METRE.getValue()
                            && j != eventType.FOUR_BY_TWO_HUNDRED_METRE.getValue()
                            && j != eventType.FOUR_HUNDRED_METRE_RELAY.getValue())
                    {
                        //mass
                        hFirst = null;
                        hSecond = null;
                        hThird = null;
                        hTiedThird = null;

                        r = getResultFromDB(eventType.fromInt(j)
                                , false
                                , iYear
                                , iYearOfEntry);

                        //if an event if missing, return
                        if (r != null)
                        {
                            //get the classes that came 1st, 2nd, 3rd (and tied 3rd if applicable)
                            switch (r.getCliffPosition())
                            {
                                case FIRST:
                                    hFirst = house.CLIFF;
                                    break;
                                case SECOND:
                                    hSecond = house.CLIFF;
                                    break;
                                case THIRD:
                                    hThird = house.CLIFF;
                                    break;
                            }

                            switch (r.getDownsPosition())
                            {
                                //if tied first, put in second place position. tie will be indicated by same time/distance
                                case FIRST:
                                    if (hFirst == null)
                                    {
                                        hFirst = house.DOWNS;
                                    } else
                                    {
                                        hSecond = house.DOWNS;
                                    }
                                    break;
                                case SECOND:
                                    hSecond = house.DOWNS;
                                    break;
                                case THIRD:
                                    //if hThird hasnt been set, must be first to be set
                                    //else must be a tie, add to hTiedThird
                                    if (hThird == null)
                                    {
                                        hThird = house.DOWNS;
                                    } else
                                    {
                                        hTiedThird = house.DOWNS;
                                    }
                            }

                            switch (r.getFleetPosition())
                            {
                                //if tied first, put in second place position. tie will be indicated by same time/distance
                                case FIRST:
                                    if (hFirst == null)
                                    {
                                        hFirst = house.FLEET;
                                    } else
                                    {
                                        hSecond = house.FLEET;
                                    }
                                    break;
                                case SECOND:
                                    hSecond = house.FLEET;
                                    break;
                                case THIRD:
                                    if (hThird == null)
                                    {
                                        hThird = house.FLEET;
                                    } else
                                    {
                                        hTiedThird = house.FLEET;
                                    }
                            }

                            switch (r.getHillPosition())
                            {
                                //if tied first, put in second place position. tie will be indicated by same time/distance
                                case FIRST:
                                    if (hFirst == null)
                                    {
                                        hFirst = house.HILL;
                                    } else
                                    {
                                        hSecond = house.HILL;
                                    }
                                    break;
                                case SECOND:
                                    hSecond = house.HILL;
                                    break;
                                case THIRD:
                                    if (hThird == null)
                                    {
                                        hThird = house.HILL;
                                    } else
                                    {
                                        hTiedThird = house.HILL;
                                    }
                            }

                            switch (r.getSchoolPosition())
                            {
                                //if tied first, put in second place position. tie will be indicated by same time/distance
                                case FIRST:
                                    if (hFirst == null)
                                    {
                                        hFirst = house.SCHOOL;
                                    } else
                                    {
                                        hSecond = house.SCHOOL;
                                    }
                                    break;
                                case SECOND:
                                    hSecond = house.SCHOOL;
                                    break;
                                case THIRD:
                                    if (hThird == null)
                                    {
                                        hThird = house.SCHOOL;
                                    } else
                                    {
                                        hTiedThird = house.SCHOOL;
                                    }
                            }

                            switch (r.getTownPosition())
                            {
                                //if tied first, put in second place position. tie will be indicated by same time/distance
                                case FIRST:
                                    if (hFirst == null)
                                    {
                                        hFirst = house.TOWN;
                                    } else
                                    {
                                        hSecond = house.TOWN;
                                    }
                                    break;
                                case SECOND:
                                    hSecond = house.TOWN;
                                    break;
                                case THIRD:
                                    if (hThird == null)
                                    {
                                        hThird = house.TOWN;
                                    } else
                                    {
                                        hTiedThird = house.TOWN;
                                    }
                            }

                            //if j is between 1 to 5, or is between 8 to 11
                            if ((j >= 1 && j <= 5) || (j >= 8 && j <= 11))
                            {
                                bw.write("\r\n" +
                                        + iYear
                                        + "," + iYearOfEntry
                                        + "," + r.getEvent().getEventType().getValue()
                                        + "," + r.getEvent().isElite()
                                        + "," + r.getFirstPlacePerson(0).getFirstName().charAt(0)
                                        + " " + r.getFirstPlacePerson(0).getLastName()
                                        + "," + hFirst.name()
                                        + "," + r.getFirstResult()
                                        + "," + r.getSecondPlacePerson(0).getFirstName().charAt(0)
                                        + " " + r.getSecondPlacePerson(0).getLastName()
                                        + "," + hSecond.name()
                                        + "," + r.getSecondResult());

                                if (hThird != null)
                                {
                                    bw.write("," + r.getThirdPlacePerson(0).getFirstName().charAt(0)
                                            + " " + r.getThirdPlacePerson(0).getLastName()
                                            + "," + hThird.name()
                                            + "," + r.getThirdResult());
                                } else
                                {
                                    bw.write(",,,");
                                }

                                if (hTiedThird != null)
                                {
                                    bw.write(
                                            "," + r.getThirdPlacePerson(1).getFirstName()
                                                    + " " + r.getThirdPlacePerson(1).getLastName()
                                                    + "," + hTiedThird.name()
                                    );
                                } else
                                {
                                    bw.write(",,");
                                }
                            }
                        }
                    }


                    //elite
                    hFirst = null;
                    hSecond = null;
                    hThird = null;
                    hTiedThird = null;

                    r = getResultFromDB(eventType.fromInt(j)
                            , true
                            , iYear
                            , iYearOfEntry);

                    //if an event if missing, return
                    if(r != null)
                    {
                        //get the classes that came 1st, 2nd, 3rd (and tied 3rd if applicable)
                        switch (r.getCliffPosition())
                        {
                            case FIRST:
                                hFirst = house.CLIFF;
                                break;
                            case SECOND:
                                hSecond = house.CLIFF;
                                break;
                            case THIRD:
                                hThird = house.CLIFF;
                                break;
                        }

                        switch (r.getDownsPosition())
                        {
                            //if tied first, put in second place position. tie will be indicated by same time/distance
                            case FIRST:
                                if (hFirst == null)
                                {
                                    hFirst = house.DOWNS;
                                }
                                else
                                {
                                    hSecond = house.DOWNS;
                                }
                                break;
                            case SECOND:
                                hSecond = house.DOWNS;
                                break;
                            case THIRD:
                                //if hThird hasnt been set, must be first to be set
                                //else must be a tie, add to hTiedThird
                                if (hThird == null)
                                {
                                    hThird = house.DOWNS;
                                }
                                else
                                {
                                    hTiedThird = house.DOWNS;
                                }
                        }

                        switch (r.getFleetPosition())
                        {
                            //if tied first, put in second place position. tie will be indicated by same time/distance
                            case FIRST:
                                if (hFirst == null)
                                {
                                    hFirst = house.FLEET;
                                }
                                else
                                {
                                    hSecond = house.FLEET;
                                }
                                break;
                            case SECOND:
                                hSecond = house.FLEET;
                                break;
                            case THIRD:
                                if (hThird == null)
                                {
                                    hThird = house.FLEET;
                                }
                                else
                                {
                                    hTiedThird = house.FLEET;
                                }
                        }

                        switch (r.getHillPosition())
                        {
                            //if tied first, put in second place position. tie will be indicated by same time/distance
                            case FIRST:
                                if (hFirst == null)
                                {
                                    hFirst = house.HILL;
                                }
                                else
                                {
                                    hSecond = house.HILL;
                                }
                                break;
                            case SECOND:
                                hSecond = house.HILL;
                                break;
                            case THIRD:
                                if (hThird == null)
                                {
                                    hThird = house.HILL;
                                }
                                else
                                {
                                    hTiedThird = house.HILL;
                                }
                        }

                        switch (r.getSchoolPosition())
                        {
                            //if tied first, put in second place position. tie will be indicated by same time/distance
                            case FIRST:
                                if (hFirst == null)
                                {
                                    hFirst = house.SCHOOL;
                                }
                                else
                                {
                                    hSecond = house.SCHOOL;
                                }
                                break;
                            case SECOND:
                                hSecond = house.SCHOOL;
                                break;
                            case THIRD:
                                if (hThird == null)
                                {
                                    hThird = house.SCHOOL;
                                }
                                else
                                {
                                    hTiedThird = house.SCHOOL;
                                }
                        }

                        switch (r.getTownPosition())
                        {
                            //if tied first, put in second place position. tie will be indicated by same time/distance
                            case FIRST:
                                if (hFirst == null)
                                {
                                    hFirst = house.TOWN;
                                }
                                else
                                {
                                    hSecond = house.TOWN;
                                }
                                break;
                            case SECOND:
                                hSecond = house.TOWN;
                                break;
                            case THIRD:
                                if (hThird == null)
                                {
                                    hThird = house.TOWN;
                                }
                                else
                                {
                                    hTiedThird = house.TOWN;
                                }
                        }

                        //if j is between 1 to 5, or is between 8 to 11
                        if ((j >= 1 && j <= 5) || (j >= 8 && j <= 11))
                        {
                            bw.write("\r\n" +
                                    + iYear
                                    + "," + iYearOfEntry
                                    + "," + r.getEvent().getEventType().getValue()
                                    + "," + r.getEvent().isElite()
                                    + "," + r.getFirstPlacePerson(0).getFirstName().charAt(0)
                                    + " " + r.getFirstPlacePerson(0).getLastName()
                                    + "," + hFirst.name()
                                    + "," + r.getFirstResult()
                                    + "," + r.getSecondPlacePerson(0).getFirstName().charAt(0)
                                    + " " + r.getSecondPlacePerson(0).getLastName()
                                    + "," + hSecond.name()
                                    + "," + r.getSecondResult());

                            if (hThird != null)
                            {
                                bw.write("," + r.getThirdPlacePerson(0).getFirstName().charAt(0)
                                        + " " + r.getThirdPlacePerson(0).getLastName()
                                        + "," + hThird.name()
                                        + "," + r.getThirdResult());
                            }
                            else
                            {
                                bw.write(",,,");
                            }

                            if (hTiedThird != null)
                            {
                                bw.write(
                                        "," + r.getThirdPlacePerson(1).getFirstName()
                                                + " " + r.getThirdPlacePerson(1).getLastName()
                                                + "," + hTiedThird.name()
                                );
                            }
                            else
                            {
                                bw.write(",,");
                            }
                        }
                        //team events, no names
                        if (j == 7 || j == 8 || j == 12)
                        {
                            bw.write("\r\n" +
                                    + iYear
                                    + "," + iYearOfEntry
                                    + "," + r.getEvent().getEventType().getValue()
                                    + "," + r.getEvent().isElite()
                                    + ",," + hFirst.name()
                                    + "," + r.getFirstResult()
                                    + ",," + hSecond.name()
                                    + "," + r.getSecondResult()
                                    + ",," + hThird.name()
                                    + "," + r.getThirdResult()
                            );

                            //if there are two teams stored in the third place indicating a third place tie
                            if (r.getThirdPlacePeople().length > (2 * getParticipantCount(r.getEvent().getEventType())))
                            {
                                bw.write(",," + hTiedThird.name());
                            } else
                            {
                                bw.write(",,");
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            bw.close();
            fFile.delete();
            return false;
        }

        bw.flush();
        bw.close();
        return true;
    }

    /**
     * ======================================================================
     * OUTPUT RESULTS FOR CLASS OVER YEARS
     * Inputs: h (house), iYear (int)
     * Outputs: boolean
     *          True if goes through
     *          False if db not connected or missing event
     * Comments: Outputs the position for a class over the years
     *           house,year_of_entry,year,100m,200m,400m,800m,1500m,8x100m,4x200m,lj,hj,j,sp,400mR
     *           Cannot be done on year 7 as they have no record
     * ======================================================================
     */

    public boolean outputResultForClassOverYears(house h, int iYearGroup) throws IOException
    {
        DateFormat df = new SimpleDateFormat("ddMMyy-HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        int iYearOfEntry = 1970, iYearOfEvent, i;
        result r;

        if (iYearGroup < 8 || iYearGroup == 11 || iYearGroup > 12)
        {
            return false;
        }

        switch(iYearGroup)
        {
            //year 8
            case 8:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 2;
                break;
            //year 9
            case 9:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 3;
                break;
            //year 10
            case 10:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 4;
                break;
            case 12:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 6;
                break;
            default:
                return false;
        }

        System.err.println(iYearGroup + "-" + h.name() + "Results-" + df.format(cal.getTime()) + ".csv");

        File fFile = new File(iYearGroup + "-" + h.name() + "Results-" + df.format(cal.getTime()) + ".csv");
        fFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, true));

        try
        {
            getSession().connect();
            if(!getSession().isConnected())
            {
                System.err.println(this.getClass().getName() + ".java :: SQL Error: Failed to connect to database.");
                return false;
            }

            //output headings
            bw.write("year,100m_m,100m_e,200m_m,200m_e,400m_m,400m_e,800m_m,800m_e,"
                        +"1500m_m,1500m_e,8x100m,4x200m,lj_m,lj_e,hj_m,hj_e,j_m,j_e,"
                        +"sp_m,sp_e");

            //for all the years that the team can be present
            iYearOfEvent = iYearOfEntry + 1;
            while(iYearOfEvent < Calendar.getInstance().get(Calendar.YEAR))
            {
                bw.write("\r\n" + iYearOfEvent);

                //for all events that they can participate in
                for(i = 1 ; i < 12 ; i++)
                {
                    //if relay then don't execute
                    if(i != eventType.EIGHT_BY_HUNDRED_METRE.getValue()
                            && i != eventType.FOUR_BY_TWO_HUNDRED_METRE.getValue())
                    {
                        r = getResultFromDB(eventType.fromInt(i), false, iYearOfEvent, iYearOfEntry);

                        if (r != null)
                        {
                            //output position if result occurred
                            switch (h)
                            {
                                case CLIFF:
                                    bw.write("," + r.getCliffPosition().getValue());
                                    break;
                                case DOWNS:
                                    bw.write("," + r.getDownsPosition().getValue());
                                    break;
                                case FLEET:
                                    bw.write("," + r.getFleetPosition().getValue());
                                    break;
                                case HILL:
                                    bw.write("," + r.getHillPosition().getValue());
                                    break;
                                case SCHOOL:
                                    bw.write("," + r.getSchoolPosition().getValue());
                                    break;
                                case TOWN:
                                    bw.write("," + r.getTownPosition().getValue());
                                    break;
                            }
                        }
                        else
                        {
                            //if not present, mark empty
                            bw.write(",");
                        }
                    }

                    //elite
                    r = getResultFromDB(eventType.fromInt(i), true, iYearOfEvent, iYearOfEntry);

                    if(r != null)
                    {
                        //output position if result occurred
                        switch (h)
                        {
                            case CLIFF:
                                bw.write("," + r.getCliffPosition().getValue());
                                break;
                            case DOWNS:
                                bw.write("," + r.getDownsPosition().getValue());
                                break;
                            case FLEET:
                                bw.write("," + r.getFleetPosition().getValue());
                                break;
                            case HILL:
                                bw.write("," + r.getHillPosition().getValue());
                                break;
                            case SCHOOL:
                                bw.write("," + r.getSchoolPosition().getValue());
                                break;
                            case TOWN:
                                bw.write("," + r.getTownPosition().getValue());
                                break;
                        }
                    }
                    else
                    {
                        //if not present, mark empty
                        bw.write(",");
                    }
                }

                //loop round once all results for calendar year found
                iYearOfEvent++;
            }
        }
        catch(Exception s)
        {
            bw.close();
            fFile.delete();
            return false;
        }

        bw.flush();
        bw.close();

        return true;
    }

    /**
     * ======================================================================
     * OUTPUT RESULTS FOR HOUSE OVER YEARS
     * Inputs: h (house)
     * Outputs: boolean
     *          True if goes through
     *          False if db not connected or missing event
     * Comments: Outputs the points for a house over the years
     *           year,y7_score,y8_score,y9_score,y10_score,y12_score
     *           Cannot be done on year 7 as they have no record
     * ======================================================================
     */

    public boolean outputResultsForHouseOverYears(house h, int iYear) throws IOException
    {
        DateFormat df = new SimpleDateFormat("ddMMyy-HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        int iYearOfEntry = 1970;
        result r;

        File fFile = new File(h.name() + "-Results-" + df.format(cal.getTime()) + ".csv");
        fFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, true));

        bw.write("year,y7_score,y8_score,y9_score,y10_score,y12_score");

        //get year of entry
        switch(iYear)
        {
            case 8:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 2;
                break;
            case 9:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 3;
                break;
            case 10:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 4;
                break;
            case 11:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 5;
                break;
            case 12:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 6;
                break;
            case 13:
                iYearOfEntry = Calendar.getInstance().get(Calendar.YEAR) - 7;
                break;
        }
        try
        {
            //get the year's points
            sql = "SELECT yearSevenScore, yearEightScore, yearNineScore, yearTenScore, yearTwelveScore FROM house "
                    + " WHERE yearOfEntry = " + iYearOfEntry + " AND house = " + h.getValue() + ";";

            getSession().connect();
            if(!getSession().isConnected())
            {
                return false;
            }

            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            if(resultSet.next())
            {
                bw.write("\r\n");
                do
                {
                    bw.write(iYearOfEntry + ", " + resultSet.getInt("yearSevenScore")
                            + ", " + resultSet.getInt("yearEightScore")
                            + ", " + resultSet.getInt("yearNineScore")
                            + ", " + resultSet.getInt("yearTenScore")
                            + ", " + resultSet.getInt("yearTwelveScore"));
                }
                while(resultSet.next());
            }


        }
        catch(SQLException se)
        {
            bw.close();
            fFile.delete();
            return false;
        }

        bw.flush();
        bw.close();
        return true;

    }

    /**
     * ======================================================================
     * OUTPUT RESULTS FOR EVENT OVER YEARS
     * Inputs: et (eventType)
     * Outputs: boolean
     *          True if goes through
     *          False if db not connected or missing event
     * Comments: Outputs the results for an event over the years
     *           Cannot be done on year 7 as they have no record
     * ======================================================================
     */

    public boolean outputResultsForEventOverYears(eventType et) throws IOException
    {
        DateFormat df = new SimpleDateFormat("ddMMyy-HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        int iYearOfEvent, iYearOfEntry = 1970, i;
        result r;
        boolean bRecordPresent = true;

        File fFile = new File(et.name() + "-Results-" + df.format(cal.getTime()) + ".csv");
        fFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, true));

        iYearOfEvent = Calendar.getInstance().get(Calendar.YEAR) - 1;

        bw.write("event,year,y7_m,y7_e,y8_m,y8_e,y9_m,y9_e,y10_m,y10_e,y12_m,y12_e");

        while(bRecordPresent)
        {
            bw.write("\r\n" + et.name() + "," + iYearOfEvent);

            //set to false.
            //if a record is present, can check if next is present
            bRecordPresent = false;
            for(i = 7 ; i < 12 ; i++)
            {
                //skip year 11
                if(i == 11)
                {
                    i++;
                }

                //get year of entry for year group based upon year of event
                switch(i)
                {
                    case 7:
                        iYearOfEntry = iYearOfEvent - 1;
                        break;
                    //year 8
                    case 8:
                        iYearOfEntry = iYearOfEvent - 2;
                        break;
                    //year 9
                    case 9:
                        iYearOfEntry = iYearOfEvent - 3;
                        break;
                    //year 10
                    case 10:
                        iYearOfEntry = iYearOfEvent - 4;
                        break;
                    //year 12
                    case 12:
                        iYearOfEntry = iYearOfEvent - 6;
                        break;
                    default:
                        return false;
                }

                r = getResultFromDB(et, false, iYearOfEvent, iYearOfEntry);

                if(r != null)
                {
                    bw.write("," + r.getFirstResult());
                    bRecordPresent = true;
                }
                if(r == null)
                {
                    bw.write(",");
                }

                r = getResultFromDB(et, true, iYearOfEvent, iYearOfEntry);

                if(r != null)
                {
                    bw.write("," + r.getFirstResult());
                    bRecordPresent = true;
                }
                if(r == null)
                {
                    bw.write(",");
                }
            }

            iYearOfEvent--;
        }

        bw.flush();
        bw.close();

        return true;
    }

    /**
     * ======================================================================
     * OUTPUT RECORD HOLDERS
     * Inputs: N/A
     * Outputs: boolean
     *          True if goes through
     *          False if db not connected
     * Comments: Outputs the record holders
     * ======================================================================
     */

    public boolean outputRecordHolders() throws IOException
    {
        int iYear;
        int i, j, k;
        DateFormat df = new SimpleDateFormat("ddMMyy-HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        boolean bRecordCheck;
        result r;
        LinkedList<house> h = new LinkedList<house>();

        File fFile = new File("Records-" + df.format(cal.getTime()) + ".csv");
        fFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, true));

        //output headings
        bw.write("year_group,100m_name,100m_result,200m_name,200m_result,400m_name,400m_result," +
                "800m_name,800m_result,1500m_name,1500m_result,8x100m_house,8x100m_result,4x200m_house,4x200m_result,"
                + "lj_name,lj_result,hj_name,hj_result,j_name,j_result,sp_name,sp_result,400mr_name,400mr_result");

        for(i = 1 ; i < 7 ; i++)
        {
            //skip year 11, go straight to year 12
            if(i == 5)
            {
                i++;
            }
            //create new line and output year (i value + 6)
            bw.write("\r\n" + (i+6));
            for (j = 1; j < 12; j++)
            {
                //if year 12, check what event
                if(i == 6)
                {
                    switch(j)
                    {
                        //skip events with id 3 to 11, fill in missing commas
                        case 3:
                            j = 12;
                            bw.write(",,,,,,,,,,,,,,,,,,");
                            break;
                        //if 100m to 200m ignore
                        case 1:
                        case 2:
                            break;
                    }
                }

                //initialise record check
                bRecordCheck = false;
                iYear = Calendar.getInstance().get(Calendar.YEAR);
                while(!bRecordCheck)
                {
                    //mass participation check
                    r = getResultFromDB(eventType.fromInt(j), false, iYear, iYear - i);
                    if(r != null)
                    {
                        //if record check is successful, output house intial(s) or name
                        //exit loop once successful
                        bRecordCheck = checkForRecord(r);
                        if(bRecordCheck)
                        {
                            switch (j)
                            {
                                case 6:
                                case 7:
                                case 12:
                                    h.clear();
                                    if (r.getCliffPosition() == position.FIRST)
                                    {
                                        h.add(house.CLIFF);
                                    }
                                    if (r.getDownsPosition() == position.FIRST)
                                    {
                                        h.add(house.DOWNS);
                                    }
                                    if (r.getFleetPosition() == position.FIRST)
                                    {
                                        h.add(house.FLEET);
                                    }
                                    if (r.getHillPosition() == position.FIRST)
                                    {
                                        h.add(house.HILL);
                                    }
                                    if (r.getSchoolPosition() == position.FIRST)
                                    {
                                        h.add(house.SCHOOL);
                                    }
                                    if (r.getTownPosition() == position.FIRST)
                                    {
                                        h.add(house.TOWN);
                                    }
                                    bw.write(",");
                                    for (k = 0; k < h.size(); k++)
                                    {
                                        bw.write(h.get(k).name().charAt(0));
                                    }
                                    bw.write("," + r.getFirstResult());
                                    break;
                                default:
                                    bw.write("," + r.getFirstPlacePerson().getFirstName().charAt(0)
                                            + " " + r.getFirstPlacePerson().getLastName()
                                            + "," + r.getFirstResult());
                                    break;
                            }
                            continue;
                        }
                    }

                    //elite participation check
                    r = getResultFromDB(eventType.fromInt(j), true, iYear, iYear - i);
                    if(r != null)
                    {
                        //if record check is successful, output house intial(s) or name
                        //exit loop once successful
                        bRecordCheck = checkForRecord(r);
                        if(bRecordCheck)
                        {
                            switch(j)
                            {
                                case 6:
                                case 7:
                                case 12:
                                    h.clear();
                                    if (r.getCliffPosition() == position.FIRST)
                                    {
                                        h.add(house.CLIFF);
                                    }
                                    if (r.getDownsPosition() == position.FIRST)
                                    {
                                        h.add(house.DOWNS);
                                    }
                                    if (r.getFleetPosition() == position.FIRST)
                                    {
                                        h.add(house.FLEET);
                                    }
                                    if (r.getHillPosition() == position.FIRST)
                                    {
                                        h.add(house.HILL);
                                    }
                                    if (r.getSchoolPosition() == position.FIRST)
                                    {
                                        h.add(house.SCHOOL);
                                    }
                                    if (r.getTownPosition() == position.FIRST)
                                    {
                                        h.add(house.TOWN);
                                    }
                                    bw.write(",");
                                    for (k = 0; k < h.size(); k++)
                                    {
                                        bw.write(h.get(k).name().charAt(0));
                                    }
                                    bw.write("," + r.getFirstResult());
                                    break;
                                default:
                                    bw.write("," + r.getFirstPlacePerson().getFirstName().charAt(0)
                                            + " " + r.getFirstPlacePerson().getLastName()
                                            + "," + r.getFirstResult());
                                    break;
                            }
                            continue;
                        }
                    }
                    iYear--;
                    //assume that if the year is less than 1975 (further than records go back) then missing any result
                    if(iYear < 1975)
                    {
                        bw.write(",,");
                        bRecordCheck = true;
                    }
                }
            }
        }

        bw.flush();
        bw.close();

        return true;
    }

    /**
     * ======================================================================
     * GET LIKELY RESULTS OF SPORTS DAY
     * Inputs: N/A
     * Outputs: int[]
     *          Indexes 0 to 5 representing the respective scores of Cliff to Town
     * Comments: Gets the projected score for each house
     * ======================================================================
     */

    public int[] getLikelyResultsOfSportsDay() throws IOException
    {
        int [] iProjectedScore =
        {
                0, 0, 0, 0, 0, 0
        };
        int [][] iElo = new int[6][2];
        int i, j, k = 0;
        int iYear = Calendar.getInstance().get(Calendar.YEAR);
        int iYearOfEntry = 1970;

        //if not connected, don't attempt
        getSession().connect();
        if(!getSession().isConnected())
        {
            return null;
        }

        try
        {
            //for years 8 to 10
            for(i = 8 ; i < 11 ; i++)
            {
                //get year of entry
                switch(i)
                {
                    //year 8
                    case 8:
                        iYearOfEntry = iYear - 2;
                        break;
                    //year 9
                    case 9:
                        iYearOfEntry = iYear - 3;
                        break;
                    //year 10
                    case 10:
                        iYearOfEntry = iYear - 4;
                        break;
                }

                //for all year 8 to 10 events (excludes 400m relay)
                for(j = 1 ; j < 12 ; j++)
                {
                    //if event is not a relay (relays are considered elite)
                    if(j != eventType.EIGHT_BY_HUNDRED_METRE.getValue()
                            && j != eventType.FOUR_BY_TWO_HUNDRED_METRE.getValue())
                    {
                        sql = "SELECT elo FROM elo WHERE "
                                + "house IN (" + house.CLIFF.getValue()
                                + ", " + house.DOWNS.getValue()
                                + ", " + house.HILL.getValue()
                                + ", " + house.SCHOOL.getValue()
                                + ", " + house.TOWN.getValue()
                                + ", " + house.FLEET.getValue()
                                + ")"
                                + " AND competition = " + j
                                + " AND elite = " + false
                                + " AND yearOfEntry = " + iYearOfEntry
                                + " ORDER BY FIELD"
                                + "(house, " + house.CLIFF.getValue()
                                + ", " + house.DOWNS.getValue()
                                + ", " + house.FLEET.getValue()
                                + ", " + house.HILL.getValue()
                                + ", " + house.SCHOOL.getValue()
                                + ", " + house.TOWN.getValue()
                                + ")"
                                + ";";

                        sqlStatement = getSession().getConnection().createStatement();
                        resultSet = sqlStatement.executeQuery(sql);

                        //if results present, sort array and then add points
                        if(resultSet.next())
                        {
                            k = 0;
                            do {
                                iElo[k][0] = resultSet.getInt("elo");
                                iElo[k][1] = k;
                                k++;
                            }
                            while (resultSet.next());

                            iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                            for (k = 0; k < 6; k++) {
                                iProjectedScore[iElo[k][1]] += 12 - (2 * k);
                            }
                        }
                    }

                    //elite event
                    sql = "SELECT elo FROM elo WHERE "
                            + "house IN (" + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ", " + house.FLEET.getValue()
                            + ")"
                            + " AND competition = " + j
                            + " AND elite = " + true
                            + " AND yearOfEntry =" + iYearOfEntry
                            + " ORDER BY FIELD"
                            + "(house, " + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.FLEET.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ")"
                            + ";";


                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    //if results present, sort array then add points
                    if(resultSet.next())
                    {
                        k = 0;

                        do
                        {
                            iElo[k][0] = resultSet.getInt("elo");
                            iElo[k][1] = k;
                            k++;
                        }
                        while (resultSet.next());

                        iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                        for (k = 0; k < 6; k++)
                        {
                            iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                        }
                    }
                }
            }

            //specific year 12 elo checks
            i = 12;
            iYearOfEntry = iYear - 6;
            //100m and 200m (elite and mass in year 12)
            for(j = 1 ; j < 3 ; j++)
            {
                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ", " + house.FLEET.getValue()
                        + ")"
                        + " AND competition = " + j
                        + " AND elite = " + false
                        + " AND yearOfEntry = " + iYearOfEntry
                        + " ORDER BY FIELD"
                        + "(house, " + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.FLEET.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ")"
                        + ";";

                sqlStatement = getSession().getConnection().createStatement();
                resultSet = sqlStatement.executeQuery(sql);

                if(resultSet.next())
                {
                    k = 0;
                    do {
                        iElo[k][0] = resultSet.getInt("elo");
                        iElo[k][1] = k;
                        k++;
                    }
                    while (resultSet.next());

                    iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                    for (k = 0; k < 6; k++) {
                        iProjectedScore[iElo[k][1]] += 12 - (2 * k);
                    }
                }


                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ", " + house.FLEET.getValue()
                        + ")"
                        + " AND competition = " + j
                        + " AND elite = " + true
                        + " AND yearOfEntry = " + iYearOfEntry
                        + " ORDER BY FIELD"
                        + "(house, " + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.FLEET.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ")"
                        + ";";


                sqlStatement = getSession().getConnection().createStatement();
                resultSet = sqlStatement.executeQuery(sql);

                if(resultSet.next())
                {
                    k = 0;

                    do
                    {
                        iElo[k][0] = resultSet.getInt("elo");
                        iElo[k][1] = k;
                        k++;
                    }
                    while (resultSet.next());

                    iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                    for (k = 0; k < 6; k++)
                    {
                        iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                    }
                }
            }

            //8x100m relay for year 12
            sql = "SELECT elo FROM elo WHERE "
                + "house IN (" + house.CLIFF.getValue()
                + ", " + house.DOWNS.getValue()
                + ", " + house.HILL.getValue()
                + ", " + house.SCHOOL.getValue()
                + ", " + house.TOWN.getValue()
                + ", " + house.FLEET.getValue()
                + ")"
                + " AND competition = " + 6
                + " AND elite = " + true
                + " AND yearOfEntry = " + iYearOfEntry
                + " ORDER BY FIELD"
                + "(house, " + house.CLIFF.getValue()
                + ", " + house.DOWNS.getValue()
                + ", " + house.FLEET.getValue()
                + ", " + house.HILL.getValue()
                + ", " + house.SCHOOL.getValue()
                + ", " + house.TOWN.getValue()
                + ")"
                + ";";


            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            if(resultSet.next())
            {
                k = 0;

                do
                {
                    iElo[k][0] = resultSet.getInt("elo");
                    iElo[k][1] = k;
                    k++;
                }
                while (resultSet.next());

                iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                for (k = 0; k < 6; k++)
                {
                    iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                }
            }

            //400m relay for year 12
            sql = "SELECT elo FROM elo WHERE "
                    + "house IN (" + house.CLIFF.getValue()
                    + ", " + house.DOWNS.getValue()
                    + ", " + house.HILL.getValue()
                    + ", " + house.SCHOOL.getValue()
                    + ", " + house.TOWN.getValue()
                    + ", " + house.FLEET.getValue()
                    + ")"
                    + " AND competition = " + 12
                    + " AND elite = " + true
                    + " AND yearOfEntry = " + iYearOfEntry
                    + " ORDER BY FIELD"
                    + "(house, " + house.CLIFF.getValue()
                    + ", " + house.DOWNS.getValue()
                    + ", " + house.FLEET.getValue()
                    + ", " + house.HILL.getValue()
                    + ", " + house.SCHOOL.getValue()
                    + ", " + house.TOWN.getValue()
                    + ")"
                    + ";";


            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            if(resultSet.next())
            {
                k = 0;

                do
                {
                    iElo[k][0] = resultSet.getInt("elo");
                    iElo[k][1] = k;
                    k++;
                }
                while (resultSet.next());

                iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                for (k = 0; k < 6; k++)
                {
                    iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                }
            }
            iProjectedScore[2] = getWeightedFleetScore(iProjectedScore[2]);
        }
        catch(SQLException se)
        {
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
            return null;
        }

        return iProjectedScore;
    }

    /**
     * ======================================================================
     * GET LIKELY RESULTS OF YEAR GROUP
     * Inputs: h (house)
     * Outputs: int[]
     *          Indexes 0 to 5 representing the respective scores of Cliff to Town
     * Comments: Gets the projected score for each house
     * ======================================================================
     */

    public int[] getLikelyResultsOfYearGroup(int iYearGroup) throws IOException
    {
        int[] iProjectedScore =
                {
                        0, 0, 0, 0, 0, 0
                };
        int[][] iElo = new int[6][2];
        int j, k = 0;
        int iYear = Calendar.getInstance().get(Calendar.YEAR);
        int iYearOfEntry = 1970;

        if(!getSession().isConnected())
        {
            return null;
        }

        //if not between 8 to 10, or 12 then stop as not useful to predict years that will not participate
        if(iYearGroup < 8 || iYearGroup == 11 || iYearGroup > 12)
        {
            return null;
        }

        try
        {
            switch(iYearGroup)
            {
                //year 8
                case 8:
                    iYearOfEntry = iYear - 2;
                    break;
                //year 9
                case 9:
                    iYearOfEntry = iYear - 3;
                    break;
                //year 10
                case 10:
                    iYearOfEntry = iYear - 4;
                    break;
                case 12:
                    iYearOfEntry = iYear - 6;
                    break;
            }

            if(iYearGroup > 7 && iYearGroup < 11)
            {
                for(j = 1; j < 12; j++)
                {
                    //if event is not relay (that are considered mass)
                    if(j != eventType.EIGHT_BY_HUNDRED_METRE.getValue()
                            && j != eventType.FOUR_BY_TWO_HUNDRED_METRE.getValue())
                    {
                        sql = "SELECT elo FROM elo WHERE "
                                + "house IN (" + house.CLIFF.getValue()
                                + ", " + house.DOWNS.getValue()
                                + ", " + house.HILL.getValue()
                                + ", " + house.SCHOOL.getValue()
                                + ", " + house.TOWN.getValue()
                                + ", " + house.FLEET.getValue()
                                + ")"
                                + " AND competition = " + j
                                + " AND elite = " + false
                                + " AND yearOfEntry = " + iYearOfEntry
                                + " ORDER BY FIELD"
                                + "(house, " + house.CLIFF.getValue()
                                + ", " + house.DOWNS.getValue()
                                + ", " + house.FLEET.getValue()
                                + ", " + house.HILL.getValue()
                                + ", " + house.SCHOOL.getValue()
                                + ", " + house.TOWN.getValue()
                                + ")"
                                + ";";

                        sqlStatement = getSession().getConnection().createStatement();
                        resultSet = sqlStatement.executeQuery(sql);

                        //add points if present
                        if(resultSet.next())
                        {
                            k = 0;
                            do {
                                iElo[k][0] = resultSet.getInt("elo");
                                iElo[k][1] = k;
                                k++;
                            }
                            while (resultSet.next());

                            iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                            for (k = 0; k < 6; k++) {
                                iProjectedScore[iElo[k][1]] += 12 - (2 * k);
                            }
                        }
                    }

                    //elite participation
                    sql = "SELECT elo FROM elo WHERE "
                            + "house IN (" + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ", " + house.FLEET.getValue()
                            + ")"
                            + " AND competition = " + j
                            + " AND elite = " + true
                            + " AND yearOfEntry = " + iYearOfEntry
                            + " ORDER BY FIELD"
                            + "(house, " + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.FLEET.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ")"
                            + ";";


                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        k = 0;

                        do
                        {
                            iElo[k][0] = resultSet.getInt("elo");
                            iElo[k][1] = k;
                            k++;
                        }
                        while (resultSet.next());

                        iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                        for (k = 0; k < 6; k++)
                        {
                            iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                        }
                    }
                }
            }

            if(iYearGroup == 12)
            {
                //100m and 200m mass and elite
                for (j = 1; j < 3; j++)
                {
                    //mass
                    sql = "SELECT elo FROM elo WHERE "
                            + "house IN (" + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ", " + house.FLEET.getValue()
                            + ")"
                            + " AND competition = " + j
                            + " AND elite = " + false
                            + " AND yearOfEntry = " + iYearOfEntry
                            + " ORDER BY FIELD"
                            + "(house, " + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.FLEET.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ")"
                            + ";";

                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        k = 0;
                        do {
                            iElo[k][0] = resultSet.getInt("elo");
                            iElo[k][1] = k;
                            k++;
                        }
                        while (resultSet.next());

                        iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                        for (k = 0; k < 6; k++) {
                            iProjectedScore[iElo[k][1]] += 12 - (2 * k);
                        }
                    }

                    //elite
                    sql = "SELECT elo FROM elo WHERE "
                            + "house IN (" + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ", " + house.FLEET.getValue()
                            + ")"
                            + " AND competition = " + j
                            + " AND elite = " + true
                            + " AND yearOfEntry = " + iYearOfEntry
                            + " ORDER BY FIELD"
                            + "(house, " + house.CLIFF.getValue()
                            + ", " + house.DOWNS.getValue()
                            + ", " + house.FLEET.getValue()
                            + ", " + house.HILL.getValue()
                            + ", " + house.SCHOOL.getValue()
                            + ", " + house.TOWN.getValue()
                            + ")"
                            + ";";

                    sqlStatement = getSession().getConnection().createStatement();
                    resultSet = sqlStatement.executeQuery(sql);

                    if(resultSet.next())
                    {
                        k = 0;

                        do
                        {
                            iElo[k][0] = resultSet.getInt("elo");
                            iElo[k][1] = k;
                            k++;
                        }
                        while (resultSet.next());

                        iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                        for (k = 0; k < 6; k++)
                        {
                            iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                        }
                    }

                }

                //8x100m relay
                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ", " + house.FLEET.getValue()
                        + ")"
                        + " AND competition = " + 6
                        + " AND elite = " + true
                        + " AND yearOfEntry = " + iYearOfEntry
                        + " ORDER BY FIELD"
                        + "(house, " + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.FLEET.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ")"
                        + ";";


                sqlStatement = getSession().getConnection().createStatement();
                resultSet = sqlStatement.executeQuery(sql);

                if(resultSet.next())
                {
                    k = 0;

                    do
                    {
                        iElo[k][0] = resultSet.getInt("elo");
                        iElo[k][1] = k;
                        k++;
                    }
                    while (resultSet.next());

                    iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                    for (k = 0; k < 6; k++)
                    {
                        iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                    }
                }

                //400m relay
                sql = "SELECT elo FROM elo WHERE "
                        + "house IN (" + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ", " + house.FLEET.getValue()
                        + ")"
                        + " AND competition = " + 12
                        + " AND elite = " + true
                        + " AND yearOfEntry = " + iYearOfEntry
                        + " ORDER BY FIELD"
                        + "(house, " + house.CLIFF.getValue()
                        + ", " + house.DOWNS.getValue()
                        + ", " + house.FLEET.getValue()
                        + ", " + house.HILL.getValue()
                        + ", " + house.SCHOOL.getValue()
                        + ", " + house.TOWN.getValue()
                        + ")"
                        + ";";


                sqlStatement = getSession().getConnection().createStatement();
                resultSet = sqlStatement.executeQuery(sql);

                if(resultSet.next())
                {
                    k = 0;

                    do
                    {
                        iElo[k][0] = resultSet.getInt("elo");
                        iElo[k][1] = k;
                        k++;
                    }
                    while (resultSet.next());

                    iElo = sortArrayDescending(iElo, 0, iElo.length - 1);

                    for (k = 0; k < 6; k++)
                    {
                        iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                    }
                }

            }
            //weight fleet score
            iProjectedScore[2] = getWeightedFleetScore(iProjectedScore[2]);
        }
        catch(SQLException se)
        {
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
            return null;
        }

        return iProjectedScore;
    }

    /**
     * ======================================================================
     * GET LIKELY RESULTS OF YEAR GROUP
     * Inputs: et (eventType), el (boolean), iYearGroup (int)
     * Outputs: int[]
     *          Indexes 0 to 5 representing the respective scores of Cliff to Town
     * Comments: Gets the projected score for each house for an event
     * ======================================================================
     */

    public int[] getLikelyResultsOfEvent(eventType et, boolean el, int iYearGroup) throws IOException
    {
        int[] iProjectedScore =
                {
                        0, 0, 0, 0, 0, 0
                };
        int[][] iElo = new int[6][2];
        int j, k = 0;
        int iYear = Calendar.getInstance().get(Calendar.YEAR);
        int iYearOfEntry = 1970;

        //if not connected, disconnect and stop
        if(!getSession().isConnected())
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: SQL Error: No Database Connection.");
            return null;
        }

        //if not in correct year interval, stop
        if(iYearGroup < 8 || iYearGroup == 11 || iYearGroup > 12)
        {
            getSession().disconnect();
            System.err.println(this.getClass().getName() + ".java :: Error: Not a valid year group.");
            return null;
        }

        //if event is incorrect for year group (12) return null
        if(iYearGroup == 12)
        {
            switch(et)
            {
                case FOUR_HUNDRED_METRE:
                case EIGHT_HUNDRED_METRE:
                case FIFTEEN_HUNDRED_METRE:
                case FOUR_BY_TWO_HUNDRED_METRE:
                case LONG_JUMP:
                case HIGH_JUMP:
                case JAVELIN:
                case SHOT_PUTT:
                    System.err.println(this.getClass().getName() + ".java :: Error: Event not valid for year group (12)");
                    return null;
            }
        }

        try
        {
            switch (iYearGroup)
            {
                //year 8
                case 8:
                    iYearOfEntry = iYear - 2;
                    break;
                //year 9
                case 9:
                    iYearOfEntry = iYear - 3;
                    break;
                //year 10
                case 10:
                    iYearOfEntry = iYear - 4;
                    break;
                //year 12
                case 12:
                    iYearOfEntry = iYear - 6;
                    break;
            }

            sql = "SELECT elo FROM elo WHERE "
                    + "house IN (" + house.CLIFF.getValue()
                    + ", " + house.DOWNS.getValue()
                    + ", " + house.HILL.getValue()
                    + ", " + house.SCHOOL.getValue()
                    + ", " + house.TOWN.getValue()
                    + ", " + house.FLEET.getValue()
                    + ")"
                    + " AND competition = " + et.getValue()
                    + " AND elite = " + el
                    + " AND yearOfEntry = " + iYearOfEntry
                    + " ORDER BY FIELD"
                    + "(house, " + house.CLIFF.getValue()
                    + ", " + house.DOWNS.getValue()
                    + ", " + house.FLEET.getValue()
                    + ", " + house.HILL.getValue()
                    + ", " + house.SCHOOL.getValue()
                    + ", " + house.TOWN.getValue()
                    + ")"
                    + ";";

            sqlStatement = getSession().getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            if(resultSet.next())
            {
                k = 0;
                do
                {
                    iElo[k][0] = resultSet.getInt("elo");
                    iElo[k][1] = k;
                    k++;
                }
                while (resultSet.next());

                iElo = sortArrayDescending(iElo, 0, iElo.length - 1);
                if(el)
                {
                    for (k = 0; k < 6; k++)
                    {
                        iProjectedScore[iElo[k][1]] += 24 - (2 * k);
                    }
                }
                if(!el)
                {
                    for(k = 0 ; k < 6 ; k++)
                    {
                        iProjectedScore[iElo[k][1]] += 12 - (2 * k);
                    }
                }
            }
            //weight fleet's scores
            iProjectedScore[2] = getWeightedFleetScore(iProjectedScore[2]);
        }
        catch(SQLException se)
        {
            System.err.println(this.getClass().getName() + ".java :: SQL Exception: " + se);
            return null;
        }

        return iProjectedScore;
    }

    /**
     * ======================================================================
     * SORT ARRAY - DESCENDING
     * Inputs: iArray (int[][]), iLow (int), iHigh (int)
     * Outputs: int[][]
     *          First [] represents order in place
     *          [][0] represents Elo
     *          [][1] represents House (in int form)
     * Comments: Sorts a 2D array using the quick-sort algorithm
     *           Uses recursion to sort the left hand and right hand sides
     * ======================================================================
     */

    public int[][] sortArrayDescending(int[][] iArray, int iLow, int iHigh)
    {
        int iMiddle, iPivot, i, j;
        int iTemp[];

        iMiddle = (iLow + iHigh)/2;
        iPivot = iArray[iMiddle][0];

        i = iLow;
        j = iHigh;
        while(i <= j)
        {
            while(iArray[i][0] > iPivot)
            {
                i++;
            }

            while(iArray[j][0] < iPivot)
            {
                j--;
            }

            if(i <= j)
            {
                iTemp = iArray[i];
                iArray[i] = iArray[j];
                iArray[j] = iTemp;
                i++;
                j--;
            }
        }

        if(iLow < j)
        {
            iArray = sortArrayDescending(iArray, iLow, j);
        }
        if(iHigh > i)
        {
            iArray = sortArrayDescending(iArray, i, iHigh);
        }
        return iArray;
    }

    /**
     * ======================================================================
     * GET SESSION
     * Inputs: N/A
     * Outputs: session
     * Comments: Gets the database session currently in use
     * ======================================================================
     */

    public session getSession()
    {
        return this.dbSession;
    }

    /**
     * ======================================================================
     * WEIGHT FLEET SCORE
     * Inputs: bYearGroup (boolean[])
     * Outputs: int
     * Comments: Gets the weight for Fleet scoreboard/prediction points
     *           Based upon user setting
     * ======================================================================
     */

    public int weightFleetScore(boolean [] bYearGroup)
    {
        int iWeight = 0;

        if(bYearGroup[0])
        {
            iWeight += 20;
        }
        if(bYearGroup[1])
        {
            iWeight += 20;
        }
        if(bYearGroup[2])
        {
            iWeight += 20;
        }
        if(bYearGroup[3])
        {
            iWeight += 20;
        }
        if(bYearGroup[4])
        {
            iWeight += 6;
        }
        if(iWeight == 0)
        {
            iWeight = 1;
        }

        return iWeight;
    }

    /**
     * ======================================================================
     * GET WEIGHTED FLEET SCORE
     * Inputs: iScore (int)
     * Outputs: int
     * Comments: Outputs weighted score based on user settings
     * ======================================================================
     */

    public int getWeightedFleetScore(int iScore) throws IOException
    {
        return (int)(iScore * (86.0 / weightFleetScore(getFleetSettings())));
    }

    /**
     * ======================================================================
     * GET FLEET SCORE
     * Inputs: N/A
     * Outputs: boolean[]
     * Comments: Based upon user's settings
     * ======================================================================
     */


    public boolean [] getFleetSettings() throws IOException
    {
        String szFileName = "settings.csv";
        boolean [] bFleetSettings = new boolean[5];
        BufferedReader br = new BufferedReader(new FileReader(szFileName));
        String szInput;
        String [] szTemp;
        int i;

        szInput = br.readLine();
        szTemp = szInput.split(",");

        for(i = 0 ; i < 5 ; i++)
        {
            switch(szTemp[i])
            {
                case "1":
                    bFleetSettings[i] = true;
                    break;
                case "0":
                    bFleetSettings[i] = false;
                    break;
                default:
                    bFleetSettings[i] = true;
                    break;
            }
        }

        return bFleetSettings;
    }

    /**
     * ======================================================================
     * SET FLEET SCORE
     * Inputs: bFleetSettings (boolean[])
     * Outputs: N/A
     * Comments: Sets the weight for Fleet scoreboard/prediction points
     * =======================================================================
     */

    public void setFleetSettings(boolean[] bFleetSettings) throws IOException
    {
        String szPathName = "settings.csv";
        File fFile = new File(szPathName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(fFile, false));
        String szOutput = "";
        int i;

        for(i = 0 ; i < 5 ; i++)
        {
            if(bFleetSettings[i])
            {
                szOutput += "1";
            }
            if(!bFleetSettings[i])
            {
                szOutput += "0";
            }

            if(i != 4)
            {
                szOutput += ",";
            }
        }

        bw.write(szOutput);
        bw.flush();
        bw.close();

    }
}
