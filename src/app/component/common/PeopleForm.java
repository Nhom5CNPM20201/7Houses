package app.component.common;

import app.utility.viewUtils.ScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.*;
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

    @FXML
    private DatePicker ngayCapCMND;

    @FXML
    private ComboBox<String> Gioitinh;
    ObservableList<String> list1 = FXCollections.observableArrayList("Nữ","Nam");

    @FXML
    private ComboBox<String> QuanHeCH;
    ObservableList<String> list2 = FXCollections.observableArrayList("Bố","Mẹ ","Anh", "Chị", "Em","Chủ Hộ");

    @FXML
    private SubScene PeopleFormScene;

    public void switchView(URL FXMLname) {

        ScreenController.activeSubscreen(this.PeopleFormScene, FXMLname);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Gioitinh.setItems(list1);
        QuanHeCH.setItems(list2);

        this.switchView(getClass().getResource("./PeopleForm.fxml"));

    }

    @FXML
    public void onclickCancel(ActionEvent actionEvent) {
        this.switchView(getClass().getResource("../dashboard/peopleManage/PeopleList.fxml"));
    }

    @FXML
    public void onClickOK(ActionEvent actionEvent) {

    }

    @FXML
    public void comboBoxOption1(ActionEvent event) {

    }

    @FXML
    public void comboBoxOption2(ActionEvent event) {

    }

}
