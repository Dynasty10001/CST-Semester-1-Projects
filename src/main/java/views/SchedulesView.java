package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TournamentController;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import models.Game;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchedulesView implements Initializable
{


    public Label awayTeamName;
    public Label homeTeamName;
    public Label gameDate;
    public Label gameLocation;
    public AnchorPane rightPane;
    @FXML protected TableView<Game> gameList;

    @FXML BorderPane borderPane;



    /**
     * This method creates columns to be used in a table view, using a label as the name of the column and the
     * property name being the text name of the attribute.
     * @param label
     * @param propertyName
     * @return
     */
    private TableColumn<Game,String> createColumn(String label, String propertyName){
        TableColumn<Game, String> returnCol = new TableColumn<Game, String>(label);
        returnCol.setCellValueFactory(new PropertyValueFactory<Game, String>(propertyName));
        return returnCol;
    }

    /**
     * Handles the button press of adding a game
     * @throws IOException
     */
    @FXML
    protected void gameViewAddGameHandler() throws IOException
    {
        AppView.changePaneHandler("add-game-view.fxml",AppView.staticBorderPane);
    }


    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("UI: TeamView Initialization");

        showRightInfo(false);

        //This adds colomns to the table view, a method called create column is used to make this easier
        gameList.getColumns().setAll(
                ViewUtilities.getColumn("HomeTeam", "homeTeam"),
                ViewUtilities.getColumn("AwayTeam", "awayTeam"),
                ViewUtilities.getColumn("Date", "startTime")
        );

        updateGameList();

    }

    /**
     * This method updates the game list with all available data from the database.
     */
    public void updateGameList()
    {

        try
        {
            gameList.getItems().addAll(new GameController(App.connection)
                    .getAllGamesByTournament(App.currentTournament)); //Query call goes in here

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method creates the popup for the date picker.
     * @param actionEvent
     */
    public void scheduleViewOnFilterHandler(ActionEvent actionEvent)
    {
        FilterDatePopup.setView(this);
        AppView.popupHandler("filter-date-popup.fxml");

    }

    /**
     * Method for the button that clears the filter and displays all data.
     * @param actionEvent
     */
    public void clearFilterHandler(ActionEvent actionEvent)
    {
        updateGameList();
    }


    /**
     * this method shows the labels on the right pane before an entry is selected
     */
    public void showRightInfo(boolean value){
        rightPane.setVisible(value);
    }


    /**
     * Triggeres when an entry is selected in the list
     * @param mouseEvent
     */
    public void selectScheduleEntryHandler(MouseEvent mouseEvent)
    {
        Game currentGame = gameList.getSelectionModel().getSelectedItem();
        homeTeamName.setText(currentGame.getHomeTeam().toString());
        awayTeamName.setText(currentGame.getAwayTeam().toString());
        gameDate.setText(currentGame.getStartTime().toLocaleString());
        gameLocation.setText(currentGame.getLocation());

        showRightInfo(true);
    }
}
