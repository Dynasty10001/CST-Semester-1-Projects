package com.cosacpmg;

import com.j256.ormlite.dao.*;
import com.j256.ormlite.support.*;
import com.j256.ormlite.table.*;
import java.sql.SQLException;
import java.util.*;

public class PlayerController {

    public Dao<Player, Long> repo;
    private ValidationHelper vh = new ValidationHelper();

    public PlayerController(ConnectionSource dbConn) {
        try {
            this.repo = DaoManager.createDao(dbConn, Player.class);
            repo.setAutoCommit(dbConn.getReadWriteConnection("Player"), true);
            //ensure table exist
            TableUtils.createTableIfNotExists(dbConn, Player.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




    public Player getPlayer()
    {
        Player returnedPlayer = null;

        return returnedPlayer;
    }

    public Player addPlayer(Player player) throws SQLException {
        Player returnedPlayer = player;
        this.repo.create(returnedPlayer);

        return returnedPlayer;
    }

    public Player editplayer()
    {
        Player returnedPlayer = null;

        return returnedPlayer;
    }

}
