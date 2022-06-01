package com.cosacpmg;

import controllers.GameController;
import controllers.PlayerController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.Player;
import models.Team;
import views.RosterPopup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseDummyData {
    public static ArrayList<Game> PlaceDummyData() throws SQLException {
        TeamController tc = null;
        GameController gc = null;
        TournamentController TUC = null;
        PlayerController pc = null;
        try {
            pc = new PlayerController(App.connection);
            tc = new TeamController(App.connection);
            gc = new GameController(App.connection);
            TUC = new TournamentController(App.connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Game> gameList = new ArrayList<>();
        TUC.createTournament("Dummy");
        Team testTeam;
        Player validPlayer1, validPlayer2, validPlayer3, validPlayer4, validPlayer5, validPlayer6, validPlayer7, validPlayer8;
        Player[] PlayerList;
        Team one = TeamController.createTeam("Saskatoon","Sparks" , "Brighton","Jack" ,"111 111 1111" );
        tc.addTeam(one);
        Team two = TeamController.createTeam("Royals", "Regina", "redArbour", "Jack", "111 111 1111");
        tc.addTeam(two);
        Date first = new Date();
        Date second = new Date();
        second.setTime(second.getTime()+3600000);
        gameList.add(gc.createGame(one,two, first,new String(),TUC.getTournament()));
        gameList.add(gc.createGame(one,two, second,new String(),TUC.getTournament()));



        testTeam = TeamController.createTeam(
                "TheHeffingtonHeffs",
                "Saskatoon",
                "brighton",
                "Coach Coachington",
                "555 555 1234");

        validPlayer1 = PlayerController.createPlayer(
                "Heff",
                "Heffington",
                10,
                "Forward",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer2 = PlayerController.createPlayer(
                "John",
                "Heffington",
                15,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer3 = PlayerController.createPlayer(
                "Ethan",
                "Heffington",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer4 = PlayerController.createPlayer(
                "Levi",
                "Heffington",
                13,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer5 = PlayerController.createPlayer(
                "Zach",
                "Heffington",
                99,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer6 = PlayerController.createPlayer(
                "Ernesto",
                "Heffington",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer7 = PlayerController.createPlayer(
                "NoYou",
                "Heffington",
                69,
                "Midfield",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        validPlayer8 = PlayerController.createPlayer(
                "Bob",
                "Heffington",
                17,
                "GoalTender",
                "Heff1234@gmail.com",
                "Mother Heffington",
                "3061234567",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        PlayerList = new Player[] {validPlayer1, validPlayer2, validPlayer3,validPlayer4,validPlayer5,validPlayer6,validPlayer7,validPlayer8};

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 8; i++){
            pc.addPlayer(PlayerList[i]);
            pc.addPlayerToTeam(PlayerList[i],testTeam);
        }

        return gameList;
    }
}
