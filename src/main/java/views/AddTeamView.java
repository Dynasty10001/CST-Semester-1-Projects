package views;

import com.cosacpmg.App;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Team;
import models.ValidationHelper;

import java.io.IOException;
import java.util.HashMap;


public class AddTeamView
{


	@FXML
	TextField teamField, cityField, areaField, coachField, coachNumField;

	@FXML
	Label teamFieldError, cityFieldError, areaFieldError, coachFieldError, coachNumFieldError;
	
	
	@FXML
	protected void addTeamSubmitHandler()
	{

		teamFieldError.setText("");
		cityFieldError.setText("");
		areaFieldError.setText("");
		coachFieldError.setText("");
		coachNumFieldError.setText("");

		//System.out.println("Team Added");

		ValidationHelper vh = new ValidationHelper();
		Team team = TeamController.createTeam(teamField.getText(), cityField.getText(), areaField.getText(),
											  coachField.getText(), coachNumField.getText());

		HashMap<String, String> error = vh.getErrors(team);

		System.out.println(team.getArea());

		teamFieldError.setText(error.get("teamName"));
		cityFieldError.setText(error.get("city"));
		areaFieldError.setText(error.get("area"));
		coachFieldError.setText((error.get("coachName")));
		coachNumFieldError.setText(error.get("coachNumber"));

	}

	@FXML
	protected void addTeamCancelHandler() throws IOException
	{
		AppView.changePaneHandler("team-view.fxml", AppView.staticBorderPane);
	}
	
	
}
