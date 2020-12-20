package app.component.dashboard.search;

import app.component.dashboard.householdManage.HouseholdList;
import app.entity.HouseHold;
import app.entity.People;
import app.services.ServiceFactory;
import app.services.common.LogService;
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
    private TableView tblSearch;
    @FXML
    private TableColumn<HouseHold, String> searchNo;
    @FXML
    private TableColumn<HouseHold, String> searchName;
    @FXML
    private TableColumn<HouseHold, String> searchAddress;

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

    @FXML
    void findOnClick(ActionEvent event) {
        var selectedItem = comboboxOption.getSelectionModel().getSelectedItem();
        var query = queryTextField.getText().trim();

        switch(selectedItem) {
            case HO_KHAU:
            	onGoingSearchHouseHold(event);
                searchHouseHold(query);
                break;
            case NHAN_KHAU:
            	onGoingSearchPeople(event);
                searchPeople(query);
                break;
            default:
                break;
        }
    }

    private void searchHouseHold(String query) {
        List<HouseHold> data = ServiceFactory.getHouseHoldService().searchHouseHoldFull(query);
        for (var item : data) {
            LogService.info(item.getAddressDetail());
        }
        
    }

    private void searchPeople(String query) {
        List<People> data = ServiceFactory.getPeopleService().searchPeopleFull(query);
        for (var item : data) {
            LogService.info(item.getFullName());
        }

    }

    @FXML
    void optionOnAction(ActionEvent event) {

        if(comboboxOption.getValue().equals("Tên")){
          //  System.out.println("Tìm kiếm theo tên");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxOption.setItems(FXCollections.observableArrayList(HO_KHAU, NHAN_KHAU)); 
//        onGoingSearchHouseHold(null);
    }
    
    public void onGoingSearchHouseHold(ActionEvent e) {
        this.switchView(getClass().getResource("../householdManage/HouseholdList.fxml"));
    }
    
    public void onGoingSearchPeople(ActionEvent e) {
        this.switchView(getClass().getResource("../peopleManage/PeopleList.fxml"));
    }
    
    private void switchView(URL FXMLname){
    	mainSearchController = ScreenController.activeSubscreen(this.mainSearch, FXMLname);
    }
}