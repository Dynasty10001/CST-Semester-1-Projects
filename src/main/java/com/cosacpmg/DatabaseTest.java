package com.cosacpmg;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controller.PlayerController;

import java.sql.SQLException;

public class DatabaseTest {
    private static final String CONNECT_STRING = "jdbc:sqlite:schedule.db";
    public static void main(String[] args){
        ConnectionSource dbConn = null;
        try {
            dbConn = new JdbcPooledConnectionSource(CONNECT_STRING);

            PlayerController PC = new PlayerController(dbConn);
            TeamController TC = new TeamController(dbConn);
            Team one = TC.team();
//            PC.;



        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }
}
