package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import controllers.PlayerController;
import controllers.TeamController;
import models.Player;
import models.Team;
import org.junit.*;
import views.RosterPopup;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class RosterTest {

    RosterPopup testView;
    public Team testTeam;
    public Player validPlayer1, validPlayer2, validPlayer3, validPlayer4, validPlayer5, validPlayer6, validPlayer7, validPlayer8;
    Player[] PlayerList;
    PlayerController PC;
    TeamController TC;

    /**
     * Purpose:
     * This will set up the Players and the List of them, allowing the test methods to run
     */
    @Before
    public void testSetup()
    {
        testView = new RosterPopup();

        try {
            PC = new PlayerController(new JdbcPooledConnectionSource(App.CONNECTION_STRING));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        testView.pc = PC;
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

        PlayerList = new Player[] {validPlayer1, validPlayer2, validPlayer3,validPlayer4,validPlayer5,validPlayer6,validPlayer7,validPlayer8};

        try {
            TC = new TeamController(new JdbcPooledConnectionSource(App.CONNECTION_STRING));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TC.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 8; i++){
            PC.addPlayer(PlayerList[i]);
            PC.addPlayerToTeam(PlayerList[i],testTeam);
        }

        testView.MakePlayerList(PC);

    }

    @After
    public void onTearDown(){
        for (int i = 0; i<8;i++) {
            try {
                PC.removePlayer(PlayerList[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        Player returnedPlayer = null;

        try {
            returnedPlayer = PC.repo.queryForEq("playerID", validPlayer1.getPlayerId()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        validPlayer1.setAssignPosition("Forward");
        PC.updatePlayer(validPlayer1);

        assertEquals("Forward",validPlayer1.getAssignPosition());

        testView.removePlayerPosition(validPlayer1);
        assertEquals("Substitution",validPlayer1.getAssignPosition());

        Player returnedPlayer = null;

        try {
            returnedPlayer = PC.repo.queryForEq("playerID", validPlayer1.getPlayerId()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (returnedPlayer != null){
            assertEquals(returnedPlayer.getAssignPosition(),validPlayer1.getAssignPosition());
        }
        else {
            fail();
        }

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


        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

        validPlayer1 = PC.getPlayer(validPlayer1);
        validPlayer7 = PC.getPlayer(validPlayer7);
        validPlayer5 = PC.getPlayer(validPlayer5);
        validPlayer3 = PC.getPlayer(validPlayer3);

        assertEquals("Substitution", validPlayer1.getAssignPosition());
        assertEquals("Substitution", validPlayer7.getAssignPosition());
        assertEquals("Substitution", validPlayer3.getAssignPosition());
        assertEquals("Substitution", validPlayer5.getAssignPosition());
    }

    /**
     * Purpose:
     * This will test if the number validator will activate for an incorrect number of GoalTender position on a team
     */
    @Test
    public void playerPositionNumberValidatorGoalTender()
    {
        validPlayer1.setAssignPosition("GoalTender");
        PC.updatePlayer(validPlayer1);

        validPlayer7.setAssignPosition("GoalTender");
        PC.updatePlayer(validPlayer7);

        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

        validPlayer1 = PC.getPlayer(validPlayer1);
        validPlayer7 = PC.getPlayer(validPlayer7);
        assertEquals("Substitution", validPlayer1.getAssignPosition());
        assertEquals("Substitution", validPlayer7.getAssignPosition());
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
        testView.positionNumberValidator();

        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

        validPlayer1 = PC.getPlayer(validPlayer1);
        validPlayer7 = PC.getPlayer(validPlayer7);
        validPlayer6 = PC.getPlayer(validPlayer6);

        assertEquals("Substitution", validPlayer1.getAssignPosition());
        assertEquals("Substitution", validPlayer7.getAssignPosition());
        assertEquals("Substitution", validPlayer6.getAssignPosition());

        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

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
        testView.positionNumberValidator();

        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

        validPlayer1 = PC.getPlayer(validPlayer1);
        validPlayer7 = PC.getPlayer(validPlayer7);

        assertTrue(validPlayer1.getAssignPosition().equals("Substitution"));
        assertTrue(validPlayer7.getAssignPosition().equals("Substitution"));
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
        validPlayer7.setAssignPosition("GoalTender");
        validPlayer8.setAssignPosition("Substitution");

        testView.positionNumberValidator();

        testView.MakePlayerList(PC);
        testView.positionNumberValidator();

        assertTrue(validPlayer8.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer1.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer2.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer3.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer4.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer5.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer6.getAssignPosition().equals("Substitution"));
        assertFalse(validPlayer7.getAssignPosition().equals("Substitution"));
    }


}
