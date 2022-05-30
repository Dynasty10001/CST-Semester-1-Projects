package views;

import com.cosacpmg.App;
import controllers.TournamentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Tournament;

import java.io.IOException;
import java.sql.SQLException;

import static views.AppView.staticBorderPane;

public class TournamentPopup
{
    @FXML
    ComboBox<Tournament> tournamentBox;

    //Starts the Popup combo box values
    @FXML
    protected void initialize() throws SQLException {
        TournamentController TourneyController = new TournamentController(App.connection);

        tournamentBox.getItems().addAll(TourneyController.getAllTournaments());
    }

    //sets app tournamanet to slected tournament
    @FXML
    protected void addTournamentSubmitHandler() throws IOException {
        App.currentTournament = tournamentBox.getValue();
//        Platform.exit();
        //todo close window not entire program and Update Nav
        AppView.changePaneHandler("home-view.fxml", staticBorderPane);
    }

    @FXML
    protected void newTournamentHandler()
    {
        popupHandler("add-tournament-popup.fxml");
    }

    /**
     * Passing this method a fxmlPath will create a show and wait popup that will instatiate a view of that fxml file
     * @param fxmlPath fxml path to create a popup for
     */
    protected static void popupHandler(String fxmlPath)
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        try
        {
            dialog.setScene(new Scene(loader.load()));
            dialog.showAndWait();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
