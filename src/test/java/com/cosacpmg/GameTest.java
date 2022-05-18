package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.*;
import org.junit.*;
import views.AddGameView;
import views.GameView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

public class GameTest
{
    //private static final String CONNECT_STRING = "jdbc:sqlight:schedule.db";
    private static ValidatorFactory VF;
    private static Validator validator;
    Game masterTest;
    Tournament masterTournament;
    private static GameController testGameController;
    private static TournamentController testTournamentController;
    private static TeamController testTeamController;
    private static ConnectionSource dbConn;
    Team testHometeam, testAwayTeam;
    Field testLocation;
    Date date;

    @BeforeClass
    public static void SetupValidator() throws SQLException {
        VF = Validation.buildDefaultValidatorFactory();
        validator = VF.getValidator();

        dbConn = new JdbcPooledConnectionSource("jdbc:sqlite:schedule.db");
        testTeamController = new TeamController(dbConn);
        testGameController = new GameController( dbConn);
        testTournamentController = new TournamentController(dbConn);




    }

    @AfterClass
    public static void tearDownValidator()
    {
        VF.close();
    }


    @Before
    public void TestSetup() throws SQLException {



        testTeamController = new TeamController(dbConn);
        testGameController = new GameController( dbConn);
        testTournamentController = new TournamentController(dbConn);

        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        date = time.getTime();

        TableUtils.clearTable(dbConn,Tournament.class);
        TableUtils.clearTable(dbConn,Game.class);
        TableUtils.clearTable(dbConn,Team.class);

        masterTournament = testTournamentController.Tournament("MasterTournament");
        testHometeam = testTeamController.team();
        testAwayTeam = testTeamController.team();
        testLocation = new Field();
        masterTest = testGameController.createGame(testHometeam, testAwayTeam, date, testLocation, masterTournament);

    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Test to make sure a Game is successfully created and the values are going into the correct slots
     */
    @Test
    public void ManualCreationOfGame() throws SQLException {

        Game testGame = testGameController.createGame(testHometeam,testAwayTeam, date,testLocation,masterTournament);

        assertNotNull(testGame);
        assertEquals(testHometeam,testGame.getHomeTeam());
        assertEquals(testAwayTeam,testGame.getAwayTeam());
        assertEquals(testLocation,testGame.getLocation());
        assertEquals(date,testGame.getStartTime());
    }

    /**
     * Test to make sure a created game is added to the database
     */
    @Test
    public void GameAddedToDatabase() throws SQLException {
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);

        assertTrue(schedule.size() > 1);
    }

    /**
     * Test to make sure a team is NOT added to the database when the Home Team is Null
     */
    @Test
    public void HomeTeamIsNull(){
        Team UnusedTeamOne = null;
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }

        assertTrue(threwException);
    }

    /**
     * Test to make sure a team is NOT added to the database when the Away Team is Null
     */
    @Test
    public void AwayTeamIsNull(){
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = null;
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }

        assertTrue(threwException);
    }

    /**
     * Test to make sure a team is NOT added to the database when the Both Teams are Null
     */
    @Test
    public void BothTeamIsNull(){
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = null;
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }

        assertTrue(threwException);

    }


    /**
     * Test to make sure a team is NOT added to the database when the Date is set into the future
     // TODO: 5/18/2022  for some reason, @Future doesnt seem to be working. Must Fix
     */
    //@Test
    public void GameDateMustBeInFuture(){

        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(1, Calendar.JUNE,10);
        Date newDate = time.getTime();
        List<Game> schedule = new ArrayList<Game>();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);
            schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }

        assertTrue(schedule.size()>0);
        assertTrue(schedule.size()<2);
    }

    /**
     * Test to make sure a team is NOT added to the database when Both Teams are Null
     */
    @Test
    public void DateIsNull(){
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = null;
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),masterTournament);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }

        assertTrue(threwException);

    }

    /**
     * Test to make sure a team is NOT added to the database when the Date is Null
     */
    @Test
    public void LocationIsNull(){
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,null,masterTournament);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }
        assertTrue(threwException);
    }

    /**
     * Test to make sure a team is NOT added to the database when the tournament is Null
     */
    @Test
    public void TournamentIsNull(){
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date newDate = time.getTime();
        boolean threwException = false;
        try {
            Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, newDate,new Field(),null);
            List<Game> schedule = testGameController.getSchedule(masterTournament);
            assertTrue(schedule.size() == 1);
        } catch (SQLException e) {
            threwException = true;
        }
        assertTrue(threwException);
    }


}
