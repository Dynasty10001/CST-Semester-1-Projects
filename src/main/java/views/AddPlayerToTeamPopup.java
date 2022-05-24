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
	protected void initialize(){
		System.out.println("YOOP");
		PlayerController pc = new PlayerController(App.connection);
		allPlayerList.getItems().addAll(pc.getAllPlayer());
		
	}

}
