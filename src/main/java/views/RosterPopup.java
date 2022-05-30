package views;

import com.cosacpmg.App;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Player;
import models.Team;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;


public class RosterPopup {


	@FXML
	protected Label lblForward, lblDefence1, lblDefence2, lblMidfield1 ,lblMidfield2, lblMidfield3, lblGoalTender;
	
	@FXML
	protected ListView<Player> allPlayerList;
	@FXML
	protected ListView<Player> teamPlayerList;
	
	private static Team currentTeam = null;

	ArrayList<Player> playersOnTeam;

	public PlayerController pc;

	Player[] fieldPosArray = new Player[7];
	
	@FXML
	protected void initialize(){
//		System.out.println("Current team is " + currentTeam.getId());
		pc = new PlayerController(App.connection);
		initAllPlayerList(pc);
		initTeamPlayerList(pc);

		// initialize all labels
		lblForward = new Label();
		lblDefence1 = new Label();
		lblGoalTender = new Label();
		lblDefence2 = new Label();
		lblMidfield1 = new Label();
		lblMidfield2 = new Label();
		lblMidfield3 = new Label();

		
	}
	
	/**
	 * Updates the UI, mainly the Player Lists
	 */
	protected void updateUI() {
		PlayerController pc = new PlayerController(App.connection);
		initAllPlayerList(pc);
		initTeamPlayerList(pc);
	}
	
	
	/**
	 * Initializes the Team's Player List
	 * @param pc player controller
	 */
	protected void initTeamPlayerList(PlayerController pc)
	{
		MakePlayerList(pc);
		teamPlayerList.getItems().setAll(playersOnTeam);
	}

	public void MakePlayerList(PlayerController pc) {
		playersOnTeam = new ArrayList<>();
		playersOnTeam.addAll(pc.queryForPlayersOnTeam(currentTeam));
	}


	/**
	 * Initializes all player list
	 * @param pc player controller
	 */
	private void initAllPlayerList(PlayerController pc)
	{
		
		
		ArrayList<Player> allPlayertTemp = pc.queryForPlayersOnTeam(null);
		if (allPlayertTemp != null)
		{
			allPlayerList.getItems().setAll(allPlayertTemp);
		}
		
	}
	
	
	/**
	 * handler for the add player button
	 */
	@FXML
	protected void rosterAddPlayerHandler()
	{
		Player selectedPlayer = (Player) allPlayerList.getSelectionModel().getSelectedItems().get(0);
		PlayerController pc = new PlayerController(App.connection);
		if(selectedPlayer != null)
		{
			selectedPlayer.setTeam(currentTeam);
			pc.updatePlayer(selectedPlayer);
			updateUI();
			
		}
	}
	
	/**
	 * A setter that sets the currentTeam, used when the RosterPopup is created
	 * @param team currentTeam
	 */
	public static void setCurrentTeam(Team team)
	{
			currentTeam = team;
	}
	
	/**
	 * Hander for the remove player button
	 */
	@FXML
	protected void rosterRemovePlayerHandler() {
		Player selectedPlayer = (Player) teamPlayerList.getSelectionModel().getSelectedItems().get(0);
		PlayerController pc = new PlayerController(App.connection);
		
		if(selectedPlayer != null)
		{
			selectedPlayer.setTeam(null);
			pc.updatePlayer(selectedPlayer);
			updateUI();
		}
		//todo ErrorMessage for no selectedplayer
		
		
		
	}

	/**
	 * Purpose:
	 * this helper method will change a player's assigned position into the given position
	 * @param player
	 * @param position
	 */
	public void givePlayerPosition(Player player, String position)
	{
		player.setAssignPosition(position);
		pc.updatePlayer(player);
	}

	/**
	 * Purpose:
	 * This helper method will remove the assigned position from the player.
	 * @param player
	 */
	public void removePlayerPosition(Player player)
	{
		player.setAssignPosition("Substitution");
		pc.updatePlayer(player);
	}

	/**
	 * Purpose:
	 * this method will be the main method for dragging a player across the screen and into their positions
	 */
	public void playerDragger()
	{

	}

	/**
	 * Purpose:
	 * This method will protect against errors by making sure there can never be more than a set number of players in each position, and will clear them all if it does happen.
	 */
	public void PositionNumberValidator()
	{

		// DEBUG //////////////
		//playersOnTeam.stream().forEach(x -> System.out.printf(x.getFirstName()));

		///////////////////////

		// Array list to hold the different position on a team.
		ArrayList<Player> forward = new ArrayList<Player>();
		ArrayList<Player> midfield = new ArrayList<Player>();
		ArrayList<Player> defence = new ArrayList<Player>();
		ArrayList<Player> GoalTender = new ArrayList<Player>();

		// Loop thorough all the players on a team and assign them to arraylists depending on their position.

		for (Player player : playersOnTeam)
		{
			String POS = player.getAssignPosition();
			switch (POS){
				case "Forward":
					forward.add(player);
					break;
				case "Defence":
					defence.add(player);
					break;
				case "Midfield":
					midfield.add(player);
					break;
				case "GoalTender":
					GoalTender.add(player);
					break;
				case "Substitution":
					break;
				default:
					player.setAssignPosition("Substitution");
					break;
			}
		}

		// Check the size of the list of players in the forward position, and if it is greater than 1, unassign all of the
		// players with that position and set them as Substitutes.
		if (forward.size()>1)
		{
			for (Player player:forward) {
				removePlayerPosition(player);
			}
		}
		//Do the same to the midfield list, but making sure there is a max of 3.
		if (midfield.size()>3)
		{
			for (Player player:midfield) {
				removePlayerPosition(player);
			}
		}

		//Do the same to the midfield list, making sure there is a max of 2.
		if (defence.size()>2)
		{
			for (Player player:defence) {
				removePlayerPosition(player);
			}
		}

		//Do the same to the GoalTender list, making sure there is a max of 1.
		if (GoalTender.size()>1)
		{
			for (Player player:GoalTender) {
				removePlayerPosition(player);
			}
		}

		fieldPositionFiller();
	}

	/**
	 * Purpose:
	 * This method will put players that have a field position (so not sub) on the field. This will change the labels
	 * on the field and assign values to an organized array. This array will store the players sequentially
	 * in this order: Forward, Midfield1, Midfield2, Midfield3, Defence1, Defence2, and Goaltender.
	 */
	private void fieldPositionFiller()
	{
		// Array to hold the player positions on the field

		// This will loop through all players on the team and set the field labels
		// to their names depending on their position
		for (Player player : playersOnTeam)
		{
			String POS = player.getAssignPosition();
			switch (POS) {
				case "Forward":
					fieldPosArray[0] = player;
					break;

				case "Defence":
					if (fieldPosArray[1] != null)
					{
						fieldPosArray[1] = player;
						playersOnTeam.remove(player);
					}
					else
					{
						fieldPosArray[2] = player;
						playersOnTeam.remove(player);
					}
					break;

				case "Midfield":
					if (fieldPosArray[3] != null)
					{
						fieldPosArray[3] = player;
						playersOnTeam.remove(player);
					}
					else if (fieldPosArray[4] != null)
					{
						fieldPosArray[4] = player;
						playersOnTeam.remove(player);
					}
					else
					{
						fieldPosArray[5] = player;
						playersOnTeam.remove(player);
					}
					break;

				case "GoalTender":
					fieldPosArray[6] = player;
					playersOnTeam.remove(player);
					break;

				default:
					break;
			}
		}
	}
}
