package app.component.common;

import app.entity.HouseHold;
import app.entity.People;
import app.helper.DateHelper;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.spec.ECField;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
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
    private TextField houseHoldNo;

    @FXML
    private ComboBox<String> cbboxQHCH;

    @FXML
    private ComboBox<String> cbboxGioiTinh;
    ObservableList<String> genderList = FXCollections.observableArrayList("Nam","Nữ","Khác");
    ObservableList<String> qhchList = FXCollections.observableArrayList("Bố", "Mẹ","Vợ","Con","Chồng");

    private boolean isUpdate = false;

    private People newPeople = new People();

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
    void onClickOK(ActionEvent event) throws Exception {
//        try {

            HouseHold houseHoldMap = ServiceFactory.getHouseHoldService().getHouseHoldByNo(ValidateHelper.validateText(houseHoldNo.getText()));
            if (houseHoldMap == null) {
                Optional<ButtonType> confirm = NotiService.confirm("Không tìm thấy dữ liệu về hộ khẩu bạn đã nhập. Bạn vẫn muốn tiếp tục?");
                if (confirm.get() != ButtonType.OK)
                    return;

            } else {
                newPeople.setIdHouseHold(houseHoldMap.getId());
            }

            newPeople.setFullName(ValidateHelper.validateText(hoTen.getText()));
            newPeople.setNickName(ValidateHelper.validateText(biDanh.getText()));
            newPeople.setDateOfBirth(ValidateHelper.validateDate(ngaySinh.getValue()));
            newPeople.setBirthPlace(ValidateHelper.validateText(noiSinh.getText()));
            newPeople.setEthnic(ValidateHelper.validateText(danToc.getText()));
            newPeople.setWorkPlace(ValidateHelper.validateText(noiLamViec.getText()));
            newPeople.setIdentityNo(ValidateHelper.validateText(CMND.getText()));
            newPeople.setIdentityMfg(ValidateHelper.validateDate(ngayCapCMND.getValue()));
            newPeople.setIdentityOrigin(ValidateHelper.validateText(noiCapCMND.getText()));
            newPeople.setRegisDate(ValidateHelper.validateDate(ngayDK.getValue()));

            if (isUpdate)
                newPeople = ServiceFactory.getPeopleService().updatePeople(newPeople);
            else
                newPeople = ServiceFactory.getPeopleService().createPeople(newPeople);

            Mediator.Notify("peopleOnClick");
//        } catch (Exception e) {
//            NotiService.error(e.getMessage());
//        }
    }

    public void update(People people) {
        isUpdate = true;
        newPeople = people;

        hoTen.setText(people.getFullName());
        biDanh.setText(people.getNickName());
        ngaySinh.setValue(DateHelper.convertLocalDate(people.getDateOfBirth()));
        noiSinh.setText(people.getBirthPlace());
        danToc.setText(people.getEthnic());
        noiLamViec.setText(people.getWorkPlace());
        CMND.setText(people.getIdentityNo());
        nguyenQuan.setText(people.getBirthPlace());
        ngayCapCMND.setValue(DateHelper.convertLocalDate(people.getIdentityMfg()));
        noiCapCMND.setText(people.getIdentityOrigin());
        HouseHold houseHoldOfPeople = people.getHouseHold();
        if (houseHoldOfPeople != null)
            houseHoldNo.setText(houseHoldOfPeople.getHouseHoldBook());
        ngayDK.setValue(DateHelper.convertLocalDate(people.getRegisDate()));
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
