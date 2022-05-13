package com.cosacpmg;

import controllers.GameController;
import models.Game;
import models.Team;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {


    @Test
    public void twoObjectsAreTheSame()
    {
        Game Test0 = new Game();
        Game Test1 = GameController.Game();
        assertEquals(1,1);
        assertEquals(Test0,Test1);
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
