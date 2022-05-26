package views;

import com.cosacpmg.App;
import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Player;


public class RosterPopup {
	
	@FXML
	protected ListView<Player> allPlayerList;
	@FXML
	protected ListView<Player> teamPlayerList;
	
	private static int currentTeamId = 0;
	
	
	
	@FXML
	protected void initialize(){
		System.out.println("Current team is " + currentTeamId);
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
		teamPlayerList.getItems().setAll(pc.queryForPlayersOnTeam(currentTeamId));
	
	}
	
	
	private void initAllPlayerList(PlayerController pc)
	{
		allPlayerList.getItems().setAll(pc.queryForPlayersOnTeam(0));
	}
	
	
	@FXML
	protected void rosterAddPlayerHandler()
	{
		Player selectedPlayer = (Player) allPlayerList.getSelectionModel().getSelectedItems().get(0);
		PlayerController pc = new PlayerController(App.connection);
		selectedPlayer.setTeam(currentTeamId);
		pc.updatePlayer(selectedPlayer);
		updateUI();
	}
	
	public static void setCurrentTeam(int teamId)
	{
			currentTeamId = teamId;
	}

	@FXML
	protected void rosterRemovePlayerHandler() {
		Player selectedPlayer = (Player) teamPlayerList.getSelectionModel().getSelectedItems().get(0);
		PlayerController pc = new PlayerController(App.connection);
		selectedPlayer.setTeam(0);
		pc.updatePlayer(selectedPlayer);
		updateUI();
	}
}
