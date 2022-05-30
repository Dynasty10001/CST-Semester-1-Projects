package views;

import com.cosacpmg.App;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeView
{
	private static AppView mainAppView;

	@FXML
	Label lblHomePageTournamentName;

	@FXML
	Button playerHomeBtn, teamHomeBtn, scheduleHomeBtn, standingsHomeBtn;

	@FXML
	protected void initialize()
	{
		if(App.currentTournament !=null)
		{
			lblHomePageTournamentName.setText(App.currentTournament.getTournamentName());
		}
		else
		{
			playerHomeBtn.setDisable(true);
			teamHomeBtn.setDisable(true);
			scheduleHomeBtn.setDisable(true);
			standingsHomeBtn.setDisable(true);
		}
	}
	
	
	protected void onHomeNavHandler() throws IOException
	{
//        System.out.println("Working?");
		mainAppView.showNavButtons();
		mainAppView.onHomeNavHandler();
	}
	
	@FXML
	protected void onPlayerNavHandler() throws IOException
	{
		mainAppView.showNavButtons();
		mainAppView.onPlayerNavHandler();
	}
	
	@FXML
	protected void onTeamsNavHandler() throws IOException
	{
		mainAppView.showNavButtons();
		mainAppView.onTeamsNavHandler();
	}
	
	@FXML
	protected void onSchedulesNavHandler() throws IOException
	{
		mainAppView.showNavButtons();
		mainAppView.onSchedulesNavHandler();
	}
	
	@FXML
	protected void onStandingNavHandler() throws IOException
	{
		mainAppView.showNavButtons();
		mainAppView.onStandingNavHandler();
	}
	
	public AppView getMainAppView()
	{
		return mainAppView;
	}
	
	public static void setMainAppView(AppView input)
	{
		mainAppView = input;
	}
}
