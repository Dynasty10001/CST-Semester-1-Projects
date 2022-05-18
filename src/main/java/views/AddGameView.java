package views;

import com.cosacpmg.App;
import controllers.GameController;
import controllers.TournamentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Game;
import models.ValidationHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class AddGameView
{


        @FXML
        TextField homeTeamBox, awayTeamBox, DateBox, locationBox;

        @FXML
        Label teamFieldError, cityFieldError, areaFieldError, coachFieldError, coachNumFieldError;


    @FXML
    protected void addGameSubmitHandler() throws SQLException {
        TournamentController TUC = new TournamentController(App.connection);
        GameController GC = new GameController(App.connection);
        //System.out.println("Team Added");

        ValidationHelper vh = new ValidationHelper();
        Game game = GC.createGame(homeTeamBox.getText(), awayTeamBox.getText(), DateBox.getText(),
                locationBox.getText(), TUC.getTournament());

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

