package app.component.common;

import app.entity.People;
import app.helper.StringHelper;
import app.helper.ValidateHelper;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.viewUtils.Mediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.spec.ECField;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PeopleForm implements Initializable {
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
    ObservableList<String> genderList = FXCollections.observableArrayList("Nam","Nữ","Khác");
    ObservableList<String> qhchList = FXCollections.observableArrayList("Bố", "Mẹ","Vợ","Con","Chồng");

    private People newPeople;

    public PeopleForm() {

    }

    @FXML
    void gioitinhOnclick(ActionEvent event) {

    }

    @FXML
    void onClickCancel(ActionEvent event) {
        Mediator.Notify("peopleOnClick");
    }

    @FXML
    void onClickOK(ActionEvent event) {
        try {
            newPeople = new People();
            newPeople.setFullName(ValidateHelper.validateText(hoTen.getText()));
            newPeople.setNickName(ValidateHelper.validateText(biDanh.getText()));
            newPeople.setDateOfBirth(ValidateHelper.validateDate(ngaySinh.getValue()));
            newPeople.setBirthPlace(ValidateHelper.validateText(noiSinh.getText()));
            newPeople.setEthnic(ValidateHelper.validateText(danToc.getText()));
            newPeople.setWorkPlace(ValidateHelper.validateText(noiLamViec.getText()));
            newPeople.setIdentityNo(ValidateHelper.validateText(CMND.getText()));
            newPeople.setIdentityMfg(ValidateHelper.validateDate(ngayCapCMND.getValue()));
            newPeople.setIdentityOrigin(ValidateHelper.validateText(noiCapCMND.getText()));
            //newPeople.setIdHouseHold(Integer.getInteger(idHoKhau.getText()));
            newPeople.setRegisDate(ValidateHelper.validateDate(ngayDK.getValue()));

            newPeople = ServiceFactory.getPeopleService().createPeople(newPeople);

            Mediator.Notify("peopleOnClick");
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }

    @FXML
    void qhchOnclick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cbboxQHCH.setItems(qhchList);
        cbboxGioiTinh.setItems(genderList);
    }
}
