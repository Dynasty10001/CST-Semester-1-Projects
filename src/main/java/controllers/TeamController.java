package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Game;
import models.Team;
import models.ValidationHelper;

import java.sql.SQLException;

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

    public Team team() {
        Team team = new Team();
        return team;
    }
}
