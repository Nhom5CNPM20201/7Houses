package app.component.common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StayingTAForm implements Initializable {
    private final String TAM_CHU = "Tạm chú";
    private final String TAM_VANG = "Tạm vắng";
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<?> tblContent;

    @FXML
    private DatePicker startTime;

    @FXML
    private DatePicker endTime;

    @FXML
    private ComboBox<String> typeCombobox;
    public ObservableList<String> list = FXCollections.observableArrayList(TAM_CHU,TAM_VANG);
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    void addOnclick(ActionEvent event) {

    }

    @FXML
    void cancelOnclick(ActionEvent event) {

    }

    @FXML
    void okOnclick(ActionEvent event) {

    }

    @FXML
    void searchOnclick(ActionEvent event) {

    }

    @FXML
    void typeCombxOnclick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCombobox.setItems(list);
    }
}
