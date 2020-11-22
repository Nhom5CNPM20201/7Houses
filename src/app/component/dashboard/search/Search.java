package app.component.dashboard.search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Search implements Initializable {

    @FXML
    private ComboBox<String> comboboxOption;
    ObservableList<String> list = FXCollections.observableArrayList("Số hộ khẩu","Tên","Chủ hộ");
    @FXML
    private Button btnFind;

    @FXML
    void findOnClick(ActionEvent event) {

    }

    @FXML
    void optionOnAction(ActionEvent event) {

        if(comboboxOption.getValue().equals("Tên")){
            System.out.println("Tìm kiếm theo tên");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxOption.setItems(list);
    }
}