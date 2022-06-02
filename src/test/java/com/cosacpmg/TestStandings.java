package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.StandingsEntry;
import models.Team;
import models.Tournament;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestStandings {

    Game game;
    GameController gc;
    Team testHometeam;
    Team testAwayTeam;
    String testLocation = "field1";
    Tournament masterTournament;
    Date date;

    //Sets up a database for testing
    @BeforeClass
    public static void settupDB(){
        new App().startDB();
    }

    //Sets up the objects and controllers needed for testing
    @Before
    public void setup() throws SQLException {

        TournamentController TC = new TournamentController(App.connection);
        TeamController tc = new TeamController(App.connection);


        gc = new GameController(App.connection);

        testHometeam = TeamController.createTeam("team Home", "Berlin", "west germany", "Angela Merkel", "234 567 8910");
        testAwayTeam = TeamController.createTeam("team Away", "London", "Brixton", "Margret Thatcher", "234 567 5555");
        tc.addTeam(testHometeam);
        tc.addTeam(testAwayTeam);

        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        date = time.getTime();
        masterTournament = TC.createTournament("MasterTournament", date, date);

        game = gc.createGame(testHometeam, testAwayTeam, date, testLocation, masterTournament);
        gc.addGame(game);
    
    }


    @After
    public void teardown()
    {
//        testGameController.removeGame(game);
    }

    /**
     * Tests that wins are properly added to the standings entry from the database
     */
    @Test
    public void testStandingsEntryWinsQuery(){

        game.setWinners(1);
        game.setPlayed(true);
        gc.update(game);
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getWins());

    }

    /**
     * Tests that losses are properly added to the standings entry from the database
     */
    @Test
    public void testStandingsEntryLossesQuery(){

        game.setWinners(-1);
        game.setPlayed(true);
        gc.update(game);
    
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getLosses());

    }

    /**
     * Tests that ties are properly added to the standings entry from the database
     */
    @Test
    public void testStandingsEntryTiesQuery(){

        game.setWinners(0);
        game.setPlayed(true);
        gc.update(game);
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getTies());
    
    }
}
