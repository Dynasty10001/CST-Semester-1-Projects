package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import models.custom.constraints.StartEndDate;

import javax.validation.constraints.*;

import java.util.*;

@DatabaseTable(tableName = "Tournaments")
@StartEndDate
public class Tournament
{
    @DatabaseField(generatedId = true)
    private long tournamentID;

    @DatabaseField(canBeNull = false, unique = true)
    @Size(max = 64, message = "Validation Error: Tournament name has to be 64 characters or less")
    @NotEmpty(message = "Validation Error: Tournament name field is empty, please enter valid entry")
    //@UniqueElements(message = "Validation Error: Tournament name is already used, please enter a different name")
    private String tournamentName;


    @ForeignCollectionField(eager = true)
    protected ForeignCollection<Game> games;


    @DatabaseField(canBeNull = false)
    @NotNull(message = "A date must be selected for the Tournament")
    @Future(message = "Date must not be in the past")
    private  Date startDate;

    @DatabaseField(canBeNull = false)
    @NotNull(message = "A date must be selected for the Tournament")
    @Future(message = "Date must not be in the past")
    private Date endDate;


    public ForeignCollection<Game> getGames() {
        return games;
    }

    public void setGames(ForeignCollection<Game> games) {
        this.games = games;
    }

    public Tournament()
    {

    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getTournamentID() {
        return tournamentID;
    }

    @Override
    public String toString()
    {
        return tournamentName;
    }


}

