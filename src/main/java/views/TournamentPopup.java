package views;

import com.cosacpmg.App;
import controllers.TournamentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Tournament;

import java.io.IOException;
import java.sql.SQLException;

public class TournamentPopup
{
    @FXML
    ComboBox<Tournament> tournamentBox;

    @FXML
    Button tournamentSubmitBtn, tournamentNewTournamentBtn;



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
        AppView.changePaneHandler("home-view.fxml", AppView.staticBorderPane);
        Stage stage = (Stage) tournamentSubmitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void newTournamentHandler()
    {
        popupHandler("add-tournament-popup.fxml");
        Stage stage = (Stage) tournamentNewTournamentBtn.getScene().getWindow();
        stage.close();
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
