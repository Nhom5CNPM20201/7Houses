package app.component.common;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private TextField tfAddress;

    @FXML
    private DatePicker dateMove;

    @FXML
    private ComboBox<String> moveType;
    public ObservableList<String> list = FXCollections.observableArrayList(CHUYEN_DEN,CHUYEN_DI);

    @FXML
    private TableView<HouseHold> tblHouseHold;

    @FXML
    private TableColumn<HouseHold, String> houseHoldNo;

    @FXML
    private TableColumn<HouseHold, String> houseHoldName;

    @FXML
    private TableColumn<HouseHold, String> houseHoldAddress;

    private ObservableList<HouseHold> houseHolds;

    private HouseHold selectedHouseHold;

    public MoveForm() {
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getAllHouseHold());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveType.setItems(list);

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
                    tfAddress.setText(selectedHouseHold.getAddressDetail());
                }
            }
        });
    }

    @FXML
    void cancelOnclick(ActionEvent event) {
        Mediator.Notify("houseHoldOnClick");
    }

    @FXML
    void okOnclick(ActionEvent event) {
        try {
            Move move = new Move();
            move.setIdOldAddress(selectedHouseHold.getIdAddress());
            move.setMovingDate(ValidateHelper.validateDate(dateMove.getValue()));

            Mediator.Notify("houseHoldOnClick");
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    @FXML
    void searchOnClick(ActionEvent event) {

    }
}
