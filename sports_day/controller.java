package sports_day;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Alex Lockwood
 * @purpose The controller for the MVC framework. The middleman between the view and model objects.
 */

public class controller
{
    private model mModel;
    private view vView;

    public controller(model m, view v)
    {
        //add model and view to controller
        this.setModel(m);
        this.setView(v);

        //team button listeners
        this.vView.addCliffEnterButtonListener(new CliffButtonListener());
        this.vView.addDownsEnterButtonListener(new DownsButtonListener());
        this.vView.addFleetEnterButtonListener(new FleetButtonListener());
        this.vView.addHillEnterButtonListener(new HillButtonListener());
        this.vView.addSchoolEnterButtonListener(new SchoolButtonListener());
        this.vView.addTownEnterButtonListener(new TownButtonListener());

        //add/alter results listeners
        this.vView.addResultsEnterButtonListener(new ResultButtonListener());
        this.vView.addFinalTimingButtonListener(new FinalTimingButtonListener());
        this.vView.addFinalDistanceButtonListener(new FinalDistanceButtonListener());

        //scoreboard listener
        this.vView.addScoreboardButtonListener(new ScoreboardButtonListener());

        //report listeners
        this.vView.addThisYearReportButtonListener(new ThisYearReportButtonListener());
        this.vView.addClassReportButtonListener(new ClassOverYearsButtonListener());
        this.vView.addHouseOverYearsButtonListener(new HouseOverYearsButtonListener());
        this.vView.addEventOverYearsButtonListener(new EventOverYearsButtonListener());
        this.vView.addRecordButtonListener(new RecordReportButtonListener());

        //prediction listeners
        this.vView.addPredictWholeSchoolListener(new PredictWholeSchoolListener());
        this.vView.addPredictYearGroupListener(new PredictYearGroupListener());
        this.vView.addPredictEventListener(new PredictEventListener());

        //settings listeners
        this.vView.addSettingsButtonListener(new SettingsButtonListener());
        this.vView.addSettingsSubmitListener(new SettingsSubmitButtonListener());
    }

    //setters
    public void setModel(model m)
    {
        this.mModel = m;
    }

    public void setView(view v)
    {
        this.vView = v;
    }

