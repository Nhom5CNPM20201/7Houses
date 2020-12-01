package app.component.common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ContributeForm implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private ComboBox<String> comboBoxOption1;
    ObservableList<String> list1 = FXCollections.observableArrayList("khoan phi 1","khoan phi 2 ");


    @FXML
    private ComboBox<String> comboBoxOption2;
    ObservableList<String> list2 = FXCollections.observableArrayList("ho 1","ho 2");



    @FXML
    void optionOnAction1(ActionEvent event) {

    }

    @FXML
    void optionOnAction2(ActionEvent event) {

    }

    @FXML
    void cancelAddOnClick(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxOption1.setItems(list1);
        comboBoxOption2.setItems(list2);


    }
}
