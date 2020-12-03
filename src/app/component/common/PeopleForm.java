package app.component.common;

import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PeopleForm implements Initializable{



    @FXML
    private SplitMenuButton hoTenCH;

    @FXML
    private TextField hoTen;

    @FXML
    private TextField biDanh;

    @FXML
    private Button onClickCancel;

    @FXML
    private Button onClickOK;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField noiSinh;

    @FXML
    private TextField danToc;

    @FXML
    private TextField nguyenQuan;

    @FXML
    private TextField noiLamViec;

    @FXML
    private TextField CMND;

    @FXML
    private TextField noiCapCMND;

    @FXML
    private DatePicker ngayDK;

    private SubScene PeopleFormScene;
    private URL FXMLname;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("./PeopleForm.fxml"));
    }

    private void switchView(URL resource) {
        ScreenController.activeSubscreen(this.PeopleFormScene, FXMLname);
    }

    @FXML
    public void onclickCancel(ActionEvent actionEvent) {
        this.switchView(getClass().getResource("../dashboard/peopleManage/PeopleList.fxml"));
    }

    @FXML
    public void onClickOK(ActionEvent actionEvent) {

    }


}
