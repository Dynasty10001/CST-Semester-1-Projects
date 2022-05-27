package controllers;

import com.j256.ormlite.dao.*;
import com.j256.ormlite.support.*;
import com.j256.ormlite.table.*;
import models.Player;
import models.Team;
import models.ValidationHelper;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    public Dao<Player, Long> repo;
    private ValidationHelper vh = new ValidationHelper();
    private static final String CONNECT_STRING = "jdbc:sqlite:schedule.db";
    
    /**
     * Contructor that also sets up the connection for the db
     * @param dbConn connection object pass it the connection from App.Connection
     */
    public PlayerController(ConnectionSource dbConn) {
        try {
            this.repo = DaoManager.createDao(dbConn, Player.class);
            repo.setAutoCommit(dbConn.getReadWriteConnection("Player"), true);
            //ensure table exist
            TableUtils.createTableIfNotExists(dbConn, Player.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * Stub Method
     * @return
     */
    public Player getPlayer()
    {
        Player returnedPlayer = null;

        return returnedPlayer;
    }
    
    /**
     * Returns all players that match the supplied player object, null fields are not searched
     * @param player search Player object
     * @return arraylist of players that matched
     */
    public ArrayList<Player>  getAllPlayersMatch(Player player)
    {
    
        try
        {
            List queryList = repo.queryForMatching(player);
            if (queryList.size() > 0)
            {
                return (ArrayList<Player>)queryList;
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Returns an arraylist of all players in the database
     * @return arraylist of all players
     */
    public ArrayList<Player> getAllPlayer()
    {
        try
        {
            return (ArrayList<Player>) repo.queryForAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Constuctor method that returns a filled player usign the supplied parameters
     * @param First firstname
     * @param Last last name
     * @param Jersey jersay number
     * @param Pos position
     * @param Email Email
     * @param Phone phone
     * @param EName Emergency name
     * @param EPhone Emergency Phone number
     * @param EEmail emergency Email
     * @param Street street of player
     * @param City City of player
     * @param Prov province of player
     * @param Postal postal code of player
     * @return
     */
    public static Player createPlayer(String First, String Last, int Jersey, String Pos, String Email, String Phone, String EName, String EPhone, String EEmail, String Street, String City, String Prov, String Postal)
    {
        Player player = new Player();
        player.setFirstName(First);
        player.setLastName(Last);
        player.setJerseyNo(Jersey);
        player.setPosition(Pos);
        player.setEmail(Email);
        player.setPhoneNumber(Phone);
        player.setEmergencyName(EName);
        player.setEmergencyPhoneNumber(EPhone);
        player.setEmergencyEmail(EEmail);
        player.setStreetAddress(Street);
        player.setCity(City);
        player.setProvince(Prov);
        player.setPostalCode(Postal);

        return player;
    }
    
    /**
     * This method adds the supplie dplayer to the database, it will return the player, or return null if an error is
     * encountered
     * @param player the player to be added to the db
     * @return
     */
    public Player addPlayer(Player player) {
        Player returnedPlayer = player;
        try {
            this.repo.create(returnedPlayer);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return returnedPlayer;
    }
    
    /**
     * This method updates the player in the Player Databaes using the supplied player variable, It will return the
     * player that is currently within the database, if it failed to write the player object returned will have the
     * old values (the ones still in the db)
     * If it fails to write and fails to read it will return null
     * @param player player to be updated with the changed fields
     * @return the player that is within he db
     */
    public Player updatePlayer(Player player)
    {

        try {
            this.repo.update(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return repo.queryForEq("playerID", player.getPlayerId()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * A passthrough method that calls the update player, as long the player's team is unnassigned (null)
     * @param player player to change team
     * @param team team object
     * @return the player with the changed value or unchanged if it fails
     */
    public Player addPlayerToTeam(Player player, Team team) {
        if (player.getTeam() == null)
        {
            player.setTeam(team);
            updatePlayer(player);
        }

        return player;
    }
    
    /**
     * Removes the player from the team, setting it to null
     * @param player player to change
     * @return the player with its null'd team
     */
    public Player removePlayerFromTeam(Player player) {

        player.setTeam(null);

        updatePlayer(player);

        return player;
    }
    
    /**
     * this method querries the database using the getAllPlayersMatch method and seraches by the teamId provided
     * @param team
     * @return
     */
    public ArrayList<Player> queryForPlayersOnTeam(Team team)
    {
        
        
        try {
            if (team == null)
            {
                return (ArrayList<Player>) repo.queryBuilder().where().isNull("team_id").query();
            }
            ArrayList<Player> returnList = (ArrayList<Player>) repo.queryForEq("team_id", team);
            return returnList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
