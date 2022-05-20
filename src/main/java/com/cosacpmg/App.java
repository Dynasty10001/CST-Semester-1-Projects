package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Game;
import models.Team;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class App extends Application
{
    public static final java.lang.String CONNECTION_STRING = "jdbc:sqlite:schedule.db";
    public static ConnectionSource connection;
    private static Stage mainStage;
    public static TournamentController TUC;
    public static TeamController TC;
    public static GameController GC;
//    private static TeamController teamController;

    // AppView.class.getResource("/com.cosacpmg/app-view.fxml")
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        startDB();
    }

    public static void main(java.lang.String[] args) {
        launch();
    }

    /**
     * This method will be used to start the database.
     */
    public void startDB()
    {
        try
        {
            connection = new JdbcPooledConnectionSource(CONNECTION_STRING);
            TUC = new TournamentController(connection);
            GC = new GameController(connection);
            TC = new TeamController(connection);
            PlaceDummyData(TC,GC,TUC);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Just loads in dummy data.
     * @return
     */
    public static ArrayList<Game> PlaceDummyData(TeamController tc, GameController gc, TournamentController TUC) throws SQLException {
        ArrayList<Game> gameList = new ArrayList<>();
        TUC.createTournament("Dummy");
        Team one = tc.createTeam("Saskatoon","Sparks" , "Brighton","Jack" ,"111 111 1111" );
        tc.addTeam(one);
        Team two = tc.createTeam("Royals", "Regina", "redArbour", "Jack", "111 111 1111");
        tc.addTeam(two);
        Date first = new Date();
        Date second = new Date();
        second.setTime(second.getTime()+3600000);
        gameList.add(gc.createGame(one,two, first,new String(),TUC.getTournament()));
        gameList.add(gc.createGame(one,two, second,new String(),TUC.getTournament()));


        return gameList;
    }



    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
