package com.cosacpmg;

import controllers.TeamController;
import models.Team;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class TeamTest
{
    Team compareTeam;
    String teamName = "Sparks";
    String city = "Saskatoon";
    String area = "Brighton";
    String coachName = "John C. Coachington";
    String coachNum = "306 555 6356";
    TeamController teamController;

    // Validation Variables
    private static ValidatorFactory validationFactory;
    private static Validator validator;

    /**
     * This method will set up the validator factory and the validator before running any ofthe tests.
     */
    @BeforeClass
    public static void setupValidator()
    {
        validationFactory = Validation.buildDefaultValidatorFactory();
        validator = validationFactory.getValidator();
    }

    /**
     * This method will close the validator factory after all tests have been run
     */
    @AfterClass
    public static void tearDownValididator()
    {
        validationFactory.close();
    }

    /**
     * This method creates a valid team for testing purposes.
     */
    @Before
    public void createCompatibleValidTeam()
    {
        teamController = new TeamController();
        compareTeam = teamController.createTeam(teamName, city, area, coachName, coachNum);
    }

    /**
     * Purpose:
     * Test if a valid team is created successfully.
     */
    @Test
    public void testThatTeamIsCreated()
    {
        assertEquals(teamController.createTeam(teamName, city, area, coachName, coachNum), compareTeam);
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Team name.
     */
    @Test
    public void testThatInvalidTeamWasNotCreated()
    {
        assertNull(teamController.createTeam("", city, area, coachName, coachNum));
        assertNull(teamController.createTeam(repeatM(65),
                city, area, coachName, coachNum));//x x 65

    }

    /**
     * Purpose:
     * Test if a team was created with an invalid City name.
     */
    @Test
    public void testThatInvalidCityTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, "", area, coachName, coachNum));
        assertNull(teamController.createTeam(teamName, repeatM(65),
                area, coachName, coachNum));
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Area name.
     */
    @Test
    public void testThatInvalidAreaTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, "", coachName, coachNum));
        assertNull(teamController.createTeam(teamName, city,
                repeatM(65), coachName, coachNum));
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Coach name.
     */
    @Test
    public void testThatInvalidCoachNameTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, area, "", coachNum));
        assertNull(teamController.createTeam(teamName, city, area,
                repeatM(65), coachNum));
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Coach number.
     */
    @Test
    public void testThatInvalidCoachNumTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, area, coachName, ""));
//        assertNull(teamController.createTeam(teamName, city, area, coachName, ));
    }
    
    /**
     * Purpose:
     * Test if a team was created with a correct team name that matches the team name pattern.
     */
    @Test
    public void testThatCoachNumberIsValid()
    {
        compareTeam.setCoachNumber("123-123-2134");
        
        assertInvalidTeam("team",
                          "Validation Error: Coach phone number must in the following format: xxx xxx xxxx",
                          "123-123-2134");
    }

    /**
     * Purpose:
     * Test if a team was created with a correct team name that matches the team name pattern.
     */
    @Test
    public void testThatTeamNameIsValid()
    {
        compareTeam.setTeamName("Asdw as w asdw &");

        assertInvalidTeam("teamName",
                "Validation Error: Team name must not contain special characters (except ! and _)" +
                        " and must start and end with a letter", "Asdw as w asdw &");
    }


    /**
     * Purpose:
     * Test is for edge case for the team name during team creation
     */
    @Test
    public void testThatTeamNameIsLessThanOrEqual64()
    {
        compareTeam.setTeamName(repeatM(64));
        assertEquals(0, validator.validate(compareTeam).size());
    }
    
    /**
     * Purpose
     * Test is for edge case that Team name is too long as its above 64 characters
     */
    @Test
    public void testThatTeamNameLengthIsInvalid()
    {
        compareTeam.setTeamName(repeatM(65));
    
        assertInvalidTeam("teamName",
                          "Validation Error: Team name has to be 64 characters or less"
                                        , repeatM(65));
    }
    
    
    /**
     * Purpose:
     * Test is for edge case for the City during team creation
     */
    @Test
    public void testThatCityNameIsLessThanOrEqual64()
    {
        compareTeam.setCity(repeatM(64));
        assertEquals(0, validator.validate(compareTeam).size());
    }
    
    /**
     * Purpose
     * Test is for edge case that City name is too long as its above 64 characters
     */
    @Test
    public void testThatCityLengthIsInvalid()
    {
        compareTeam.setCity(repeatM(65));
        
        assertInvalidTeam("city",
                          "Validation Error: City has to be 64 characters or less"
                , repeatM(65));
    }
    
    
    /**
     * Purpose:
     * Test is for edge case for the team name during team creation
     */
    @Test
    public void testThatAreaIsLessThanOrEqual64()
    {
        compareTeam.setArea(repeatM(64));
        assertEquals(0, validator.validate(compareTeam).size());
    }
    
    /**
     * Purpose
     * Test is for edge case that Team name is too long as its above 64 characters
     */
    @Test
    public void testThatCityNameLengthIsInvalid()
    {
        compareTeam.setCity(repeatM(65));
        
        assertInvalidTeam("city",
                          "Validation Error: City has to be 64 characters or less"
                , repeatM(65));
    }
    
    
    /**
     * Purpose:
     * Test is for edge case for the team name during team creation
     */
    @Test
    public void testThatCoachNameIsLessThanOrEqual64()
    {
        compareTeam.setCoachName(repeatM(64));
        assertEquals(0, validator.validate(compareTeam).size());
    }
    
    /**
     * Purpose
     * Test is for edge case that Team name is too long as its above 64 characters
     */
    @Test
    public void testThatCoachNameNameLengthIsInvalid()
    {
        compareTeam.setCoachName(repeatM(65));
        
        assertInvalidTeam("coachName",
                          "Validation Error: Coach name has to be 64 characters or less"
                , repeatM(65));
    }
    
    /**
     * Helper tests length
     * @param length inclusive
     * @param testString
     * @return
     */
    public static boolean testMaxLength(int length, String testString)
    {
        return testString.length() <= length;
    
    }

    /**
     * This helper method will return a string of "M"s that is 65 characters long.
     * Used for form validation
     * @param count
     * @return
     */
    private String repeatM(int count){
        return new String(new char[count]).replace('\0','M');
    }
    
    
    /**
     * This method was sources from the sample project.
     * This method will check the validation on the current team (compareTeam) and checks what validation error message
     * is associated with the error.
     * @param expectedProperty
     * @param expectedErrMsg
     * @param expectedValue
     */
    private void assertInvalidTeam(String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Team>> constraintViolations = validator.validate( compareTeam ); //use the private global car created in setUpValidCar

        //count how many violations - SHOULD ONLY BE 1
        assertEquals( 1, constraintViolations.size() );

        //get first violation from constraintViolations collection
        ConstraintViolation<Team> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        //ensure error message matches what is expected
        assertEquals( expectedErrMsg, violation.getMessage() );

        //ensure the invalid value is what was set
        assertEquals( expectedValue, violation.getInvalidValue() );
    }
    
    

}
