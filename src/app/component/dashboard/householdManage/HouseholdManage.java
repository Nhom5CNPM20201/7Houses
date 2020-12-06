package app.component.dashboard.householdManage;

import app.component.common.HouseHoldForm;
import app.entity.HouseHold;
import app.services.ServiceFactory;

import app.utility.viewUtils.Mediator;
import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HouseholdManage implements Initializable {
    public HouseholdManage(){}

    @FXML
    private TableView<?> tblListHouseHold;

    @FXML
    private SubScene mainHouseHold;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private Object mainHouseHoldController;

    @FXML
    public void addOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/HouseHoldForm.fxml"));
    }
    @FXML
    public void deleteOnClick(ActionEvent event) {
        Mediator.Notify("onGoingMainHouseHold");
    }

    @FXML
    public void editOnClick(ActionEvent event) {
        Mediator.Notify("onGoingMainHouseHold");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onGoingMainHouseHold(null);

        Mediator.unSubscribe("onGoingMainHouseHold");
        Mediator.subscribe("onGoingMainHouseHold", event -> onGoingMainHouseHold(null));
    }

    public void onGoingMainHouseHold(ActionEvent e) {
        this.switchView(getClass().getResource("./HouseholdList.fxml"));
    }

    private void switchView(URL FXMLname){
        mainHouseHoldController = ScreenController.activeSubscreen(this.mainHouseHold, FXMLname);
    }
}
