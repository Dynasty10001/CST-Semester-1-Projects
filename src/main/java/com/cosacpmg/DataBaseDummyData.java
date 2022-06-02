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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        Date date = time.getTime();

        TableUtils.clearTable(dbConn, Tournament.class);
        TableUtils.clearTable(dbConn,Game.class);
        TableUtils.clearTable(dbConn,Team.class);
        TableUtils.clearTable(dbConn,Player.class);





        Player validPlayer1 = null;
        Player validPlayer2 = null;
        Player validPlayer3 = null;
        Player validPlayer4 = null;
        Player validPlayer5 = null;
        Player validPlayer6 = null;
        Player validPlayer7 = null;
        Player validPlayer8 = null;
        Player[] playerList = new Player[] {validPlayer1, validPlayer2, validPlayer3,validPlayer4,validPlayer5,validPlayer6,validPlayer7,validPlayer8};

        ArrayList<Team> teams= CreateTeams(playerList);

        CreateGames(teams);

    }

    private static void CreateGames(ArrayList<Team> teams) throws SQLException {

        Calendar time = Calendar.getInstance();
        time.set(2022, Calendar.JUNE,10);
        time.add(Calendar.HOUR_OF_DAY, 10);
        Date date = time.getTime();
        Tournament dummy = TUC.createTournament("Dummy",date,date );
        TUC.addTournament(dummy);

        ArrayList<Game> schedule = new ArrayList<>();

        String location1 = "field1";
        String location2 = "field2";
        int x = 0;
        schedule.add(gc.createGame(teams.get(x++), teams.get(x++), date, location1, dummy));
        schedule.add(gc.createGame(teams.get(x++), teams.get(x++), date, location2, dummy));

        x = 1;
        date.setTime(date.getTime()+6000000);
        schedule.add(gc.createGame(teams.get(x++), teams.get(x++), date, location1, dummy));
        schedule.add(gc.createGame(teams.get(x++), teams.get(0), date, location2, dummy));

        date.setTime(date.getTime()+6000000);
        schedule.add(gc.createGame(teams.get(1), teams.get(3), date, location1, dummy));
        schedule.add(gc.createGame(teams.get(2), teams.get(0), date, location2, dummy));

        x = 0;
        schedule.get(x).setPlayed(true);
        schedule.get(x++).setWinners(-1);
        schedule.get(x).setPlayed(true);
        schedule.get(x++).setWinners(1);
        schedule.get(x).setPlayed(true);
        schedule.get(x++).setWinners(1);
        schedule.get(x).setPlayed(true);
        schedule.get(x++).setWinners(1);
        schedule.get(x).setPlayed(true);
        schedule.get(x).setWinners(0);

        for (int i = 0; i < schedule.size(); i++)
        {
            gc.addGame(schedule.get(i));
        }
    }

    private static ArrayList<Team> CreateTeams(Player[] playerList)
    {
        ArrayList<Team> teams = new ArrayList<>();

        teams.add(CreateHuffs(playerList));
        teams.add(CreateRWBY(playerList));
        teams.add(CreateOnePiece(playerList));
        teams.add(CreateFastFood(playerList));

        return teams;
    }

    private static Team CreateHuffs(Player[] playerList) {
        Team testTeam = TeamController.createTeam(
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

        //playerList[7].setAssignPosition("GoalTender");
        //playerList[1].setAssignPosition("Forward");
        //playerList[5].setAssignPosition("Defence");

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 8; i++){
            pc.addPlayer(playerList[i]);
            pc.addPlayerToTeam(playerList[i], testTeam);
        }

        return testTeam;
    }

    private static Team CreateRWBY(Player[] playerList) {

        Team testTeam = TeamController.createTeam(
                "Hunteresses",
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
            pc.addPlayerToTeam(playerList[i], testTeam);
        }

        return testTeam;
    }

    private static Team CreateOnePiece( Player[] playerList) {
        Team testTeam = TeamController.createTeam(
                "StrawHat Crew",
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

        return testTeam;
    }

    private static Team CreateFastFood(Player[] playerList) {
        Team testTeam = TeamController.createTeam(
                "FFFD",
                "Everywhere",
                "America",
                "Coach Coachington",
                "555 555 1234");

        playerList[1] = PlayerController.createPlayer(
                "Mac",
                "Donalds",
                15,
                "Forward",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");


        playerList[2] = PlayerController.createPlayer(
                "Dairy",
                "Queen",
                7,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");

        playerList[3] = PlayerController.createPlayer(
                "Fat",
                "Burger",
                13,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");

        playerList[4] = PlayerController.createPlayer(
                "Wendy",
                "Eeeez",
                99,
                "Defence",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");

        playerList[5] = PlayerController.createPlayer(
                "Allan",
                "& Wright",
                72,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");

        playerList[7] = PlayerController.createPlayer(
                "Five",
                "Guys",
                69,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "New York",
                "New York",
                "S7V0A1");

        playerList[6] = PlayerController.createPlayer(
                "Kentucky",
                "FriedChicken",
                17,
                "Midfield",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Frankfort",
                "Kentucky",
                "S7V0A1");

        playerList[0] = PlayerController.createPlayer(
                "Tim Horton",
                "Robin",
                17,
                "GoalTender",
                "Heff1234@gmail.com",
                "3061234567",
                "Ozpin",
                "Ozpinn@gmail.com",
                "3061234567",
                "123 Fake Street",
                "Toronto",
                "Ontario",
                "S7V0A1");

        tc.addTeam(testTeam);
        RosterPopup.setCurrentTeam(testTeam);

        for (int i = 0; i < 7; i++) {
            playerList[i].setAssignPosition(playerList[i].getPosition());
        }

        for (int i = 0; i < 8; i++){
            pc.addPlayer(playerList[i]);
            pc.addPlayerToTeam(playerList[i], testTeam);
        }

        pc.addPlayer(
                PlayerController.createPlayer(
                        "Burger",
                        "King",
                        1,
                        "GoalTender",
                        "Heff1234@gmail.com",
                        "3061234567",
                        "Ozpin",
                        "Ozpinn@gmail.com",
                        "3061234567",
                        "123 Fake Street",
                        "New York",
                        "New Yorke",
                        "S7V0A1")
                );

        return testTeam;
    }
}


