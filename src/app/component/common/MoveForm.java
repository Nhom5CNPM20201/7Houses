package app.component.common;

import app.entity.Address;
import app.entity.HouseHold;
import app.entity.Move;
import app.helper.ValidateHelper;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
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
import java.util.ResourceBundle;

public class MoveForm implements Initializable {
    private final String CHUYEN_DEN = "Chuyển đến";
    private final String CHUYEN_DI = "Chuyển đi";
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;
    @FXML
    private TextField tfAddressDes;
    @FXML
    private Button btnAdd;

    @FXML
    private TextField tfAddressCur;

    private Stage stage;

    private AddressForm addressController;
    @FXML
    private DatePicker dateMove;

    @FXML
    private ComboBox<String> moveType;
    public ObservableList<String> moveTypeList = FXCollections.observableArrayList(CHUYEN_DI,CHUYEN_DEN);

    @FXML
    private TableView<HouseHold> tblHouseHold;

    @FXML
    private TableColumn<HouseHold, String> houseHoldNo;

    @FXML
    private TableColumn<HouseHold, String> houseHoldName;

    private Button btnCancel;
    private Address newAddress;
    @FXML
    private TableColumn<HouseHold, String> houseHoldAddress;

    private ObservableList<HouseHold> houseHolds;

    private HouseHold selectedHouseHold;

    public MoveForm() {
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getAllHouseHold());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveType.setItems(moveTypeList);

        // household table
        houseHoldNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        houseHoldName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        houseHoldAddress.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));

        tblHouseHold.getItems().setAll(houseHolds);

        tblHouseHold.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HouseHold>() {
            @Override
            public void changed(ObservableValue<? extends HouseHold> observableValue, HouseHold houseHold, HouseHold t1) {
                selectedHouseHold = tblHouseHold.getSelectionModel().getSelectedItem();
                if (selectedHouseHold != null)
                {
                    tfSearch.setText(selectedHouseHold.getName());
                    tfAddressCur.setText(selectedHouseHold.getAddressDetail());
                }
            }
        });

        Mediator.unSubscribe("onCloseAddAddress");
        Mediator.subscribe("onCloseAddAddress", this::onCloseAddAddress);
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

    private void onCloseAddAddress(ActionEvent e) {
        this.newAddress = this.addressController != null ? this.addressController.getNewAddress() : null;

        if (newAddress != null) {
            tfAddressDes.setText(newAddress.getDetail());
        }
        this.stage.close();
    }

    @FXML
    void cancelOnclick(ActionEvent event) {
        Mediator.Notify("houseHoldOnClick");
    }

    @FXML
    void okOnclick(ActionEvent event) {
        try {
            if (newAddress  == null) throw new Exception("Bạn chưa nhập địa chỉ mới");
            int moveTypeIndex = moveType.getSelectionModel().getSelectedIndex();
            if (moveTypeIndex < 0) throw new Exception("Bạn chưa chọn thể loại chuyển đi");
            if (selectedHouseHold == null) throw new Exception("Bạn chưa chọn thông tin về họ khẩu");

            newAddress = ServiceFactory.getAddressService().createAddress(newAddress);

            Move move = new Move();
            move.setIdHouseHold(selectedHouseHold.getId());
            move.setIdOldAddress(selectedHouseHold.getIdAddress());
            move.setMovingDate(ValidateHelper.validateDate(dateMove.getValue()));
            move.setIdNewAddress(newAddress.getId());
            move.setType(moveTypeIndex);

            ServiceFactory.getMoveService().createMove(move);
            Mediator.Notify("houseHoldOnClick");
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    @FXML
    void searchOnClick(ActionEvent event) {
        String query = tfSearch.getText();
        this.tblHouseHold.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getHouseHoldService().searchHouseHoldFull(query)));
    }
}
