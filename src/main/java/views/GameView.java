package views;

import controllers.TournamentController;
import models.Tournament;

public class GameView {

    private static TournamentController TournamentCtrl;

    public static void submit() {

    }

    public static void cancel() {
    }

    public static void setTournament(Tournament tournament) {
        TournamentCtrl.changeTournament(tournament);
    }
}
