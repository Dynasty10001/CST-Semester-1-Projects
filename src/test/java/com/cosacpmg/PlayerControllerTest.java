package com.cosacpmg;

import com.j256.ormlite.jdbc.*;
import controllers.PlayerController;
import models.Player;
import org.junit.*;


import java.sql.SQLException;

import static org.junit.Assert.*;

public class PlayerControllerTest {

    private static PlayerController PC;

    private static Player player;

    @Before
    public void setUp()
    {
        player = new Player();
        player.setFirstName ("Heff");
        player.setLastName("Heffington");
        player.setJerseyNo(10);
        player.setPosition("Forward");
        player.setEmail("Heff1234@gmail.com");
        player.setPhoneNumber("3061234567");
        player.setEmergencyName("Mother Heffington");
        player.setEmergencyPhoneNumber("3061234567");
        player.setEmergencyEmail("MotherHeffington@gmail.com");
        player.setStreetAddress("123 Fake Street");
        player.setCity("Saskatoon");
        player.setProvince("Saskatchewan");
        player.setPostalCode("S7V0A1");

        PC = new PlayerController(App.connection);
    }


    @Test
    public void testThatPlayerIsAddedToTeam()
    {
        PC.addPlayerToTeam(player, 1);
        assertEquals(1, player.getTeam());
    }

    @Test
    public void testThatPlayerIsRemovedFromTeam()
    {
        PC.addPlayerToTeam(player, 1);
        PC.removePlayerFromTeam(player);
        assertEquals(0, player.getTeam());
    }


}
