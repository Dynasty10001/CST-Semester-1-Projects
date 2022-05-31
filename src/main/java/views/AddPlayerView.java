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

    //text fields for data entry
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

    //labels for the error fields
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

    /**
     * Populates the team combobox with teams that can be chosen.
     */
    @FXML
    protected void initialize()
    {
        cbTeam.getItems().setAll(new TeamController(App.connection).getAllTeams());
    }

    /**
     * This method does data validation for adding a player. It sets error messages, if there are any, and will create the
     * player and add it to the database if there aren't.
     */
    @FXML
    protected void addPlayerSubmitHandler()
    {
        ValidationHelper vh = new ValidationHelper();
        //makes sure the jersey number is populated with a number, if not applies -1 to the final value so error is thrown properly
        String tempS = tfJersey.getText();
        int tempJ = -1;

        if (tempS.matches("^[//d]+$"))
        {
            tempJ = Integer.parseInt(tempS);

        }
        //makes sure the combo boxes are set to an empty string instead of null so errors are shown properly.
        String positionString = cbPosition.getValue() == null? "" : cbPosition.getValue().toString();
        String provinceString = cbProvince.getValue() == null? "" : cbProvince.getValue().toString();

        player = PlayerController.createPlayer(tfFirstName.getText(), tfLastName.getText(), tempJ,
                positionString, tfEmail.getText(), tfPhone.getText(), tfEName.getText(), tfEPhone.getText(), tfEEmail.getText(),
                tfStreet.getText(), tfCity.getText(), provinceString, tfPostalCode.getText());
        if (cbTeam.getValue() != null)
        {
            Team temp = (Team) cbTeam.getSelectionModel().getSelectedItem();
            player.setTeam(temp);
        }
        //creates hashmap of errors
        HashMap<String, String> error = vh.getErrors(player);

        //sets error messages if any exist
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

        //creates a player and addis it to the database if there are no errors, then clears all fields
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

    /**
     * This method is called when the cancel button is pressed. It will make the program go back to the home screen.
     * @throws IOException
     */
    @FXML
    protected void addPlayerCancelHandler() throws IOException
    {
        new HomeView().onHomeNavHandler();
    }

}
