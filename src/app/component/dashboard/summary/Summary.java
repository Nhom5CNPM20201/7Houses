package app.component.dashboard.summary;

import app.entity.HouseHold;
import app.services.ServiceFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Summary implements Initializable {

    @FXML
    private Text peopleSummaryText;
    @FXML
    private Text householdSummaryText;
    @FXML
    private TableView newHouseHoldTable;
    @FXML
    private TableColumn<HouseHold, String> noColumn;
    @FXML
    private TableColumn<HouseHold, String> holderNameColumn;
    @FXML
    private TableColumn<HouseHold, String> addressColumn;
    @FXML
    private TableColumn<HouseHold, String> addedTimeColumn;

    private ObservableList<HouseHold> houseHolds;

    public Summary() {
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getAllHouseHold());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int countAllPeople = ServiceFactory.getPeopleService().coutAllPeople();
        int countAllHouseHold = ServiceFactory.getHouseHoldService().countAllHouseHold();

        peopleSummaryText.setText(String.valueOf(countAllPeople));
        householdSummaryText.setText(String.valueOf(countAllHouseHold));

        // household table
        noColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        holderNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        addressColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));


        newHouseHoldTable.getItems().setAll(houseHolds);
    }
}
