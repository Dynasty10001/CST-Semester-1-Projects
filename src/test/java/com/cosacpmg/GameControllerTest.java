package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.Team;
import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
public class GameControllerTest {
    private static ValidatorFactory VF;
    private static Validator validator;
    Game masterTest;
    Tournament masterTournament;
    private static GameController testGameController;
    private static TournamentController testTournamentController;
    private static TeamController testTeamController;
    private static ConnectionSource dbConn;
    Team testHometeam, testAwayTeam, testUnusedTeam;
    String testLocation;
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
        time.add(Calendar.HOUR_OF_DAY, 10);
        date = time.getTime();



        testTeamController = new TeamController(dbConn);
        testGameController = new GameController( dbConn);
        testTournamentController = new TournamentController(dbConn);

        TableUtils.clearTable(dbConn,Tournament.class);
        TableUtils.clearTable(dbConn,Game.class);
        TableUtils.clearTable(dbConn,Team.class);

        masterTournament = testTournamentController.createTournament("MasterTournament", new Date(), new Date());
        testHometeam = testTeamController.createTeam("team Home", "Berlin", "west germany", "Angela Merkel", "234 567 8910");
        testAwayTeam = testTeamController.createTeam("team Away", "London", "Brixton", "Margret Thatcher", "234 567 5555");
        testUnusedTeam = testTeamController.createTeam("unused", "nowhere", "space", "go", "666 666 6666");
        testLocation = "field1";
        masterTest = testGameController.createGame(testHometeam, testAwayTeam, date, testLocation, masterTournament);

        testTeamController.addTeam(testHometeam);
        testTeamController.addTeam(testAwayTeam);
        testTeamController.addTeam(testUnusedTeam);
        testTournamentController.addTournament(masterTournament);
        testGameController.addGame(masterTest);


    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void CreateGameWithHomeTeamAlreadyInGameAtSameTime() throws SQLException
    {
        Game secondGame = testGameController.createGame(testHometeam,testUnusedTeam, date,testLocation,masterTournament);
        assertFalse(testGameController.spaceTimeValidator(secondGame));
    }

    @Test
    public void CreateGameWithAwayTeamAlreadyInGameAtSameTime() throws SQLException
    {
        Game secondGame = testGameController.createGame(testUnusedTeam,testAwayTeam, date,testLocation,masterTournament);
        assertFalse(testGameController.spaceTimeValidator(secondGame));
    }

    @Test
    public void CreateGameWithSameTeam() throws SQLException
    {
        Game secondGame = testGameController.createGame(testUnusedTeam,testUnusedTeam, date,testLocation, masterTournament);
        assertFalse( testGameController.roundRobinValidator(secondGame));
    }

    @Test
    public void CreateGameWithTeamsSwitched() throws SQLException
    {
        Game secondGame = testGameController.createGame(testAwayTeam,testHometeam, date,testLocation,masterTournament);
        assertFalse(testGameController.roundRobinValidator(secondGame));
    }
    @Test
    public void CreateGameWithTeamsAlreadyPlayed() throws SQLException
    {
        Game secondGame = testGameController.createGame(testHometeam,testAwayTeam, date,testLocation,masterTournament);
        assertFalse(testGameController.roundRobinValidator(secondGame));
    }
////////////////////////////////


    @Test
    public void CreateGameWithHomeTeamNotInGameAtSameTime() throws SQLException
    {
        date.setTime(date.getTime() + 2*60*60*1000L);
        Game secondGame = testGameController.createGame(testHometeam,testUnusedTeam, date,testLocation,masterTournament);
        assertTrue(testGameController.spaceTimeValidator(secondGame));
    }

    @Test
    public void CreateGameWithAwayTeamNotInGameAtSameTime() throws SQLException
    {
        date.setTime(date.getTime() - 2*60*60*1000L);
        Game secondGame = testGameController.createGame(testUnusedTeam,testAwayTeam, date,testLocation,masterTournament);
        assertTrue(testGameController.spaceTimeValidator(secondGame));
    }

    @Test
    public void CreateGameValidGame() throws SQLException
    {
        date.setTime(date.getTime() - 2*60*60*1000L);
        Game secondGame = testGameController.createGame(testUnusedTeam,testAwayTeam, date,testLocation,masterTournament);
        assertTrue(testGameController.roundRobinValidator(secondGame));
    }






    @Test
    public void CreateGameOnUsedField() throws SQLException {
        Team UnusedTeamOne = new Team();
        Team UnusedTeamTwo = new Team();
        Game SecondGame = testGameController.createGame(UnusedTeamOne,UnusedTeamTwo, date,testLocation,masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);
        assertTrue(schedule.size()>0);
//        assertFalse(schedule.contains(SecondGame));
        assertFalse(schedule.size()>1);
    }

    //@Test
    public void CreateGameRoundRobinRescheduled()
    {
        fail();
    }
    //@Test
    public void CreateGameRoundRobinNotReschedule()
    {
        fail();
    }

}
