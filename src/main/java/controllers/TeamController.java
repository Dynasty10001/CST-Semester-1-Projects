package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Game;
import models.Team;
import models.Tournament;
import models.ValidationHelper;

import java.sql.SQLException;
import java.util.List;

public class TeamController {

    public Dao<Team, Long> repo;
    private ValidationHelper vh = new ValidationHelper();

    public TeamController(ConnectionSource dbConn) {
        try {
            this.repo = DaoManager.createDao(dbConn, Team.class);
            repo.setAutoCommit(dbConn.getReadWriteConnection("Team"), true);
            //ensure table exist
            TableUtils.createTableIfNotExists(dbConn, Team.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Team team() throws SQLException {
        Team myTeam = new Team();
        repo.create(myTeam);
        return myTeam;
    }

    public Team createTeam(String saskatoon, String sparks, String brighton, String jack, String s) throws SQLException {
        Team myTeam = new Team();
        repo.create(myTeam);
        return myTeam;
    }

    public List<Team> getRoster() throws SQLException {
        QueryBuilder<Team, Long> scheduleQuery = repo.queryBuilder();
        List<Team> roster = repo.query(scheduleQuery.prepare());
        return roster;
    }
}
