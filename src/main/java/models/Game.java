package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@DatabaseTable(tableName = "games")
public class Game {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true)
    private long gameID;

    //@Column(nullable = false)
    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "Game must have a Home Team" )
    private Team homeTeam;

    //@Column(nullable = false)
    @DatabaseField(foreign = true,canBeNull = false )
    @NotEmpty(message = "Game must have an Away Team" )
    private Team awayTeam;

    //@Column(nullable = true)
    @DatabaseField(canBeNull = true)
    private int winners; // 1 is Home, -1 is Away, 0 is a tie game
    private Date startTime;
    private Field location;
    private ArrayList<GameEvent> gameEvents;
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

    public ArrayList<GameEvent> getGameEvents()
    {
        return gameEvents;
    }

    public void setGameEvents(ArrayList<GameEvent> gameEvents)
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


