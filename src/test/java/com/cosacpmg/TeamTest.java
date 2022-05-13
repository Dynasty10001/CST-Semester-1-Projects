package com.cosacpmg;

import models.Team;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest
{
    Team compareTeam;

    @Before
    public void createCompatibleValidTeam()
    {
        String teamName = "Sparks";
        String city = "Saskatoon";
        String area = "Brighton";
        String coachName = "John C. Coachington";
        String coachNum = "306 555 6356";

        compareTeam = new Team();
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
     * helper tests length
     * @param length inclusive
     * @param testString
     * @return
     */
    public static boolean testMaxLength(int length, String testString)
    {
        return testString.length() <= length;
    
    }
    
    
    
    

}
