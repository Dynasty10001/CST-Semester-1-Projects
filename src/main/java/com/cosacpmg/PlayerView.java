package com.cosacpmg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class PlayerView {
    private Player player;
    private PlayerController PC;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPhone;
    @FXML private TextField tfJersey;
    @FXML private TextField tfCity;
    @FXML private TextField tfStreet;
    @FXML private TextField tfPostalCode;
    @FXML private TextField tfEName;
    @FXML private TextField tfEPhone;
    @FXML private TextField tfEEmail;

    @FXML private ComboBox cbProvince;
    @FXML private ComboBox cbTeam;
    @FXML private ComboBox cbPosition;

    @FXML private Button cmdCancel;
    @FXML private Button cmdSubmit;

    @FXML private Label lblERRFirstName;
    @FXML private Label lblERRLastName;
    @FXML private Label lblERREmail;
    @FXML private Label lblERRPhone;
    @FXML private Label lblERRJersey;
    @FXML private Label lblERRCity;
    @FXML private Label lblERRStreet;
    @FXML private Label lblERRPostalCode;
    @FXML private Label lblERREName;
    @FXML private Label lblERREPhone;
    @FXML private Label lblERREEmail;
    @FXML private Label lblERRProvince;
    @FXML private Label lblERRTeam;
    @FXML private Label lblERRPosition;
    @FXML private Label lblERRHeader;
    @FXML private Label lblERREHeader;
}
