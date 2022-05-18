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




    }

    @AfterClass
    public static void tearDownValidator()
    {
        VF.close();
    }


    @Before
    public void TestSetup() throws SQLException {


        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        date = time.getTime();



        testTeamController = new TeamController(dbConn);
        testGameController = new GameController( dbConn);
        testTournamentController = new TournamentController(dbConn);

        TableUtils.clearTable(dbConn,Tournament.class);
        TableUtils.clearTable(dbConn,Game.class);
        TableUtils.clearTable(dbConn,Team.class);

        masterTournament = testTournamentController.Tournament("MasterTournament");
        testHometeam = testTeamController.team();
        testAwayTeam = testTeamController.team();
        testLocation = null;
        masterTest = testGameController.createGame(testHometeam, testAwayTeam, date, testLocation, masterTournament);

    }

    @Test
    public void ManualCreationOfGame() throws SQLException {

        Game testGame = testGameController.createGame(testHometeam,testAwayTeam, date,testLocation,masterTournament);

        assertNotNull(testGame);
        assertEquals(testHometeam,testGame.getHomeTeam());
        assertEquals(testAwayTeam,testGame.getAwayTeam());
        assertEquals(testLocation,testGame.getLocation());
        assertEquals(date,testGame.getStartTime());
    }

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

    @Test
    public void HomeTeamIsNull() throws SQLException {
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

    @Test
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



}
