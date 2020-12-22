package app.component.dashboard.peopleManage;


import app.component.common.PeopleForm;
import app.services.common.NotiService;
import app.utility.viewUtils.ScreenController;

import app.entity.People;

import app.services.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PeopleManage implements Initializable {

    @FXML
    private SubScene mainPeople;

    private Object mainPeopleController;

    @FXML
    void addOnClick(ActionEvent event) {

        this.switchView(getClass().getResource("../../common/PeopleForm.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("PeopleList.fxml"));
    }

    public void switchView(URL FXMLname){
        mainPeopleController = ScreenController.activeSubscreen(mainPeople,FXMLname);
    }
    @FXML
    void temporaryOnclick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/StayingTAForm.fxml"));
    }

    @FXML
    void changeOnClick(ActionEvent event) {
        try {
            if (mainPeopleController != null  && mainPeopleController instanceof PeopleList) {
                People selectedPeople =((PeopleList) mainPeopleController).getSelectedPeople();
                if (selectedPeople == null)
                    throw new Exception("Bạn chưa chọn nhân khẩu.");

                onGoingUpdatePeople(null);

                if (mainPeopleController instanceof PeopleForm) {
                    ((PeopleForm) mainPeopleController).update(selectedPeople);
                }

            } else
                throw new Exception("Vui lòng chọn nhân khẩu.");

        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    private void onGoingUpdatePeople(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/PeopleForm.fxml"));
    }
}