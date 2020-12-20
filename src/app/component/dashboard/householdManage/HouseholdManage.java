package app.component.dashboard.householdManage;

import app.component.common.HouseHoldForm;
import app.entity.HouseHold;
import app.services.ServiceFactory;

import app.services.common.LogService;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import javax.swing.*;
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
        onGoingAddHouseHold(null);
    }

    @FXML
    public void moveOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/MoveForm.fxml"));
    }

    @FXML
    public void deleteOnClick(ActionEvent event) {
        Mediator.Notify("onGoingMainHouseHold");
    }

    @FXML
    public void updateOnClick(ActionEvent event) {
        try {
            if (mainHouseHoldController != null && mainHouseHoldController instanceof HouseholdList) {
                HouseHold selectedHouseHold = ((HouseholdList) mainHouseHoldController).getSelectedHouseHold();
                if (selectedHouseHold == null) {
                    throw new Exception("Bạn chưa chọn hộ khẩu.");
                }

                onGoingAddHouseHold(null);
                if (mainHouseHoldController instanceof HouseHoldForm) {
                    ((HouseHoldForm) mainHouseHoldController).update(selectedHouseHold);
                }
            } else {
                throw new Exception("Vui lòng chọn hộ khẩu.");
            }
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onGoingMainHouseHold(null);

        Mediator.unSubscribe("onGoingMainHouseHold");
        Mediator.subscribe("onGoingMainHouseHold", event -> onGoingMainHouseHold(null));
    }

    public void onGoingAddHouseHold(ActionEvent e) {
        this.switchView(getClass().getResource("../../common/HouseHoldForm.fxml"));
    }

    public void onGoingMainHouseHold(ActionEvent e) {
        this.switchView(getClass().getResource("./HouseholdList.fxml"));
    }

    private void switchView(URL FXMLname){
        mainHouseHoldController = ScreenController.activeSubscreen(this.mainHouseHold, FXMLname);
    }
}
