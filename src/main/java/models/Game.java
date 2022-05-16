package models;

import java.util.ArrayList;
import java.util.Date;

public class Game {

    private Team homeTeam, awayTeam;
    private int winners; // 1 is Home, -1 is Away, 0 is a tie game
    private Date startTime;
    private Field location;
    private ArrayList<Event> gameEvents;
    private Tournament tournament;

    public Game()
    {
    }

    public Team getHomeTeam()
    {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam()
    {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public int getWinners()
    {
        //gameEvents.get()
        return winners;
    }

    public void setWinners(int winners)
    {
        this.winners = winners;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Field getLocation()
    {
        return location;
    }

    public void setLocation(Field location)
    {
        this.location = location;
    }

    public ArrayList<Event> getGameEvents()
    {
        return gameEvents;
    }

    public void setGameEvents(ArrayList<Event> gameEvents)
    {
        this.gameEvents = gameEvents;
    }

    public Tournament getTournament()
    {
        return tournament;
    }

    public void setTournament(Tournament tournament)
    {
        this.tournament = tournament;
    }
}


