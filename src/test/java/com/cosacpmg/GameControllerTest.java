package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Field;
import models.Game;
import models.Team;
import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    Team testHometeam, testAwayTeam;
    Field testLocation;
    Date date;

    private void assertInvalidGameMessage(String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Game>> constraintViolations = validator.validate( masterTest );//use the private global car created in setUpValidCar

        //count how many violations - SHOULD ONLY BE 1
        assertEquals( 1, constraintViolations.size() );

        //get first violation from constraintViolations collection
        ConstraintViolation<Game> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        //ensure error message matches what is expected
        assertEquals( expectedErrMsg, violation.getMessage() );

        //ensure the invalid value is what was set
        assertEquals( expectedValue, violation.getInvalidValue() );
    }

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
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void CreateGameWithTeam1AlreadyInGame() throws SQLException {
        Team UnusedTeam = new Team();
        Game SecondGame = testGameController.createGame(testHometeam,UnusedTeam, date,new Field(),masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);
        assertTrue(schedule.size()>0);
//        assertFalse(schedule.contains(SecondGame));
        assertFalse(schedule.size()>1);
    }

    @Test
    public void CreateGameWithTeam2AlreadyInGame() throws SQLException {
        Team UnusedTeam = new Team();
        Game SecondGame = testGameController.createGame(UnusedTeam,testAwayTeam, date,new Field(),masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);
        assertTrue(schedule.size()>0);
//        assertFalse(schedule.contains(SecondGame));
        assertFalse(schedule.size()>1);
    }
    @Test
    public void CreateGameWithBothTeamAlreadyInGame() throws SQLException {
        Team UnusedTeam = new Team();
        Game SecondGame = testGameController.createGame(testHometeam,testAwayTeam, date,new Field(),masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);
        assertTrue(schedule.size()>0);
//        assertFalse(schedule.contains(SecondGame));
        assertFalse(schedule.size()>1);
    }

    @Test
    public void CreateGameWithSameTeam() throws SQLException {
        Team UnusedTeam = new Team();
        Game SecondGame = testGameController.createGame(UnusedTeam,UnusedTeam, date,new Field(),masterTournament);

        List<Game> schedule = testGameController.getSchedule(masterTournament);
        assertTrue(schedule.size()>0);
//        assertFalse(schedule.contains(SecondGame));
        assertFalse(schedule.size()>1);
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
