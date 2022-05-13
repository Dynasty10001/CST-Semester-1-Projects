package com.cosacpmg;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private String repeatA(int count){
        return new String(new char[count]).replace('\0','A');
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
    public void testPlayerFirstName()
    {
        assertTrue(emptyPlayer.firstName.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.firstName.length()>64);
        assertTrue(edgePlayer.firstName.length()<=64 && edgePlayer.firstName.length()>0);
    }

    @Test
    public void testPlayerLastName()
    {
        assertTrue(emptyPlayer.lastName.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.lastName.length()>64);
        assertTrue(edgePlayer.lastName.length()<=64 && edgePlayer.lastName.length()>0);
    }

    @Test
    public void testPlayerJerseyNumber()
    {
        assertTrue(emptyPlayer.jerseyNo == -1);
        assertTrue(invalidPlayer.jerseyNo == 100);
        assertTrue(edgePlayer.jerseyNo == 99);
    }

    @Test
    public void testPlayerPosition()
    {
        assertTrue(emptyPlayer.position.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.position.length()>64);
        assertTrue(edgePlayer.position.equalsIgnoreCase("Mid-Field"));
    }

    @Test
    public void testPlayerEmail()
    {
        assertTrue(emptyPlayer.email.equalsIgnoreCase(""));
        assertTrue(!(invalidPlayer.email.contains("@")&& invalidPlayer.email.contains(".")));
        assertTrue(edgePlayer.email.contains("@") && edgePlayer.email.contains("."));
    }

    @Test
    public void testPlayerPhoneNumber()
    {
        assertTrue(emptyPlayer.phoneNumber.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.phoneNumber.length()!=10);
        assertTrue(edgePlayer.phoneNumber.length()==10);
    }

    @Test
    public void testPlayerEmergencyContactName()
    {
        assertTrue(emptyPlayer.emergencyName.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.emergencyName.length()>64);
        assertTrue(edgePlayer.emergencyName.length()<=64 && edgePlayer.emergencyName.length()>0);
    }

    @Test
    public void testPlayerEmergencyContactPhoneNumber()
    {
        assertTrue(emptyPlayer.emergencyPhoneNumber.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.emergencyPhoneNumber.length()!=10);
        assertTrue(edgePlayer.emergencyPhoneNumber.length()==10);
    }

    @Test
    public void testPlayerEmergencyEmail()
    {
        assertTrue(emptyPlayer.emergencyEmail.equalsIgnoreCase(""));
        assertTrue(!(invalidPlayer.emergencyEmail.contains("@")&& invalidPlayer.emergencyEmail.contains(".")));
        assertTrue(edgePlayer.emergencyEmail.contains("@") && edgePlayer.emergencyEmail.contains("."));
    }

    @Test
    public void testPlayerStreetAddress()
    {
        assertTrue(emptyPlayer.streetAddress.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.streetAddress.length()>64);
        assertTrue(edgePlayer.streetAddress.length()<=64 && edgePlayer.streetAddress.length()>0);
    }

    @Test
    public void testPlayerCity()
    {
        assertTrue(emptyPlayer.city.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.city.length()>64);
        assertTrue(edgePlayer.city.length()<=64 && edgePlayer.city.length()>0);
    }

    @Test
    public void testPlayerProvince()
    {
        assertTrue(emptyPlayer.province.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.province.equalsIgnoreCase("Saskatchatoon"));
        assertTrue(edgePlayer.province.equalsIgnoreCase("Saskatchewan"));
    }

    @Test
    public void testPlayerPostaCode()
    {
        assertTrue(emptyPlayer.province.equalsIgnoreCase(""));
        assertTrue(invalidPlayer.postalCode.length() != 6);
        assertTrue(edgePlayer.postalCode.length() == 6);
    }

}
