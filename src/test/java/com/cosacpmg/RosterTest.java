package com.cosacpmg;

import controllers.PlayerController;
import controllers.TeamController;
import models.Player;
import models.Team;
import org.junit.*;
import static org.junit.Assert.*;


public class RosterTest {

    public Team testTeam;
    public Player validPlayer1, validPlayer2, validPlayer3, validPlayer4, validPlayer5, validPlayer6, validPlayer7, validPlayer8;

    /**
     * Purpose:
     * This test will fuck you levi
     */
    @Before
    public void testSetup()
    {
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
    }



    /**
     * Purpose:
     * This will test if a player was assigned a position successfully.
     */
    @Test
    public void playerGivenNewPosition()
    {
        assertFalse(true);
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

    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Goalie position on a team
     */
    @Test
    public void playerPositionNumberValidatorGoalie()
    {

    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Defence position on a team
     */
    @Test
    public void playerPositionNumberValidatorDefence()
    {

    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of Forward position on a team
     */
    @Test
    public void playerPositionNumberValidatorForward()
    {

    }

    /**
     * Purpose;
     * This will test if a team has the correct number of positions.
     * The validator should not throw an exception and the plays should maintain their positions.
     */
    @Test
    public void playerPositionNumberValidatorCorrectNumberOfPositions()
    {

    }


}
