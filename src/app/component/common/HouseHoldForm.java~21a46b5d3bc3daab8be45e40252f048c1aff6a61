package app.component.common;

import app.entity.Address;
import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HouseHoldForm {
    public static Address add;
    @FXML
    private TextField idHousehold;

    @FXML
    public static TextField address;

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

    private void plusOnClick(ActionEvent event) throws IOException {
        AnchorPane root= (AnchorPane) FXMLLoader.load(getClass().getResource("AddressForm.fxml"));
        Scene scene= new Scene(root, 395, 422);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void okOnClick(ActionEvent event){
        String id = idHousehold.getText();
        String houseHoldName = householdName.getText();

    }
}
