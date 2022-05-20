package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;
import models.Tournament;

import java.sql.SQLException;
import java.util.ArrayList;

public class TournamentController
{
//	Tournament currentTournament;

    public Dao<Tournament, Long> repo;
    private Tournament CurrentTournament;


    public TournamentController(ConnectionSource dbConn) throws SQLException {

        this.repo = DaoManager.createDao(dbConn,Tournament.class);
        repo.setAutoCommit(dbConn.getReadWriteConnection("Game"), true);
        //ensure table exist
        TableUtils.createTableIfNotExists(dbConn, Tournament.class);
        CurrentTournament = repo.queryForFirst(repo.queryBuilder().prepare());

    }

    public Tournament createTournament(String name) throws SQLException {
        Tournament myTournament = new Tournament();
        myTournament.setTournamentName(name);
        changeTournament(myTournament);
        myTournament.setTeamList(new ArrayList<Team>());
        repo.create(myTournament);
        return myTournament;
    }

    public Tournament getTournament(){
        return CurrentTournament;
    }

    public void changeTournament(Tournament tournament) {
        CurrentTournament = tournament;
    }

}