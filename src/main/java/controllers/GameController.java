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
        List<Game> schedule = repo.queryForAll();
        return schedule;
    }

    private boolean roundRobinValidator(Game game) throws SQLException
    {
        if(game.getAwayTeam().getId() == game.getHomeTeam().getId())
        {
            return false;
        }
        List<Game> awayGames = repo.queryForEq("awayTeam_id", game.getAwayTeam().getId());
        List<Game> homeGames = repo.queryForEq("homeTeam_id", game.getAwayTeam().getId());
//        awayGames.stream().forEach()
//        List<Game> schedules = repo.queryForAll();
//        for(int i = 0; i < schedules.size(); i++)
//        {
//            for(int j = i+1; j< schedules.size(); j++)
//            {
//                //Compare all 4 team combos
//                //if any of the teams of 2 games are the same true
//                if(schedules.get(i).getHomeTeam().equals(schedules.get(j).getHomeTeam()) ||
//                        schedules.get(i).getAwayTeam().equals(schedules.get(j).getAwayTeam()) ||
//                        schedules.get(i).getAwayTeam().equals(schedules.get(j).getHomeTeam()) ||
//                        schedules.get(i).getHomeTeam().equals(schedules.get(j).getAwayTeam()))
//                {
//                    //if Home team is the same team
//                    if(schedules.get(i).getHomeTeam().equals(schedules.get(j).getHomeTeam()) ||
//                            schedules.get(i).getHomeTeam().equals(schedules.get(j).getAwayTeam()))
//                    {
//                        if(schedules.get(i).getAwayTeam().equals(schedules.get(j).getHomeTeam()))
//                    }
//
//                }
//
//            }
//        }
        return true;
    }

    public boolean spaceTimeValidator() throws SQLException
    {
        List<Game> schedules = repo.queryForAll();
        for(int i = 0; i < schedules.size(); i++)
        {
            for(int j = i+1; j< schedules.size(); j++)
            {
                //Compare that each team is not playing a game at the same time in a different game
                if(schedules.get(i).getStartTime().equals(schedules.get(j).getStartTime()))
                {
                    return false;
                }

            }
        }

        return true;
    }
}
