package views;

import javafx.fxml.FXML;

import java.io.IOException;

public class HomeView
{
	private static AppView mainAppView;
	
	
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
