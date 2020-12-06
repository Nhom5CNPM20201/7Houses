package app.component.common;

import app.entity.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HouseHoldForm {
    public static Address add;

    @FXML
    private TextField idHouseHold;

    @FXML
    public static TextField address;

    @FXML
    private TextField name;

    @FXML
    private Button plusButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private SubScene householdScene;


    @FXML
    private void plusOnClick(ActionEvent event) throws IOException {
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("AddressForm.fxml"));
        Scene scene = new Scene(root, 395, 422);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void okOnClick(ActionEvent event) {
        if(idHouseHold.getText().isEmpty() || address.getText().isEmpty() || name.getText().isEmpty()){
            okButton.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification Alert");
            alert.show();
        }else{
            okButton.setVisible(true);
        }
        Platform.exit();
    }


    @FXML
    void cancelOnClick(ActionEvent event){
        Platform.exit();
    }
}
