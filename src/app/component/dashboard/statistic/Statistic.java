package app.component.dashboard.statistic;

import app.entity.People;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Statistic implements Initializable {
    private final String FEMALE = "Nữ";
    private final String MALE = "Nam";
    private final String OTHER ="Khác";


    @FXML
    private AnchorPane data;

    @FXML
    private Button btnTest;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField ageStart;

    @FXML
    private TextField ageFinish;

    @FXML
    private DatePicker startTime;

    @FXML
    private DatePicker endTime;

    @FXML
    private TableView<People> tblPeoPleData;


    @FXML
    private Pane paneView;

    private ObservableList<String> typeList;
    private ObservableList<String> genderList;

    @FXML
    private TableColumn<People, String> peopleID;

    @FXML
    private TableColumn<People, String> peopleName;

    @FXML
    private TableColumn<People, String> peopleIdenNo;

    @FXML
    private TableColumn<People, String> peopleGender;

    @FXML
    void typeCbxOnAction(ActionEvent event) {

    }
    @FXML
    void testOnclick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderList = FXCollections.observableArrayList(FEMALE,MALE,OTHER);
        gender.setItems(genderList);
    }
}
