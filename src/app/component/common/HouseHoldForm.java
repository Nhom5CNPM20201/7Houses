package app.component.common;

import app.entity.Address;
import app.entity.HouseHold;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HouseHoldForm implements Initializable {
    @FXML
    private TextField houseHoldNo;

    @FXML
    public TextField address;

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

    private Stage stage;

    private AddressForm addressController;

    private Address newAddress;

    private HouseHold newHouseHold;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Mediator.unSubscribe("onCloseAddAddress");
        Mediator.subscribe("onCloseAddAddress", event -> onCloseAddAddress(null));
    }

    private void onCloseAddAddress(ActionEvent e) {
        this.newAddress = this.addressController != null ? this.addressController.getNewAddress() : null;

        if (newAddress != null) {
            address.setText(newAddress.getDetail());
        }
        this.stage.close();
    }

    @FXML
    private void plusOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressForm.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 395, 422);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

        this.addressController = loader.getController();
    }
    @FXML
    public void okOnClick(ActionEvent event) {
        // validate
        if (newAddress != null) {
            newAddress = ServiceFactory.getAddressService().createAddress(newAddress);
        }

        newHouseHold = new HouseHold();
        newHouseHold.setIdAddress(newAddress.getId());
        newHouseHold.setAddressDetail(newAddress.getDetail());

        newHouseHold.setHouseHoldBook(houseHoldNo.getText());
        newHouseHold.setName(name.getText());
        HouseHold addedHouseHold = ServiceFactory.getHouseHoldService().createHouseHold(newHouseHold);
        if (addedHouseHold != null) {
            Mediator.Notify("houseHoldOnClick");
        }
    }

    @FXML
    public void cancelOnClick(ActionEvent event){
        Mediator.Notify("houseHoldOnClick");
    }
}
