package models;

import com.cosacpmg.App;
import controllers.GameController;

public class StandingsEntry
{
	
	
	
	Team team;
	int score, wins, losses, ties;

	public StandingsEntry(Team team)
	{

		this.team = team;

		GameController gc = new GameController(App.connection);

		this.wins = gc.queryWins(team);
		this.losses = gc.queryLosses(team);
		this.ties = gc.queryTies(team);

		this.score = GameController.computeScore(wins, losses, ties);
		
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getWins()
	{
		return wins;
	}
	
	public int getLosses()
	{
		return losses;
	}
	
	public int getTies()
	{
		return ties;
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	@Override
	public String toString() {
		return team.getCity() + " " + team.getTeamName();
	}
}
