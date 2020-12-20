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

public class MoveForm implements Initializable {
    private final String CHUYEN_DEN = "Chuyển đến";
    private final String CHUYEN_DI = "Chuyển đi";
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfAddress;

    @FXML
    private DatePicker dateMove;

    @FXML
    private ComboBox<String> moveType;
    public ObservableList<String> list = FXCollections.observableArrayList(CHUYEN_DEN,CHUYEN_DI);
    @FXML
    private TableView<?> tblContent;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    void cancelOnclick(ActionEvent event) {

    }

    @FXML
    void okOnclick(ActionEvent event) {

    }

    @FXML
    void searchOnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveType.setItems(list);
    }
}
