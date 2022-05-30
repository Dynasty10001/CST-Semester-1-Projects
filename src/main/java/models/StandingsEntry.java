package models;

public class StandingsEntry
{
	
	int scoreAnInt, wins, losses, ties;
	public StandingsEntry(Team testHometeam)
	{
		//todo querries the games that this team played in and set the variables in this class
		
	}
	
	public int getScoreAnInt()
	{
		return scoreAnInt;
	}
	
	public void setScoreAnInt(int scoreAnInt)
	{
		this.scoreAnInt = scoreAnInt;
	}
	
	public int getWins()
	{
		return wins;
	}
	
	public void setWins(int wins)
	{
		this.wins = wins;
	}
	
	public int getLosses()
	{
		return losses;
	}
	
	public void setLosses(int losses)
	{
		this.losses = losses;
	}
	
	public int getTies()
	{
		return ties;
	}
	
	public void setTies(int ties)
	{
		this.ties = ties;
	}
}
