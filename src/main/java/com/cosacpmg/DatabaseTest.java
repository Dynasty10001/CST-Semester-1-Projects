package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Field;
import models.Team;
import models.Tournament;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class DatabaseTest {
    private static final String CONNECT_STRING = "jdbc:sqlite:schedule.db";
    public static void main(String[] args){
        ConnectionSource dbConn = null;
        try {
            dbConn = new JdbcPooledConnectionSource(CONNECT_STRING);

            TournamentController TUC = new TournamentController(dbConn);
            GameController GC = new GameController(dbConn);
            TeamController TC = new TeamController(dbConn);
            TUC.Tournament("ThisOtherTournament");
            Team one = TC.team();
            Team two = TC.team();
            Calendar time;
            time = Calendar.getInstance();
            time.set(2022, Calendar.JUNE,10);
            Date date = time.getTime();
            GC.Game(one,two,date,new Field(),TUC.getTournament());



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
