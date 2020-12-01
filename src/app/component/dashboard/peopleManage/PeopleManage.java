package app.component.dashboard.peopleManage;

import app.utility.viewUtils.ScreenController;
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
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("./PeopleList.fxml"));
    }

    @FXML
    void addOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/PeopleForm.fxml"));
    }

    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void editOnClick(ActionEvent event) {

    }


    public void switchView(URL FXMLname){
        ScreenController.activeSubscreen(mainPeople,FXMLname);
    }
}
