package com.cosacpmg;

public class Team
{
	private String teamName, city, area, coachName, coachNumber;
//	ArrayList<Player> playerList;

	public Team(String teamName, String city, String area, String coachName, String coachNumber)
	{
		this.teamName = teamName;
		this.city = city;
		this.area = area;
		this.coachName = coachName;
		this.coachNumber = coachNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getCity() {
		return city;
	}

	public String getArea() {
		return area;
	}

	public String getCoachName() {
		return coachName;
	}

	public String getCoachNumber() {
		return coachNumber;
	}


}
