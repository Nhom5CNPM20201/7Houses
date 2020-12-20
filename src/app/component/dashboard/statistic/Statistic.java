package app.component.dashboard.statistic;

import app.entity.People;
import app.entity.TemporaryResident;
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
    private final String NHANKHAU = "Nhân khẩu";
    private final String TCTV = "Tạm chú/Tạm vắng";
    private final String FEMALE = "Nữ";
    private final String MALE = "Nam";
    private final String OTHER ="Khác";
    @FXML
    private ComboBox<String> typeStatistic;

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
    private TextField yearStart;

    @FXML
    private TextField yearFinish;

    @FXML
    private TableView<People> tblPeoPleData;
    private TableView<TemporaryResident> tblStayingData;

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
    private TableColumn<People, String> startTime;
    private TableColumn<People, String> endTime;
    @FXML
    void typeCbxOnAction(ActionEvent event) {
        if(typeStatistic.getValue().equals(NHANKHAU)){
            gender.setVisible(true);
            ageStart.setVisible(true);
            ageFinish.setVisible(true);
            tblPeoPleData.setVisible(true);
            tblStayingData.setVisible(false);
        }
        else if(typeStatistic.getValue().equals(TCTV)){
            gender.setVisible(false);
            ageStart.setVisible(false);
            ageFinish.setVisible(false);
            tblStayingData.setVisible(true);
            tblPeoPleData.setVisible(false);
        }
    }
    @FXML
    void testOnclick(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList = FXCollections.observableArrayList(NHANKHAU,TCTV);
        typeStatistic.setItems(typeList);

        genderList = FXCollections.observableArrayList(FEMALE,MALE,OTHER);
        gender.setItems(genderList);S
    }
}
