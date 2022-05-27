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




    public Player getPlayer()
    {
        Player returnedPlayer = null;

        return returnedPlayer;
    }
    
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

    public Player addPlayer(Player player) {
        Player returnedPlayer = player;
        try {
            this.repo.create(returnedPlayer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnedPlayer;
    }

    //todo write test for this
    public Player updatePlayer(Player player)
    {

        try {
            this.repo.update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            return repo.queryForEq("playerID", player.getPlayerId()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Player addPlayerToTeam(Player player, Team team) {
        if (player.getTeam() == null)
        {
            player.setTeam(team);
            updatePlayer(player);
        }

        return player;
    }

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
