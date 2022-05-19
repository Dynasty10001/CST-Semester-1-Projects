package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Field;
import models.Game;
import models.Team;
import models.Tournament;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class GameView implements Initializable
{


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

    @FXML
    protected void gameViewAddGameHandler() throws IOException
    {
        AppView.changePaneHandler("add-game-view.fxml",AppView.staticBorderPane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("UI: TeamView Initialization");


        //This adds colomns to the table view, a method called create column is used to make this easier
        gameList.getColumns().setAll(
                ViewUtilities.getColumn("HomeTeam", "homeTeam"),
                ViewUtilities.getColumn("AwayTeam", "awayTeam"),
                ViewUtilities.getColumn("Date", "startTime")
        );


        try {
            gameList.getItems().addAll(new GameController(App.connection)
                    .getSchedule(App.TUC.getTournament())); //Query call goes in here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
