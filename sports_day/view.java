package sports_day;

/**
 * @author Alex Lockwood
 * @purpose The view of the MVC framework. Handles the GUI and allows for navigation
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class view
{
    //main screen components
    private JButton resultsButton = new JButton("Results");
    private JButton reportsButton = new JButton("Reports");
    private JButton predictionsButton = new JButton("Predictions");
    private JButton settingsButton = new JButton("Settings");
    private JButton helpButton = new JButton("Help");
    private JButton aboutButton = new JButton("About");

    //result menu components
    private JButton addResultsButton = new JButton("Add/Alter Result");
    private JButton scoreboardButton = new JButton("Scoreboard");

    //result screen components
    private String [] szEventTypes = {
            "Hundred Metres"
            , "Two Hundred Metres"
            , "Four Hundred Metres"
            , "Eight Hundred Metres"
            , "Fifteen Hundred Metres"
            , "Eight by Hundred Metres"
            , "Four by Two Hundred Metres"
            , "Long Jump"
            , "High Jump"
            , "Javelin"
            , "Shot Putt"
            , "Four Hundred Metre Relay"
    };

    private String [] szElite = {
            "Mass"
            , "Elite"
    };

    private String [] szYears = {
            "7"
            , "8"
            , "9"
            , "10"
            , "12"
    };

    private String [] szPositions = {
            "DNS"
            , "DNF"
            , "1st"
            , "2nd"
            , "3rd"
            , "4th"
            , "5th"
            , "6th"
    };

    private JLabel eventLabel = new JLabel("Event: ");
    private JLabel yearLabel = new JLabel("Year Group: ");
    private JLabel participationLabel = new JLabel("Participation: ");
    private JLabel firstNameLabel1 = new JLabel("First Name: ");
    private JLabel surnameLabel1 = new JLabel("Surname: ");
    private JLabel positionLabel1 = new JLabel("Position: ");
    private JLabel firstNameLabel2 = new JLabel("First Name: ");
    private JLabel surnameLabel2 = new JLabel("Surname: ");
    private JLabel positionLabel2 = new JLabel("Position: ");
    private JLabel firstNameLabel3 = new JLabel("First Name: ");
    private JLabel surnameLabel3 = new JLabel("Surname: ");
    private JLabel positionLabel3 = new JLabel("Position: ");
    private JLabel firstNameLabel4 = new JLabel("First Name: ");
    private JLabel surnameLabel4 = new JLabel("Surname: ");
    private JLabel positionLabel4 = new JLabel("Position: ");
    private JLabel firstNameLabel5 = new JLabel("First Name: ");
    private JLabel surnameLabel5 = new JLabel("Surname: ");
    private JLabel positionLabel5 = new JLabel("Position: ");
    private JLabel firstNameLabel6 = new JLabel("First Name: ");
    private JLabel surnameLabel6 = new JLabel("Surname: ");
    private JLabel positionLabel6 = new JLabel("Position: ");

    private JComboBox eventList = new JComboBox(szEventTypes);
    private JComboBox eliteList = new JComboBox(szElite);
    private JComboBox yearField = new JComboBox(szYears);
    private JLabel cliffLabel = new JLabel("Cliff");
    private JTextField cliffFirstNameField = new JTextField(15);
    private JTextField cliffSurnameField = new JTextField(15);
    private JComboBox cliffPosition = new JComboBox(szPositions);
    private JLabel downsLabel = new JLabel("Downs");
    private JTextField downsFirstNameField = new JTextField(15);
    private JTextField downsSurnameField = new JTextField(15);
    private JComboBox downsPosition = new JComboBox(szPositions);
    private JLabel fleetLabel = new JLabel("Fleet");
    private JTextField fleetFirstNameField = new JTextField(15);
    private JTextField fleetSurnameField = new JTextField(15);
    private JComboBox fleetPosition = new JComboBox(szPositions);
    private JLabel hillLabel = new JLabel("Hill");
    private JTextField hillFirstNameField = new JTextField(15);
    private JTextField hillSurnameField = new JTextField(15);
    private JComboBox hillPosition = new JComboBox(szPositions);
    private JLabel schoolLabel = new JLabel("School");
    private JTextField schoolFirstNameField = new JTextField(15);
    private JTextField schoolSurnameField = new JTextField(15);
    private JComboBox schoolPosition = new JComboBox(szPositions);
    private JLabel townLabel = new JLabel("Town");
    private JTextField townFirstNameField = new JTextField(15);
    private JTextField townSurnameField = new JTextField(15);
    private JComboBox townPosition = new JComboBox(szPositions);
    private JButton enterButton = new JButton("Submit");

    private JButton finalResultButton = new JButton("Add Timings...");
    private JButton teamButton1 = new JButton("Add Team...");
    private JButton teamButton2 = new JButton("Add Team...");
    private JButton teamButton3 = new JButton("Add Team...");
    private JButton teamButton4 = new JButton("Add Team...");
    private JButton teamButton5 = new JButton("Add Team...");
    private JButton teamButton6 = new JButton("Add Team...");

    //team screen components
    private JLabel personLabel1 = new JLabel("Person 1");
    private JLabel personLabel2 = new JLabel("Person 2");
    private JLabel personLabel3 = new JLabel("Person 3");
    private JLabel personLabel4 = new JLabel("Person 4");
    private JLabel personLabel5 = new JLabel("Person 5");
    private JLabel personLabel6 = new JLabel("Person 6");
    private JLabel personLabel7 = new JLabel("Person 7");
    private JLabel personLabel8 = new JLabel("Person 8");
    private JLabel firstNameLabel7 = new JLabel("First Name: ");
    private JLabel surnameLabel7 = new JLabel("Surname: ");
    private JLabel firstNameLabel8 = new JLabel("First Name: ");
    private JLabel surnameLabel8 = new JLabel("Surname: ");
    private JTextField person1FirstNameField = new JTextField(15);
    private JTextField person1SurnameField = new JTextField(15);
    private JTextField person2FirstNameField = new JTextField(15);
    private JTextField person2SurnameField = new JTextField(15);
    private JTextField person3FirstNameField = new JTextField(15);
    private JTextField person3SurnameField = new JTextField(15);
    private JTextField person4FirstNameField = new JTextField(15);
    private JTextField person4SurnameField = new JTextField(15);
    private JTextField person5FirstNameField = new JTextField(15);
    private JTextField person5SurnameField = new JTextField(15);
    private JTextField person6FirstNameField = new JTextField(15);
    private JTextField person6SurnameField = new JTextField(15);
    private JTextField person7FirstNameField = new JTextField(15);
    private JTextField person7SurnameField = new JTextField(15);
    private JTextField person8FirstNameField = new JTextField(15);
    private JTextField person8SurnameField = new JTextField(15);
    private JButton cliffEnterButton = new JButton("Submit");
    private JButton downsEnterButton = new JButton("Submit");
    private JButton fleetEnterButton = new JButton("Submit");
    private JButton hillEnterButton = new JButton("Submit");
    private JButton schoolEnterButton = new JButton("Submit");
    private JButton townEnterButton = new JButton("Submit");
    private boolean bParticipants = true;

    //timings and distances screen components
    private JLabel firstPlaceLabel = new JLabel("First Place: ");
    private JLabel secondPlaceLabel = new JLabel("Second Place: ");
    private JLabel thirdPlaceLabel = new JLabel("Third Place: ");
    private JTextField firstPlaceFirstField = new JTextField(5);
    private JTextField firstPlaceSecondField = new JTextField(5);
    private JTextField firstPlaceThirdField = new JTextField(5);
    private JLabel firstPlaceMinutes = new JLabel("M");
    private JLabel firstPlaceSeconds = new JLabel("s");
    private JLabel firstPlaceMilliseconds = new JLabel("ms");
    private JLabel firstPlaceMetres = new JLabel("m");
    private JLabel firstPlaceCentimetres = new JLabel("cm");
    private JTextField secondPlaceFirstField = new JTextField(5);
    private JTextField secondPlaceSecondField = new JTextField(5);
    private JTextField secondPlaceThirdField = new JTextField(5);
    private JLabel secondPlaceMinutes = new JLabel("M");
    private JLabel secondPlaceSeconds = new JLabel("s");
    private JLabel secondPlaceMilliseconds = new JLabel("ms");
    private JLabel secondPlaceMetres = new JLabel("m");
    private JLabel secondPlaceCentimetres = new JLabel("cm");
    private JTextField thirdPlaceFirstField = new JTextField(5);
    private JTextField thirdPlaceSecondField = new JTextField(5);
    private JTextField thirdPlaceThirdField = new JTextField(5);
    private JLabel thirdPlaceMinutes = new JLabel("M");
    private JLabel thirdPlaceSeconds = new JLabel("s");
    private JLabel thirdPlaceMilliseconds = new JLabel("ms");
    private JLabel thirdPlaceMetres = new JLabel("m");
    private JLabel thirdPlaceCentimetres = new JLabel("cm");
    private JButton finalTimingSubmitButton = new JButton("Submit");
    private JButton finalDistanceSubmitButton = new JButton("Submit");

    //report main screen components
    //thisYearReportButton set to current year
    private JButton thisYearReportButton = new JButton("All Events");
    private JButton classReportButton = new JButton("Class");
    private JButton houseReportButton = new JButton("House");
    private JButton eventReportButton = new JButton("Event");
    private JButton recordReportButton = new JButton("Records");

    //all events report
    private JTextField yearEventField = new JTextField(4);

    //class over years pre-confirmation components
    private String [] szClassOverYearsYearGroups = {
            "8"
            , "9"
            , "10"
            , "12"
    };

    private String [] szHouses = {
            "Cliff"
            , "Downs"
            , "Fleet"
            , "Hill"
            , "School"
            , "Town"
    };

    private JComboBox classYearsList = new JComboBox(szClassOverYearsYearGroups);
    private JComboBox housesList = new JComboBox(szHouses);

    //house over years pre-confirmation components
    private String [] szHouseOverYearsYearGroups = {
            "8"
            , "9"
            , "10"
            , "11"
            , "12"
            , "13"
    };

    private JComboBox houseOverYearsYearList = new JComboBox(szHouseOverYearsYearGroups);

    //predictions main screen components
    private JButton predictEventButton = new JButton("Event");
    private JButton predictYearGroupButton = new JButton("Year Group");
    private JButton predictWholeSchoolButton = new JButton("Year 8 to 12");

    //prediction output components
    private JLabel cliffPoints;
    private JLabel downsPoints;
    private JLabel fleetPoints;
    private JLabel hillPoints;
    private JLabel schoolPoints;
    private JLabel townPoints;
    
    //settings components
    private JLabel fleetPresent = new JLabel("Fleet Presence");
    private JCheckBox yearSevenCheckBox = new JCheckBox("Year Seven");
    private JCheckBox yearEightCheckBox = new JCheckBox("Year Eight");
    private JCheckBox yearNineCheckBox = new JCheckBox("Year Nine");
    private JCheckBox yearTenCheckBox = new JCheckBox("Year Ten");
    private JCheckBox yearTwelveCheckBox = new JCheckBox("Year Twelve");
    private JButton settingSubmitButton = new JButton("Submit");

    //about components
    private JLabel sportsDayTitle = new JLabel("Sports Day Data Handler");
    private JLabel versionNumber = new JLabel("Version 1.0.0");
    private JLabel createdBy = new JLabel("Created by Alex Lockwood");
    private JLabel createdFor = new JLabel("Made for Gravesend Grammar School PE Department");

    //help components
    private JLabel helpLabel = new JLabel("Help");
    private JLabel resultsHelpLabel = new JLabel("Results");
    private JLabel addResultHelpLabel = new JLabel("<html>" +
            "Add/Alter Results: Used to add results from the current year" +
                                                "<br>Existing results will be edited if entered again" +
                                                "<br>Timings are in format XX:XX:XX" +
                                                "<br>Distances are in format XX:XX</html>");
    private JLabel scoreboardHelpLabel = new JLabel("<html>Scoreboard: " +
            "Outputs the current points of the events from this year" +
                                                "<br>Fleet scores are weighted. " +
            "New Score = Original Score * (Number of Events/Events with Fleet)</html>");
    private JLabel reportsHelpLabel = new JLabel("Reports");
    private JLabel reportsExplanationLabel = new JLabel("<html>Outputs the results to a .csv file." +
                                                        "<br>Whole School will only output if all events has occurred.</html>");
    private JLabel predictionsHelpLabel = new JLabel("Predictions");
    private JLabel predictionsExplanationLabel = new JLabel("Predicts the results of the the specified output " +
            "with respect to previous events");
    private JLabel settingsHelpLabel = new JLabel("Settings");
    private JLabel settingsExplanationLabel = new JLabel("Used to set if Fleet is present in specific years");

    //constructor
    //doesn't need to instantiate anything on creation therefore blank
    public view()
    {

    }

    /**
     * =====================================================================
     * MAIN SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: The main screen that the user is greeted with upon loading
     * ======================================================================
     */

    public void mainScreen()
    {
        JFrame jF = new JFrame();

        resultsButton.setFont(new Font(resultsButton.getFont().getFontName(), Font.BOLD, 30));
        reportsButton.setFont(new Font(reportsButton.getFont().getFontName(), Font.BOLD, 30));
        predictionsButton.setFont(new Font(predictionsButton.getFont().getFontName(), Font.BOLD, 30));
        settingsButton.setFont(new Font(settingsButton.getFont().getFontName(), Font.BOLD, 30));
        helpButton.setFont(new Font(helpButton.getFont().getFontName(), Font.BOLD, 30));
        aboutButton.setFont(new Font(aboutButton.getFont().getFontName(), Font.BOLD, 30));

        JPanel mainScreenPanel = new JPanel();
        mainScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jF.setSize(440, 810);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Home Page");

        resultsButton.setPreferredSize(new Dimension(400, 140));
        reportsButton.setPreferredSize(new Dimension(400, 140));
        predictionsButton.setPreferredSize(new Dimension(400, 140));
        settingsButton.setPreferredSize(new Dimension(400, 140));
        helpButton.setPreferredSize(new Dimension(400, 80));
        aboutButton.setPreferredSize(new Dimension(400, 80));

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        mainScreenPanel.add(resultsButton, c);
        c.gridy++;
        mainScreenPanel.add(reportsButton, c);
        c.gridy++;
        mainScreenPanel.add(predictionsButton, c);
        c.gridy++;
        mainScreenPanel.add(settingsButton, c);
        c.gridy++;
        mainScreenPanel.add(helpButton, c);
        c.gridy++;
        mainScreenPanel.add(aboutButton, c);

        resultsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resultsMenuScreen();
            }
        });

        reportsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reportsScreen();
            }
        });

        predictionsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                predictionsScreen();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                settingsScreen();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutScreen();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpScreen();
            }
        });

        jF.add(mainScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * ERROR MESSAGE POP-UP
     * Inputs: error (String)
     * Outputs: N/A
     * Comments: Creates a pop-up if an error occurs
     * ======================================================================
     */
    public void errorMessage(String error)
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "Error: " + error,
                "Sports Day - Error",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.OK_OPTION,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RESULTS MENU SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Opens up the menu to select if a user wants to add/alter results
     *           or if they want to view the scoreboard for the year
     * ======================================================================
     */
    public void resultsMenuScreen()
    {
        JFrame jF = new JFrame();

        addResultsButton.setFont(new Font(addResultsButton.getFont().getFontName(), Font.BOLD, 30));
        scoreboardButton.setFont(new Font(scoreboardButton.getFont().getFontName(), Font.BOLD, 30));

        JPanel resultsMenuPanel = new JPanel();
        resultsMenuPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 360);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Results Menu");

        addResultsButton.setPreferredSize(new Dimension(400, 140));
        scoreboardButton.setPreferredSize(new Dimension(400, 140));

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        resultsMenuPanel.add(addResultsButton, c);
        c.gridy++;
        resultsMenuPanel.add(scoreboardButton, c);

        addResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultsScreen();
            }
        });

        jF.add(resultsMenuPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * REPORTS SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Gets the user's choice of what report type they would like to output
     * ======================================================================
     */
    public void reportsScreen()
    {
        JFrame jF = new JFrame();

        thisYearReportButton.setFont(new Font(thisYearReportButton.getFont().getFontName(), Font.BOLD, 30));
        classReportButton.setFont(new Font(classReportButton.getFont().getFontName(), Font.BOLD, 30));
        houseReportButton.setFont(new Font(houseReportButton.getFont().getFontName(), Font.BOLD, 30));
        eventReportButton.setFont(new Font(eventReportButton.getFont().getFontName(), Font.BOLD, 30));
        recordReportButton.setFont(new Font(recordReportButton.getFont().getFontName(), Font.BOLD, 30));

        classReportButton.setToolTipText("Outputs the positions of a class over the years");
        houseReportButton.setToolTipText("Outputs the points for a house over the years");
        eventReportButton.setToolTipText("Outputs the results for an event over the years");
        recordReportButton.setToolTipText("Outputs the current record holders");

        JPanel reportsScreenPanel = new JPanel();
        reportsScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 810);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Reports");

        thisYearReportButton.setPreferredSize(new Dimension(400, 140));
        classReportButton.setPreferredSize(new Dimension(400, 140));
        houseReportButton.setPreferredSize(new Dimension(400, 140));
        eventReportButton.setPreferredSize(new Dimension(400, 140));
        recordReportButton.setPreferredSize(new Dimension(400, 140));

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        reportsScreenPanel.add(thisYearReportButton, c);
        c.gridy++;
        reportsScreenPanel.add(classReportButton, c);
        c.gridy++;
        reportsScreenPanel.add(houseReportButton, c);
        c.gridy++;
        reportsScreenPanel.add(eventReportButton, c);
        c.gridy++;
        reportsScreenPanel.add(recordReportButton, c);

        jF.add(reportsScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * PREDICTIONS SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Gets the user's choice of prediction that they wish to make
     * ======================================================================
     */
    public void predictionsScreen()
    {
        JFrame jF = new JFrame();

        predictWholeSchoolButton.setFont(new Font(predictWholeSchoolButton.getFont().getFontName(), Font.BOLD, 30));
        predictYearGroupButton.setFont(new Font(predictYearGroupButton.getFont().getFontName(), Font.BOLD, 30));
        predictEventButton.setFont(new Font(predictEventButton.getFont().getFontName(), Font.BOLD, 30));

        JPanel predictionsScreenPanel = new JPanel();
        predictionsScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 490);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Predictions");

        //ask user if they want to predict an event, year group or whole school
        //give disclaimer that the whole school one should not be used as an accurate portrayal
        //does not give prediction for y7 due to them being fresh blood

        predictWholeSchoolButton.setPreferredSize(new Dimension(400, 140));
        predictYearGroupButton.setPreferredSize(new Dimension(400, 140));
        predictEventButton.setPreferredSize(new Dimension(400, 140));

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        predictionsScreenPanel.add(predictWholeSchoolButton, c);
        c.gridy++;
        predictionsScreenPanel.add(predictYearGroupButton, c);
        c.gridy++;
        predictionsScreenPanel.add(predictEventButton, c);

        jF.add(predictionsScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * SETTINGS SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Gets the user's settings
     * ======================================================================
     */

    public void settingsScreen()
    {
        JFrame jF = new JFrame();
        JPanel settingsScreenPanel = new JPanel();
        settingsScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(200, 400);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Settings");

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        settingsScreenPanel.add(fleetPresent, c);
        c.gridy++;
        settingsScreenPanel.add(yearSevenCheckBox, c);
        c.gridy++;
        settingsScreenPanel.add(yearEightCheckBox, c);
        c.gridy++;
        settingsScreenPanel.add(yearNineCheckBox, c);
        c.gridy++;
        settingsScreenPanel.add(yearTenCheckBox, c);
        c.gridy++;
        settingsScreenPanel.add(yearTwelveCheckBox, c);
        c.gridy++;
        settingsScreenPanel.add(settingSubmitButton, c);

        jF.add(settingsScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * ABOUT SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Gives details about the program
     * ======================================================================
     */

    public void aboutScreen()
    {
        JFrame jF = new JFrame();
        JPanel aboutScreenPanel = new JPanel();
        aboutScreenPanel.setLayout(new GridBagLayout());

        sportsDayTitle.setFont(new Font(sportsDayTitle.getFont().getFontName(), Font.BOLD, 30));


        GridBagConstraints c = new GridBagConstraints();
        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 200);
        jF.setResizable(false);
        jF.setTitle("Sports Day - About");

        c.weightx = 0.5;
        c.weighty = 0.2;

        c.gridx = 0;
        c.gridy = 0;

        aboutScreenPanel.add(sportsDayTitle, c);
        c.gridy++;
        aboutScreenPanel.add(versionNumber, c);
        c.gridy++;
        aboutScreenPanel.add(createdBy, c);
        c.gridy++;
        aboutScreenPanel.add(createdFor, c);

        jF.add(aboutScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * HELP SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Outlines some of the theory behind the program
     * ======================================================================
     */

    public void helpScreen()
    {
        JFrame jF = new JFrame();
        JPanel helpScreenPanel = new JPanel();
        helpScreenPanel.setLayout(new GridBagLayout());

        helpLabel.setFont(new Font(helpLabel.getFont().getFontName(), Font.BOLD, 30));
        resultsHelpLabel.setFont(new Font(resultsHelpLabel.getText(), Font.ITALIC, 18));
        reportsHelpLabel.setFont(new Font(reportsHelpLabel.getText(), Font.ITALIC, 18));
        predictionsHelpLabel.setFont(new Font(predictionsHelpLabel.getText(), Font.ITALIC, 18));
        settingsHelpLabel.setFont(new Font(settingsHelpLabel.getText(), Font.ITALIC, 18));

        GridBagConstraints c = new GridBagConstraints();
        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(560, 500);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Help");

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;

        helpScreenPanel.add(helpLabel, c);
        c.gridy++;
        helpScreenPanel.add(resultsHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(addResultHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(scoreboardHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(reportsHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(reportsExplanationLabel, c);
        c.gridy++;
        helpScreenPanel.add(predictionsHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(predictionsExplanationLabel, c);
        c.gridy++;
        helpScreenPanel.add(settingsHelpLabel, c);
        c.gridy++;
        helpScreenPanel.add(settingsExplanationLabel, c);

        jF.add(helpScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * RESULT SCREEN
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Used to add or update events that have occurred this year
     * ======================================================================
     */
    public void resultsScreen()
    {
        JFrame jF = new JFrame();

        cliffLabel.setFont(new Font(cliffLabel.getText(), Font.ITALIC, 18));
        downsLabel.setFont(new Font(downsLabel.getText(), Font.ITALIC, 18));
        fleetLabel.setFont(new Font(fleetLabel.getText(), Font.ITALIC, 18));
        schoolLabel.setFont(new Font(schoolLabel.getText(), Font.ITALIC, 18));
        townLabel.setFont(new Font(townLabel.getText(), Font.ITALIC, 18));
        hillLabel.setFont(new Font(hillLabel.getText(), Font.ITALIC, 18));

        JPanel resultsScreenPanel = new JPanel();
        resultsScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(450, 750);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Results");

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        resultsScreenPanel.add(eventLabel, c);
        c.gridy++;
        resultsScreenPanel.add(participationLabel, c);
        c.gridy++;
        resultsScreenPanel.add(yearLabel, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel1, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel2, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel3, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel4, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel4, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel5, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel5, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel5, c);

        c.gridy += 2;
        resultsScreenPanel.add(firstNameLabel6, c);
        c.gridy++;
        resultsScreenPanel.add(surnameLabel6, c);
        c.gridy++;
        resultsScreenPanel.add(positionLabel6, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        resultsScreenPanel.add(eventList, c);
        c.gridy++;
        resultsScreenPanel.add(eliteList, c);
        c.gridy++;
        resultsScreenPanel.add(yearField, c);

        c.gridy++;
        resultsScreenPanel.add(cliffLabel, c);
        c.gridy++;
        resultsScreenPanel.add(cliffFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(cliffSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(cliffPosition, c);

        c.gridy++;
        resultsScreenPanel.add(downsLabel, c);
        c.gridy++;
        resultsScreenPanel.add(downsFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(downsSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(downsPosition, c);

        c.gridy++;
        resultsScreenPanel.add(fleetLabel, c);
        c.gridy++;
        resultsScreenPanel.add(fleetFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(fleetSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(fleetPosition, c);

        c.gridy++;
        resultsScreenPanel.add(hillLabel, c);
        c.gridy++;
        resultsScreenPanel.add(hillFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(hillSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(hillPosition, c);

        c.gridy++;
        resultsScreenPanel.add(schoolLabel, c);
        c.gridy++;
        resultsScreenPanel.add(schoolFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(schoolSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(schoolPosition, c);

        c.gridy++;
        resultsScreenPanel.add(townLabel, c);
        c.gridy++;
        resultsScreenPanel.add(townFirstNameField, c);
        c.gridy++;
        resultsScreenPanel.add(townSurnameField, c);
        c.gridy++;
        resultsScreenPanel.add(townPosition, c);
        c.gridy++;
        resultsScreenPanel.add(enterButton, c);

        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;

        c.gridy = 2;
        resultsScreenPanel.add(finalResultButton, c);

        c.gridy += 2;;
        resultsScreenPanel.add(teamButton1, c);

        c.gridy += 4;
        resultsScreenPanel.add(teamButton2, c);

        c.gridy += 4;
        resultsScreenPanel.add(teamButton3, c);

        c.gridy += 4;
        resultsScreenPanel.add(teamButton4, c);

        c.gridy += 4;
        resultsScreenPanel.add(teamButton5, c);

        c.gridy += 4;
        resultsScreenPanel.add(teamButton6, c);

        teamButton1.setEnabled(false);
        teamButton2.setEnabled(false);
        teamButton3.setEnabled(false);
        teamButton4.setEnabled(false);
        teamButton5.setEnabled(false);
        teamButton6.setEnabled(false);

        //listener for eventList combo box
        //changes what fields are editable and what buttons are clickable
        eventList.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    {
                        finalResultButton.setText("Add Timings...");
                        cliffFirstNameField.setEditable(true);
                        cliffSurnameField.setEditable(true);
                        downsFirstNameField.setEditable(true);
                        downsSurnameField.setEditable(true);
                        fleetFirstNameField.setEditable(true);
                        fleetSurnameField.setEditable(true);
                        hillFirstNameField.setEditable(true);
                        hillSurnameField.setEditable(true);
                        schoolFirstNameField.setEditable(true);
                        schoolSurnameField.setEditable(true);
                        townFirstNameField.setEditable(true);
                        townSurnameField.setEditable(true);

                        teamButton1.setEnabled(false);
                        teamButton2.setEnabled(false);
                        teamButton3.setEnabled(false);
                        teamButton4.setEnabled(false);
                        teamButton5.setEnabled(false);
                        teamButton6.setEnabled(false);
                        break;
                    }
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    {
                        finalResultButton.setText("Add Distances...");
                        cliffFirstNameField.setEditable(true);
                        cliffSurnameField.setEditable(true);
                        downsFirstNameField.setEditable(true);
                        downsSurnameField.setEditable(true);
                        fleetFirstNameField.setEditable(true);
                        fleetSurnameField.setEditable(true);
                        hillFirstNameField.setEditable(true);
                        hillSurnameField.setEditable(true);
                        schoolFirstNameField.setEditable(true);
                        schoolSurnameField.setEditable(true);
                        townFirstNameField.setEditable(true);
                        townSurnameField.setEditable(true);

                        teamButton1.setEnabled(false);
                        teamButton2.setEnabled(false);
                        teamButton3.setEnabled(false);
                        teamButton4.setEnabled(false);
                        teamButton5.setEnabled(false);
                        teamButton6.setEnabled(false);
                        break;
                    }
                    case 5:
                    case 6:
                    {
                        finalResultButton.setText("Add Timings...");
                        cliffFirstNameField.setEditable(false);
                        cliffSurnameField.setEditable(false);
                        downsFirstNameField.setEditable(false);
                        downsSurnameField.setEditable(false);
                        fleetFirstNameField.setEditable(false);
                        fleetSurnameField.setEditable(false);
                        hillFirstNameField.setEditable(false);
                        hillSurnameField.setEditable(false);
                        schoolFirstNameField.setEditable(false);
                        schoolSurnameField.setEditable(false);
                        townFirstNameField.setEditable(false);
                        townSurnameField.setEditable(false);

                        teamButton1.setEnabled(true);
                        teamButton2.setEnabled(true);
                        teamButton3.setEnabled(true);
                        teamButton4.setEnabled(true);
                        teamButton5.setEnabled(true);
                        teamButton6.setEnabled(true);
                        break;
                    }
                    case 11:
                    {
                        finalResultButton.setText("Add Timings...");
                        cliffFirstNameField.setEditable(false);
                        cliffSurnameField.setEditable(false);
                        downsFirstNameField.setEditable(false);
                        downsSurnameField.setEditable(false);
                        fleetFirstNameField.setEditable(false);
                        fleetSurnameField.setEditable(false);
                        hillFirstNameField.setEditable(false);
                        hillSurnameField.setEditable(false);
                        schoolFirstNameField.setEditable(false);
                        schoolSurnameField.setEditable(false);
                        townFirstNameField.setEditable(false);
                        townSurnameField.setEditable(false);

                        teamButton1.setEnabled(false);
                        teamButton2.setEnabled(false);
                        teamButton3.setEnabled(false);
                        teamButton4.setEnabled(false);
                        teamButton5.setEnabled(false);
                        teamButton6.setEnabled(false);
                        break;
                    }
                    default:
                    {
                        cliffFirstNameField.setEditable(false);
                        cliffSurnameField.setEditable(false);
                        downsFirstNameField.setEditable(false);
                        downsSurnameField.setEditable(false);
                        fleetFirstNameField.setEditable(false);
                        fleetSurnameField.setEditable(false);
                        hillFirstNameField.setEditable(false);
                        hillSurnameField.setEditable(false);
                        schoolFirstNameField.setEditable(false);
                        schoolSurnameField.setEditable(false);
                        townFirstNameField.setEditable(false);
                        townSurnameField.setEditable(false);

                        teamButton1.setEnabled(false);
                        teamButton2.setEnabled(false);
                        teamButton3.setEnabled(false);
                        teamButton4.setEnabled(false);
                        teamButton5.setEnabled(false);
                        teamButton6.setEnabled(false);
                        break;
                    }
                }
            }
        });

        //listener for Cliff 'Add Team...' button
        teamButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        cliffTeamScreen(true);
                        break;
                    case 6:
                        cliffTeamScreen(false);
                        break;
                }
            }
        });

        //listener for Downs 'Add Team...' button
        teamButton2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        downsTeamScreen(true);
                        break;
                    case 6:
                        downsTeamScreen(false);
                        break;
                }
            }
        });

        //listener for Fleet 'Add Team...' button
        teamButton3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        fleetTeamScreen(true);
                        break;
                    case 6:
                        fleetTeamScreen(false);
                        break;
                }
            }
        });

        //listener for Hill 'Add Team...' button
        teamButton4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        hillTeamScreen(true);
                        break;
                    case 6:
                        hillTeamScreen(false);
                        break;
                }
            }
        });

        //listener for School 'Add Team...' button
        teamButton5.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        schoolTeamScreen(true);
                        break;
                    case 6:
                        schoolTeamScreen(false);
                        break;
                }
            }
        });

        //listener for Town 'Add Team...' button
        teamButton6.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 5:
                        townTeamScreen(true);
                        break;
                    case 6:
                        townTeamScreen(false);
                        break;
                }
            }
        });

        //listener for 'Add Results...' button
        //changes what screen is opened based upon event selected
        finalResultButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(eventList.getSelectedIndex())
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 11:
                        timingsScreen();
                        break;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        distancesScreen();
                        break;
                    default:
                        errorMessage("Cannot open Results Screen");
                }
            }
        });

        jF.add(resultsScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * TIMINGS CONFIRMATION
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Creates a pop-up if the timings have been added
     * ======================================================================
     */

    public void timingConfirmationMessage()
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The timings have been added.",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * DISTANCES CONFIRMATION
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Creates a pop-up if the distances have been added
     * ======================================================================
     */

    public void distancesConfirmationMessage()
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The distances have been added.",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RESULT CONFIRMATION POP-UP
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Used to indicate that the event has been added, with no new record holders
     * ======================================================================
     */
    public void resultConfirmationMessage()
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The result has been added.",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RESULT CONFIRMATION POP-UP
     * Inputs: iYear (int)
     * Outputs: N/A
     * Comments: Used to indicate that the event has been added, with a record set by multiple houses
     * ======================================================================
     */
    public void resultConfirmationMessage(int iYear)
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The result has been added."+"\n Multiple houses have set a new Year " + iYear + " record!",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RESULT CONFIRMATION POP-UP
     * Inputs: szFirst (String), szSurname (String), iYear (int)
     * Outputs: N/A
     * Comments: Used to indicate that the event has been added, with a record set by an individual
     * ======================================================================
     */
    public void resultConfirmationMessage(String szFirst, String szSurname, int iYear)
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The result has been added."
                        +"\n" + szFirst + " " + szSurname + " has set a new Year " + iYear + " record!",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RESULT CONFIRMATION POP-UP
     * Inputs: szHouse (String), iYear (int)
     * Outputs: N/A
     * Comments: Used to indicate that the event has been added, with a record set by a team
     * ======================================================================
     */

    public void resultConfirmationMessage(String szHouse, int iYear)
    {
        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The result has been added." + "\n" + szHouse + " has set a new Year " + iYear + " record!",
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    //record screen getters
    public int getEventListSelection()
    {
        return eventList.getSelectedIndex();
    }

    public int getEliteListSelection()
    {
        return eliteList.getSelectedIndex();
    }

    public int getYearListSelection()
    {
        return yearField.getSelectedIndex();
    }

    public String getCliffFirstName()
    {
        if(cliffFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return cliffFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getCliffSurname()
    {
        if(cliffSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return cliffSurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getDownsFirstName()
    {
        if(downsFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return downsFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getDownsSurname()
    {
        if(downsSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return downsSurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getFleetFirstName()
    {
        if(fleetFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return fleetFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getFleetSurname()
    {
        if(fleetSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return fleetSurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getHillFirstName()
    {
        if(hillFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return hillFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getHillSurname()
    {
        if(hillSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return hillSurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getSchoolFirstName()
    {
        if(schoolFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return schoolFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getSchoolSurname() 
    {
        if(schoolSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return schoolSurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getTownFirstName()
    {
        if(townFirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return townFirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getTownSurname()
    {
        if(townSurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return townSurnameField.getText();
        }
        else
        {
            return null;
        }
    }
    
    public int getCliffPosition()
    {
        return cliffPosition.getSelectedIndex();
    }

    public int getDownsPosition()
    {
        return downsPosition.getSelectedIndex();
    }

    public int getFleetPosition()
    {
        return fleetPosition.getSelectedIndex();
    }

    public int getHillPosition()
    {
        return hillPosition.getSelectedIndex();
    }

    public int getSchoolPosition()
    {
        return schoolPosition.getSelectedIndex();
    }

    public int getTownPosition()
    {
        return townPosition.getSelectedIndex();
    }

    /**
     * =====================================================================
     * CLIFF TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments: Used to input Cliff team members. Changes view dependent if bEight is true or false
     * ======================================================================
     */
    public void cliffTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel cliffTeamScreenPanel = new JPanel();
        cliffTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - Cliff Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        cliffTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        cliffTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        cliffTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        cliffTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        cliffTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        cliffTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        cliffTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        cliffTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        cliffTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        cliffTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        cliffTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        cliffTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        cliffTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        cliffTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;;
        cliffTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        cliffTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;;
        cliffTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        cliffTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;;
        cliffTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        cliffTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            cliffTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            cliffTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            cliffTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            cliffTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            cliffTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            cliffTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            cliffTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            cliffTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            cliffTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            cliffTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            cliffTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            cliffTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            cliffTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            cliffTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;;
            cliffTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            cliffTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;;
            cliffTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            cliffTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;;
            cliffTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            cliffTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        cliffTeamScreenPanel.add(cliffEnterButton, c);

        jF.add(cliffTeamScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * DOWNS TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments: Used to input Downs team members. Changes view dependent if bEight is true or false
     * ======================================================================
     */
    public void downsTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel downsTeamScreenPanel = new JPanel();
        downsTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - Downs Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        downsTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        downsTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        downsTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        downsTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        downsTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        downsTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        downsTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        downsTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        downsTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        downsTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        downsTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        downsTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        downsTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        downsTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;;
        downsTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        downsTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;;
        downsTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        downsTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;;
        downsTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        downsTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            downsTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            downsTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            downsTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            downsTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            downsTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            downsTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            downsTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            downsTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            downsTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            downsTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            downsTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            downsTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            downsTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            downsTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;;
            downsTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            downsTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;;
            downsTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            downsTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;;
            downsTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            downsTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        downsTeamScreenPanel.add(downsEnterButton, c);

        jF.add(downsTeamScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * FLEET TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments: Used to input Fleet team members. Changes view dependent if bEight is true or false
     * ======================================================================
     */
    public void fleetTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel fleetTeamScreenPanel = new JPanel();
        fleetTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - Fleet Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        fleetTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        fleetTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        fleetTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        fleetTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        fleetTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        fleetTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        fleetTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        fleetTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        fleetTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        fleetTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        fleetTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        fleetTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        fleetTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        fleetTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;;
        fleetTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        fleetTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;;
        fleetTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        fleetTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;;
        fleetTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        fleetTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            fleetTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            fleetTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            fleetTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            fleetTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            fleetTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            fleetTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            fleetTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            fleetTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            fleetTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            fleetTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            fleetTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            fleetTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            fleetTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            fleetTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;
            fleetTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            fleetTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;
            fleetTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            fleetTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;
            fleetTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            fleetTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        fleetTeamScreenPanel.add(fleetEnterButton, c);

        jF.add(fleetTeamScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * HILL TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments: Used to input Hill team members. Changes view dependent if bEight is true or false
     * ======================================================================
     */
    public void hillTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel hillTeamScreenPanel = new JPanel();
        hillTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - Hill Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        hillTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        hillTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        hillTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        hillTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        hillTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        hillTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        hillTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        hillTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        hillTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        hillTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        hillTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        hillTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        hillTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        hillTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;
        hillTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        hillTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;
        hillTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        hillTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;
        hillTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        hillTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            hillTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            hillTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            hillTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            hillTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            hillTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            hillTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            hillTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            hillTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            hillTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            hillTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            hillTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            hillTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            hillTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            hillTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;
            hillTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            hillTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;
            hillTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            hillTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;
            hillTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            hillTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        hillTeamScreenPanel.add(hillEnterButton, c);

        jF.add(hillTeamScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * SCHOOL TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments:
     * ======================================================================
     */
    public void schoolTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel schoolTeamScreenPanel = new JPanel();
        schoolTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - School Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        schoolTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        schoolTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        schoolTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        schoolTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        schoolTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        schoolTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        schoolTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        schoolTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        schoolTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        schoolTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        schoolTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        schoolTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        schoolTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        schoolTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;
        schoolTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        schoolTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;
        schoolTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        schoolTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;
        schoolTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        schoolTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            schoolTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            schoolTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            schoolTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            schoolTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            schoolTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            schoolTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            schoolTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            schoolTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            schoolTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            schoolTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            schoolTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            schoolTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            schoolTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            schoolTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;
            schoolTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            schoolTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;
            schoolTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            schoolTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;
            schoolTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            schoolTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        schoolTeamScreenPanel.add(schoolEnterButton, c);

        jF.add(schoolTeamScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * TOWN TEAM SCREEN
     * Inputs: bEight (boolean)
     * Outputs: First name and surname of team members number 1 to 4, or 1 to 8
     * Comments: Used to input Town team members. Changes view dependent if bEight is true or false
     * ======================================================================
     */
    public void townTeamScreen(boolean bEight)
    {
        JFrame jF = new JFrame();
        JPanel townTeamScreenPanel = new JPanel();
        townTeamScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setTitle("Sports Day - Town Team");
        jF.setSize(251, 361);
        jF.setResizable(false);

        this.setPartipantFlag(false);

        personLabel1.setFont(personLabel1.getFont().deriveFont(personLabel1.getFont().getStyle() ^ Font.ITALIC));
        personLabel2.setFont(personLabel2.getFont().deriveFont(personLabel2.getFont().getStyle() ^ Font.ITALIC));
        personLabel3.setFont(personLabel3.getFont().deriveFont(personLabel3.getFont().getStyle() ^ Font.ITALIC));
        personLabel4.setFont(personLabel4.getFont().deriveFont(personLabel4.getFont().getStyle() ^ Font.ITALIC));
        personLabel5.setFont(personLabel5.getFont().deriveFont(personLabel5.getFont().getStyle() ^ Font.ITALIC));
        personLabel6.setFont(personLabel6.getFont().deriveFont(personLabel6.getFont().getStyle() ^ Font.ITALIC));
        personLabel7.setFont(personLabel7.getFont().deriveFont(personLabel7.getFont().getStyle() ^ Font.ITALIC));
        personLabel8.setFont(personLabel8.getFont().deriveFont(personLabel8.getFont().getStyle() ^ Font.ITALIC));

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        townTeamScreenPanel.add(personLabel1, c);
        c.gridy++;
        townTeamScreenPanel.add(firstNameLabel1, c);
        c.gridy++;
        townTeamScreenPanel.add(surnameLabel1, c);
        c.gridy++;
        townTeamScreenPanel.add(personLabel2, c);
        c.gridy++;
        townTeamScreenPanel.add(firstNameLabel2, c);
        c.gridy++;
        townTeamScreenPanel.add(surnameLabel2, c);
        c.gridy++;
        townTeamScreenPanel.add(personLabel3, c);
        c.gridy++;
        townTeamScreenPanel.add(firstNameLabel3, c);
        c.gridy++;
        townTeamScreenPanel.add(surnameLabel3, c);
        c.gridy++;
        townTeamScreenPanel.add(personLabel4, c);
        c.gridy++;
        townTeamScreenPanel.add(firstNameLabel4, c);
        c.gridy++;
        townTeamScreenPanel.add(surnameLabel4, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        townTeamScreenPanel.add(person1FirstNameField, c);
        c.gridy++;
        townTeamScreenPanel.add(person1SurnameField, c);
        c.gridy += 2;
        townTeamScreenPanel.add(person2FirstNameField, c);
        c.gridy++;
        townTeamScreenPanel.add(person2SurnameField, c);
        c.gridy += 2;
        townTeamScreenPanel.add(person3FirstNameField, c);
        c.gridy++;
        townTeamScreenPanel.add(person3SurnameField, c);
        c.gridy += 2;
        townTeamScreenPanel.add(person4FirstNameField, c);
        c.gridy++;
        townTeamScreenPanel.add(person4SurnameField, c);
        c.gridy += 2;

        if(bEight)
        {
            this.setPartipantFlag(true);

            jF.setSize(251, 679);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;

            c.gridy++;
            townTeamScreenPanel.add(personLabel5, c);
            c.gridy++;
            townTeamScreenPanel.add(firstNameLabel5, c);
            c.gridy++;
            townTeamScreenPanel.add(surnameLabel5, c);
            c.gridy++;
            townTeamScreenPanel.add(personLabel6, c);
            c.gridy++;
            townTeamScreenPanel.add(firstNameLabel6, c);
            c.gridy++;
            townTeamScreenPanel.add(surnameLabel6, c);
            c.gridy++;
            townTeamScreenPanel.add(personLabel7, c);
            c.gridy++;
            townTeamScreenPanel.add(firstNameLabel7, c);
            c.gridy++;
            townTeamScreenPanel.add(surnameLabel7, c);
            c.gridy++;
            townTeamScreenPanel.add(personLabel8, c);
            c.gridy++;
            townTeamScreenPanel.add(firstNameLabel8, c);
            c.gridy++;
            townTeamScreenPanel.add(surnameLabel8, c);

            c.gridx = 1;
            c.gridy = 15;
            c.anchor = GridBagConstraints.LINE_START;
            townTeamScreenPanel.add(person5FirstNameField, c);
            c.gridy++;
            townTeamScreenPanel.add(person5SurnameField, c);
            c.gridy += 2;
            townTeamScreenPanel.add(person6FirstNameField, c);
            c.gridy++;
            townTeamScreenPanel.add(person6SurnameField, c);
            c.gridy += 2;
            townTeamScreenPanel.add(person7FirstNameField, c);
            c.gridy++;
            townTeamScreenPanel.add(person7SurnameField, c);
            c.gridy += 2;
            townTeamScreenPanel.add(person8FirstNameField, c);
            c.gridy++;
            townTeamScreenPanel.add(person8SurnameField, c);
        }

        c.gridy++;
        townTeamScreenPanel.add(townEnterButton, c);

        jF.add(townTeamScreenPanel);
        jF.setVisible(true);
    }

    //team screen getters
    public void setPartipantFlag(boolean b)
    {
        this.bParticipants = b;
    }

    public boolean getParticipantFlag()
    {
        return bParticipants;
    }

    public String getPerson1FirstNameField()
    {
        if(person1FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person1FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson1SurnameField()
    {
        if(person1SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person1SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson2FirstNameField()
    {
        if(person2FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person2FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson2SurnameField()
    {
        if(person2SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person2SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson3FirstNameField()
    {
        if(person3FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person3FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson3SurnameField()
    {
        if(person3SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person3SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson4FirstNameField()
    {
        if(person4FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person4FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson4SurnameField()
    {
        if(person4SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person4SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson5FirstNameField()
    {
        if(person5FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person5FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson5SurnameField()
    {
        if(person5SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person5SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson6FirstNameField()
    {
        if(person6FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person6FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson6SurnameField()
    {
        if(person6SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person6SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson7FirstNameField()
    {
        if(person7FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person7FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson7SurnameField()
    {
        if(person7SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person7SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson8FirstNameField()
    {
        if(person8FirstNameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person8FirstNameField.getText();
        }
        else
        {
            return null;
        }
    }

    public String getPerson8SurnameField()
    {
        if(person8SurnameField.getText().matches("^[a-zA-Z ,.'-]+$"))
        {
            return person8SurnameField.getText();
        }
        else
        {
            return null;
        }
    }

    /**
     * =====================================================================
     * CONFIRM TEAM OUTPUT
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Creates a pop-up saying that a team has been added successfully
     * ======================================================================
     */

    public void confirmTeamOutput()
    {
        JFrame frame = new JFrame();

        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame,
                "The team has been added",
                "Sports Day - Team Confirmation",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.OK_OPTION,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * OUTPUT SCOREBOARD
     * Inputs: iPredictions (int[])
     * Outputs: N/A
     * Comments: Creates a screen showing the scoreboard for the current year
     * ======================================================================
     */

    public void outputScoreboard(int [] iPredictions)
    {
        JFrame jF = new JFrame();
        JPanel predictionsScoreboardScreenPanel = new JPanel();
        predictionsScoreboardScreenPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.weighty = 0.2;

        cliffLabel.setFont(new Font(cliffLabel.getText(), Font.ITALIC, 18));
        downsLabel.setFont(new Font(downsLabel.getText(), Font.ITALIC, 18));
        fleetLabel.setFont(new Font(fleetLabel.getText(), Font.ITALIC, 18));
        schoolLabel.setFont(new Font(schoolLabel.getText(), Font.ITALIC, 18));
        townLabel.setFont(new Font(townLabel.getText(), Font.ITALIC, 18));
        hillLabel.setFont(new Font(hillLabel.getText(), Font.ITALIC, 18));

        String szCliffPoints = iPredictions[0] + " points";
        String szDownsPoints = iPredictions[1] + " points";
        String szFleetPoints = iPredictions[2] + " points";
        String szHillPoints = iPredictions[3] + " points";
        String szSchoolPoints = iPredictions[4] + " points";
        String szTownPoints = iPredictions[5] + " points";

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 490);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Scoreboard for " + Calendar.getInstance().get(Calendar.YEAR));

        c.gridx = 0;
        c.gridy = 0;

        predictionsScoreboardScreenPanel.add(cliffLabel, c);
        c.gridy++;
        predictionsScoreboardScreenPanel.add(downsLabel, c);
        c.gridy++;
        predictionsScoreboardScreenPanel.add(fleetLabel, c);
        c.gridy++;
        predictionsScoreboardScreenPanel.add(hillLabel, c);
        c.gridy++;
        predictionsScoreboardScreenPanel.add(schoolLabel, c);
        c.gridy++;
        predictionsScoreboardScreenPanel.add(townLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        cliffPoints = new JLabel(szCliffPoints);
        predictionsScoreboardScreenPanel.add(cliffPoints, c);
        c.gridy++;
        downsPoints = new JLabel(szDownsPoints);
        predictionsScoreboardScreenPanel.add(downsPoints, c);
        c.gridy++;
        fleetPoints = new JLabel(szFleetPoints);
        predictionsScoreboardScreenPanel.add(fleetPoints, c);
        c.gridy++;
        hillPoints = new JLabel(szHillPoints);
        predictionsScoreboardScreenPanel.add(hillPoints, c);
        c.gridy++;
        schoolPoints = new JLabel(szSchoolPoints);
        predictionsScoreboardScreenPanel.add(schoolPoints, c);
        c.gridy++;
        townPoints = new JLabel(szTownPoints);
        predictionsScoreboardScreenPanel.add(townPoints, c);

        jF.add(predictionsScoreboardScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * TIMINGS SCREEN
     * Inputs: N/A
     * Outputs: Timings of 1st, 2nd and 3rd place
     * Comments:
     * ======================================================================
     */
    public void timingsScreen()
    {
        JFrame jF = new JFrame();

        JPanel timingsScreenPanel = new JPanel();
        timingsScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(380, 160);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Timings");

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        timingsScreenPanel.add(firstPlaceLabel, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceLabel, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        timingsScreenPanel.add(firstPlaceFirstField, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceFirstField, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceFirstField, c);

        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        timingsScreenPanel.add(firstPlaceMinutes, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceMinutes, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceMinutes, c);

        c.gridx = 3;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        timingsScreenPanel.add(firstPlaceSecondField, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceSecondField, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceSecondField, c);
        c.gridy++;
        timingsScreenPanel.add(finalTimingSubmitButton, c);

        c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        timingsScreenPanel.add(firstPlaceSeconds, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceSeconds, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceSeconds, c);

        c.gridx = 5;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        timingsScreenPanel.add(firstPlaceThirdField, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceThirdField, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceThirdField, c);

        c.gridx = 6;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        timingsScreenPanel.add(firstPlaceMilliseconds, c);
        c.gridy++;
        timingsScreenPanel.add(secondPlaceMilliseconds, c);
        c.gridy++;
        timingsScreenPanel.add(thirdPlaceMilliseconds, c);

        jF.add(timingsScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * DISTANCES SCREEN
     * Inputs: N/A
     * Outputs: Distances of 1st, 2nd and 3rd place
     * Comments:
     * ======================================================================
     */
    public void distancesScreen()
    {
        JFrame jF = new JFrame();

        JPanel distancesScreenPanel = new JPanel();
        distancesScreenPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(310, 160);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Distances");

        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        distancesScreenPanel.add(firstPlaceLabel, c);
        c.gridy++;
        distancesScreenPanel.add(secondPlaceLabel, c);
        c.gridy++;
        distancesScreenPanel.add(thirdPlaceLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        distancesScreenPanel.add(firstPlaceFirstField, c);
        c.gridy++;
        distancesScreenPanel.add(secondPlaceFirstField, c);
        c.gridy++;
        distancesScreenPanel.add(thirdPlaceFirstField, c);

        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        distancesScreenPanel.add(firstPlaceMetres, c);
        c.gridy++;
        distancesScreenPanel.add(secondPlaceMetres, c);
        c.gridy++;
        distancesScreenPanel.add(thirdPlaceMetres, c);

        c.gridx = 3;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        distancesScreenPanel.add(firstPlaceSecondField, c);
        c.gridy++;
        distancesScreenPanel.add(secondPlaceSecondField, c);
        c.gridy++;
        distancesScreenPanel.add(thirdPlaceSecondField, c);
        c.gridy++;
        distancesScreenPanel.add(finalDistanceSubmitButton, c);

        c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        distancesScreenPanel.add(firstPlaceCentimetres, c);
        c.gridy++;
        distancesScreenPanel.add(secondPlaceCentimetres, c);
        c.gridy++;
        distancesScreenPanel.add(thirdPlaceCentimetres, c);

        jF.add(distancesScreenPanel);
        jF.setVisible(true);
    }

    //timings and distances getters
    public String getFirstPlaceFirstField()
    {
        return firstPlaceFirstField.getText();
    }

    public String getFirstPlaceSecondField()
    {
        return firstPlaceSecondField.getText();
    }

    public String getFirstPlaceThirdField()
    {
        return firstPlaceThirdField.getText();
    }

    public String getSecondPlaceFirstField()
    {
        return secondPlaceFirstField.getText();
    }

    public String getSecondPlaceSecondField()
    {
        return secondPlaceSecondField.getText();
    }

    public String getSecondPlaceThirdField()
    {
        return secondPlaceThirdField.getText();
    }

    public String getThirdPlaceFirstField()
    {
        return thirdPlaceFirstField.getText();
    }

    public String getThirdPlaceSecondField()
    {
        return thirdPlaceSecondField.getText();
    }

    public String getThirdPlaceThirdField()
    {
        return thirdPlaceThirdField.getText();
    }

    /**
     * =====================================================================
     * THIS YEAR REPORT PRE-CONFIRMATION
     * Inputs: N/A
     * Outputs: N/A
     * Comments: Confirms that the user wants to output the results
     * ======================================================================
     */
    public int getThisYearReportConfirmation()
    {
        Object[] message = {
                "Year of Sports Day: ",  yearEventField
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message, "Sports Day - Output Results",JOptionPane.OK_CANCEL_OPTION);
    }

    //year group for yearly report getter
    public int getThisYearEventField()
    {
        if(yearEventField.getText() == null)
        {
            return 0;
        }

        if(yearEventField.getText().matches("^[12][0-9]{3}$")
                && Integer.parseInt(yearEventField.getText()) <= Calendar.getInstance().get(Calendar.YEAR))
        {
            return Integer.parseInt(yearEventField.getText());
        }
        else
        {
            return 0;
        }

    }

    /**
     * =====================================================================
     * THIS YEAR REPORT POST-CONFIRMATION
     * Inputs: szHouse (String), iYear (int)
     * Outputs: N/A
     * Comments: Indicates if the output of this year's report was successful
     * ======================================================================
     */

    public void outputThisYearReportConfirmation(boolean bTrue, int iYear)
    {
        String szOutput = "";

        if(bTrue)
        {
            szOutput = iYear + "'s report has been outputted!";
        }
        if(!bTrue)
        {
            szOutput = "An error occurred producing " + iYear + "'s report";
        }

        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame, szOutput
                ,
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * CLASS OVER YEARS PRE-CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Get selection of year and house for class over years report
     * ======================================================================
     */
    public int getClassOverYearsConfirmation()
    {
        Object[] message = {
                "House: ",  housesList
                , "Year Group:", classYearsList
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message, "Sports Day - Output Results",JOptionPane.OK_CANCEL_OPTION);
    }

    //houses list selection
    public int getClassOverYearHouseSelection()
    {
        return housesList.getSelectedIndex();
    }

    //class years list for class over years selection
    public int getClassYearListSelection()
    {
        return classYearsList.getSelectedIndex();
    }

    /**
     * =====================================================================
     * HOUSE OVER YEAR PRE-CONFIRMATION
     * Inputs: h (house), bTrue (boolean)
     * Outputs: N/A
     * Comments: Outputs if Class Over Years report was successfully exported
     *           Used for both House and Class as they share inputs
     * ======================================================================
     */

    public int getHouseOverYearsConfirmation()
    {
        Object[] message = {
                "House: ",  housesList
                , "Year Group:", houseOverYearsYearList
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message, "Sports Day - Output Results",JOptionPane.OK_CANCEL_OPTION);
    }

    //getter for house over year
    public int getHouseOverYearsYearListSelection()
    {
        return houseOverYearsYearList.getSelectedIndex();
    }

    /**
     * =====================================================================
     * CLASS OVER YEARS / HOUSE OVER YEARS POST-CONFIRMATION
     * Inputs: h (house), bTrue (boolean)
     * Outputs: N/A
     * Comments: Outputs if Class Over Years report was successfully exported
     *           Used for both House and Class as they share inputs
     * ======================================================================
     */

    public void outputClassOverYearsConfirmation(house h, boolean bTrue)
    {
        String szOutput = "";

        if(bTrue)
        {
            szOutput = h.name() + "'s report has been outputted!";
        }
        if(!bTrue)
        {
            szOutput = "An error occurred producing " + h.name() + "'s report";
        }

        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame, szOutput
                ,
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * EVENT OVER YEAR PRE-CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Used for Event over Years confirmation
     * ======================================================================
     */

    public int getEventOverYearsConfirmation()
    {
        Object[] message = {
                "Event: ",  eventList
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message, "Sports Day - Output Results",JOptionPane.OK_CANCEL_OPTION);
    }

    /**
     * =====================================================================
     * EVENT OVER YEAR POST-CONFIRMATION
     * Inputs: et (eventType), bTrue (boolean)
     * Outputs: N/A
     * Comments: Outputs what happened with the report for event over years
     * ======================================================================
     */

    public void outputEventOverYearsConfirmation(eventType et, boolean bTrue)
    {
        String szOutput = "";

        if(bTrue)
        {
            szOutput = et.name() + "'s report has been outputted!";
        }
        if(!bTrue)
        {
            szOutput = "An error occurred producing " + et.name() + "'s report";
        }

        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame, szOutput
                ,
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * RECORD REPORT PRE-CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Gets if the user wants to output all the record holders
     * ======================================================================
     */

    public int getRecordReportConfirmation()
    {
        JFrame frame = new JFrame();
        int iResultConfirmation;
        iResultConfirmation = JOptionPane.showOptionDialog(frame
                , "Are you sure you want to output the record holders?"
                , "Sports Day - Output Results"
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.WARNING_MESSAGE
                , null
                , null
                , null);

        return iResultConfirmation;
    }

    /**
     * =====================================================================
     * RECORD REPORT POST-CONFIRMATION
     * Inputs: bTrue (boolean)
     * Outputs: N/A
     * Comments: Outputs the result of the attempt to output record holders
     * ======================================================================
     */

    public void outputRecordConfirmation(boolean bTrue)
    {
        String szOutput = "";

        if(bTrue)
        {
            szOutput = "The records report has been outputted!";
        }
        if(!bTrue)
        {
            szOutput = "An error occurred producing the records report";
        }

        JFrame frame = new JFrame();
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(frame, szOutput
                ,
                "Sports Day - Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * =====================================================================
     * OUTPUT PREDICTIONS
     * Inputs: N/A
     * Outputs: N/A
     * Comments: The generic method to output predictions
     * ======================================================================
     */

    public void outputPredictions(int [] iPredictions)
    {
        JFrame jF = new JFrame();
        JPanel predictionsOutputScreenPanel = new JPanel();
        predictionsOutputScreenPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.weighty = 0.2;

        cliffLabel.setFont(new Font(cliffLabel.getText(), Font.ITALIC, 18));
        downsLabel.setFont(new Font(downsLabel.getText(), Font.ITALIC, 18));
        fleetLabel.setFont(new Font(fleetLabel.getText(), Font.ITALIC, 18));
        schoolLabel.setFont(new Font(schoolLabel.getText(), Font.ITALIC, 18));
        townLabel.setFont(new Font(townLabel.getText(), Font.ITALIC, 18));
        hillLabel.setFont(new Font(hillLabel.getText(), Font.ITALIC, 18));

        String szCliffPoints = iPredictions[0] + " points";
        String szDownsPoints = iPredictions[1] + " points";
        String szFleetPoints = iPredictions[2] + " points";
        String szHillPoints = iPredictions[3] + " points";
        String szSchoolPoints = iPredictions[4] + " points";
        String szTownPoints = iPredictions[5] + " points";

        jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setSize(440, 490);
        jF.setResizable(false);
        jF.setTitle("Sports Day - Predictions");
        
        c.gridx = 0;
        c.gridy = 0;
        
        predictionsOutputScreenPanel.add(cliffLabel, c);
        c.gridy++;
        predictionsOutputScreenPanel.add(downsLabel, c);
        c.gridy++;
        predictionsOutputScreenPanel.add(fleetLabel, c);
        c.gridy++;
        predictionsOutputScreenPanel.add(hillLabel, c);
        c.gridy++;
        predictionsOutputScreenPanel.add(schoolLabel, c);
        c.gridy++;
        predictionsOutputScreenPanel.add(townLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        cliffPoints = new JLabel(szCliffPoints);
        predictionsOutputScreenPanel.add(cliffPoints, c);
        c.gridy++;
        downsPoints = new JLabel(szDownsPoints);
        predictionsOutputScreenPanel.add(downsPoints, c);
        c.gridy++;
        fleetPoints = new JLabel(szFleetPoints);
        predictionsOutputScreenPanel.add(fleetPoints, c);
        c.gridy++;
        hillPoints = new JLabel(szHillPoints);
        predictionsOutputScreenPanel.add(hillPoints, c);
        c.gridy++;
        schoolPoints = new JLabel(szSchoolPoints);
        predictionsOutputScreenPanel.add(schoolPoints, c);
        c.gridy++;
        townPoints = new JLabel(szTownPoints);
        predictionsOutputScreenPanel.add(townPoints, c);

        jF.add(predictionsOutputScreenPanel);
        jF.setVisible(true);
    }

    /**
     * =====================================================================
     * WHOLE SCHOOL PREDICTIONS CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Get the user's confirmation that they want to predict the entire school
     * ======================================================================
     */

    public int getWholeSchoolPredictionConfirmation()
    {
        JFrame frame = new JFrame();
        int iResultConfirmation;
        iResultConfirmation = JOptionPane.showOptionDialog(frame
                , "Are you sure you want to predict the results for this year?"
                , "Sports Day - Predict Results"
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.WARNING_MESSAGE
                , null
                , null
                , null);

        return iResultConfirmation;
    }

    /**
     * =====================================================================
     * YEAR GROUP PREDICTIONS CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Get the user's confirmation that they want to predict a specific year group
     * ======================================================================
     */

    public int getYearGroupPredictionConfirmation()
    {
        Object[] message = {
                "Year Group:", classYearsList
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message
                , "Sports Day - Predict Results",JOptionPane.OK_CANCEL_OPTION);
    }

    /**
     * =====================================================================
     * EVENT PREDICTIONS CONFIRMATION
     * Inputs: N/A
     * Outputs: int
     * Comments: Get the user's confirmation that they want to predict a specific event
     * ======================================================================
     */

    public int getEventPredictionConfirmation()
    {
        Object[] message = {
                "Year Group:", classYearsList
                , "Event:", eventList
                , "Participation:", eliteList
        };
        JFrame frame = new JFrame();

        return JOptionPane.showConfirmDialog(frame, message
                , "Sports Day - Predict Results",JOptionPane.OK_CANCEL_OPTION);
    }

    //settings getters
    public boolean getYearSevenCheckBox()
    {
        return this.yearSevenCheckBox.isSelected();
    }

    public boolean getYearEightCheckBox()
    {
        return this.yearEightCheckBox.isSelected();
    }

    public boolean getYearNineCheckBox()
    {
        return this.yearNineCheckBox.isSelected();
    }

    public boolean getYearTenCheckBox()
    {
        return this.yearTenCheckBox.isSelected();
    }

    public boolean getYearTwelveCheckBox()
    {
        return this.yearTwelveCheckBox.isSelected();
    }

    public void setYearSevenCheckBox(boolean bSelection)
    {
        this.yearSevenCheckBox.setSelected(bSelection);
    }

    public void setYearEightCheckBox(boolean bSelection)
    {
        this.yearEightCheckBox.setSelected(bSelection);
    }

    public void setYearNineCheckBox(boolean bSelection)
    {
        this.yearNineCheckBox.setSelected(bSelection);
    }

    public void setYearTenCheckBox(boolean bSelection)
    {
        this.yearTenCheckBox.setSelected(bSelection);
    }

    public void setYearTwelveCheckBox(boolean bSelection)
    {
        this.yearTwelveCheckBox.setSelected(bSelection);
    }

    /**
     * =====================================================================
     * EXTERNAL LISTENERS FOR CONTROLLER
     * ======================================================================
     */

    //scoreboardButton listener
    void addScoreboardButtonListener(ActionListener ScoreboardButtonListener)
    {
        scoreboardButton.addActionListener(ScoreboardButtonListener);
    }

    //enterButton listener
    void addResultsEnterButtonListener(ActionListener listenForResultEnterButton)
    {
        enterButton.addActionListener(listenForResultEnterButton);
    }

    //cliffEnterButton listener
    void addCliffEnterButtonListener(ActionListener listenForCliffEnterButton)
    {
        cliffEnterButton.addActionListener(listenForCliffEnterButton);
    }

    //downsEnterButton listener
    void addDownsEnterButtonListener(ActionListener listenForDownsEnterButton)
    {
        downsEnterButton.addActionListener(listenForDownsEnterButton);
    }

    //fleetEnterButton listener
    void addFleetEnterButtonListener(ActionListener listenForFleetEnterButton)
    {
        fleetEnterButton.addActionListener(listenForFleetEnterButton);
    }

    //hillEnterButton listener
    void addHillEnterButtonListener(ActionListener listenForHillEnterButton)
    {
        hillEnterButton.addActionListener(listenForHillEnterButton);
    }

    //schoolEnterButton listener
    void addSchoolEnterButtonListener(ActionListener listenForSchoolEnterButton)
    {
        schoolEnterButton.addActionListener(listenForSchoolEnterButton);
    }

    //townEnterButton listener
    void addTownEnterButtonListener(ActionListener listenForTownEnterButton)
    {
        townEnterButton.addActionListener(listenForTownEnterButton);
    }

    //finalTimingSubmitButton listener
    void addFinalTimingButtonListener(ActionListener listenForFinalTimingButton)
    {
        finalTimingSubmitButton.addActionListener(listenForFinalTimingButton);
    }

    //finalDistanceSubmitButton listener
    void addFinalDistanceButtonListener(ActionListener listenForFinalDistanceButton)
    {
        finalDistanceSubmitButton.addActionListener(listenForFinalDistanceButton);
    }

    //thisYearReportButton listener
    void addThisYearReportButtonListener(ActionListener listenForYearReportButton)
    {
        thisYearReportButton.addActionListener(listenForYearReportButton);
    };

    //classReportButton listener
    void addClassReportButtonListener(ActionListener listenForClassReportButton)
    {
        classReportButton.addActionListener(listenForClassReportButton);
    }

    //houseReportButton
    void addHouseOverYearsButtonListener(ActionListener houseOverYearsButtonListener)
    {
        houseReportButton.addActionListener(houseOverYearsButtonListener);
    }

    //eventReportButton listener
    void addEventOverYearsButtonListener(ActionListener eventOverYearsButtonListener)
    {
        eventReportButton.addActionListener(eventOverYearsButtonListener);
    }

    //recordReportButton listener
    void addRecordButtonListener(ActionListener recordButtonListener)
    {
        recordReportButton.addActionListener(recordButtonListener);
    }

    //predictWholeSchoolButton listener
    void addPredictWholeSchoolListener(ActionListener predictWholeSchoolButtonListener)
    {
        predictWholeSchoolButton.addActionListener(predictWholeSchoolButtonListener);
    }

    //predictYearGroupButton listener
    void addPredictYearGroupListener(ActionListener predictYearGroupButtonListener)
    {
        predictYearGroupButton.addActionListener(predictYearGroupButtonListener);
    }

    //predictEventButton listener
    void addPredictEventListener(ActionListener predictEventButtonListener)
    {
        predictEventButton.addActionListener(predictEventButtonListener);
    }

    //settingsButton listener
    void addSettingsButtonListener(ActionListener settingsButtonListener)
    {
        settingsButton.addActionListener(settingsButtonListener);
    }

    //settingSubmitButton listener
    void addSettingsSubmitListener(ActionListener settingsSubmitButtonListener)
    {
        settingSubmitButton.addActionListener(settingsSubmitButtonListener);
    }

    /**
     * =====================================================================
     * MAIN
     * Comments: Used in debugging to check if the pre-listener components are present
     * =====================================================================
     */

    /*public static void main(String args[])
    {
        view ms = new view();
        ms.mainScreen();
    }*/
}