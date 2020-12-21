package app.component.dashboard.search;

import app.component.dashboard.householdManage.HouseholdList;
import app.entity.HouseHold;
import app.entity.People;
import app.services.ServiceFactory;
import app.services.common.LogService;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import app.utility.viewUtils.ScreenController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Search implements Initializable {

    @FXML
    private ComboBox<String> comboboxOption;
    
    @FXML
    private TableView tblListHouseHold;
    @FXML
    private TableColumn<HouseHold, String> houseHoldNo;
    @FXML
    private TableColumn<HouseHold, String> holderNameColumn;
    @FXML
    private TableColumn<HouseHold, String> addressColumn;

    @FXML
    private TableView tblListPeople;
    @FXML
    private TableColumn<People, String> peopleNo;
    @FXML
    private TableColumn<People, String> peopleName;
    @FXML
    private TableColumn<People, String> idCardNo;

    @FXML
    private Button btnFind;
    @FXML
    private TextField queryTextField;
    @FXML
    private SubScene mainSearch;
    
    private Object mainSearchController;
    private final String HO_KHAU = "Hộ khẩu";
    private final String NHAN_KHAU = "Nhân khẩu";

    public Search() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxOption.setItems(FXCollections.observableArrayList(HO_KHAU, NHAN_KHAU));
        tblListPeople.setVisible(false);

        // household table
        houseHoldNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        holderNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        addressColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));

        // household table
        peopleNo.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        peopleName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFullName()));
        idCardNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentityNo()));
    }

    @FXML
    void findOnClick(ActionEvent event) {
        if (comboboxOption.getSelectionModel().getSelectedIndex() < 0) return;
        var selectedItem = comboboxOption.getSelectionModel().getSelectedItem();
        var query = queryTextField.getText().trim();
        if (query.equals("")) {
        	return;
        }

        switch(selectedItem) {
            case HO_KHAU:
                searchHouseHold(query);
                break;
            case NHAN_KHAU:
                searchPeople(query);
                break;
            default:
                break;
        }
    }

    private void searchHouseHold(String query) {
        tblListHouseHold.getItems().setAll(ServiceFactory.getHouseHoldService().searchHouseHoldFull(query));
    }

    private void searchPeople(String query) {
        tblListPeople.getItems().setAll(ServiceFactory.getPeopleService().searchPeopleFull(query));
    }

    @FXML
    void optionOnAction(ActionEvent event) {

        switch (comboboxOption.getValue()) {
            case HO_KHAU:
                tblListHouseHold.setVisible(true);
                tblListPeople.setVisible(false);
                break;
            case NHAN_KHAU:
                tblListHouseHold.setVisible(false);
                tblListPeople.setVisible(true);
                break;
        }
    }
    
//    public void onGoingSearchHouseHold(ActionEvent e) {
//        this.switchView(getClass().getResource("../householdManage/HouseholdList.fxml"));
//    }
//
//    public void onGoingSearchPeople(ActionEvent e) {
//        this.switchView(getClass().getResource("../peopleManage/PeopleList.fxml"));
//    }
//
//    private void switchView(URL FXMLname){
//    	mainSearchController = ScreenController.activeSubscreen(this.mainSearch, FXMLname);
//    }
}