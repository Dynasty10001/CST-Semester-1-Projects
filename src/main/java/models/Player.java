package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

@DatabaseTable
public class Player implements Serializable {

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    private String firstName;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    private String lastName;

    @DatabaseField(generatedId = true)
    private int playerId;

    @DatabaseField(canBeNull = false)
    @Min(value = 1, message="Jersey Number Must have 2 digits")
    @Max(value = 99, message= "Jersey Number Must be an Integer between 01 and 99")
    private int jerseyNo;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "team_id")
    private Team team;

    @DatabaseField(canBeNull = false)
    @Pattern(regexp = "^(Forward|Midfield|Defence|Goalie)$", message = "Position must be one of the following: Forward, Midfield, Defence, or Goalie")
    @NotEmpty(message = "All fields must be filled out with valid information")
    private String position;

    @DatabaseField(canBeNull = false)
    @Pattern(regexp = "^(Forward|Midfield|Defence|Goalie|Substitution)$", message = "Position must be one of the following: Forward, Midfield, Defence, or Goalie")
    @NotEmpty(message = "All fields must be filled out with valid information" )
    private String assignedPosition;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    @Pattern(regexp = "^\\d+[ ]?[a-zA-Z]+.*$", message = "Invalid Street Address")
    private String streetAddress;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    private String city;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    private String province;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Pattern(regexp = "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d$", message = "Invalid Postal Code Format")
    private String postalCode;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(min = 10,max = 10, message="Invalid Phone Number")
    private String phoneNumber;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    @Email(message = "Invalid Email Format")
    private String email;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    private String emergencyName;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(max = 64, message="All fields have a max Character length of 64")
    @Email(message = "Invalid Email Format")
    private String emergencyEmail;

    @DatabaseField(canBeNull = false)
    @NotEmpty(message = "All fields must be filled out with valid information" )
    @Size(min = 10,max = 10, message="Invalid Phone Number")
    private String emergencyPhoneNumber;


    public Player()
    {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(int jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyEmail() {
        return emergencyEmail;
    }

    public void setEmergencyEmail(String emergencyEmail) {
        this.emergencyEmail = emergencyEmail;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public void setAssignPosition(String assignPosition) {
        this.assignedPosition = assignPosition;
    }

    public String getAssignPosition() {return assignedPosition;}

    /**
     * This is used by the Roster View to display the player name when placed in a list
     * @return
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

}
