package views;

import com.cosacpmg.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView
{
    @FXML BorderPane borderPane;
    static BorderPane staticBorderPane;

    @FXML
    protected void initialize()
    {
        staticBorderPane = borderPane;
    }


    private void changePaneHandler(String fxmlPath) throws IOException
    {
        changePaneHandler(fxmlPath, staticBorderPane);
    }

    protected static void changePaneHandler(String fxmlPath, BorderPane bP) throws IOException
    {
//		FXMLLoader loader = new FXMLLoader(AppView.class.getResource(fxmlPath));
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
//        Pane pane = new Pane();
//        pane.getChildren().add(loader.load());
        bP.setCenter(loader.load());
    }

    protected static void popupHandler(String fxmlPath)
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        try {
            dialog.setScene(new Scene(loader.load()));
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Todo Do this later. bozo. maybe store an array list of panes as strings in a "history" list
//    protected void onBackNavHandler()
//    {
//
//    }

    @FXML
    protected void onHomeNavHandler() throws IOException
    {
        System.out.println("Working?");
        //changePaneHandler("home-view-pane.fxml");
    }

    @FXML
    protected void onPlayerNavHandler() throws IOException
    {
        changePaneHandler("player-view.fxml");
    }

    @FXML
    protected void onTeamsNavHandler() throws IOException
    {
        changePaneHandler("team-view.fxml");
    }

    @FXML
    protected void onSchedulesNavHandler() throws IOException
    {
        changePaneHandler("schedules-view.fxml");
    }

    @FXML
    protected void onStandingNavHandler() throws IOException
    {
        changePaneHandler("standing-view-pane.fxml");
    }



}
