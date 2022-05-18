package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Field;
import models.Game;
import models.Team;
import models.Tournament;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GameView
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
    protected void initialize()
    {
        //TODO implement database call here to load in all games that are in this tournament add all of them to the
        // GameList
        System.out.println("UI: GameView Initialization");


        //This adds colomns to the table view, a method called create column is used to make this easier
        gameList.getColumns().setAll(
                createColumn("HomeTeam", "home"),
                createColumn("Away Team", "away")
        );
        try {
            gameList.getItems().addAll(getDummyGame());//Query call goes in here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Just loads in dummy data.
     * @return
     */
    protected ArrayList<Game> getDummyGame() throws SQLException {
        ArrayList<Game> gameList = new ArrayList<>();
        TeamController tc = new TeamController(App.connection);
        GameController gc = new GameController(App.connection);
        Team one = tc.createTeam("Saskatoon","Sparks" , "Brighton","Jack" ,"111 111 1111" );
        Team two = tc.createTeam("Royals", "Regina", "redArbour", "Jack", "111 111 1111");
        gameList.add(gc.createGame(one,two, new Date(),new Field(),new Tournament()));
        gameList.add(gc.createGame(one,two, new Date(),new Field(),new Tournament()));


        return gameList;
    }


    @FXML
    protected void gameViewAddGameHandler() throws IOException
    {
        AppView.changePaneHandler("add-game-view.fxml",AppView.staticBorderPane);
    }


}
