package app.component.common;

import app.entity.Contribute;
import app.entity.Fee;
import app.entity.HouseHold;
import app.entity.People;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.net.URL;
import java.util.ResourceBundle;

public class ContributeForm implements Initializable {
    @FXML
    private TextField chuHoTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextArea noteTextArea;

    @FXML
    private ComboBox<String> comboBoxOption1;
    ObservableList<String> list1;

    @FXML
    private TableView tblListHouseHold;
    @FXML
    private TableColumn<HouseHold, String> noColumn;
    @FXML
    private TableColumn<HouseHold, String> holderNameColumn;
    @FXML
    private TableColumn<HouseHold, String> addressColumn;

    private ObservableList<HouseHold> houseHolds;

    private HouseHold selectedHouseHold;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list1 = FXCollections.observableArrayList(ServiceFactory.getFeeService().getAllFeeDetail());
        comboBoxOption1.setItems(list1);
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getAllHouseHold());

        // household table
        noColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        holderNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        addressColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));

        tblListHouseHold.getItems().setAll(houseHolds);

        tblListHouseHold.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HouseHold>() {
            @Override
            public void changed(ObservableValue<? extends HouseHold> observableValue, HouseHold people, HouseHold t1) {
                selectedHouseHold = (HouseHold) tblListHouseHold.getSelectionModel().getSelectedItem();
                if (selectedHouseHold != null)
                    chuHoTextField.setText(selectedHouseHold.getName());
            }
        });
    }

    @FXML
    void optionOnAction1(ActionEvent event) {

    }

    @FXML
    void cancelAddOnClick(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void searchOnClick(ActionEvent event) {
        String query = chuHoTextField.getText();
        tblListHouseHold.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getHouseHoldService().searchHouseHoldFull(query)));
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        try {
            int selectedFeeIndex = comboBoxOption1.getSelectionModel().getSelectedIndex();
            if (selectedFeeIndex < 0) {
                NotiService.info("Bạn chưa chọn thông tin khoản phí");
                return;
            }

            Fee selectedFee = ServiceFactory.getFeeService().getFee(selectedFeeIndex + 1);
            if (selectedFee == null) {
                NotiService.error("Không tìm thấy thông tin khoản phí");
                return;
            }

            if (selectedHouseHold == null) {
                NotiService.info("Chưa có thông tin hộ khẩu");
                return;
            }

            int amount = Integer.parseInt(amountTextField.getText());
            String note = noteTextArea.getText();

            Contribute contribute = new Contribute();
            contribute.setHouseHold(selectedHouseHold);
            contribute.setFee(selectedFee);
            contribute.setAmount(amount);
            contribute.setNote(note);

            ServiceFactory.getContributeService().createContribute(contribute);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            Mediator.Notify("feeOnClick");
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }
}
