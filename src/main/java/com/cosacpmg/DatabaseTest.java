package com.cosacpmg;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class DatabaseTest {
    private static final String CONNECT_STRING = "jdbc:sqlite:schedule.db";
    public static void main(String[] args){
        ConnectionSource dbConn = null;
        try {
            dbConn = new JdbcPooledConnectionSource(CONNECT_STRING);

            PlayerController PC = new PlayerController(dbConn);
            TeamController TC = new TeamController(dbConn);
            Team one = TC.team();
            Calendar time;
            time = Calendar.getInstance();
            time.set(2022, Calendar.JUNE,10);
            Date date = time.getTime();
            PC.addPlayer();



        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }
}
