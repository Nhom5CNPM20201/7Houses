package app.component.common;

import app.entity.Address;
import app.entity.People;
import app.entity.TemporaryResident;
import app.helper.ValidateHelper;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import com.sun.media.jfxmedia.Media;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class StayingTAForm implements Initializable {
    private final String TAM_CHU = "Tạm trú";
    private final String TAM_VANG = "Tạm vắng";
    @FXML
    private TextField tfSearch;

    @FXML
    private DatePicker startTime;

    @FXML
    private DatePicker endTime;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> typeCombobox;
    public ObservableList<String> list = FXCollections.observableArrayList(TAM_CHU,TAM_VANG);

    @FXML
    private TableView<People> tblListPeople;
    @FXML
    private TableColumn<People, String> peopleNo;
    @FXML
    private TableColumn<People, String> peopleName;
    @FXML
    private TableColumn<People, String> idCardNo;

    private ObservableList<People> peoples;

    private People selectedPeople;

    private Address newAddress;

    private AddressForm addressController;

    private Stage stage;

    public StayingTAForm() {
        this.peoples = FXCollections.observableArrayList(ServiceFactory.getPeopleService().getAllPeople());
    }

    @FXML
    void addOnclick(ActionEvent event) throws IOException {
        if (this.stage != null) this.stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressForm.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        this.addressController = loader.getController();
    }

    @FXML
    void cancelOnclick(ActionEvent event) {
        Mediator.Notify("peopleOnClick");
    }

    @FXML
    void okOnclick(ActionEvent event) {
        try {
            if (selectedPeople == null) throw new Exception("Vui lòng chọn nhân khẩu.");
            int typeIndex = typeCombobox.getSelectionModel().getSelectedIndex();
            if (typeIndex < 0) throw new Exception("Chưa chọn thể loại.");
            if (newAddress == null) {
                NotiService.info("Bạn chưa nhập thông tin địa chỉ.");
                return;
            }

            newAddress = ServiceFactory.getAddressService().createAddress(newAddress);

            TemporaryResident temporaryResident = new TemporaryResident();
            temporaryResident.setIdPeople(selectedPeople.getId());
            temporaryResident.setCagetory(typeIndex);
            temporaryResident.setStart(ValidateHelper.validateDate(startTime.getValue()));
            temporaryResident.setEnd(ValidateHelper.validateDate(endTime.getValue()));
            temporaryResident.setIdAddress(newAddress.getId());
            temporaryResident.setTime(new Date());

            temporaryResident = ServiceFactory.getTemporaryresidentService().createTS(temporaryResident);
            if (temporaryResident != null) {
                Mediator.Notify("peopleOnClick");
            } else {
                NotiService.info("Vui lòng kiểm tra lại thông tin.");
            }
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    @FXML
    void searchOnclick(ActionEvent event) {
        String query = tfSearch.getText();
        tblListPeople.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getPeopleService().searchPeopleFull(query)));
    }

    @FXML
    void typeCombxOnclick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCombobox.setItems(list);

        // people table
        peopleNo.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        peopleName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFullName()));
        idCardNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentityNo()));

        tblListPeople.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<People>() {
            @Override
            public void changed(ObservableValue<? extends People> observableValue, People people, People t1) {
                selectedPeople = (People) tblListPeople.getSelectionModel().getSelectedItem();
                if (selectedPeople != null)
                    tfSearch.setText(selectedPeople.getFullName());
            }
        });

        tblListPeople.getItems().setAll(peoples);

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
}
