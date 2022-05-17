package views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddTeamView
{
	@FXML
	TextField teamField, cityField, areaField, coachField, coachNumField;
	
	
	@FXML
	protected void addTeamSubmitHandler(){
		System.out.println("Team Added");
	}
	
	protected void addTeamCancelHandler()
	{
		System.out.println("Cancel Button Pressed");
	}
	
	
}
