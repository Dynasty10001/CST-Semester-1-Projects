package views;

import com.cosacpmg.App;
import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Player;


public class AddPlayerToTeamPopup {
	
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
	
	
	
	private void initTeamPlayerList(PlayerController pc)
	{
		teamPlayerList.getItems().addAll(pc.queryForPlayersOnTeam(currentTeamId));
	
	}
	
	
	private void initAllPlayerList(PlayerController pc)
	{
		allPlayerList.getItems().addAll(pc.queryForPlayersOnTeam(0));
	}
	
	
	@FXML
	protected void playerSelected()
	{
		
		Player selectedPlayer = (Player) allPlayerList.getSelectionModel().getSelectedItems().get(0);
		System.out.println(selectedPlayer.getFirstName());
	}
	
	public static void setCurrentTeam(int teamId)
	{
			currentTeamId = teamId;
	
	}
}
