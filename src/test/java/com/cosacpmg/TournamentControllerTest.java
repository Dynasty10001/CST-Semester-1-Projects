package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import controllers.TournamentController;

import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class TournamentControllerTest {
    private static ValidatorFactory VF;
    private static Validator validator;

    Tournament masterTournament;

    private static TournamentController testTournamentController;

    private static ConnectionSource dbConn;

   
    Date date;

    @BeforeClass
    public static void SetupValidator() throws SQLException {
        VF = Validation.buildDefaultValidatorFactory();
        validator = VF.getValidator();

        dbConn = new JdbcPooledConnectionSource("jdbc:sqlite:schedule.db");
    }

    @AfterClass
    public static void tearDownValidator()
    {
        VF.close();
    }


    @Before
    public void TestSetup() throws SQLException {


        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        date = time.getTime();




        testTournamentController = new TournamentController(dbConn);

        TableUtils.clearTable(dbConn,Tournament.class);


        masterTournament = testTournamentController.createTournament("MasterTournament", new Date(), new Date());



        testTournamentController.addTournament(masterTournament);



    }

}
