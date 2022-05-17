package com.cosacpmg;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

@DatabaseTable
public class Team
{
    @DatabaseField(generatedId = true)
    private long TeamID;
}

public class TeamController
{
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

}
