package com.cosacpmg;

import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.StandingsEntry;
import models.Team;
import models.Tournament;
import org.junit.*;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStandings {

    Game game;
    GameController gc;
    Team testHometeam;
    Team testAwayTeam;
    String testLocation = "field1";
    Tournament masterTournament;


    @BeforeClass
    public static void settupDB(){
        new App().startDB();
    }
    
    @Before
    public void setup() throws SQLException {

        TournamentController TC = new TournamentController(App.connection);
        TeamController tc = new TeamController(App.connection);
    
    
        gc = new GameController(App.connection);

        testHometeam = TeamController.createTeam("team Home", "Berlin", "west germany", "Angela Merkel", "234 567 8910");
        testAwayTeam = TeamController.createTeam("team Away", "London", "Brixton", "Margret Thatcher", "234 567 5555");
        tc.addTeam(testHometeam);
        tc.addTeam(testAwayTeam);
    
    
        masterTournament = TC.createTournament("MasterTournament");

        game = gc.createGame(testHometeam, testAwayTeam, new Date(), testLocation, masterTournament);
        gc.addGame(game);
    
    }
    
    @After
    public void teardown()
    {
//        testGameController.removeGame(game);
    }


    @Test
    public void testStandingsEntryWinsQuery(){


        game.setWinners(1);
        game.setPlayed(true);
        gc.update(game);
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getWins());

    }

    @Test
    public void testStandingsEntryLossesQuery(){

        game.setWinners(-1);
        game.setPlayed(true);
        gc.update(game);
    
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getLosses());

    }

    @Test
    public void testStandingsEntryTiesQuery(){

        game.setWinners(0);
        game.setPlayed(true);
        gc.update(game);
    
        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertEquals(1, entry.getTies());
    
    }
}
