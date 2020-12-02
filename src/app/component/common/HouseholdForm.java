package app.component.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Platform;

public class HouseholdForm {
    @FXML
    private TextField idHouseHold;

    @FXML
    private TextField address;

    @FXML
    private TextField houseHoldName;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    public void okOnClick(ActionEvent event){

    }

    @FXML
    void cancelOnClick(ActionEvent event) {
        Flatform.exit();
    }

}
