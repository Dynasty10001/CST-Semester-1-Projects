package com.cosacpmg;

import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.StandingsEntry;
import models.Team;
import models.Tournament;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TestStandings {

    Game game;
    GameController testGameController;
    Team testHometeam;
    Team testAwayTeam;
    String testLocation = "field1";
    Tournament masterTournament;


    @Before
    public void setup() throws SQLException {

        TournamentController TC = new TournamentController(App.connection);

        testGameController = new GameController(App.connection);

        testHometeam = TeamController.createTeam("team Home", "Berlin", "west germany", "Angela Merkel", "234 567 8910");
        testAwayTeam = TeamController.createTeam("team Away", "London", "Brixton", "Margret Thatcher", "234 567 5555");
        masterTournament = TC.createTournament("MasterTournament");

        game = testGameController.createGame(testHometeam,testAwayTeam,new Date(),testLocation,masterTournament);
    }


    @Test
    public void testStandingsEntryWinsQuery(){


        game.setWinners(1);

        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertTrue(entry.getWins() == 1);

    }

    @Test
    public void testStandingsEntryLossesQuery(){

        Game game = testGameController.createGame(testHometeam,testAwayTeam,new Date(),testLocation,masterTournament);
        game.setWinners(-1);

        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertTrue(entry.getLosses() == 1);

    }

    @Test
    public void testStandingsEntryTiesQuery(){

        Game game = testGameController.createGame(testHometeam,testAwayTeam,new Date(),testLocation,masterTournament);
        game.setWinners(0);

        StandingsEntry entry = new StandingsEntry(testHometeam);
        assertTrue(entry.getTies() == 1);

    }
}
