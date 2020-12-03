package app.component.common;

import app.entity.Fee;
import app.services.FeeService;
import app.services.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private void showAlert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Lỗi dữ liệu");
        alert.setContentText( text);
        alert.show();
    }


    @FXML
    void cancelAddOnClick(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void okOnClick(ActionEvent event){
        String feeName;
        int type_fee;
        int money = 0;
        String note;
        String type_of_fee;

        type_of_fee = comboBoxOption1.getValue();
        if(type_of_fee == "Đóng góp"){
            type_fee = 1;
        }
        else if(type_of_fee == "Phí"){
            type_fee = 0;
        }
        else type_fee = -1;

        if(feeNameField.getText() == null || moneyField.getText() == null || noteArea.getText() == null || type_fee == -1)
            showAlert("Nhập đầy đủ dữ liệu");



        else {

            feeName = feeNameField.getText();
            try {
                money = Integer.parseInt(moneyField.getText());
                if(money <= 0 ) {
                    showAlert("Số tiền là số nguyên dương");
                    return;
                }
            }catch (Exception e){
                showAlert("Số tiền là số nguyên ");
                return;
            }
        note = noteArea.getText();
        if(note == null) note = "";




            FeeService fee_service = ServiceFactory.getFeeService();
            Fee fee = new Fee(-3, type_fee, feeName, money, note);
            fee_service.createFee(fee);
            fee_service.getAllFee();

            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxOption1.setItems(list1);
        comboBoxOption2.setItems(list2);


    }
}
