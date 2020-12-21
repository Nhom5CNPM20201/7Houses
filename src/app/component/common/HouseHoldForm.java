package app.component.common;

import app.entity.Address;
import app.entity.HouseHold;
import app.entity.People;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    @FXML
    private TableView tblListPeople;

    @FXML
    private TableColumn<People, String> peopleNo;

    @FXML
    private TableColumn<People, String> peopleName;

    @FXML
    private TableColumn<People, String> idCardNo;

    private Stage stage;

    private AddressForm addressController;

    private Address newAddress;

    private HouseHold newHouseHold;

    private People selectedPeople;

    private boolean isUpdate = false;

    private Button btnFee;
    @FXML
    private Button btnHouseHold;
    @FXML
    private Button btnPeople;
    @FXML
    private Button btnStatistic;
    @FXML
    private Button btnInformation;
    @FXML
    private Button btnSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        peopleNo.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        peopleName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFullName()));
        idCardNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentityNo()));

        tblListPeople.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<People>() {
            @Override
            public void changed(ObservableValue<? extends People> observableValue, People people, People t1) {
                selectedPeople = (People) tblListPeople.getSelectionModel().getSelectedItem();
                if (selectedPeople != null)
                    name.setText(selectedPeople.getFullName());
            }
        });

        tblListPeople.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getPeopleService().getAllPeople()));

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

    public void update(HouseHold houseHold) {
        this.isUpdate = true;
        this.newHouseHold = houseHold;

        houseHoldNo.setText(houseHold.getHouseHoldBook());
        tblListPeople.getSelectionModel().select(ServiceFactory.getPeopleService().getPeople(houseHold.getIdOwner()));
        newAddress = ServiceFactory.getAddressService().getAddress(houseHold.getIdAddress());
        address.setText(houseHold.getAddressDetail());
    }

    @FXML
    private void plusOnClick(ActionEvent event) throws IOException {
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
    public void searchOnClick(ActionEvent event) {
        String query = name.getText();
        tblListPeople.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getPeopleService().searchPeopleFull(query)));
    }

    @FXML
    public void okOnClick(ActionEvent event) {
        // validate
    	Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    	Matcher m = p.matcher(houseHoldNo.getText());
    	boolean b = m.find();

    	if (b) {
    		NotiService.info("Tên sổ hộ khẩu không được chứa kí tự");
    		return;
    	}	   
    	
        if (selectedPeople == null) {
            NotiService.info("Bạn chưa chọn thông tin chủ hộ.");
            return;
        }

        if (newAddress == null) {
            NotiService.info("Bạn chưa nhập thông tin địa chỉ.");
            return;
        }

        newAddress = ServiceFactory.getAddressService().createAddress(newAddress);

        newHouseHold = new HouseHold();
        newHouseHold.setIdAddress(newAddress.getId());
        newHouseHold.setAddressDetail(newAddress.getDetail());

        newHouseHold.setHouseHoldBook(houseHoldNo.getText());
        newHouseHold.setIdOwner(selectedPeople.getId());
        newHouseHold.setName(selectedPeople.getFullName());
        HouseHold addedHouseHold;

        if (isUpdate) {
            addedHouseHold = ServiceFactory.getHouseHoldService().updateHouseHold(newHouseHold);
        } else {
            addedHouseHold = ServiceFactory.getHouseHoldService().createHouseHold(newHouseHold);
        }
        if (addedHouseHold != null) {
            Mediator.Notify("houseHoldOnClick");
        } else {
            NotiService.info("Vui lòng kiểm tra lại thông tin.");
        }
    }

    @FXML
    public void cancelOnClick(ActionEvent event){
        Mediator.Notify("houseHoldOnClick");
    }
}
