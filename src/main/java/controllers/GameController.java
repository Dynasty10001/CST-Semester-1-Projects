package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameController
{

    public Dao<Game, Long> repo;
    private ValidationHelper vh = new ValidationHelper();

    public GameController(ConnectionSource dbConn){
        try {
            this.repo = DaoManager.createDao(dbConn,Game.class);
            repo.setAutoCommit(dbConn.getReadWriteConnection("Game"), true);
            //ensure table exist
            TableUtils.createTableIfNotExists(dbConn, Game.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Game createGame(Team homeTeam, Team awayTeam, Date startTime, Field location, Tournament tournament) throws SQLException {
 //todo pass in entire game, rename this to add game, put game constructor into AddGameView
        Game game = new Game();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setLocation(location);
        game.setStartTime(startTime);
        game.setWinners(null);
        game.setTournament(tournament);
        repo.create(game);
        return game;
    }

    public boolean addGameToSchedule(TournamentController TC,Game game){
        //game.setTournament(TC.getTournament());
        return false;
    }

    public void setGameEvents(Game game,ArrayList<GameEvent> gameEvents)
    {
        game.setGameEvents(gameEvents);
    }

    public void recordGameEvent(Game game, GameEvent gameEvent){
        game.getGameEvents().add(gameEvent);
    }

    public List<Game> getSchedule(Tournament tournament) throws SQLException {
        QueryBuilder<Game, Long> scheduleQuery = repo.queryBuilder();
        List<Game> schedule = repo.query(scheduleQuery.where().eq("tournament_id",tournament.getTournamentID()).prepare());
        return schedule;
    }

    private boolean roundRobinValidator() {
        return false;
    }

    public boolean spaceTimeValidator() {
        return false;
    }
}
