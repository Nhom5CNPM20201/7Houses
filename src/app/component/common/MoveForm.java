package app.component.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MoveForm {

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfAddress;

    @FXML
    private DatePicker dateMove;

    @FXML
    private ComboBox<?> moveType;

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

}
