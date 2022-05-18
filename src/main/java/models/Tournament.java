package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@DatabaseTable(tableName = "Tournaments")
public class Tournament
{
    @DatabaseField(generatedId = true)
    private int tournamentID;

    @DatabaseField(canBeNull = false)
    private String tournamentName;

    @ForeignCollectionField(eager = true)
    protected ForeignCollection<Game> schedule;

    protected ArrayList<Team> teamList;


    public Tournament()
    {
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public ArrayList<Game> getSchedule() {
        //return (ArrayList) Arrays.asList(schedule.toArray());
        return null;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }


}
