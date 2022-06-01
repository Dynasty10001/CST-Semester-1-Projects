package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
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


    /**
     * This method starts up the game controller and connects it to the database
     * @param dbConn
     */
    
    
    
    public static final int POINTS_FOR_WIN = 3;
    public static final int POINTS_FOR_LOSS = 0;
    public static final int POINTS_FOR_TIE = 1;


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

    /**
     * This method handles creating a game object, setting all the values, and returns the game object.
     * @param homeTeam
     * @param awayTeam
     * @param startTime
     * @param location
     * @param tournament
     * @return
     */
    public Game createGame(Team homeTeam, Team awayTeam, Date startTime, String location, Tournament tournament) {


        Game game = new Game();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setLocation(location);
        game.setStartTime(startTime);
        game.setWinners(null);
        game.setTournament(tournament);

        return game;
    }

    /**
     * This method adds a game to the database.
     * @param game
     * @return
     */
    public Game addGame(Game game){
        try
        {
            repo.create(game);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return game;
    }

    /**
     * Unused method for future application
     * @param game
     * @param gameEvents
     */
    public void setGameEvents(Game game,ArrayList<GameEvent> gameEvents)
    {
        game.setGameEvents(gameEvents);
    }

    /**
     * Unused method for future application
     * @param game
     * @param gameEvent
     */
    public void recordGameEvent(Game game, GameEvent gameEvent){
        game.getGameEvents().add(gameEvent);
    }

    /**
     * This method returns a List of games in a tournament queried from the database.
     * @param tournament
     * @return
     * @throws SQLException
     */
    public List<Game> getSchedule(Tournament tournament) throws SQLException {
        List<Game> schedule = repo.queryForAll();
        return schedule;
    }


    public boolean roundRobinValidator(Game game) throws SQLException
    {
        if(game.getAwayTeam().getId() == game.getHomeTeam().getId())
        {
            return false;
        }
        List<Game> awayGames = repo.queryForEq("awayTeam_id", game.getAwayTeam().getId());
        List<Game> homeGames = repo.queryForEq("homeTeam_id", game.getAwayTeam().getId());

        if (awayGames.stream().filter(g->g.getHomeTeam().getId()==game.getHomeTeam().getId())
                .count()>0)
        {
            return false;
        }

        if (homeGames.stream().filter(g->g.getAwayTeam().getId()==game.getHomeTeam().getId())
                .count()>0)
        {
            return false;
        }

        return true;
    }

    /**
     * This method will check if a given team is already playing in a game at a given time and if there is already a team playing
     * on a given at the given time.
     * @param game
     * @return
     * @throws SQLException
     */
    public boolean spaceTimeValidator(Game game) throws SQLException
    {
        List<Game> awayGames = repo.query(repo.queryBuilder()
                .where()
                .eq("homeTeam_id",game.getAwayTeam().getId())
                .or()
                .eq("awayTeam_id",game.getAwayTeam().getId())
                .prepare());
        if ( awayGames.stream()
                .filter(g->g.getStartTime().getTime()<(game.getStartTime().getTime()+2*60*60*1000)
                        && (g.getStartTime().getTime()+2*60*60*1000)>game.getStartTime().getTime())
                .count()>0)
        {
            return false;
        }

        List<Game> homeGames = repo.query(repo.queryBuilder()
                .where()
                .eq("homeTeam_id",game.getHomeTeam().getId())
                .or()
                .eq("awayTeam_id",game.getHomeTeam().getId())
                .prepare());
        if ( homeGames.stream()
                .filter(g->g.getStartTime().getTime()<(game.getStartTime().getTime()+2*60*60*1000)
                        && (g.getStartTime().getTime()+2*60*60*1000)>game.getStartTime().getTime())
                .count()>0)
        {
            return false;
        }
        return true;
    }

    public static int computeScore(int win, int loss, int tie){

        return win * POINTS_FOR_WIN + tie * POINTS_FOR_TIE + loss * POINTS_FOR_LOSS;
    }


    public int queryWins(Team team) {
        ArrayList<Game> games = getGamesWithTeam(team);


        return (int) games.stream().filter(e-> e.getWinner() != null && e.getWinner().getId()==team.getId() && e.isPlayed()).count();

    }

    public int queryLosses(Team team) {
        ArrayList<Game> games = getGamesWithTeam(team);

        return (int) games.stream().filter(e-> e.getWinner() != null && e.getWinner().getId() != team.getId() && e.getWinner() != null && e.isPlayed()).count();
    }

    public int queryTies(Team team) {
        ArrayList<Game> games = getGamesWithTeam(team);

        return (int) games.stream().filter(e-> e.getWinner()==null && e.isPlayed()).count();
    }

    public ArrayList<Game> getGamesWithTeam(Team team)
    {
        try {
            return (ArrayList<Game>) repo.query(repo.queryBuilder()
                    .where()
                    .eq("homeTeam_id",team)
                    .or()
                    .eq("awayTeam_id",team)
                    .prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Removes the game from the game
     * @param game
     */
    public void removeGame(Game game)
    {
        try
        {
            repo.delete(game);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * updates the supplied game in the db
     * @param game
     */
    public void update(Game game)
    {
        try
        {
            repo.update(game);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
