package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;
import models.Tournament;
import models.ValidationHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TournamentController
{
//	Tournament currentTournament;

    public Dao<Tournament, Long> repo;


    public TournamentController(ConnectionSource dbConn) throws SQLException {

        this.repo = DaoManager.createDao(dbConn,Tournament.class);
        repo.setAutoCommit(dbConn.getReadWriteConnection("Tournament"), true);
        //ensure table exists
        TableUtils.createTableIfNotExists(dbConn, Tournament.class);
//        CurrentTournament = repo.queryForFirst(repo.queryBuilder().prepare());
    }

    //Creates a new tournament
    public Tournament createTournament(String name, Date startDate, Date endDate)
    {
        ValidationHelper vh = new ValidationHelper();

        Tournament myTournament = new Tournament();
        myTournament.setTournamentName(name);
        myTournament.setStartDate(startDate);
        myTournament.setEndDate(endDate);
        if(vh.getErrors(myTournament).isEmpty()){
            return myTournament;
        }
        else {
            return null;
        }
    }

    /**
     * This method takes in a tournament and adds it to the database, returning the Tournament
     * @param tourney
     * @return
     * @throws SQLException
     */
    public Tournament addTournament(Tournament tourney)
    {


        try
        {
//            if(tournamentNameUnique(tourney))
            {
                repo.create(tourney);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tourney;


    }


    /**
     * This method  returns all tournaments
     * @return the current selected tournament
     */

    public List<Tournament> getAllTournaments()
    {
        try
        {

            return repo.queryForAll();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //todo Add to view

    /**
     * This method with ensure that the tournament namet
     * @param tourney
     * @return
     * @throws SQLException
     */
    public boolean tournamentNameUnique (Tournament tourney) throws SQLException
    {
        List <Tournament> tournaments = repo.query(repo.queryBuilder()
                .where()
                .eq("tournamentName", tourney.getTournamentName())
                .prepare()
        );

        return tournaments.stream()
                .count() <= 0;


    }



}
