package com.cosacpmg;


import org.junit.*;

import javax.validation.*;

import java.util.Set;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player validPlayer;

    private static ValidatorFactory vf;
    private static Validator validator;

    private String repeatA(int count){
        return new String(new char[count]).replace('\0','A');
    }

    private void assertInvalidPlayer(String expectedProperty, String expectedErrMsg, Object expectedValue){
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(validPlayer);

        assertEquals( 1, constraintViolations.size() );

        ConstraintViolation<Player> violation = constraintViolations.iterator().next();

        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        assertEquals( expectedErrMsg, violation.getMessage() );

        assertEquals( expectedValue, violation.getInvalidValue() );
    }

    @BeforeClass
    public static void setUpValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void tearDownValidator()
    {
        //gracefully teardown the validator factory
        vf.close();
    }


    @Before
    public void setUpValidPlayer()
    {
        Player validPlayer= new Player();
        validPlayer.setFirstName ("Heff");
        validPlayer.setLastName("Heffington");
        validPlayer.setJerseyNo(10);
        validPlayer.setPosition("Forward");
        validPlayer.setEmail("Heff1234@gmail.com");
        validPlayer.setPhoneNumber("3061234567");
        validPlayer.setEmergencyName("Mother Heffington");
        validPlayer.setEmergencyPhoneNumber("3061234567");
        validPlayer.setEmergencyEmail("MotherHeffington@gmail.com");
        validPlayer.setStreetAddress("123 Fake Street");
        validPlayer.setCity("Saskatoon");
        validPlayer.setProvince("Saskatchewan");
        validPlayer.setPostalCode("S7V0A1");

    }


    @Test
    public void testPlayerFirstNameEmpty()
    {
        validPlayer.setFirstName("");
        assertInvalidPlayer("firstName", "All fields must be filled out with valid information", "");
    }

    @Test
    public void testPlayerFirstNameTooLong()
    {
        String invalid = repeatA(65);

        validPlayer.setFirstName(invalid);
        assertInvalidPlayer("firstName", "All fields have a max Character length of 64", invalid);
    }

    @Test
    public void testPlayerFirstNameEdgeCase()
    {
        validPlayer.setFirstName(repeatA(64));
        assertEquals (0, validator.validate(validPlayer ).size());
    }

    @Test
    public void testPlayerLastNameEmpty()
    {
        validPlayer.setLastName("");
        assertInvalidPlayer("lastName", "All fields must be filled out with valid information", "");
    }

    @Test
    public void testPlayerLastNameTooLong()
    {
        String invalid = repeatA(65);
        validPlayer.setLastName(invalid);
        assertInvalidPlayer("lastName", "All fields have a max Character length of 64", invalid);
    }

    @Test
    public void testPlayerLastNameEdgeCase()
    {
        validPlayer.setLastName(repeatA(64));
        assertEquals (0, validator.validate(validPlayer ).size());
    }
   

    @Test
    public void testPlayerValidJerseyNumber()
    {
        validPlayer.setJerseyNo(01);
        assertEquals (0, validator.validate(validPlayer ).size());
    }

    @Test
    public void testPlayerJerseyNumberOneDigit()
    {
       validPlayer.setJerseyNo(1);
        assertInvalidPlayer("jerseyNo", "Jersey Number Must have 2 digits", 1);
    }

    @Test
    public void testPlayerJerseyNumberThreeDigits()
    {
        validPlayer.setJerseyNo(100);
        assertInvalidPlayer("jerseyNo", "Jersey Number Must be an Integer between 01 and 99", 100);
    }

    @Test
    public void testPlayerJerseyNumberEmpty()
    {
        validPlayer.setJerseyNo(-1);
        assertInvalidPlayer("jerseyNo", "All fields must be filled out with valid information", -1);
    }

    @Test
    public void testPlayerPositionValid()
    {
    validPlayer.setPosition("MidField");
        assertEquals (0, validator.validate(validPlayer ).size());
    }
    @Test
    public void testPlayerPositionEmpty()
    {
        validPlayer.setPosition("");
        assertInvalidPlayer("position", "All fields must be filled out with valid information", "");
    }

    @Test
    public void testPlayerEmailEmpty()
    {
        String invalid= "";
        validPlayer.setEmail(invalid);
        assertInvalidPlayer("email", "All fields must be filled out with valid information", invalid);
    }

    @Test
    public void testPlayerEmailValid()
    {
        validPlayer.setEmail(repeatA(54) + "@gmail.com");
        assertEquals (0, validator.validate(validPlayer ).size());
    }

    @Test
    public void testPlayerEmailInvalidPattern()
    {
        String[] invalids= {"A&gmail.com", "A@gmail" };
        for ( String invalid: invalids)
        {
            validPlayer.setEmail(invalid);
            assertInvalidPlayer("email", "Invalid email format", invalid);
        }
    }

    @Test
    public void testPlayerEmailTooLong()
    {
        String invalid = repeatA(65);
        validPlayer.setEmail(invalid);
        assertInvalidPlayer("email", "All fields have a max Character length of 64", invalid);
    }

    @Test
    public void testPlayerPhoneNumberEmpty()
    {
        String invalid = "";
        validPlayer.setPhoneNumber(invalid);
        assertInvalidPlayer("phoneNumber", "All fields must be filled out with valid information", invalid);
    }

    @Test
    public void testPlayerValidPhoneNumber()
    {
        validPlayer.setPhoneNumber("3067159999");
        assertEquals (0, validator.validate(validPlayer ).size());
    }

    @Test
    public void testPlayerInvalidPhoneNumber()
    {
        String invalid = "306999999";
        validPlayer.setPhoneNumber(invalid);
        assertInvalidPlayer("phoneNumber", "Invalid Phone Number", invalid);
    }

    @Test
    public void testPlayerEmergencyContactNameEmpty()
    {
        String invalid = "";
        validPlayer.setEmergencyName(invalid);
        assertInvalidPlayer("emergencyName", "All fields must be filled out with valid information", invalid);
    }

    @Test
    public void testPlayerEmergencyContactNameEdgeCase()
    {
        validPlayer.setEmergencyName(repeatA(64));
        assertEquals (0, validator.validate(validPlayer ).size());
    }

    @Test
    public void testPlayerEmergencyContactNameTooLong()
    {
        String invalid=repeatA(65);
        validPlayer.setEmergencyName(invalid);
        assertInvalidPlayer("emergencyName", "All fields have a max Character length of 64", invalid);
    }

    @Test
    public void testPlayerEmergencyContactPhoneNumberEmpty()
    {
        String invalid = "";
        validPlayer.setEmergencyPhoneNumber(invalid);
        assertInvalidPlayer("emergencyPhoneNumber ", "All fields must be filled out with valid information", invalid);
    }

    @Test
    public void testPlayerEmergencyEmail()
    {

    }

    @Test
    public void testPlayerStreetAddress()
    {

    }

    @Test
    public void testPlayerCity()
    {

    }

    @Test
    public void testPlayerProvince()
    {

    }

    @Test
    public void testPlayerPostaCode()
    {

    }

}
