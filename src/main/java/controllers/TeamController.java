package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;

import java.sql.SQLException;

public class TeamController
{
	public Dao<Team, Long> repo;
	
	public TeamController()
	{
	
	}
	
	
	public TeamController(ConnectionSource connection)
	{
		try
		{
			this.repo = DaoManager.createDao(connection,Team.class);
			repo.setAutoCommit(connection.getReadWriteConnection("teams"), true);
			TableUtils.createTableIfNotExists(connection,Team.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
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
		Team team = new Team();
		team.setTeamName(teamName);
		team.setCity(city);
		team.setArea(area);
		team.setCoachName(coachName);
		team.setCoachNumber(coachNum);
		return team;
	}
	
	
	
}
