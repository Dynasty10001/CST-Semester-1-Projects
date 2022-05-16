package com.cosacpmg;

import controllers.GameController;
import models.Game;
import models.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class GameTest
{

    Game masterTest = new Game();
    @Test
    public void twoObjectsAreTheSame()
    {

    }

    @Test
    public void UserCreatesNewGame()
    {
        Game testGame = GameController.Game();
        Calendar time;
        time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        testGame.setStartTime(time.getTime());
        Assert.assertNotNull(testGame);
    }

    @Test
    public void UserCancelsCreatingGame()
    {
        fail();
    }

    @Test
    public void CreateGameSetInPast()
    {
        Calendar past = Calendar.getInstance();
        past.set(2002, Calendar.JUNE,10);
        masterTest.setStartTime(past.getTime());
        // Create Contoller method that return if time is valid and then change it
        //assertTrue();
        fail();
    }

    @Test
    public void CreateGameWithTeamAlreadyInGame()
    {
        Game ContainsTeamUsed = new Game();
        Team UsedTeam = new Team();
        ContainsTeamUsed.setHomeTeam(UsedTeam);

        Game NewGame = new Game();
        NewGame.setHomeTeam(UsedTeam);
        NewGame.setAwayTeam(new Team());
        fail(); // Needs an exception checker
    }

    @Test
    public void CreateGameOnUsedField()
    {
        fail();
    }
    //@Test
    public void CreateGameRoundRobinRescheduled()
    {
        fail();
    }
    //@Test
    public void CreateGameRoundRobinNotReschedule()
    {
        fail();
    }

}
