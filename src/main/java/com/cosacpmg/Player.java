package com.cosacpmg;

public class Player {

    String firstName;
    String lastName;

    int playerId;
    int jerseyNo;
    String team;
    String position;

    String streetAddress;
    String city;
    String province;
    String postalCode;
    String phoneNumber;
    String email;

    String emergencyName;
    String emergencyEmail;
    String emergencyPhoneNumber;

    public Player(String first, String last, int jerseyNo, String pos, String street, String city, String prov, String postal, String phone, String email, String eName, String eEmail, String ePhone)
    {
        this.firstName = first;
        this.lastName = last;
        this.jerseyNo = jerseyNo;
        this.position = pos;
        this.streetAddress = street;
        this.city = city;
        this.province = prov;
        this.postalCode = postal;
        this.phoneNumber = phone;
        this.email = email;
        this.emergencyName = eName;
        this.emergencyEmail = eEmail;
        this.emergencyPhoneNumber = ePhone;
    }

    public Player()
    {
        this.firstName = "";
        this.lastName = "";
        this.jerseyNo = -1;
        this.position = "";
        this.streetAddress = "";
        this.city = "";
        this.province = ""
        this.postalCode = "";
        this.phoneNumber = "";
        this.email = "";
        this.emergencyName = "";
        this.emergencyEmail = "";
        this.emergencyPhoneNumber = "";
    }

}
