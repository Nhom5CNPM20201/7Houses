package app.component.dashboard.search;

import app.entity.HouseHold;
import app.services.ServiceFactory;
import app.services.common.LogService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Search implements Initializable {

    @FXML
    private ComboBox<String> comboboxOption;

    @FXML
    private Button btnFind;
    @FXML
    private TextField queryTextField;

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
        List<HouseHold> data = ServiceFactory.getHouseHoldService().searchHouseHoldFull(query);
        for (var item : data) {
            LogService.info(item.getAddressDetail());
        }
    }

    private void searchPeople(String query) {

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
    }
}