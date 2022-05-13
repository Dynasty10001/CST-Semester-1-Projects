package com.cosacpmg;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest
{
    Team compareTeam;

    @Before
    public void createCompatableValidTeam()
    {
        String teamName = "Sparks";
        String city = "Saskatoon";
        String area = "Brighton";
        String coachName = "John C. Coachington";
        String coachNum = "306 555 6356";

        compareTeam = new Team(teamName, city, area, coachName, coachNum);
    }

    @Test
    public void testThatTeamNameIsValid()
    {
        String pattern = "^[\\w!'-]*[\\w\\s'-]*[\\w!'-]*$";

        String teamNameToCompare = compareTeam.getTeamName();

        //assertTrue("", );
    }

}
