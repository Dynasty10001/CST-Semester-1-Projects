package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TournamentController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Field;
import models.Game;
import models.Team;
import models.ValidationHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class AddGameView
{


        @FXML
        TextField locationBox;

        @FXML
        DatePicker datePicker;

        @FXML
        ComboBox<Team> homeTeamBox, awayTeamBox;

        @FXML
        ComboBox<Integer> hourBox, minuteBox;

        @FXML
        ComboBox<String> ampmBox;

        @FXML
        Label teamFieldError, cityFieldError, areaFieldError, coachFieldError, coachNumFieldError;


    @FXML
    protected void addGameSubmitHandler() throws SQLException {
        TournamentController TUC = new TournamentController(App.connection);
        GameController GC = new GameController(App.connection);
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



        Game game = GC.createGame(homeTeamBox.getValue(), awayTeamBox.getValue(), gameTime.getTime(),
                new Field(locationBox.getText()), TUC.getTournament());

        //HashMap<String, String> error = vh.getErrors(team);

        System.out.println(game.getGameID());

//        teamFieldError.setText(error.get("teamName"));
//        cityFieldError.setText(error.get("city"));
//        areaFieldError.setText(error.get("area"));
//        coachFieldError.setText((error.get("coachName")));
//        coachNumFieldError.setText(error.get("coachNumber"));

    }

        @FXML
        public void addGameCancelHandler() throws IOException
        {
            AppView.changePaneHandler("team-view.fxml", AppView.staticBorderPane);
        }
}

