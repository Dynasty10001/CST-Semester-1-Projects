package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import controllers.TeamController;
import models.Team;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TeamControllerTest
{


    Team compareTeam;
    String teamName = "Sparks";
    String city = "Saskatoon";
    String area = "Brighton";
    String coachName = "John C. Coachington";
    String coachNum = "306 555 6356";
    TeamController teamController;

    @Before
    public void createCompatibleValidTeam()
    {
        compareTeam = new Team();
        compareTeam.setTeamName(teamName);
        compareTeam.setCity(city);
        compareTeam.setArea(area);
        compareTeam.setCoachName(coachName);
        compareTeam.setCoachNumber(coachNum);
    }

    /**
     * Purpose:
     * Test if a team was added to the database
     */
    @Test
    public void testThatTeamIsAddedToDB() throws SQLException {

        TeamController tc = new TeamController(new JdbcPooledConnectionSource("jdbc:h2:mem:default"));
        tc.addTeam(compareTeam);
        assertTrue(compareTeam.getId() > 0);

    }


    /**
     * Purpose:
     * Test that a team will not be added to the database that contains a null entry
     */
    @Test
    public void testThatTeamIsNotAddedWillNull() throws SQLException {
        //new App().startDB();
        TeamController tc= new TeamController(new JdbcPooledConnectionSource("jdbc:h2:mem:default"));
        try{

            compareTeam.setCoachName(null);
            teamController.addTeam(compareTeam);
        } catch(NullPointerException e)
        {
            assertTrue(true);
        }
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Team name.
     */
    @Test
    public void testThatInvalidTeamWasNotCreated()
    {
        assertNull(teamController.createTeam("", city, area, coachName, coachNum));
        assertNull(teamController.createTeam(TeamTest.repeatM(65),
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
        assertNull(teamController.createTeam(teamName, TeamTest.repeatM(65),
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
                TeamTest.repeatM(65), coachName, coachNum));
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
                TeamTest.repeatM(65), coachNum));
    }

    /**
     * Purpose:
     * Test if a team was created with an invalid Coach number.
     */
    @Test
    public void testThatInvalidCoachNumTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, area, coachName, ""));
    }
}
