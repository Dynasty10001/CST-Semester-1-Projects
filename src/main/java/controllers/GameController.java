package controllers;

import models.*;

import java.util.ArrayList;
import java.util.Date;

public class GameController
{
    public static Game Game()
    {
        Game game = new Game();

        return game;
    }

    public void setHomeTeam(Game game,Team homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Game game,Team awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public void setWinners(Game game,boolean winners)
    {
        this.winners = winners;
    }

    public void setStartTime(Game game,Date startTime)
    {
        this.startTime = startTime;
    }

    public void setLocation(Game game,Field location)
    {
        this.location = location;
    }

    public void setGameEvents(Game game,ArrayList<Event> gameEvents)
    {
        this.gameEvents = gameEvents;
    }

    public void setTournament(Game game,Tournament tournament)
    {
        this.tournament = tournament;
    }
}
