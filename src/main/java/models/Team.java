package models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "teams")
public class Team implements Serializable
{
	
	
	@DatabaseField
	private String teamName;
	@DatabaseField
	private String city;
	@DatabaseField
	private String area;
	@DatabaseField
	private String coachName;
	@DatabaseField
	private String coachNumber;
	
//	ArrayList<Player> playerList;
	
	
	public Team() {
	}
	
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setArea(String area)
	{
		this.area = area;
	}
	
	public void setCoachName(String coachName)
	{
		this.coachName = coachName;
	}
	
	public void setCoachNumber(String coachNumber)
	{
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
