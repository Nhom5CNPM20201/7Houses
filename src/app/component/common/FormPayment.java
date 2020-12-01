package app.component.common;

import app.entity.Fee;
import app.services.FeeService;
import app.services.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FormPayment implements Initializable{
    @FXML
    private TextField feeNameField;

    @FXML
    private TextField moneyField;

    @FXML
    private TextArea noteArea;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private ComboBox<String> comboBoxOption1;
    ObservableList<String> list1 = FXCollections.observableArrayList("Đóng góp","Phí");


    @FXML
    private ComboBox<String> comboBoxOption2;
    ObservableList<String> list2 = FXCollections.observableArrayList("Đóng góp theo hộ","Đóng góp theo cá nhân");



    @FXML
    void optionOnAction1(ActionEvent event) {

    }

    @FXML
    void optionOnAction2(ActionEvent event) {

    }

    @FXML
    void cancelAddOnClick(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void okOnClick(ActionEvent event){
        int type_fee;
        String feeName = feeNameField.getText();
        int money = Integer.parseInt(moneyField.getText());
        String note = noteArea.getText();
        if(note == null) note = "";
        String type_of_fee = comboBoxOption1.getValue();
        if(type_of_fee == "Đóng góp"){
            type_fee = 1;
        }
        else{
            type_fee = 0;
        }

        FeeService fee_service = ServiceFactory.getFeeService();
        Fee fee = new Fee(-1001, type_fee, feeName, money, note);
        fee_service.createFee(fee);
        fee_service.getAllFee();

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxOption1.setItems(list1);
        comboBoxOption2.setItems(list2);


    }
}
