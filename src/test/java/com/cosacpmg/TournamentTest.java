package com.cosacpmg;

import models.Game;
import models.Team;
import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TournamentTest
{
    //private static final String CONNECT_STRING = "jdbc:sqlight:schedule.db";

    private static ValidatorFactory Vf;
    private static Validator validator;
    private static Tournament validTournament;

    private String repeatA(int count){
        return new String(new char[count]).replace('\0','A');
    }


    private void assertInvalidTournament(String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Tournament>> constraintViolations = validator.validate(validTournament);//use the private global car created in setUpValidCar

        //count how many violations - SHOULD ONLY BE 1
        assertEquals( 1, constraintViolations.size() );

        //get first violation from constraintViolations collection
        ConstraintViolation<Tournament> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        //ensure error message matches what is expected
        assertEquals( expectedErrMsg, violation.getMessage() );

        //ensure the invalid value is what was set
        assertEquals( expectedValue, violation.getInvalidValue() );
    }

    @BeforeClass
    public static void SetupValidator() throws SQLException
    {
        Vf = Validation.buildDefaultValidatorFactory();
        validator = Vf.getValidator();
    }

    @AfterClass
    public static void tearDownValidator()
    {
        //gracefully teardown the validator factory
        Vf.close();
    }


    @Before
    public void TestSetup()
    {
        Date date= new Date();
        date.setTime(date.getTime()+7000);
        validTournament = new Tournament();
        validTournament.setTournamentName("Test");
        validTournament.setStartDate(date);
        date.setTime(date.getTime()+3*24*60*60*10000);
        validTournament.setEndDate(date);
    }

    @Test
    public void testTournamentNameLengthInvalid()
    {
        String validMax=repeatA(65);
        validTournament.setTournamentName(validMax);
        assertInvalidTournament("tournamentName",
                "Validation Error: Tournament name has to be 64 characters or less",
                validMax);
    }

    @Test
    public void testTournamentNameLengthLessThanOrEqual64()
    {
        validTournament.setTournamentName(repeatA(64));
        assertEquals(0,validator.validate(validTournament).size());

        validTournament.setTournamentName(repeatA(35));
        assertEquals(0,validator.validate(validTournament).size());
    }

    @Test
    public void testTournamentNameNotEmpty()
    {
        validTournament.setTournamentName("");
        assertInvalidTournament("tournamentName",
                "Validation Error: Tournament name field is empty, please enter valid entry",
                "");
    }



    @Test
    public void testStartDateNull()
    {
        validTournament.setStartDate(null);
        assertInvalidTournament("startDate",
                "A date must be selected for the game",
                null);
    }

    @Test
    public void testEndDateNull()
    {
        validTournament.setEndDate(null);
        assertInvalidTournament("endDate",
                "A date must be selected for the game",
                null);
    }

    @Test
    public void testStartDatePast()
    {
        Date date = new Date();
        date.setTime(date.getTime()-1000*60*60*48);
        validTournament.setStartDate(date);
        assertInvalidTournament("startDate",
                "Date must not be in the past",
                date);
    }

    @Test
    public void testEndDatePast()
    {
        Date date = new Date();
        date.setTime(date.getTime()-1000*60*60*48);
        validTournament.setEndDate(date);
        assertInvalidTournament("endDate",
                "Date must not be in the past",
                date);
    }

    @Test
    public void testStartDateFuture()
    {
        Date date = new Date();
        date.setTime(date.getTime()+1000*60*60*48);
        validTournament.setStartDate(date);
        assertEquals(0,validator.validate(validTournament).size());
    }

    @Test
    public void testEndDateFuture()
    {
        Date date = new Date();
        date.setTime(date.getTime()+1000*60*60*48);
        validTournament.setEndDate(date);
        assertEquals(0,validator.validate(validTournament).size());
    }

    @Test
    public void testStartDateAfterEndDate()
    {
        Date date = new Date();
        date.setTime(date.getTime()+1000*60*60*48*7);
        validTournament.setStartDate(date);
        Date date2 = new Date();
        date2.setTime(date2.getTime()*1000*60*60*48*2);
        validTournament.setEndDate(date2);
        assertInvalidTournament("endDate",
                "End date must be after the start date",
                date);
    }









}
