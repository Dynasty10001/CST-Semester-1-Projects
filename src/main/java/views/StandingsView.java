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

    /**
     * This method sets up the standings list by creating an ArrayList of Standings entries. This ArrayList is used to populate
     * the table that holds the team name and all statistics.
     */
    public void initialize() {
        ArrayList<Team> teams = new TeamController(App.connection).getAllTeams();

        ArrayList<StandingsEntry> entries = teams.stream().map((Team team1) -> new StandingsEntry(team1)).collect(Collectors.toCollection(ArrayList::new));

        standingsTeamTable.getColumns().setAll(
                ViewUtilities.getColumn("Team Name", "team"),
                ViewUtilities.getColumn("Score", "score"),
                ViewUtilities.getColumn("Wins", "wins"),
                ViewUtilities.getColumn("Losses", "losses"),
                ViewUtilities.getColumn("Ties", "ties"));

        standingsTeamTable.getItems().addAll(entries);

    }

    /**
     * This method will grab the data from the table when clicked and put it into a StandingsEntry. This entry is
     * then used to populate the labels on the right side of the splitpane with data.
     * @param mouseEvent
     */
    public void standingsViewOnClickHandler(MouseEvent mouseEvent) {
        StandingsEntry currentEntry = (StandingsEntry) standingsTeamTable.getSelectionModel().getSelectedItem();
        
        team.setText(currentEntry.getTeam().toString());
        score.setText("" +currentEntry.getScore());
        losses.setText("" +currentEntry.getLosses());
        ties.setText("" +currentEntry.getTies());
        wins.setText("" +currentEntry.getWins());
        
        
        


    }
}
