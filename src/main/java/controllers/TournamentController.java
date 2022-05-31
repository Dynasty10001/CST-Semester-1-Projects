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
    private static Tournament CurrentTournament;


    public TournamentController(ConnectionSource dbConn) throws SQLException {

        this.repo = DaoManager.createDao(dbConn,Tournament.class);
        repo.setAutoCommit(dbConn.getReadWriteConnection("Game"), true);
        //ensure table exist
        TableUtils.createTableIfNotExists(dbConn, Tournament.class);
        CurrentTournament = repo.queryForFirst(repo.queryBuilder().prepare());

    }

    //Creates a new tournament
    public Tournament createTournament(String name) throws SQLException
    {
        Tournament myTournament = new Tournament();
        myTournament.setTournamentName(name);
        changeTournament(myTournament);
        myTournament.setTeamList(new ArrayList<Team>());
        return myTournament;
    }
    
    /**
     * This method takes in a tournament and adds it to the database, returning the Tournament
     * @param tourney
     * @return
     * @throws SQLException
     */
    public Tournament addTournament(Tournament tourney) throws SQLException
    {
        try
        {
            repo.create(tourney);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tourney;
    }
    
    /**
     * This method  returns the current tournament
     * @return the current selected tournament
     */
    public Tournament getTournament(){
        return CurrentTournament;
    }
    
    
    /**
     * this method changes the current Tournament to the supplied tournament
     * @param tournament new tournament to be the setTournament
     */
    public void changeTournament(Tournament tournament) {
        CurrentTournament = tournament;
    }

}
