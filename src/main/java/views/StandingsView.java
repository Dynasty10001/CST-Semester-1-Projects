package views;

import com.cosacpmg.App;
import controllers.TeamController;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import models.StandingsEntry;
import models.Team;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StandingsView {

    public Label team;
    public Label score;
    public Label losses;
    public Label ties;
    public Label wins;

    public TableView standingsTeamTable;

    public void initialize() {
        ArrayList<Team> teams = new TeamController(App.connection).getAllTeams();

        ArrayList<StandingsEntry> entries = teams.stream().map(StandingsEntry::new).collect(Collectors.toCollection(ArrayList::new));

        standingsTeamTable.getColumns().setAll(
                ViewUtilities.getColumn("Team Name", "team"),
                ViewUtilities.getColumn("Score", "score"),
                ViewUtilities.getColumn("Wins", "wins"),
                ViewUtilities.getColumn("Losses", "losses"),
                ViewUtilities.getColumn("Ties", "ties"));

        standingsTeamTable.getItems().addAll(entries);

    }

    public void standingsViewOnClickHandler(MouseEvent mouseEvent) {
        StandingsEntry currentEntry = (StandingsEntry) standingsTeamTable.getSelectionModel().getSelectedItem();
        
        team.setText(currentEntry.getTeam().toString());
        score.setText("" +currentEntry.getScore());
        losses.setText("" +currentEntry.getLosses());
        ties.setText("" +currentEntry.getTies());
        wins.setText("" +currentEntry.getWins());
        
        
        


    }
}
