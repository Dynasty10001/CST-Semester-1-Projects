package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
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
        fxmlLoader.setLocation(AppView.class.getResource("com/cosacpmg/app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //startDB();
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
            connection = new JdbcConnectionSource(CONNECTION_STRING);
    
            TournamentController tournamentController = new TournamentController(connection);
            
           tournamentController.addTeam(new TeamController().createTeam("asd", "asd" , "asd" , "asd" , "asd"));
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
