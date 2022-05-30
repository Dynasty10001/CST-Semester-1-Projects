package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;
import models.Tournament;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TournamentController
{
//	Tournament currentTournament;

    public Dao<Tournament, Long> repo;
    private static Tournament CurrentTournament;


    public TournamentController(ConnectionSource dbConn) throws SQLException {

        this.repo = DaoManager.createDao(dbConn,Tournament.class);
        repo.setAutoCommit(dbConn.getReadWriteConnection("Game"), true);
        //ensure table exist
        TableUtils.createTableIfNotExists(dbConn, Tournament.class);
        CurrentTournament = repo.queryForFirst(repo.queryBuilder().prepare());
    }

    //Creates a new tournament
    public Tournament createTournament(String name, Date startDate, Date endDate)
    {
        Tournament myTournament = new Tournament();
        myTournament.setTournamentName(name);
        myTournament.setStartDate(startDate);
        myTournament.setEndDate(endDate);


        return myTournament;
    }

    public Tournament addTournament(Tournament tourney)
    {
        /*
        try
        {
            repo.create(tourney);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tourney;

         */
        return null;
    }

    public List<Tournament> getAllTournaments(){

        return null;
    }

    public void updateTournament(Tournament tournament) {
        //CurrentTournament = tournament;
    }

}
