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
import models.Team;
import models.ValidationHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class AddGameView
{

    TournamentController TUC ;
    GameController GC;
    TeamController TC;

        @FXML
        TextField locationField;

        @FXML
        DatePicker datePicker;

        @FXML
        ComboBox<Team> homeTeamBox, awayTeamBox;

        @FXML
        ComboBox<Integer> hourBox, minuteBox;

        @FXML
        ComboBox<java.lang.String> ampmBox;

        @FXML
        Label teamFieldError, cityFieldError, areaFieldError, coachFieldError, coachNumFieldError;

        @FXML
        protected void initialize() throws SQLException {

            TUC = App.TUC;
            GC = App.GC;
            TC = App.TC;

            homeTeamBox.getItems().addAll(TC.getAllTeams());
            awayTeamBox.getItems().addAll(TC.getAllTeams());
        }


    @FXML
    protected void addGameSubmitHandler() throws SQLException, IOException {

        if (TUC.getTournament() == null)
        {
            TUC.createTournament("TestTournament", new Date(), new Date());
        }
        //System.out.println("Team Added");

        ValidationHelper vh = new ValidationHelper();
        Calendar gameTime = Calendar.getInstance();
        gameTime.clear();
        LocalDate pickTime = datePicker.getValue();
        if(ampmBox.getValue().equals("AM"))
        {
            gameTime.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(), hourBox.getValue(),minuteBox.getValue());

        }
        else if(ampmBox.getValue().equals("PM"))
        {
            gameTime.set(pickTime.getYear(), pickTime.getMonthValue()-1, pickTime.getDayOfMonth(), hourBox.getValue()+12,minuteBox.getValue());

        }

        if (homeTeamBox.getValue() == null)
        {
            homeTeamBox.setValue(TC.createTeam(null,null,null,null,null));
        }
        if (awayTeamBox.getValue() == null)
        {
            awayTeamBox.setValue(TC.createTeam(null,null,null,null,null));
        }

        Game game = GC.createGame(homeTeamBox.getValue(), awayTeamBox.getValue(), gameTime.getTime(),
                locationField.getText(), TUC.getTournament());

        HashMap<String, String> error = vh.getErrors(game);

        teamFieldError.setText(error.get("teamName"));
        cityFieldError.setText(error.get("city"));
        areaFieldError.setText(error.get("area"));
        coachFieldError.setText((error.get("coachName")));
        coachNumFieldError.setText(error.get("coachNumber"));

        if (error.isEmpty())
        {
            GameController gc = new GameController(App.connection);
            gc.addGame(game);
            if(game.getGameID()>0L)
            {
                AppView.changePaneHandler("schedules-view.fxml",AppView.staticBorderPane);
            }

        }


    }

        @FXML
        public void addGameCancelHandler() throws IOException
        {
            AppView.changePaneHandler("schedules-view.fxml", AppView.staticBorderPane);
        }
}

