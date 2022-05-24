package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.*;

import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

public class GameTest
{
    //private static final String CONNECT_STRING = "jdbc:sqlight:schedule.db";
    private static ValidatorFactory Vf;
    private static Validator validator;
    private static Game validGame;
    private String repeatA(int count){
        return new String(new char[count]).replace('\0','A');
    }

    private void assertInvalidGame(String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Game>> constraintViolations = validator.validate( validGame );//use the private global car created in setUpValidCar

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
    public static void SetupValidator() throws SQLException 
    {
        Vf = Validation.buildDefaultValidatorFactory();
        validator = Vf.getValidator();
    }

    @AfterClass
    public static void tearDownValidator()
    {
        //gracefully teardown the validator factory
        Vf.close();
    }


    @Before
    public void TestSetup()
    {
        Date date= new Date();
        date.setTime(date.getTime()+7000);
        validGame = new Game();
        validGame.setAwayTeam (new Team());
        validGame.setHomeTeam(new Team());
        validGame.setStartTime(date);
        validGame.setLocation("Test");
        validGame.setTournament(new Tournament());
        validGame.setWinners(0);
    }
    
    @Test
    public void testGameAwayTeamNull()
    {
        validGame.setAwayTeam(null);
        assertInvalidGame("awayTeam", "Game must have an Away Team" ,null);
    }

    @Test
    public void testGameAllValid()
    {
        assertEquals (0, validator.validate(validGame ).size());
    }
    
    @Test
    public void testGameHomeTeamNull()
    {
        validGame.setHomeTeam(null);
        assertInvalidGame("homeTeam", "Game must have a Home Team" ,null);
    }


    @Test
    public void testGameStartTimeFuture()
    {
        Date date = new Date();
        date.setTime(date.getTime()+1000*60*60*48);
        validGame.setStartTime(date);
        assertEquals (0, validator.validate(validGame ).size());
    }

    @Test
    public void testGameStartTimePast()
    {
        Date date = new Date();
        date.setTime(date.getTime()-1000*60*60*48);
        validGame.setStartTime(date);
        assertInvalidGame("startTime", "Date must not be in the past" , date);
    }
    
    @Test
    public void testGameStartTimeNull()
    {
        validGame.setStartTime(null);
        assertInvalidGame("startTime", "A date must be selected for the game" , null);
    }

    @Test
    public void testGameLocationEmpty()
    {
        validGame.setLocation("");
        assertInvalidGame("location","Game must have a location that it is played at", "");
    }

    @Test
    public void testGameTournamentNull()
    {
        validGame.setTournament(null);
        assertInvalidGame("tournament", "Game must be contained inside a tournament", null);
    }

    @Test
    public void testGameWinnnersTooHigh()
    {
        validGame.setWinners(2);
        assertInvalidGame("winners", "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played",
                2);
    }

    @Test
    public void testGameWinnnersTooLow()
    {
        validGame.setWinners(-2);
        assertInvalidGame("winners", "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played",
                -2);
    }

    @Test
    public void testGameWinnersValid()
    {
        validGame.setWinners(-1);
        assertEquals (0, validator.validate(validGame ).size());

        validGame.setWinners(0);
        assertEquals (0, validator.validate(validGame ).size());

        validGame.setWinners(1);
        assertEquals (0, validator.validate(validGame ).size());

        validGame.setWinners(null);
        assertEquals (0, validator.validate(validGame ).size());
    }
}
