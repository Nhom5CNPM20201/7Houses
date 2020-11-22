package app.component.common.account;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountForm implements Initializable {

    @FXML
    private ComboBox<String> comboBoxOption1;
    ObservableList<String> list1 = FXCollections.observableArrayList("Đóng góp","Phí");


    @FXML
    private ComboBox<String> comboBoxOption2;
    ObservableList<String> list2 = FXCollections.observableArrayList("Đóng góp theo hộ","Đóng góp theo cá nhân");



    @FXML
    void optionOnAction1(ActionEvent event) {
        System.out.println("Tìm kiếm theo tên");
    }

    @FXML
    void optionOnAction2(ActionEvent event) {
        System.out.println("Tìm kiếm theo tên");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxOption1.setItems(list1);
        comboBoxOption2.setItems(list2);


    }
}
