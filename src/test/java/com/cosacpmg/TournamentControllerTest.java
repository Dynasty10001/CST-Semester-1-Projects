package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import controllers.TournamentController;

import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

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
        masterTournament = testTournamentController.createTournament("MasterTournament", date, date);
        testTournamentController.addTournament(masterTournament);
    }

    /*
    we are testing valid tournament creation
     */
    @Test
    public void createValidTournament()
    {
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        date = time.getTime();

        assertNotNull( testTournamentController.createTournament("Test2", new Date(), new Date()));
    }

    /*
     we are testing invalid tournament creation
   */
    @Test
    public void createInvalidTournament()
    {
        assertNull(testTournamentController.createTournament("MasterTournament", date, date));
    }

    /*
     we are testing adding valid tournament
   */

    @Test
    public void addValidTournament()
    {
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        date = time.getTime();
        Tournament tourneyGood = testTournamentController.createTournament("Good Torunety", date, date);

        assertNotEquals( testTournamentController.addTournament(tourneyGood).getTournamentID(),0);
    }
    /*
    we are testing adding invalid tournament
  */
    @Test
    public void addInvalidTournament()
    {

        assertEquals( testTournamentController.addTournament(testTournamentController.createTournament("Test3", null, new Date())).getTournamentID(),0);
    }









}
