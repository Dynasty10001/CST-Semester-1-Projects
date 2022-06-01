package views;

import com.cosacpmg.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView
{
    public Button scheduleNavButton;
    public Button teamsNavButton;
    public Button playerNavButton;
    public Button homeNavButton;
    public Button backNavButton;
    public Button standingNavButton;

    @FXML
    BorderPane borderPane;
    static BorderPane staticBorderPane;
    
    @FXML
    protected void initialize() throws IOException
    {
        staticBorderPane = borderPane;
        new HomeView().setMainAppView(this);
        onHomeNavHandler();
    }
    
    
    /**
     * A non-static method for changing the center pane
     * Just takes in the fxml Path, uses the current object border pane
     * @param fxmlPath
     */
    private void changePaneHandler(String fxmlPath) throws IOException
    {
        changePaneHandler(fxmlPath, staticBorderPane);
    }
    
    /**
     * Sets the cetner pane in the db to a view that is based on the fxmlPath must be supplied with the bp
     * @param fxmlPath
     * @param bP
     * @throws IOException
     */
    protected static void changePaneHandler(String fxmlPath, BorderPane bP) throws IOException
    {
//		FXMLLoader loader = new FXMLLoader(AppView.class.getResource(fxmlPath));
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
//        Pane pane = new Pane();
//        pane.getChildren().add(loader.load());
        bP.setCenter(loader.load());
    }
    
    /**
     * Passing this method a fxmlPath will create a show and wait popup that will instatiate a view of that fxml file
     * @param fxmlPath fxml path to create a popup for
     */
    protected static void popupHandler(String fxmlPath)
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        try
        {
            dialog.setScene(new Scene(loader.load()));
            dialog.showAndWait();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    // Todo Do this later. bozo. maybe store an array list of panes as strings in a "history" list
//    protected void onBackNavHandler()
//    {
//
//    }
    
    @FXML
    protected void onHomeNavHandler() throws IOException
    {
//        System.out.println("Working?");
        changePaneHandler("home-view.fxml");
        hideNavButtons();
    }
    
    @FXML
    protected void onPlayerNavHandler() throws IOException
    {
        changePaneHandler("add-player-view.fxml");
        showNavButtons();
    }
    
    @FXML
    protected void onTeamsNavHandler() throws IOException
    {
        changePaneHandler("team-view.fxml");
        showNavButtons();
    }
    
    @FXML
    protected void onSchedulesNavHandler() throws IOException
    {
        changePaneHandler("schedules-view.fxml");
        showNavButtons();
    }
    
    @FXML
    protected void onStandingNavHandler() throws IOException
    {
        changePaneHandler("standings-view.fxml");
        showNavButtons();
    }
    
    /**
     * Hides the nav buttons while on the mainmenu view
     */
    protected void hideNavButtons()
    {
        scheduleNavButton.setVisible(false);
        teamsNavButton.setVisible(false);
        playerNavButton.setVisible(false);
        homeNavButton.setVisible(false);
        standingNavButton.setVisible(false);
//        backNavButton.setVisible(false);
    }
    
    
    /**
     * Shows the nav buttons when leaving the main menu views
     */
    protected void showNavButtons(){
    
        scheduleNavButton.setVisible(true);
        teamsNavButton.setVisible(true);
        playerNavButton.setVisible(true);
        homeNavButton.setVisible(true);
        standingNavButton.setVisible(true);
//        backNavButton.setVisible(false);
    }

}
