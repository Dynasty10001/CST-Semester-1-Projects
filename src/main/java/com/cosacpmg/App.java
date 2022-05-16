package com.cosacpmg;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import controllers.TeamController;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import models.Team;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application
{
    public static final String CONNECTIONSTRING = "jdbc:sqlite:schedule.db";
    private static ConnectionSource connection;
    private static Stage mainStage;
    private static TeamController teamController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
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
            connection = new JdbcConnectionSource(CONNECTIONSTRING);
            //teamController = new TeamController(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
