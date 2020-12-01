package app.component.common;

import app.entity.Address;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddressForm {
    public static Address newAdd;
    @FXML
    private TextField txtHouseHoldNo;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtWard;

    @FXML
    private TextField txtDistrict;

    @FXML
    private TextField txtCity;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    void onClickCancel(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onClickOK(ActionEvent event) {
        newAdd = new Address(Integer.valueOf(txtHouseHoldNo.getText()),txtStreet.getText(),
                txtWard.getText(), txtDistrict.getText(), txtCity.getText()
        );
        HouseHoldForm.add = newAdd;
        HouseHoldForm.address.setText(newAdd.getDetail());
        Platform.exit();

    }

}

