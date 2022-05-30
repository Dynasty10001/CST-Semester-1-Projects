package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TeamController;
import controllers.TournamentController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Game;
import models.Tournament;
import models.ValidationHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AddTournamentPopUp
{
    @FXML
    TextField tournamentNameField;

    @FXML
    DatePicker tournamentStartDatePicker, tournamentEndDatePicker;
    @FXML
    Label lblERRTournamentName, lblERRTournamentStartDate,lblERRTournamentEndDate;


    @FXML
    protected void addTournamentSubmitHandler() throws SQLException, IOException {

        TournamentController TornamentCntrl = new TournamentController(App.connection);


        ValidationHelper vh = new ValidationHelper();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.clear();
        endDate.clear();
        LocalDate pickTime = tournamentStartDatePicker.getValue();
        startDate.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(),23,59);
        pickTime = tournamentEndDatePicker.getValue();
        endDate.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(),23,59);




        Tournament tourney = TornamentCntrl.createTournament(tournamentNameField.getText(), startDate.getTime(), endDate.getTime());


        HashMap<String, String> error = vh.getErrors(tourney);

        lblERRTournamentName.setText(error.get("tournamentName"));
        lblERRTournamentStartDate.setText(error.get("startDate"));
        lblERRTournamentEndDate.setText(error.get("endDate"));


        if (error.isEmpty())
        {
           TornamentCntrl.addTournament(tourney);

            if(tourney.getTournamentID()>0L)
            {
                AppView.changePaneHandler("schedules-view.fxml",AppView.staticBorderPane);
            }

        }


    }






}
