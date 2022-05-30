package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;

@DatabaseTable(tableName = "schedule")
public class Game {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true)
    private long gameID;

    //@Column(nullable = false)
    @DatabaseField(foreign = true,canBeNull = false, foreignAutoRefresh = true)
    @NotNull(message = "Game must have a Home Team" )
    private Team homeTeam;

    //@Column(nullable = false)
    @DatabaseField(foreign = true,canBeNull = false, foreignAutoRefresh = true)
    @NotNull(message = "Game must have an Away Team" )
    private Team awayTeam;

    //@Column(nullable = true)
    @DatabaseField(canBeNull = true)
    @Max(value = 1,message = "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played")
    @Min(value = -1 , message = "Error with winner Field. 1 is Home Team wins, -1 is Away Team Wins, 0 is Tie, null is Game not Played")
    private Integer winners; // 1 is Home, -1 is Away, 0 is a tie game, null = Game not played yet
    
    @DatabaseField()
    private boolean played = false;

    //@Column(nullable = false)
    @DatabaseField(canBeNull = false)
    @NotNull(message = "A date must be selected for the game")
    @Future(message = "Date must not be in the past")
    private Date startTime;
    // TODO: 5/18/2022  for some reason, @Future doesnt seem to be working. Must Fix

    //@Column(nullable = false)
    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "Game must have a location that it is played at")
    private String location;


    private ArrayList<GameEvent> gameEvents;

    //@Column(nullable = false)
    @DatabaseField(foreign = true, canBeNull = false)
    @NotNull(message = "Game must be contained inside a tournament")
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
    
    /**
     * This will return the winner, as long as there  was a winner, if there was not a winner(tie) or if the game is
     * yet to be played it will return null
     * @return
     */
    public Team getWinner()
    {
        if(winners == null)
        {
            return null;
        }
        return winners==1? homeTeam: winners == -1? awayTeam: null;
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

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
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
    
    public boolean isPlayed()
    {
        return played;
    }
    
    public void setPlayed(boolean played)
    {
        this.played = played;
    }
    
    
    
}


