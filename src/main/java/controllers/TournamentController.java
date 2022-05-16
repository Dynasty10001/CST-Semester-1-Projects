package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Game;
import models.Team;
import models.Tournament;

import java.sql.SQLException;
import java.util.ArrayList;

public class TournamentController {

    public Dao<Tournament, Long> repo;
    private Tournament CurrentTournament;
    ConnectionSource connection;

    public TournamentController(ConnectionSource dbConn) throws SQLException {

        this.repo = DaoManager.createDao(dbConn,Tournament.class);
        repo.setAutoCommit(dbConn.getReadWriteConnection("Game"), true);
        //ensure table exist
        TableUtils.createTableIfNotExists(dbConn, Tournament.class);
    }

    public Tournament Tournament(String name) throws SQLException {
        Tournament tournament = new Tournament();
        tournament.setTournamentName(name);
        changeTournament(tournament);
        tournament.setTeamList(new ArrayList<Team>());
        repo.create(tournament);
        return tournament;
    }

    public Tournament getTournament(){
        return CurrentTournament;
    }

    public ArrayList<Game> getSchedule(){
        repo.queryBuilder();
        return CurrentTournament.getSchedule();
    }

    public void changeTournament(Tournament tournament) {
        CurrentTournament = tournament;
    }
}
