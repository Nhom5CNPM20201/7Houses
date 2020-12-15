package app.component.common;

import app.entity.Fee;
import app.services.FeeService;
import app.services.ServiceFactory;
import app.services.common.NotiService;
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
    private final String DONG_GOP = "Đóng góp";
    private final String PHI = "Phí";

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
    private ObservableList<String> list1 = FXCollections.observableArrayList(PHI,DONG_GOP);

    @FXML
    void onSelectType(ActionEvent event) {

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
        try {
            String feeName;
            int type_fee;
            int money = 0;
            String note;
            String type_of_fee;

            type_of_fee = comboBoxOption1.getValue();
            if (type_of_fee.equals(DONG_GOP)) {
                type_fee = 1;
            } else if (type_of_fee.equals(PHI)) {
                type_fee = 0;
            } else type_fee = -1;

//            if (feeNameField.getText() == null || moneyField.getText() == null || noteArea.getText() == null || type_fee == -1)
//                NotiService.info("Nhập đầy đủ thông tin");
//            else {

            if(feeNameField.getText() == "" ){
                NotiService.info("Nhập đầy đủ thông tin");
                return;
            }
            if(moneyField.getText() == ""){
                NotiService.info("Nhập đầy đủ thông tin");
                return;
            }
            if(type_fee == -1){
                NotiService.info("Nhập đầy đủ thông tin");
                return;
            }
            feeName = feeNameField.getText();
                try {
                    money = Integer.parseInt(moneyField.getText());
                    if (money <= 0) {
                        NotiService.info("Số tiền là số nguyên dương");
                        return;
                    }
                } catch (Exception e) {
                    NotiService.info("Số tiền là số nguyên ");
                    return;
                }
                note = noteArea.getText();
                if (note == null) note = "";

                FeeService fee_service = ServiceFactory.getFeeService();
                Fee fee = new Fee(type_fee, feeName, money, note);
                fee_service.createFee(fee);

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            //}
        }
        catch (Exception e){
            NotiService.error("Nhập đầy đủ thông tin");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //feeList = FXCollections.observableArrayList(ServiceFactory.getFeeService().getAllFeeDetail());
        comboBoxOption1.setItems(list1);
    }
}
