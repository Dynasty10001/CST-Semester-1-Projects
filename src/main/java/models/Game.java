package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import controllers.GameController;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@DatabaseTable(tableName = "schedule")
public class Game {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true)
    private long gameID;

    //@Column(nullable = false)
    @DatabaseField(foreign = true,canBeNull = false,foreignAutoCreate = false,foreignAutoRefresh = true )
    @NotEmpty(message = "Game must have a Home Team" )
    private Team homeTeam;

    //@Column(nullable = false)
    @DatabaseField(foreign = true,canBeNull = false,foreignAutoCreate = false,foreignAutoRefresh = true )
    @NotEmpty(message = "Game must have an Away Team" )
    private Team awayTeam;

    //@Column(nullable = true)
    @DatabaseField(canBeNull = true)
    @Max(value = 1,message = "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played")
    @Min(value = -1 , message = "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played")
    private Integer winners; // 1 is Home, -1 is Away, 0 is a tie game, null = Game not played yet

    //@Column(nullable = false)
    @DatabaseField(canBeNull = false)
    @Future
    @NotEmpty(message = "A date must be selected for the game")
    private Date startTime;

    @DatabaseField(foreign = true)
    @NotEmpty(message = "Game must have a location that it is played at")
    private Field location;

    private ArrayList<GameEvent> gameEvents;

    @DatabaseField(foreign = true, canBeNull = false)
    @NotEmpty(message = "Game must be contained inside a tournament")
    private Tournament tournament;


    public Game()
    {
    }

    public long getGameID(){
        return this.gameID;
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

    public void setWinners(Integer winners)
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


