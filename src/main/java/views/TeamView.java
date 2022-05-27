package views;

import com.cosacpmg.App;
import controllers.PlayerController;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Team;

import java.io.IOException;
import java.util.ArrayList;

public class TeamView
{


	public Label teamName, teamCity, teamArea, teamCoachName, teamCoachPhoneNumber;
	public ListView rosterList;
	@FXML protected TableView<Team> teamList;
	@FXML protected AnchorPane teamViewDetailPane;

	@FXML BorderPane borderPane;
	
	
	@FXML
	protected void initialize()
	{
		teamViewDetailPane.setVisible(false);

		System.out.println("UI: TeamView Initialization");
		
		
		//This adds colomns to the table view, a method called create column is used to make this easier
		teamList.getColumns().setAll(
				ViewUtilities.getColumn("Team Name", "teamName"),
				ViewUtilities.getColumn("City Name", "city"),
				ViewUtilities.getColumn("Area", "area")
		                            );
		
		teamList.getItems().addAll(new TeamController(App.connection).getAllTeams()); //Query call goes in here
	}
	
	
	/**
	 * Just loads in dummy data.
	 * @return
	 */
	protected ArrayList<Team> getDummyTeam()
	{
		ArrayList<Team> teamList = new ArrayList<>();
		TeamController tc = new TeamController();
		teamList.add(tc.createTeam("Saskatoon","Sparks" , "Brighton","Jack" ,"111 111 1111" ));
		teamList.add(tc.createTeam("Royals", "Regina", "redArbour", "Jack", "111 111 1111"));
		
		
		return teamList;
	}
	
	
	@FXML
	protected void teamViewAddTeamHandler() throws IOException
	{
		AppView.changePaneHandler("add-team-view.fxml",AppView.staticBorderPane);
	}

	@FXML
	protected void teamViewRosterViewHandler() throws IOException
	{
		Team currentTeam =  teamList.getSelectionModel().getSelectedItem();
		RosterPopup.setCurrentTeam(currentTeam);
		AppView.popupHandler("roster-popup-view.fxml");
	}
	
	
	public void teamViewOnTeamSelectHandler(MouseEvent mouseEvent)
	{
		Team currentTeam =  teamList.getSelectionModel().getSelectedItem();
		teamName.setText(currentTeam.getTeamName());
		teamCity.setText(currentTeam.getCity());
		teamArea.setText(currentTeam.getArea());
		teamCoachName.setText(currentTeam.getCoachName());
		teamCoachPhoneNumber.setText(currentTeam.getCoachNumber());

		rosterList.getItems().setAll(new PlayerController(App.connection).queryForPlayersOnTeam(currentTeam));

		teamViewDetailPane.setVisible(true);
	
	}
}
