package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GameController
{

    private Dao<Game, Long> repo;
    private ValidationHelper vh = new ValidationHelper();

    public GameController(ConnectionSource dbConn){
        try {
            this.repo = DaoManager.createDao(dbConn,Game.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static Game Game(Team homeTeam,Team awayTeam, Date startTime, Field location)
    {
        Game game = new Game();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setLocation(location);
        game.setStartTime(startTime);
        return game;
    }

    public void setGameEvents(Game game,ArrayList<GameEvent> gameEvents)
    {
        game.setGameEvents(gameEvents);
    }

    public void recordGameEvent(Game game, GameEvent gameEvent){
        game.getGameEvents().add(gameEvent);
    }

    public boolean setTournament(Game game)
    {
        return false;
    }

    private boolean RoundRobinValidator() {
        return false;
    }

    private boolean SpaceTimeValidator() {
        return false;
    }
}
