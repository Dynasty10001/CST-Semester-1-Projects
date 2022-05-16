package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.*;
import org.h2.command.ddl.CreateTable;
import org.junit.*;
import views.GameView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class GameTest
{
    //private static final String CONNECT_STRING = "jdbc:sqlight:schedule.db";
    private static ValidatorFactory VF;
    private static Validator validator;
    Game masterTest;
    Tournament masterTournament;
    GameController testGameController;
    TournamentController testTournamentController;
    TeamController testTeamController;
    ConnectionSource dbConn;
    Team testHometeam, testAwayTeam;
    Field testLocation;

    @BeforeClass
    public static void SetupValidator(){
        VF = Validation.buildDefaultValidatorFactory();
        validator = VF.getValidator();

    }

    @AfterClass
    public static void tearDownValidator()
    {
        VF.close();
    }
    @Before
    public void TestSetup() throws SQLException {

        dbConn = new JdbcPooledConnectionSource("jdbc:h2:mem:myDb");


        testTeamController = new TeamController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );
        testGameController = new GameController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );
        testTournamentController = new TournamentController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );

        TableUtils.createTable(testTeamController.repo);
        TableUtils.createTable(testGameController.repo);

        masterTest = new Game();
        masterTournament = testTournamentController.Tournament("ThisTournament");
        testHometeam = testTeamController.team();
        testAwayTeam = testTeamController.team();
        testLocation = null;

    }

    @Test
    public void ManualCreationOfGame() throws SQLException {
        Calendar time;
        time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date date = time.getTime();

        Game testGame = testGameController.Game(testHometeam,testAwayTeam, time.getTime(),testLocation,masterTournament);

        assertNotNull(testGame);
        assertEquals(testHometeam,testGame.getHomeTeam());
        assertEquals(testAwayTeam,testGame.getAwayTeam());
        assertEquals(testLocation,testGame.getLocation());
        assertEquals(date,testGame.getStartTime());
    }

    private void assertInvalidGame(String expectedProperty, String expectedErrMsg, Object expectedValue){
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

    @Test
    public void UserCreatesNewGame()
    {
        GameView.setTournament(masterTournament);
        masterTournament.getSchedule().clear();
        GameView.submit();
        assertFalse( masterTournament.getSchedule().isEmpty() );
    }

    @Test
    public void GameDateMustBeInFuture()
    {
        Calendar past = Calendar.getInstance();
        past.set(2002, Calendar.JUNE,10);
        masterTest.setStartTime(past.getTime());
        Long Time = past.getTime().getTime();

        assertInvalidGame("startTime","Message",Time);

    }

    @Test
    public void UserCancelsCreatingGame()
    {
        GameView.cancel();
        assertTrue( masterTournament.getSchedule().isEmpty() );
    }

    @Test
    public void CreateGameSetInPast() throws SQLException {
        Calendar past = Calendar.getInstance();
        past.set(2002, Calendar.JUNE,10);
        past.getTime();
        masterTest = testGameController.Game(testHometeam,testAwayTeam,past.getTime(),testLocation,masterTournament);
        // Make Validator that checks Time is in future
        assertTrue(testGameController.spaceTimeValidator());
        fail();
    }

    @Test
    public void CreateGameWithTeamAlreadyInGame()
    {
        Game ContainsTeamUsed = new Game();
        Team UsedTeam = new Team();
        ContainsTeamUsed.setHomeTeam(UsedTeam);

        Game NewGame = new Game();
        NewGame.setHomeTeam(UsedTeam);
        NewGame.setAwayTeam(new Team());
        fail(); // Needs an exception checker
    }

    @Test
    public void CreateGameOnUsedField()
    {
        fail();
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
