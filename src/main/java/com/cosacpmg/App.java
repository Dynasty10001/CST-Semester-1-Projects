package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.GameView;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application
{
    public static final String CONNECTION_STRING = "jdbc:sqlite:schedule.db";
    public static ConnectionSource connection;
    public static TournamentController TUC;
    public static TeamController TC;
    public static GameController GC;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        startDB();
        stage.show();

        GameView.getDummyGame(TC,GC,TUC);
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
            TUC = new TournamentController(connection);
            GC = new GameController(connection);
            TC = new TeamController(connection);
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