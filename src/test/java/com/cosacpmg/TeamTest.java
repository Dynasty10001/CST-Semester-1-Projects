package com.cosacpmg;

import controllers.TeamController;
import models.Team;
import org.junit.Before;
import org.junit.Test;
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
    
    
    @Before
    public void createCompatibleValidTeam()
    {
        teamController = new TeamController();
        compareTeam = teamController.createTeam(teamName, city, area, coachName, coachNum);
    }
    
    @Test
    public void testThatTeamIsCreated()
    {
        assertEquals(teamController.createTeam(teamName, city, area, coachName, coachNum), compareTeam);
    }
    
    @Test
    public void testThatInvalidTeamWasNotCreated()
    {
        assertNull(teamController.createTeam("", city, area, coachName, coachNum));
        assertNull(teamController.createTeam("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
                city, area, coachName, coachNum));//x x 65

    }

    @Test
    public void testThatInvalidCityTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, "", area, coachName, coachNum));
        assertNull(teamController.createTeam(teamName, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
                area, coachName, coachNum));
    }

    @Test
    public void testThatInvalidAreaTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, "", coachName, coachNum));
        assertNull(teamController.createTeam(teamName, city,
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", coachName, coachNum));
    }

    @Test
    public void testThatInvalidCoachNameTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, area, "", coachNum));
        assertNull(teamController.createTeam(teamName, city, area,
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", coachNum));
    }

    @Test
    public void testThatInvalidCoachNumTeamNotCreated()
    {
        assertNull(teamController.createTeam(teamName, city, area, coachName, ""));
        assertNull(teamController.createTeam(teamName, city, area, coachName,
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
    }

    
    
    @Test
    public void testThatTeamNameIsValid()
    {
        String pattern = "^[\\w!'-]*[\\w\\s'-]*[\\w!'-]*$";
        String teamNameToCompare = compareTeam.getTeamName();
        assertTrue("Matches pattern???", teamNameToCompare.matches(pattern) );
    }
    
    
    @Test
    public void testThatTeamNameIsLessThanOrEqual64()
    {
        assertTrue(testMaxLength(64, compareTeam.getTeamName()));
    }
    
    @Test
    public void testThatCityIsLessThanOrEqual64()
    {
        assertTrue(testMaxLength(64, compareTeam.getCity()));
    }
    
    @Test
    public void testThatAreaIsLessThanOrEqual64()
    {
        assertTrue(testMaxLength(64, compareTeam.getArea()));
    }
    
    @Test
    public void testThatCoachNameIsLessThanOrEqual64()
    {
        assertTrue(testMaxLength(64, compareTeam.getCoachName()));
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
    
    
    
    
    

}
