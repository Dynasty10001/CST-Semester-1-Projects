package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TournamentController;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import models.Game;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchedulesView implements Initializable
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
                    .getAllGamesByTournament(new TournamentController(App.connection).getTournament())); //Query call goes in here
//            gameList.stream().foreach(x->{
//
//            });
//            loop i // For Every Game in the Game List
//            int teamid = gameList.getColumns() //Get all columns in team list
//                              .get(teamColumn) // get home/away team column
//                              .get(i) // get the home/away team for that game
//                              .getID // get its ID ( the only valid value)
//            team correctTeam = querry TeamTable for teamid // get the ACTUAL team for that ID
//            gameList.getColums.get(teamColumn).replace(i to correctTeam) // replace the blank team with the correct one


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
