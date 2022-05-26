package views;

import com.cosacpmg.App;
import controllers.PlayerController;
import controllers.TeamController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Player;
import models.Team;
import models.ValidationHelper;

import java.io.IOException;
import java.util.HashMap;


public class AddPlayerView {

    private Player player;


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


    @FXML
    protected void initialize()
    {
        cbTeam.getItems().setAll(new TeamController(App.connection).getAllTeams());
    }

    @FXML
    protected void addPlayerSubmitHandler()
    {
        ValidationHelper vh = new ValidationHelper();
        player = PlayerController.createPlayer(tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfJersey.getText()),
                cbPosition.getValue().toString(), tfEmail.getText(), tfPhone.getText(), tfEName.getText(), tfEPhone.getText(), tfEEmail.getText(),
                tfStreet.getText(), tfCity.getText(), cbProvince.getValue().toString(), tfPostalCode.getText());
//        (Team)(cbTeam.getValue()).
//        player.setTeam(cbTeam.getSelectionModel().getSelectedItem());
//fixme add team stuff
        HashMap<String, String> error = vh.getErrors(player);

        lblERRFirstName.setText(error.get("firstName"));
        lblERRLastName.setText(error.get("lastName"));
        lblERREmail.setText(error.get("email"));
        lblERRPhone.setText(error.get("phoneNumber"));
        lblERRJersey.setText(error.get("jerseyNo"));
        lblERRCity.setText(error.get("city"));
        lblERRStreet.setText(error.get("streetAddress"));
        lblERRPostalCode.setText(error.get("postalCode"));
        lblERREName.setText(error.get("emergencyName"));
        lblERREPhone.setText(error.get("emergencyPhoneNumber"));
        lblERREEmail.setText(error.get("emergencyEmail"));
        lblERRProvince.setText(error.get("province"));
        lblERRTeam.setText(error.get("team"));
        lblERRPosition.setText(error.get("position"));
        
        if (error.isEmpty())
        {
    
            PlayerController pc = new PlayerController(App.connection);
            pc.addPlayer(player);

            tfFirstName.setText("");
            tfLastName.setText("");
            tfEmail.setText("");
            tfPhone.setText("");
            tfJersey.setText("");
            tfCity.setText("");
            tfStreet.setText("");
            tfPostalCode.setText("");
            tfEName.setText("");
            tfEPhone.setText("");
            tfEEmail.setText("");
        }
        

    }

    @FXML
    protected void addPlayerCancelHandler() throws IOException
    {

    }

}
