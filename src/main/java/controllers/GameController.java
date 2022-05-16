package controllers;

import models.*;

import java.util.ArrayList;
import java.util.Date;

public class GameController
{
    public static Game Game(Team homeTeam,Team awayTeam, Date startTime, Field location)
    {
        Game game = new Game();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setLocation(location);
        game.setStartTime(startTime);
        return game;
    }

    public void setGameEvents(Game game,ArrayList<Event> gameEvents)
    {
        game.setGameEvents(gameEvents);
    }

    public void recordGameEvent(Game game, Event gameEvent){ game.getGameEvents().add(gameEvent);}

    public boolean setTournament(Game game,Tournament tournament)
    {
        game.setTournament(tournament);
        if (tournament.addGameToSchedule(game))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
