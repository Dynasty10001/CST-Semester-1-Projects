package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
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

    /**
     * Just loads in dummy data.
     * @return
     */
    public static ArrayList<Game> getDummyGame(TeamController tc, GameController gc, TournamentController TUC) throws SQLException {
        ArrayList<Game> gameList = new ArrayList<>();
        TUC.Tournament("Dummy");
        Team one = tc.createTeam("Saskatoon","Sparks" , "Brighton","Jack" ,"111 111 1111" );
        Team two = tc.createTeam("Royals", "Regina", "redArbour", "Jack", "111 111 1111");
        Date first = new Date();
        Date second = new Date();
        second.setTime(second.getTime()+3600000);
        gameList.add(gc.createGame(one,two, first,new Field(),TUC.getTournament()));
        gameList.add(gc.createGame(one,two, second,new Field(),TUC.getTournament()));


        return gameList;
    }

    @FXML
    protected void initialize(){
        //TODO implement database call here to load in all teams that are in this tournament add all of them to the
        // TeamList
        System.out.println("UI: TeamView Initialization");


        //This adds colomns to the table view, a method called create column is used to make this easier
        gameList.getColumns().setAll(
                ViewUtilities.getColumn("HomeTeam", "homeTeam"),
                ViewUtilities.getColumn("AwayTeam", "awayTeam"),
                ViewUtilities.getColumn("Date", "startTime")
        );


        try {
            //getDummyGame();
            gameList.getItems().addAll(new GameController(App.connection).getSchedule(App.TUC.getTournament())); //Query call goes in here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void gameViewAddGameHandler() throws IOException
    {
        AppView.changePaneHandler("add-game-view.fxml",AppView.staticBorderPane);
    }


}
