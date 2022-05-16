package com.cosacpmg;

import controllers.GameController;
import models.Game;
import models.Team;
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
        Game test0 = new Game();
        Game test1 = GameController.Game();
        assertEquals(1,1);
        assertEquals(test0,test1);
        test1.setStartTime(Calendar.getInstance().set(2002,5,10));
        assertNotEquals(test0,test1);
    }

    @Test
    public void UserCreatesNewGame()
    {

        fail();
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
        past.set(2002,5,10);
        assertFalse(masterTest.setStartTime(past.getTime()));
        // Create Contoller method that return if time is valid and then change it
//        fail();
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
