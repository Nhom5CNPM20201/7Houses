package app.component.common;

import app.entity.Address;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressForm implements Initializable {
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

    private Address newAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Address getNewAddress() {
        return this.newAddress;
    }

    @FXML
    void onClickCancel(ActionEvent event) {


        Mediator.Notify("onCloseAddAddress");
    }

    @FXML
    void onClickOK(ActionEvent event) {
        if (txtHouseHoldNo.getText().isEmpty()) {
            NotiService.info("Bạn chưa chọn thông tin số nhà.");
            return;
        }

        if (txtStreet.getText().isEmpty()) {
            NotiService.info("Bạn chưa nhập thông tin đường phố.");
            return;
        }
        if(txtWard.getText().isEmpty()){
            NotiService.info("Bạn chưa nhập thông tin phường.");
        }
        if (txtDistrict.getText().isEmpty()) {
            NotiService.info("Bạn chưa nhập thông tin quận huyện.");
            return;
        }
        if(txtCity.getText().isEmpty()){
            NotiService.info("Bạn chưa nhập thông tin tỉnh, thành phố.");
            return;
        }
        Address add = new Address();
        try {
            int number=Integer.parseInt(txtHouseHoldNo.getText());
            add.setNumberHouse(number);
        }catch (NumberFormatException e){
            NotiService.info("Đã nhập sai dịnh dạng số nhà.");
            return;
        }
        add.setStreet(txtStreet.getText());
        add.setSubDistrict(txtWard.getText());
        add.setDistrict(txtDistrict.getText());
        add.setCity(txtCity.getText());
        this.newAddress = add;

        Mediator.Notify("onCloseAddAddress");
    }
}

