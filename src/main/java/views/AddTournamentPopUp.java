package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Game;
import models.Tournament;
import models.ValidationHelper;

import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDate;

import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AddTournamentPopUp
{
    public Label dateWarning;
    @FXML
    TextField tournamentNameField;

    @FXML
    DatePicker tournamentStartDatePicker, tournamentEndDatePicker;
    @FXML
    Label lblERRTournamentName, lblERRTournamentStartDate,lblERRTournamentEndDate;

    @FXML
    Button addTournamentSubmitBtn;


    @FXML
    private void addTournamentSubmitHandler() throws SQLException, IOException {

        TournamentController TournamentCntrl = new TournamentController(App.connection);


        ValidationHelper vh = new ValidationHelper();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.clear();
        endDate.clear();

        LocalDate pickTime;


        if (tournamentStartDatePicker.getValue() == null)
        {
            lblERRTournamentStartDate.setText("A date must be selected for the Tournament");
        }
        else
        {
            if (tournamentStartDatePicker.getValue().compareTo(LocalDate.now())<0)
            {
                lblERRTournamentStartDate.setText("Date must not be in the past");
            }
            else {
                lblERRTournamentStartDate.setText("");
                pickTime = tournamentStartDatePicker.getValue();
                startDate.set(pickTime.getYear(), pickTime.getMonthValue() - 1, pickTime.getDayOfMonth(), 23, 59);
            }
        }

        if (tournamentEndDatePicker.getValue() == null)
        {
            lblERRTournamentEndDate.setText("A date must be selected for the Tournament");
        }
        else
        {
            if (tournamentStartDatePicker.getValue().compareTo(tournamentEndDatePicker.getValue())>0)
            {
                lblERRTournamentEndDate.setText("End date must be after the start date");
            }
            else {
                lblERRTournamentEndDate.setText("");
                pickTime = tournamentEndDatePicker.getValue();
                endDate.set(pickTime.getYear(), pickTime.getMonthValue() - 1, pickTime.getDayOfMonth(), 23, 59);
            }
        }

        if (tournamentEndDatePicker.getValue() != null && tournamentStartDatePicker.getValue() != null) {


            Tournament tourney = TournamentCntrl.createTournament(tournamentNameField.getText(), startDate.getTime(), endDate.getTime());


            HashMap<String, String> error = vh.getErrors(tourney);


            lblERRTournamentName.setText(error.get("tournamentName"));
            lblERRTournamentStartDate.setText(error.get("startDate"));
            lblERRTournamentEndDate.setText(error.get("endDate"));


            if (tournamentEndDatePicker.getValue() != null)

                if (TournamentCntrl.tournamentNameUnique(tourney)) {
                    if (error.isEmpty()) {
                        TournamentCntrl.addTournament(tourney);
                        if (tourney.getTournamentID() > 0L) {
                            App.currentTournament = tourney;
                            AppView.changePaneHandler("home-view.fxml", AppView.staticBorderPane);
                            Stage stage = (Stage) addTournamentSubmitBtn.getScene().getWindow();
                            stage.close();
                        }
                    }
                } else {
                    lblERRTournamentName.setText("Tournament name is already used, please enter a different name");
                }
        }

    }


    public void secondDateSelected(ActionEvent actionEvent) {
    tournamentStartDatePicker.getValue();
        LocalDate EndDate= tournamentEndDatePicker.getValue();

            if (tournamentEndDatePicker.getValue()!=null)
        {
            dateWarning.setText("Tournament duration  should be 3 days or less");
        }

    }
}
