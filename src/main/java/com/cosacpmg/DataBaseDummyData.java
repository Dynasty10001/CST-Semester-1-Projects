package com.cosacpmg;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import controllers.GameController;
import controllers.PlayerController;
import controllers.TeamController;
import controllers.TournamentController;
import models.Game;
import models.Player;
import models.Team;
import models.Tournament;
import views.RosterPopup;

import java.sql.SQLException;

public class DataBaseDummyData {

    static TeamController tc = null;
    static GameController gc = null;
    static TournamentController TUC = null;
    static PlayerController pc = null;

    public static void PlaceDummyData() throws SQLException {

        ConnectionSource dbConn = App.connection;
        try {
            pc = new PlayerController(dbConn);
            tc = new TeamController(dbConn);
            gc = new GameController(dbConn);
            TUC = new TournamentController(dbConn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableUtils.clearTable(dbConn, Tournament.class);
        TableUtils.clearTable(dbConn,Game.class);
        TableUtils.clearTable(dbConn,Team.class);
        TableUtils.clearTable(dbConn,Player.class);

        TUC.createTournament("Dummy");
        Team testTeam = null;
        Player validPlayer1 = null;
        Player validPlayer2 = null;
        Player validPlayer3 = null;
        Player validPlayer4 = null;
        Player validPlayer5 = null;
        Player validPlayer6 = null;
        Player validPlayer7 = null;
        Player validPlayer8 = null;
        Player[] playerList = new Player[] {validPlayer1, validPlayer2, validPlayer3,validPlayer4,validPlayer5,validPlayer6,validPlayer7,validPlayer8};

        CreateTeams(testTeam, playerList);



    }

    private static void CreateTeams(Team testTeam, Player[] playerList)
    {
        CreateHuffs(testTeam,playerList);
        CreateRWBY(testTeam,playerList);
        CreateOnePiece(testTeam,playerList);
    }

    private static void CreateHuffs(Team testTeam, Player[] playerList) {
        testTeam = TeamController.createTeam(
                "Heffs",
                "Heffington",
                "brighton",
                "Coach Coachington",
                "555 555 1234");

        playerList[0] = PlayerController.createPlayer(
                "Heff",
                "Heffington",
                10,
                "Forward",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[1] = PlayerController.createPlayer(
                "John",
                "Heffington",
                15,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[2] = PlayerController.createPlayer(
                "Ethan",
                "Heffington",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[3] = PlayerController.createPlayer(
                "Levi",
                "Heffington",
                13,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");
        playerList[4] = PlayerController.createPlayer(
                "Zach",
                "Heffington",
                99,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");
        playerList[5] = PlayerController.createPlayer(
                "Ernesto",
                "Heffington",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[6] = PlayerController.createPlayer(
                "NoYou",
                "Heffington",
                69,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[7] = PlayerController.createPlayer(
                "Bob",
                "Heffington",
                17,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Mother Heffington",
                "MotherHeffington@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Saskatoon",
                "Saskatchewan",
                "S7V0A1");

        playerList[7].setAssignPosition("GoalTender");
        playerList[1].setAssignPosition("Forward");
        playerList[5].setAssignPosition("Defence");

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 8; i++){
            pc.addPlayer(playerList[i]);
            pc.addPlayerToTeam(playerList[i],testTeam);
        }
    }

    private static void CreateRWBY(Team testTeam, Player[] playerList) {
        testTeam = TeamController.createTeam(
                "Hunters",
                "Vale",
                "Beacon",
                "Coach Coachington",
                "555 555 1234");

        playerList[0] = PlayerController.createPlayer(
                "Ruby",
                "Rose",
                1,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Signal",
                "Vale",
                "S7V0A1");

        playerList[1] = PlayerController.createPlayer(
                "Weiss",
                "Schnee",
                15,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Atlas",
                "Atlas",
                "S7V0A1");


        playerList[2] = PlayerController.createPlayer(
                "Blake",
                "Belladonna",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Menagerie",
                "Menagerie",
                "S7V0A1");

        playerList[3] = PlayerController.createPlayer(
                "Yang",
                "Xiao-Long",
                13,
                "Forward",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Signal",
                "Vale",
                "S7V0A1");

        playerList[7] = PlayerController.createPlayer(
                "Nora",
                "Valkyrie",
                99,
                "Forward",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Kuroyuri",
                "Mistral",
                "S7V0A1");

        playerList[5] = PlayerController.createPlayer(
                "Lie",
                "Ren",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Kuroyuri",
                "Mistral",
                "S7V0A1");

        playerList[6] = PlayerController.createPlayer(
                "Pyrrha",
                "Nikos",
                69,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Argus",
                "Atlas",
                "S7V0A1");

        playerList[4] = PlayerController.createPlayer(
                "Jaune",
                "Arc",
                17,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Ansel",
                "Vale",
                "S7V0A1");

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 7; i++) {
            playerList[i].setAssignPosition(playerList[i].getPosition());
        }

        for (int i = 0; i < 8; i++){
            pc.addPlayer(playerList[i]);
            pc.addPlayerToTeam(playerList[i],testTeam);
        }

    }

    private static void CreateOnePiece(Team testTeam, Player[] playerList) {
        testTeam = TeamController.createTeam(
                "StrawhatCrew",
                "Pirates",
                "GrandLine",
                "Coach Coachington",
                "555 555 1234");

        playerList[0] = PlayerController.createPlayer(
                "Luffy",
                "D. Monkey",
                1,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Foosha",
                "East Blue",
                "S7V0A1");

        playerList[1] = PlayerController.createPlayer(
                "Roronoa",
                "Zoro",
                15,
                "Forward",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Shimotsuki",
                "East Blue",
                "S7V0A1");


        playerList[2] = PlayerController.createPlayer(
                "Sanji",
                "Vinsmoke",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Germa",
                "North Blue",
                "S7V0A1");

        playerList[3] = PlayerController.createPlayer(
                "Nami",
                "Bell-mere",
                13,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Cocoyasi",
                "East Blue",
                "S7V0A1");

        playerList[4] = PlayerController.createPlayer(
                "Usopp",
                "Sniper-King",
                99,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Syrup Village",
                "East Blue",
                "S7V0A1");

        playerList[5] = PlayerController.createPlayer(
                "Tony Tony",
                "Chopper",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Drum Island",
                "Grand Line",
                "S7V0A1");

        playerList[7] = PlayerController.createPlayer(
                "Franky",
                "Flam",
                69,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Water 7",
                "South Blue",
                "S7V0A1");

        playerList[6] = PlayerController.createPlayer(
                "Nico",
                "Robin",
                17,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Ohara",
                "West Blue",
                "S7V0A1");

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 7; i++) {
            playerList[i].setAssignPosition(playerList[i].getPosition());
        }

        for (int i = 0; i < 8; i++){
            pc.addPlayer(playerList[i]);
            pc.addPlayerToTeam(playerList[i],testTeam);
        }

    }
}


