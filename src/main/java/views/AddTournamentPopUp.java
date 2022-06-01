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
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
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
        LocalDate pickTime = tournamentStartDatePicker.getValue();
        startDate.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(),23,59);
        pickTime = tournamentEndDatePicker.getValue();
        endDate.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(),23,59);




        Tournament tourney = TournamentCntrl.createTournament(tournamentNameField.getText(), startDate.getTime(), endDate.getTime());


        HashMap<String, String> error = vh.getErrors(tourney);
        if(TournamentCntrl.tournamentNameUnique(tourney))
        {

        }


        lblERRTournamentName.setText(error.get("tournamentName"));
        lblERRTournamentStartDate.setText(error.get("startDate"));
        lblERRTournamentEndDate.setText(error.get("endDate"));


        if(TournamentCntrl.tournamentNameUnique(tourney))
        {
            if (error.isEmpty())
            {
                TournamentCntrl.addTournament(tourney);
                if(tourney.getTournamentID()>0L)
                {
                    App.currentTournament = tourney;
                    AppView.changePaneHandler("home-view.fxml", AppView.staticBorderPane);
                    Stage stage = (Stage) addTournamentSubmitBtn.getScene().getWindow();
                    stage.close();
                }
            }
        }
        else
        {
            lblERRTournamentName.setText("Tournament name is already used, please enter a different name");
        }

    }


    public void secondDateSelected(ActionEvent actionEvent) {
        tournamentStartDatePicker.getValue();
        tournamentEndDatePicker.getValue();

        if (((LocalDate)tournamentEndDatePicker.getValue()).minus(3,java.time.temporal.TemporalAmount.).compareTo(tournamentStartDatePicker.getValue()))
        {
            dateWarning.setText("Tournament duration  should be 3 days or less");
        }

    }
}
