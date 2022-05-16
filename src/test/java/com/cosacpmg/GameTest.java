package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.GameController;
import controllers.TournamentController;
import models.Field;
import models.Game;
import models.Team;
import models.Tournament;
import org.junit.Before;
import org.junit.Test;
import views.GameView;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class GameTest
{
    //private static final String CONNECT_STRING = "jdbc:sqlight:schedule.db";
    Game masterTest;
    Tournament masterTournament;
    GameController testGameController;
    TournamentController testTournamentController;
    ConnectionSource dbConn;

    @Before
    public void TestSetup() throws SQLException {

        dbConn = new JdbcPooledConnectionSource("jdbc:h2:mem:myDb");

        testGameController = new GameController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );
        testTournamentController = new TournamentController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );
        masterTest = new Game();
        masterTournament = new Tournament();

    }

    @Test
    public void ManualCreationOfGame()
    {
        Calendar time;
        time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        Date date = time.getTime();
        Team testHometeam = null,testAwayTeam = null;
        Field testLocation = null;

        Game testGame = GameController.Game(testHometeam,testAwayTeam, time.getTime(),testLocation);

        assertNotNull(testGame);
        assertEquals(testHometeam,testGame.getHomeTeam());
        assertEquals(testAwayTeam,testGame.getAwayTeam());
        assertEquals(testLocation,testGame.getLocation());
        assertEquals(date,testGame.getStartTime());
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
    public void UserCancelsCreatingGame()
    {
        GameView.cancel();
        assertTrue( masterTournament.getSchedule().isEmpty() );
    }

    @Test
    public void CreateGameSetInPast()
    {
        Calendar past = Calendar.getInstance();
        past.set(2002, Calendar.JUNE,10);
        masterTest.setStartTime(past.getTime());
        // Create Contoller method that return if time is valid and then change it
        //assertTrue();
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
