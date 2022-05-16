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
        validPlayer.setFirstName(repeatA(65));
        assertInvalidPlayer("firstName", "All fields have a max Character length of 64", "");
    }

    @Test
    public void testPlayerLastNameEmpty()
    {
        validPlayer.setLastName("");
        assertInvalidPlayer("firstName", "All fields must be filled out with valid information", "");
    }

    @Test
    public void testPlayerLastNameTooLong()
    {
        validPlayer.setLastName(repeatA(65));
        assertInvalidPlayer("firstName", "All fields have a max Character length of 64", "");
    }

   

    @Test
    public void testPlayerJerseyNumber()
    {

    }

    @Test
    public void testPlayerPosition()
    {

    }

    @Test
    public void testPlayerEmail()
    {

    }

    @Test
    public void testPlayerPhoneNumber()
    {

    }

    @Test
    public void testPlayerEmergencyContactName()
    {

    }

    @Test
    public void testPlayerEmergencyContactPhoneNumber()
    {

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
