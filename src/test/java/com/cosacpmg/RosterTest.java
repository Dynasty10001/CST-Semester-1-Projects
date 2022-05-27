package com.cosacpmg;

import controllers.PlayerController;
import controllers.TeamController;
import models.Player;
import models.Team;
import org.junit.*;
import views.RosterView;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;


public class RosterTest {

    RosterView testView;
    public Team testTeam;
    public Player validPlayer1, validPlayer2, validPlayer3, validPlayer4, validPlayer5, validPlayer6, validPlayer7, validPlayer8;
    Player[] PlayerList;
    PlayerController PC;

    /**
     * Purpose:
     * This test will fuck you levi
     */
    @Before
    public void testSetup()
    {
        testView = new RosterView();
         testTeam = TeamController.createTeam(
                "TheHeffingtonHeffs",
                "Saskatoon",
                "brighton",
                "Coach Coachington",
                "555 555 1234");

        validPlayer1 = PlayerController.createPlayer(
                "Heff",
                "Heffington",
                10,
                "Forward",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer2 = PlayerController.createPlayer(
                "John",
                "Heffington",
                15,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer3 = PlayerController.createPlayer(
                "Ethan",
                "Heffington",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer4 = PlayerController.createPlayer(
                "Levi",
                "Heffington",
                13,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer5 = PlayerController.createPlayer(
                "Zach",
                "Heffington",
                99,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer6 = PlayerController.createPlayer(
                "Ernesto",
                "Heffington",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer7 = PlayerController.createPlayer(
                "NoYou",
                "Heffington",
                69,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer8 = PlayerController.createPlayer(
                "Bob",
                "Heffington",
                17,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        PC = new PlayerController(App.connection);
        PlayerList = new Player[] {validPlayer1, validPlayer2, validPlayer3,validPlayer4,validPlayer5,validPlayer6,validPlayer7,validPlayer8};

        for (int i = 0; i < 8; i++){
            PC.addPlayerToTeam(PlayerList[i],testTeam);
            PC.addPlayer(PlayerList[i]);
        }
    }

    @After
    public void onTearDown(){
        for (int i = 0; i<8;i++) {
            PC.removePlayer(PlayerList[i]);
        }
    }



    /**
     * Purpose:
     * This will test if a player was assigned a position successfully.
     */
    @Test
    public void playerGivenNewPosition()
    {
        testView.givePlayerPosition(validPlayer1, "Forward");
        assertSame("Forward", validPlayer1.getAssignPosition());
        Player returnedPlayer = PC.updatePlayer(validPlayer1);
        if (returnedPlayer != null){
            assertEquals(returnedPlayer.getAssignPosition(),validPlayer1.getAssignPosition());
        }
        else {
            fail();
        }
    }

    /**
     * Purpose:
     * This will test if a players position was removed successfully.
     */
    @Test
    public void playerPositionRemoved()
    {
        fail();
    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of midfield position on a team
     */
    @Test
    public void playerPositionNumberValidatorMidField()
    {
        validPlayer1.setAssignPosition("Midfield");
        validPlayer7.setAssignPosition("Midfield");
        validPlayer3.setAssignPosition("Midfield");
        validPlayer5.setAssignPosition("Midfield");
        testView.PositionNumberValidator();
        assertSame("Substitution", validPlayer1.getAssignPosition());
        assertSame("Substitution", validPlayer7.getAssignPosition());
        assertSame("Substitution", validPlayer3.getAssignPosition());
        assertSame("Substitution", validPlayer5.getAssignPosition());
    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Goalie position on a team
     */
    @Test
    public void playerPositionNumberValidatorGoalie()
    {
        validPlayer1.setAssignPosition("Goalie");
        validPlayer7.setAssignPosition("Goalie");
        testView.PositionNumberValidator();
        assertSame("Substitution", validPlayer1.getAssignPosition());
        assertSame("Substitution", validPlayer7.getAssignPosition());
    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Defence position on a team
     */
    @Test
    public void playerPositionNumberValidatorDefence()
    {
        validPlayer1.setAssignPosition("Defence");
        validPlayer6.setAssignPosition("Defence");
        validPlayer7.setAssignPosition("Defence");
        testView.PositionNumberValidator();
        assertSame("Substitution", validPlayer1.getAssignPosition());
        assertSame("Substitution", validPlayer7.getAssignPosition());
        assertSame("Substitution", validPlayer6.getAssignPosition());
    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Forward position on a team
     */
    @Test
    public void playerPositionNumberValidatorForward()
    {
        validPlayer1.setAssignPosition("Forward");
        validPlayer7.setAssignPosition("Forward");
        testView.PositionNumberValidator();
        assertSame("Substitution", validPlayer1.getAssignPosition());
        assertSame("Substitution", validPlayer7.getAssignPosition());
    }

    /**
     * Purpose;
     * This will test if a team has the correct number of positions.
     * The validator should not throw an exception and the plays should maintain their positions.
     */
    @Test
    public void playerPositionNumberValidatorCorrectNumberOfPositions()
    {
        validPlayer1.setAssignPosition("Forward");
        validPlayer2.setAssignPosition("Defence");
        validPlayer3.setAssignPosition("Defence");
        validPlayer4.setAssignPosition("Midfield");
        validPlayer5.setAssignPosition("Midfield");
        validPlayer6.setAssignPosition("Midfield");
        validPlayer7.setAssignPosition("Goalie");
        validPlayer8.setAssignPosition("Substitution");
    }


}
