package app.component.common;

import app.entity.People;
import app.utility.viewUtils.Mediator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class PeopleForm implements Initializable {
    private People newPeople;
    @FXML
    private DatePicker ngayCapCMND;

    @FXML
    private DatePicker ngayDK;

    @FXML
    private TextField hoTen;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField biDanh;

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
    private TextField idHoKhau;

    @FXML
    private ComboBox<String> cbboxQHCH;

    @FXML
    private ComboBox<String> cbboxGioiTinh;

    @FXML
    void gioitinhOnclick(ActionEvent event) {

    }

    @FXML
    void onClickCancel(ActionEvent event) {
        Mediator.Notify("peopleOnClick");
    }

    @FXML
    void onClickOK(ActionEvent event) {
        newPeople = new People();
        
    }

    @FXML
    void qhchOnclick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
