package controllers;

import com.j256.ormlite.support.ConnectionSource;
import models.Game;
import models.Team;
import models.Tournament;

import java.util.ArrayList;

public class TournamentController {
    private Tournament CurrentTournament;
    ConnectionSource connection;

    public TournamentController(ConnectionSource dbConn){
        connection = dbConn;
    }

    public Tournament Tournament(String name)
    {
        Tournament tournament = new Tournament();
        tournament.setTournamentName(name);
        tournament.setSchedule(new ArrayList<Game>());
        tournament.setTeamList(new ArrayList<Team>());
        return tournament;
    }

    public Tournament getTournament(){
        return CurrentTournament;
    }

    public ArrayList<Game> getSchedule(){
        return CurrentTournament.getSchedule();
    }

    public void changeTournament(Tournament tournament) {
        CurrentTournament = tournament;
    }
}
