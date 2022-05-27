package com.cosacpmg;

import models.Game;
import models.Team;
import models.Tournament;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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


    private void assertInvalidGame(String expectedProperty, String expectedErrMsg, Object expectedValue){
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





}