    //listeners
    class MainResultButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearFirstPlace();
            mModel.clearSecondPlace();
            mModel.clearThirdPlace();
        }
    }

    class CliffButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearCliffTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {

                mModel.addToTeam(house.CLIFF, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.CLIFF, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.CLIFF, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.CLIFF, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.CLIFF, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.CLIFF, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.CLIFF, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.CLIFF, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class DownsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearDownsTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {
                mModel.addToTeam(house.DOWNS, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.DOWNS, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.DOWNS, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.DOWNS, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.DOWNS, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.DOWNS, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.DOWNS, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.DOWNS, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class FleetButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearFleetTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {
                mModel.addToTeam(house.FLEET, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.FLEET, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.FLEET, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.FLEET, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.FLEET, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.FLEET, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.FLEET, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.FLEET, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
            else
            {
                vView.errorMessage("Team was not added");
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class HillButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearHillTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {
                mModel.addToTeam(house.HILL, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.HILL, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.HILL, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.HILL, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.HILL, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.HILL, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.HILL, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.HILL, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class SchoolButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearSchoolTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {
                mModel.addToTeam(house.SCHOOL, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.SCHOOL, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.SCHOOL, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.SCHOOL, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.SCHOOL, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.SCHOOL, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.SCHOOL, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.SCHOOL, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class TownButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mModel.clearTownTeam();
            int iParticipants;

            if(!vView.getParticipantFlag())
            {
                iParticipants = 4;
            }
            else
            {
                iParticipants = 8;
            }

            if(confirmTeamNotNull(iParticipants))
            {
                mModel.addToTeam(house.TOWN, vView.getPerson1FirstNameField(), vView.getPerson1SurnameField());
                mModel.addToTeam(house.TOWN, vView.getPerson2FirstNameField(), vView.getPerson2SurnameField());
                mModel.addToTeam(house.TOWN, vView.getPerson3FirstNameField(), vView.getPerson3SurnameField());
                mModel.addToTeam(house.TOWN, vView.getPerson4FirstNameField(), vView.getPerson4SurnameField());

                if (iParticipants == 8)
                {
                    mModel.addToTeam(house.TOWN, vView.getPerson5FirstNameField(), vView.getPerson5SurnameField());
                    mModel.addToTeam(house.TOWN, vView.getPerson6FirstNameField(), vView.getPerson6SurnameField());
                    mModel.addToTeam(house.TOWN, vView.getPerson7FirstNameField(), vView.getPerson7SurnameField());
                    mModel.addToTeam(house.TOWN, vView.getPerson8FirstNameField(), vView.getPerson8SurnameField());
                }

                vView.confirmTeamOutput();
            }
        }

        public boolean confirmTeamNotNull(int iParticipants)
        {
            if(vView.getPerson1FirstNameField() == null
                    || vView.getPerson1SurnameField() == null
                    || vView.getPerson2FirstNameField() == null
                    || vView.getPerson2SurnameField() == null
                    || vView.getPerson3FirstNameField() == null
                    || vView.getPerson3SurnameField() == null
                    || vView.getPerson4FirstNameField() == null
                    || vView.getPerson4SurnameField() == null)
            {
                return false;
            }
            if(iParticipants == 8)
            {
                if(vView.getPerson5FirstNameField() == null
                        || vView.getPerson5SurnameField() == null
                        || vView.getPerson6FirstNameField() == null
                        || vView.getPerson6SurnameField() == null
                        || vView.getPerson7FirstNameField() == null
                        || vView.getPerson7SurnameField() == null
                        || vView.getPerson8FirstNameField() == null
                        || vView.getPerson8SurnameField() == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    class ResultButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            result r;
            boolean b;
            LinkedList<house> firstPlace = new LinkedList<house>();
            int iYear = 7;
            int iFirstPlaceCount = 0;
            int iSecondPlaceCount = 0;
            int iThirdPlaceCount = 0;
            int iFourthPlaceCount = 0;
            int i;

            //not added fleet as fleet may not be present in a race
            if((vView.getEventListSelection() != 5
                && vView.getEventListSelection() != 6
                && vView.getEventListSelection() != 11)
                    && (vView.getCliffFirstName() == null || vView.getCliffSurname()== null
                    || vView.getDownsFirstName()== null || vView.getDownsSurname()== null
                    || vView.getHillFirstName() == null || vView.getHillSurname() == null
                    || vView.getSchoolFirstName() == null || vView.getSchoolSurname() == null
                    || vView.getTownFirstName() == null || vView.getTownSurname() == null))
            {

                vView.errorMessage("Not added all names or formatting incorrect.");
                return;
            }

            if((vView.getCliffPosition() == 0 || vView.getCliffPosition() == 1)
                    && (vView.getDownsPosition() == 0 || vView.getDownsPosition() == 1)
                    && (vView.getFleetPosition() == 0 || vView.getFleetPosition() == 1)
                    && (vView.getHillPosition() == 0 || vView.getHillPosition() == 1)
                    && (vView.getSchoolPosition() == 0 || vView.getSchoolPosition() == 1)
                    && (vView.getTownPosition() == 0 || vView.getTownPosition() == 1))
            {
                vView.errorMessage("Not set any positions");
            }

            //get amount of first place, second place, third place and fourth place winners
            for(i = 2 ; i < 5 ; i++)
            {
                if(vView.getCliffPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
                if(vView.getDownsPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
                if(vView.getFleetPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
                if(vView.getHillPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
                if(vView.getSchoolPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
                if(vView.getTownPosition() == i)
                {
                    switch(i)
                    {
                        case 2:
                            iFirstPlaceCount++;
                            break;
                        case 3:
                            iSecondPlaceCount++;
                            break;
                        case 4:
                            iThirdPlaceCount++;
                            break;
                        case 5:
                            iFourthPlaceCount++;
                            break;
                    }
                }
            }

            //if tied places are not set up correctly, return error
            if(iFirstPlaceCount > 1 && iSecondPlaceCount > 0)
            {
                vView.errorMessage("Second place present when first place is tied.");
                return;
            }

            if(iSecondPlaceCount > 1 && iThirdPlaceCount > 0)
            {
                vView.errorMessage("Third place present when second place is tied.");
                return;
            }

            if(iThirdPlaceCount > 1 && iFourthPlaceCount > 0)
            {
                vView.errorMessage("Fourth place present when third place is tied.");
                return;
            }
            

            switch(vView.getEventListSelection())
            {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 7:
                case 8:
                case 9:
                case 10:
                    mModel.addToTeam(house.CLIFF, vView.getCliffFirstName(), vView.getCliffSurname());
                    mModel.addToTeam(house.DOWNS, vView.getDownsFirstName(), vView.getDownsSurname());
                    mModel.addToTeam(house.FLEET, vView.getFleetFirstName(), vView.getFleetSurname());
                    mModel.addToTeam(house.HILL, vView.getHillFirstName(), vView.getHillSurname());
                    mModel.addToTeam(house.SCHOOL, vView.getSchoolFirstName(), vView.getSchoolSurname());
                    mModel.addToTeam(house.TOWN, vView.getTownFirstName(), vView.getTownSurname());
                    break;
            }

            r = mModel.addViewSelectionToResult(
                    vView.getEventListSelection()
                    , vView.getEliteListSelection()
                    , vView.getYearListSelection()
                    , vView.getCliffPosition()
                    , vView.getDownsPosition()
                    , vView.getFleetPosition()
                    , vView.getHillPosition()
                    , vView.getSchoolPosition()
                    , vView.getTownPosition()
            );

            if(mModel.getFirstPlaceResult().equals("") || mModel.getSecondPlaceResult().equals("") || mModel.getThirdPlaceResult().equals(""))
            {
                vView.errorMessage("Not added all results");
                return;
            }

            mModel.addFinishedEvent(r);
            b = mModel.checkForRecord(r);

            switch (vView.getYearListSelection())
            {
                //year 7
                case 0:
                    iYear = 7;
                    break;
                //year 8
                case 1:
                    iYear = 8;
                    break;
                //year 9
                case 2:
                    iYear = 9;
                    break;
                //year 10
                case 3:
                    iYear = 10;
                    break;
                //year 12
                case 4:
                    iYear = 12;
                    break;
            }

            if(b)
            {
                switch(r.getEvent().getEventType())
                {
                    case EIGHT_BY_HUNDRED_METRE:
                    case FOUR_BY_TWO_HUNDRED_METRE:
                    case FOUR_HUNDRED_METRE_RELAY:
                    {
                        if(r.getCliffPosition() == position.FIRST)
                        {
                            firstPlace.add(house.CLIFF);
                        }
                        if(r.getDownsPosition() == position.FIRST)
                        {
                            firstPlace.add(house.DOWNS);
                        }
                        if(r.getFleetPosition() == position.FIRST)
                        {
                            firstPlace.add(house.FLEET);
                        }
                        if(r.getHillPosition() == position.FIRST)
                        {
                            firstPlace.add(house.HILL);
                        }
                        if(r.getSchoolPosition() == position.FIRST)
                        {
                            firstPlace.add(house.SCHOOL);
                        }
                        if(r.getTownPosition() == position.FIRST)
                        {
                            firstPlace.add(house.TOWN);
                        }

                        if(firstPlace.size() > 1)
                        {
                            vView.resultConfirmationMessage(iYear);
                        }
                        if(firstPlace.size() == 1)
                        {
                            vView.resultConfirmationMessage(firstPlace.get(0).name(), iYear);
                        }
                        break;
                    }
                    case EIGHT_HUNDRED_METRE:
                    case FIFTEEN_HUNDRED_METRE:
                    case FOUR_HUNDRED_METRE:
                    case HIGH_JUMP:
                    case HUNDRED_METRE:
                    case JAVELIN:
                    case LONG_JUMP:
                    case SHOT_PUTT:
                    case TWO_HUNDRED_METRE:
                    {
                        if(r.getCliffPosition() == position.FIRST)
                        {
                            firstPlace.add(house.CLIFF);
                        }
                        if(r.getDownsPosition() == position.FIRST)
                        {
                            firstPlace.add(house.DOWNS);
                        }
                        if(r.getFleetPosition() == position.FIRST)
                        {
                            firstPlace.add(house.FLEET);
                        }
                        if(r.getHillPosition() == position.FIRST)
                        {
                            firstPlace.add(house.HILL);
                        }
                        if(r.getSchoolPosition() == position.FIRST)
                        {
                            firstPlace.add(house.SCHOOL);
                        }
                        if(r.getTownPosition() == position.FIRST)
                        {
                            firstPlace.add(house.TOWN);
                        }

                        if(firstPlace.size() > 1)
                        {
                            vView.resultConfirmationMessage(iYear);
                        }
                        if(firstPlace.size() == 1)
                        {
                            vView.resultConfirmationMessage(r.getFirstPlacePerson().getFirstName(), r.getFirstPlacePerson().getLastName(), iYear);
                        }
                        break;
                    }
                 }
            }
            if(!b)
            {
                vView.resultConfirmationMessage();
            }

        }
    }

    class FinalTimingButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String szFirstPlace = vView.getFirstPlaceFirstField()
                    + ":" + vView.getFirstPlaceSecondField()
                    + ":" + vView.getFirstPlaceThirdField();

            String szSecondPlace = vView.getSecondPlaceFirstField()
                    + ":" + vView.getSecondPlaceSecondField()
                    + ":" + vView.getSecondPlaceThirdField();

            String szThirdPlace = vView.getThirdPlaceFirstField()
                    + ":" + vView.getThirdPlaceSecondField()
                    + ":" + vView.getThirdPlaceThirdField();

            System.err.println(szFirstPlace);
            System.err.println(szSecondPlace);
            System.err.println(szThirdPlace);

            if(szFirstPlace.matches("([0-5][0-9]:)?[0-5][0-9]:[0-9]{2}$")
                    && szSecondPlace.matches("([0-5][0-9]:)?[0-5][0-9]:[0-9]{2}$")
                    && szThirdPlace.matches("([0-5][0-9]:)?[0-5][0-9]:[0-9]{2}$"))
            {
                mModel.setFirstPlace(vView.getFirstPlaceFirstField()
                        , vView.getFirstPlaceSecondField()
                        , vView.getFirstPlaceThirdField());
                mModel.setSecondPlace(vView.getSecondPlaceFirstField()
                        , vView.getSecondPlaceSecondField()
                        , vView.getSecondPlaceThirdField());
                mModel.setThirdPlace(vView.getThirdPlaceFirstField()
                        , vView.getThirdPlaceSecondField()
                        , vView.getThirdPlaceThirdField());

                vView.timingConfirmationMessage();
            }
            else
            {
                vView.errorMessage("Timings does not meet formatting requirements");
            }
        }
    }

    class FinalDistanceButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String szFirstPlace = vView.getFirstPlaceFirstField()
                    + "." + vView.getFirstPlaceSecondField();

            String szSecondPlace = vView.getSecondPlaceFirstField()
                    + "." + vView.getSecondPlaceSecondField();

            String szThirdPlace = vView.getThirdPlaceFirstField()
                    + "." + vView.getThirdPlaceSecondField();

            if(szFirstPlace.matches("[0-9]?[0-9]\\.[0-9]{2}")
                    && szSecondPlace.matches("[0-9]?[0-9]\\.[0-9]{2}")
                    && szThirdPlace.matches("[0-9]?[0-9]\\.[0-9]{2}"))
            {
                mModel.setFirstPlace(vView.getFirstPlaceFirstField()
                        , vView.getFirstPlaceSecondField());
                mModel.setSecondPlace(vView.getSecondPlaceFirstField()
                        , vView.getSecondPlaceSecondField());
                mModel.setThirdPlace(vView.getThirdPlaceFirstField()
                        , vView.getThirdPlaceSecondField());

                vView.distancesConfirmationMessage();
            }
            else
            {
                vView.errorMessage("Distances does not meet formatting requirements");
            }
        }
    }

    class ScoreboardButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                vView.outputScoreboard(mModel.getScoreboardPoints());
            }
            catch(IOException io)
            {

            }
        }
    }

    class ThisYearReportButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int iSelection;
                boolean bResultOutput = false;
                iSelection = vView.getThisYearReportConfirmation();

                if(iSelection == JOptionPane.YES_OPTION)
                {
                    //check if field is correct, if so then go through with report
                    if(vView.getThisYearEventField() != 0)
                    {
                        bResultOutput = mModel.outputResultsForYear(vView.getThisYearEventField());
                    }

                    vView.outputThisYearReportConfirmation(bResultOutput, vView.getThisYearEventField());
                }
            }
            catch(IOException io)
            {
                System.err.println("controller.java :: IO Exception: " + io);
            }
        }
    }

    class ClassOverYearsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                boolean bResultOutput;
                int iSelection, iYearGroup = 0;
                house h;
                iSelection = vView.getClassOverYearsConfirmation();

                if(iSelection == JOptionPane.OK_OPTION)
                {
                    h = house.fromInt(vView.getClassOverYearHouseSelection() + 1);
                    switch(vView.getClassYearListSelection())
                    {
                        case 0:
                            iYearGroup = 8;
                            break;
                        case 1:
                            iYearGroup = 9;
                            break;
                        case 2:
                            iYearGroup = 10;
                            break;
                        case 3:
                            iYearGroup = 12;
                            break;
                    }

                    bResultOutput = mModel.outputResultForClassOverYears(h, iYearGroup);
                    vView.outputClassOverYearsConfirmation(h, bResultOutput);
                }
            }
            catch(IOException io)
            {
                System.err.println("controller.java :: IO Exception: " + io);
            }
        }
    }

    class HouseOverYearsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                boolean bResultOutput;
                int iSelection, iYearGroup = 8;
                house h;

                iSelection = vView.getHouseOverYearsConfirmation();

                if(iSelection == JOptionPane.OK_OPTION)
                {
                    h = house.fromInt(vView.getHouseOverYearsYearListSelection() + 1);
                    //add index of selection
                    iYearGroup = iYearGroup + vView.getHouseOverYearsYearListSelection();

                    bResultOutput = mModel.outputResultsForHouseOverYears(h, iYearGroup);

                    vView.outputClassOverYearsConfirmation(h, bResultOutput);
                }
            }
            catch(IOException io)
            {
                System.err.println("controller.java :: IO Exception: " + io);
            }
        }
    }

    class EventOverYearsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                boolean bResultOutput;
                int iSelection;
                eventType et;

                iSelection = vView.getEventOverYearsConfirmation();
                if(iSelection == JOptionPane.OK_OPTION);
                {
                    et = eventType.fromInt(vView.getEventListSelection() + 1);

                    bResultOutput = mModel.outputResultsForEventOverYears(et);
                    vView.outputEventOverYearsConfirmation(et, bResultOutput);
                }
            }
            catch(IOException io)
            {
                System.err.println("controller.java :: IO Exception: " + io);
            }
        }
    }

    class RecordReportButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int iSelection;
                boolean bResultOutput;
                iSelection = vView.getRecordReportConfirmation();

                if(iSelection == JOptionPane.YES_OPTION)
                {
                    //as outputResultsForYear returns boolean
                    bResultOutput = mModel.outputRecordHolders();
                    vView.outputRecordConfirmation(bResultOutput);
                }
            }
            catch(IOException io)
            {
                System.err.println("controller.java :: IO Exception: " + io);
            }
        }
    }

    class PredictWholeSchoolListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int iSelection;
                int [] iResults;

                iSelection = vView.getWholeSchoolPredictionConfirmation();
                if(iSelection == JOptionPane.YES_OPTION)
                {
                    iResults = mModel.getLikelyResultsOfSportsDay();

                    vView.outputPredictions(iResults);
                }
            }
            catch(IOException io)
            {

            }
        }
    }

    class PredictYearGroupListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int iSelection;
                int [] iResults;
                int iYearGroup = 8;

                iSelection = vView.getYearGroupPredictionConfirmation();
                if(iSelection == JOptionPane.OK_OPTION)
                {
                    switch(vView.getClassYearListSelection())
                    {
                        case 0:
                            iYearGroup = 8;
                            break;
                        case 1:
                            iYearGroup = 9;
                            break;
                        case 2:
                            iYearGroup = 10;
                            break;
                        case 3:
                            iYearGroup = 12;
                            break;
                    }

                    iResults = mModel.getLikelyResultsOfYearGroup(iYearGroup);

                    vView.outputPredictions(iResults);
                }
            }
            catch(IOException io)
            {

            }
        }
    }

    class PredictEventListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int iSelection;
                int [] iResults;
                int iYearGroup = 8;
                boolean bParticipation = false;
                eventType et;

                iSelection = vView.getEventPredictionConfirmation();
                if(iSelection == JOptionPane.OK_OPTION)
                {
                    switch(vView.getClassYearListSelection())
                    {
                        case 0:
                            iYearGroup = 8;
                            break;
                        case 1:
                            iYearGroup = 9;
                            break;
                        case 2:
                            iYearGroup = 10;
                            break;
                        case 3:
                            iYearGroup = 12;
                            break;
                    }

                    switch(vView.getEliteListSelection())
                    {
                        case 0:
                            bParticipation = false;
                            break;
                        case 1:
                            bParticipation = true;
                            break;
                    }

                    et = eventType.fromInt(vView.getEventListSelection() + 1);

                    iResults = mModel.getLikelyResultsOfEvent(et, bParticipation, iYearGroup);

                    vView.outputPredictions(iResults);
                }
            }
            catch(IOException io)
            {

            }
        }
    }

    class SettingsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                boolean [] bSettings = mModel.getFleetSettings();
                vView.setYearSevenCheckBox(bSettings[0]);
                vView.setYearEightCheckBox(bSettings[1]);
                vView.setYearNineCheckBox(bSettings[2]);
                vView.setYearTenCheckBox(bSettings[3]);
                vView.setYearTwelveCheckBox(bSettings[4]);
            }
            catch(IOException io)
            {

            }
        }
    }

    class SettingsSubmitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                boolean [] bSettings = new boolean[5];
                bSettings[0] = vView.getYearSevenCheckBox();
                bSettings[1] = vView.getYearEightCheckBox();
                bSettings[2] = vView.getYearNineCheckBox();
                bSettings[3] = vView.getYearTenCheckBox();
                bSettings[4] = vView.getYearTwelveCheckBox();

                mModel.setFleetSettings(bSettings);
            }
            catch(IOException io)
            {

            }
        }
    }
}
