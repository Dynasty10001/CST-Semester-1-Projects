package models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.validation.constraints.*;
import java.io.Serializable;

@DatabaseTable()
public class Team implements Serializable
{


	@DatabaseField(generatedId = true)
	private long Id;


	@DatabaseField(canBeNull = false)
	@Size(max = 64, message = "Validation Error: Team name has to be 64 characters or less")
	@NotEmpty(message = "Validation Error: Team name field is empty, please enter valid entry")
	@Pattern(regexp = "^[\\w!'-]*[\\w\\s'-]*[\\w!'-]*$",
			message = "Validation Error: Team name must not contain special characters (except ! and _) and must" +
					" start and end with a letter")
	private String teamName;

	@DatabaseField(canBeNull = false)
	@Size(max = 64, message = "Validation Error: City has to be 64 characters or less")
	@NotEmpty(message = "Validation Error: City field is empty, please enter valid entry")
	private String city;

	@DatabaseField(canBeNull = false)
	@Size(max = 64, message = "Validation Error: Area has to be 64 characters or less")
	@NotEmpty(message = "Validation Error: Area field is empty, please enter valid entry")
	private String area;

	@DatabaseField(canBeNull = false)
	@Size(max = 64, message = "Validation Error: Coach name has to be 64 characters or less")
	@NotEmpty(message = "Validation Error: Coach name field is empty, please enter valid entry")
	private String coachName;

	@DatabaseField(canBeNull = false)
	@NotEmpty(message = "Validation Error: Coach phone number field is empty, please enter valid entry")
	@Pattern(regexp = "^\\d{3}\\s\\d{3}\\s\\d{4}$",
			message = "Validation Error: Coach phone number must in the following format: xxx xxx xxxx")
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


	public long getId()
	{
		return Id;
	}

	@Override
	public String toString(){
		return teamName + " " + city;
	}

}
