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
import models.Player;
import models.Team;
import models.Tournament;
import views.RosterPopup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class App extends Application
{
    public static final java.lang.String CONNECTION_STRING = "jdbc:sqlite:schedule.db";
    public static ConnectionSource connection;
    private static Stage mainStage;
    public static Tournament currentTournament;

    // AppView.class.getResource("/com.cosacpmg/app-view.fxml")
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Molez's Stats and Schedulez");
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
            //DataBaseDummyData.PlaceDummyData(); //Uncomment this and run it to fill the database with dummy data

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
