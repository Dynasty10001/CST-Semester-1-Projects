package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;
import models.ValidationHelper;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamController
{
	public Dao<Team, Long> repo;

	public TeamController() {
	}



	public TeamController(ConnectionSource connection)
	{
		try
		{
			this.repo = DaoManager.createDao(connection,Team.class);
			repo.setAutoCommit(connection.getReadWriteConnection("Team"), true);
			TableUtils.createTableIfNotExists(connection,Team.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method gets a team from the database by matching with the supplied search object, any null or empty
	 * valies are ignored.
	 * @param searchObject
	 * @return
	 */
	public ArrayList<Team> getTeam(Team searchObject)
	{

		try
		{

			return (ArrayList<Team>) repo.queryForMatching(searchObject);


		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * A method that gets all teams from the database
	 * @return
	 */
	public ArrayList<Team> getAllTeams()
	{
		try
		{
			return (ArrayList<Team>) repo.queryForAll();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * this method takes in a team and adds it to the database
	 * @param team
	 * @return
	 */
	public Team addTeam(Team team)
	{
		try
		{
			repo.create(team);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return team;
	}

	/**
	 * This is a 'factory' method that will create a team
	 * @param teamName
	 * @param city
	 * @param area
	 * @param coachName
	 * @param coachNum
	 * @return
	 */
	public static Team createTeam(String teamName, String city, String area, String coachName, String coachNum)
	{
		ValidationHelper vh = new ValidationHelper();

		Team team = new Team();
		team.setTeamName(teamName);
		team.setCity(city);
		team.setArea(area);
		team.setCoachName(coachName);
		team.setCoachNumber(coachNum);


		if(vh.getErrors(team).isEmpty()){
			return team;
		}
		else
			return null;
	}



}
