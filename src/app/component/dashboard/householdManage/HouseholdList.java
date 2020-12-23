package app.component.dashboard.householdManage;

import app.entity.HouseHold;
import app.helper.DateHelper;
import app.services.ServiceFactory;
import app.services.common.LogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HouseholdList implements Initializable {

    @FXML
    private TableView<HouseHold> tblListHouseHold;
    @FXML
    private TableColumn<HouseHold, String> noColumn;
    @FXML
    private TableColumn<HouseHold, String> holderNameColumn;
    @FXML
    private TableColumn<HouseHold, String> addressColumn;
    @FXML
    private TableColumn<HouseHold, String> addedTimeColumn;

    private ObservableList<HouseHold> houseHolds;

    private HouseHold selectedHouseHold;

    public HouseholdList() {
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getAllHouseHold());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // household table
        noColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        holderNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        addressColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));
        addedTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(DateHelper.getDateString(c.getValue().getCreatedDate())));

        tblListHouseHold.getItems().setAll(houseHolds);

        tblListHouseHold.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HouseHold>() {
            @Override
            public void changed(ObservableValue<? extends HouseHold> observableValue, HouseHold houseHold, HouseHold t1) {
                selectedHouseHold = tblListHouseHold.getSelectionModel().getSelectedItem();
            }
        });
    }

    public HouseHold getSelectedHouseHold() {
        return selectedHouseHold;
    }
    
    public void searchHouseHold(List<HouseHold> houseHoldList) {
    	tblListHouseHold.getItems().setAll(FXCollections.observableList(houseHoldList));
    }
}
