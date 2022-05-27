package com.cosacpmg;

import com.j256.ormlite.jdbc.*;
import controllers.PlayerController;
import models.Player;
import models.Team;
import org.junit.*;


import java.sql.SQLException;

import static org.junit.Assert.*;

public class PlayerControllerTest {

    private static PlayerController PC;

    private static Player player;
    
    /**
     * Test settup
     */
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

        new App().startDB();//Jank, but just for testing

        PC = new PlayerController(App.connection);
        PC.addPlayer(player);
    }
    
    
    /**
     * Test that player is added proppery
     */
    @Test
    public void testThatPlayerIsAddedToTeam()
    {
        Team team = new Team();
        PC.addPlayerToTeam(player, team);
        assertEquals(team, player.getTeam());
    }
    
    /**
     * Test that player is removed properly
     */
    @Test
    public void testThatPlayerIsRemovedFromTeam()
    {
        Team team = new Team();
        PC.addPlayerToTeam(player, team);
        PC.removePlayerFromTeam(player);
        assertEquals(null, player.getTeam());
    }
    
    /**
     * Test to see that player is updated correctly
     */
    @Test public void testThatPlayerIsUpdated()
    {
        String newName = "newFirstName";
        player.setFirstName(newName);
        assertEquals(PC.updatePlayer(player).getFirstName(), newName);
    }
    
    
    /**
     * Test that palyer cannot be updated with invalid field
     */
    @Test
    public void testThatPlayerIsNotUpdatedWithInvalidName()
    {
//        String oldName = player.getFirstName();
        player.setFirstName(null);
        assertEquals(PC.updatePlayer(player).getFirstName(), "Heff");
    }
    
}
