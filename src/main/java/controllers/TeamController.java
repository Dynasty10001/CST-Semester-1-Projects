package controllers;

import models.Team;

public class TeamController
{
	
	
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
