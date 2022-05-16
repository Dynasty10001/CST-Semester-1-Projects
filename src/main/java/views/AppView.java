package views;

import com.cosacpmg.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppView
{
    BorderPane borderPane;

	protected void changePaneHandler(String fxmlPath) throws IOException
	{
        // Fixme App might be broken. A little unsure check this later if broken.
		FXMLLoader loader = new FXMLLoader(AppView.class.getResource(fxmlPath));
		Pane pane = new Pane();
		pane.getChildren().add(loader.load());
        borderPane.setCenter(pane);
	}

    // Todo Do this later. bozo. maybe store an array list of panes as strings in a "history" list
//    protected void onBackNavHandler()
//    {
//
//    }

    @FXML
    protected void onHomeNavHandler() throws IOException
    {
        System.out.println("Working?");
        //changePaneHandler("home-view-pane.fxml");
    }

    @FXML
    protected void onPlayerNavHandler() throws IOException
    {
        changePaneHandler("player-view-pane.fxml");
    }

    @FXML
    protected void onTeamsNavHandler() throws IOException
    {
        changePaneHandler("teams-view-pane.fxml");
    }

    @FXML
    protected void onSchedulesNavHandler() throws IOException
    {
        changePaneHandler("schedules-view-pane.fxml");
    }

    @FXML
    protected void onStandingNavHandler() throws IOException
    {
        changePaneHandler("standing-view-pane.fxml");
    }




}
