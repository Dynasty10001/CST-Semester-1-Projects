package views;

import com.cosacpmg.App;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Team;

import java.io.IOException;
import java.util.ArrayList;

public class TeamView
{
	
	
	@FXML protected TableView<Team> teamList;

	@FXML BorderPane borderPane;
	
	
	/**
	 * This method creates columns to be used in a table view, using a label as the name of the column and the
	 * property name being the text name of the attribute.
	 * @param label
	 * @param propertyName
	 * @return
	 */
	private TableColumn<Team,String> createColumn(String label, String propertyName){
		TableColumn<Team, String> returnCol = new TableColumn<Team, String>(label);
		returnCol.setCellValueFactory(new PropertyValueFactory<Team, String>(propertyName));
		return returnCol;
	}
	
	@FXML
	protected void initialize()
	{
		//TODO implement database call here to load in all teams that are in this tournament add all of them to the
		// TeamList
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
		int currentTeam = (int) teamList.getSelectionModel().getSelectedItem().getId();
		RosterPopup.setCurrentTeam(currentTeam);
		AppView.popupHandler("roster-popup-view.fxml");
	}
	
	
}
