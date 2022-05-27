package controllers;

import com.j256.ormlite.dao.*;
import com.j256.ormlite.support.*;
import com.j256.ormlite.table.*;
import models.Player;
import models.Position;
import models.ValidationHelper;

import java.sql.SQLException;

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

    public static Player createPlayer(String First, String Last, int Jersey, String Pos, String Email, String Phone, String EName, String EPhone, String EEmail, String Street, String City, String Prov, String Postal)
    {
        Player player = new Player();
        player.setFirstName (First);
        player.setLastName(Last);
        player.setJerseyNo(Jersey);
        player.setPosition(Pos);
        player.setAssignPosition("Substitution");
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

    public Player addPlayer(Player player) throws SQLException {
        Player returnedPlayer = player;
        this.repo.create(returnedPlayer);

        return returnedPlayer;
    }

    public Player editplayer()
    {
        Player returnedPlayer = null;

        return returnedPlayer;
    }

}
