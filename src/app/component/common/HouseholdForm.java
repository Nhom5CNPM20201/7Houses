package app.component.common;

import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;

public class HouseholdForm {
    @FXML
    private TextField idHousehold;

    @FXML
    private TextField address;

    @FXML
    private TextField householdName;

    @FXML
    private Button plusButton;

    @FXML
    private ButtonType cancelButtonType;

    @FXML
    private ButtonType okButtonType;

    @FXML
    private SubScene householdScene;


    private void switchView(URL FXMLname) {
        ScreenController.activeSubscreen(this.householdScene, FXMLname);
    }

    private void plusOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("+/+.fxml"));
    }

    public void okOnClick(ActionEvent event){
        String id = idHousehold.getText();
        String houseHoldName = householdName.getText();
    }
}
