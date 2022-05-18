package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.AppView;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application
{
    public static final String CONNECTION_STRING = "jdbc:sqlite:schedule.db";
    public static ConnectionSource connection;
    private static Stage mainStage;
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

    public static void main(String[] args) {
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
