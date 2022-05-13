package models;

import java.util.ArrayList;

public class Tournament
{
    private String tournamentName;
    protected ArrayList<Game> schedule;
    protected ArrayList<Team> teamList;

    public Tournament()
    {
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public ArrayList<Game> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Game> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }
}
