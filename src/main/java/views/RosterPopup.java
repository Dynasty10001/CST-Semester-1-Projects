package views;

import com.cosacpmg.App;
import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Player;
import models.Team;

import java.util.ArrayList;


public class RosterPopup {
	
	@FXML
	protected ListView<Player> allPlayerList;
	@FXML
	protected ListView<Player> teamPlayerList;
	
	private static Team currentTeam = null;
	
	
	
	@FXML
	protected void initialize(){
//		System.out.println("Current team is " + currentTeam.getId());
		PlayerController pc = new PlayerController(App.connection);
		initAllPlayerList(pc);
		initTeamPlayerList(pc);
		
	}

	protected void updateUI() {
		PlayerController pc = new PlayerController(App.connection);
		initAllPlayerList(pc);
		initTeamPlayerList(pc);
	}
	
	
	private void initTeamPlayerList(PlayerController pc)
	{
		teamPlayerList.getItems().setAll(pc.queryForPlayersOnTeam(currentTeam));
	
	}
	
	
	private void initAllPlayerList(PlayerController pc)
	{
		
		
		ArrayList<Player> allPlayertTemp = pc.queryForPlayersOnTeam(null);
		if (allPlayertTemp != null)
		{
			allPlayerList.getItems().setAll(allPlayertTemp);
		}
		
	}
	
	
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
	
	public static void setCurrentTeam(Team team)
	{
			currentTeam = team;
	}

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
}
