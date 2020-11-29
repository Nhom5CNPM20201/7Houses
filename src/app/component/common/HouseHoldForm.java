package app.component.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HouseHoldForm {
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
        String text1 = idHouseHold.getText();
        String text2 = houseHoldName.getText();

    }

    public void cancelOnClick(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
