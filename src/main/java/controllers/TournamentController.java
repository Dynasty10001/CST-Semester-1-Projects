package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Team;
import models.Tournament;

import java.sql.SQLException;

public class TournamentController
{
//	Tournament currentTournament;
	
	public Dao<Tournament, Long> repo;
	
	
	public TournamentController()
	{

	}
	
	public TournamentController(ConnectionSource connection)
	{
		try
		{
			this.repo = DaoManager.createDao(connection, Tournament.class);
			repo.setAutoCommit(connection.getReadWriteConnection("tournaments"), true);
			TableUtils.createTableIfNotExists(connection, Tournament.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Team addTeam(Team team)
	{
		Team rTeam = null;
		try
		{

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}


		return rTeam;

	}
	
	
	
	
	
}
