/**
 * @author Alex Lockwood
 * @purpose An object that stores the result of an event
 */

package sports_day;

import java.util.LinkedList;

public class result
{
    //variables
    private event eCompetition;

    private LinkedList<person> pFirstPlace = new LinkedList<person>();
    private LinkedList<person> pSecondPlace = new LinkedList<person>();
    private LinkedList<person> pThirdPlace = new LinkedList<person>();

    private position poCliffPosition;
    private position poDownsPosition;
    private position poFleetPosition;
    private position poHillPosition;
    private position poSchoolPosition;
    private position poTownPosition;

    //add results as string.
    //: denotes time
    //. denotes distance
    private String szFirstResult;
    private String szSecondResult;
    private String szThirdResult;

    //constructors
    public result()
    {
        reset();
    }

    public result(event e, person w1, person w2, person w3, position poC, position poD, position poF, position poH
            , position poS, position poT, String szF, String szS, String szT)
    {
        this();
        setEvent(e);
        addToFirstPlace(w1);
        addToSecondPlace(w2);
        addToThirdPlace(w3);
        setCliffPosition(poC);
        setDownsPosition(poD);
        setFleetPosition(poF);
        setHillPosition(poH);
        setSchoolPosition(poS);
        setTownPosition(poT);
        setFirstResult(szF);
        setSecondResult(szS);
        setThirdResult(szT);
    }

    public result(event e, person[] w1, person[] w2, person[] w3, position poC, position poD, position poF, position poH
            , position poS, position poT, String szF, String szS, String szT)
    {
        this();
        setEvent(e);
        addToFirstPlace(w1);
        addToSecondPlace(w2);
        addToThirdPlace(w3);
        setCliffPosition(poC);
        setDownsPosition(poD);
        setFleetPosition(poF);
        setHillPosition(poH);
        setSchoolPosition(poS);
        setTownPosition(poT);
        setFirstResult(szF);
        setSecondResult(szS);
        setThirdResult(szT);
    }

    //getters
    public event getEvent()
    {
        return this.eCompetition;
    }

    public person getFirstPlacePerson()
    {
        if(this.pFirstPlace.size() > 0)
        {
            return this.pFirstPlace.get(0);
        }
        else
        {
            return null;
        }
    }

    public person getFirstPlacePerson(int iListPosition)
    {
        if(this.pFirstPlace.size() > iListPosition)
        {
            return this.pFirstPlace.get(iListPosition);
        }
        else
        {
            return null;
        }
    }

    public person[] getFirstPlacePeople()
    {
        person[] poFirstPlace = new person[this.pFirstPlace.size()];
        int i;

        for(i = 0 ; i < this.pFirstPlace.size() ; i++)
        {
            poFirstPlace[i] = this.pFirstPlace.get(i);
        }

        return poFirstPlace;
    }

    public person getSecondPlacePerson()
    {
        if(this.pSecondPlace.size() > 0)
        {
            return this.pSecondPlace.get(0);
        }
        else
        {
            return null;
        }
    }

    public person getSecondPlacePerson(int iListPosition)
    {
        if(this.pSecondPlace.size() > iListPosition)
        {
            return this.pSecondPlace.get(iListPosition);
        }
        else
        {
            return null;
        }
    }

    public person[] getSecondPlacePeople()
    {
        person[] poSecondPlace = new person[this.pSecondPlace.size()];
        int i;

        for(i = 0 ; i < this.pSecondPlace.size() ; i++)
        {
            poSecondPlace[i] = this.pSecondPlace.get(i);
        }

        return poSecondPlace;
    }

    public person getThirdPlacePerson()
    {
        if(this.pThirdPlace.size() > 0)
        {
            return this.pThirdPlace.get(0);
        }
        else
        {
            return null;
        }
    }

    public person getThirdPlacePerson(int iListPosition)
    {
        if(this.pThirdPlace.size() > iListPosition)
        {
            return this.pThirdPlace.get(iListPosition);
        }
        else
        {
            return null;
        }
    }

    public person[] getThirdPlacePeople()
    {
        person[] poThirdPlace = new person[this.pThirdPlace.size()];
        int i;

        for(i = 0 ; i < this.pThirdPlace.size() ; i++)
        {
            poThirdPlace[i] = this.pThirdPlace.get(i);
        }

        return poThirdPlace;
    }

    public position getCliffPosition() { return this.poCliffPosition; }

    public position getDownsPosition() { return this.poDownsPosition; }

    public position getFleetPosition() { return this.poFleetPosition; }

    public position getHillPosition() { return this.poHillPosition; }

    public position getSchoolPosition() { return this.poSchoolPosition; }

    public position getTownPosition() { return this.poTownPosition; }

    public String getFirstResult() { return this.szFirstResult; }

    public String getSecondResult() { return this.szSecondResult; }

    public String getThirdResult() { return this.szThirdResult; }

    //setters
    public void setEvent(event e) { this.eCompetition = e; }

    //add one person to first place
    public void addToFirstPlace(person p) { this.pFirstPlace.add(p); }

    //add multiple people to first place
    public void addToFirstPlace(person[] p)
    {
        int i;

        for(i = 0 ; i < p.length ; i++)
        {
            this.pFirstPlace.add(p[i]);
        }
    }

    //add one person to second place
    public void addToSecondPlace(person p) { this.pSecondPlace.add(p); }

    //add multiple people to second place
    public void addToSecondPlace(person[] p)
    {
        int i;

        for(i = 0 ; i < p.length ; i++)
        {
            this.pSecondPlace.add(p[i]);
        }
    }

    //add one person to third place
    public void addToThirdPlace(person p) { this.pThirdPlace.add(p); }

    //add mulitple people to third place
    public void addToThirdPlace(person[] p)
    {
        int i;

        for(i = 0 ; i < p.length ; i++)
        {
            this.pThirdPlace.add(p[i]);
        }
    }

    //set positions
    public void setCliffPosition(position poCliffPosition) { this.poCliffPosition = poCliffPosition; }

    public void setDownsPosition(position poDownsPosition) { this.poDownsPosition = poDownsPosition; }

    public void setFleetPosition(position poFleetPosition) { this.poFleetPosition = poFleetPosition; }

    public void setHillPosition(position poHillPosition) { this.poHillPosition = poHillPosition; }

    public void setSchoolPosition(position poSchoolPosition) { this.poSchoolPosition = poSchoolPosition; }

    public void setTownPosition(position poTownPosition) { this.poTownPosition = poTownPosition; }

    //set results
    public void setFirstResult(String sz) { this.szFirstResult = sz; }

    public void setSecondResult(String sz) { this.szSecondResult = sz; }

    public void setThirdResult(String sz) { this.szThirdResult = sz; }

    //methods
    public void reset()
    {
        this.setCliffPosition(position.DID_NOT_FINISH);
        this.setDownsPosition(position.DID_NOT_FINISH);
        this.setFleetPosition(position.DID_NOT_FINISH);
        this.setHillPosition(position.DID_NOT_FINISH);
        this.setSchoolPosition(position.DID_NOT_FINISH);
        this.setTownPosition(position.DID_NOT_FINISH);
    }
}