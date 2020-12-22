package app.component.dashboard.statistic;

import app.common.Gender;
import app.entity.People;

import app.helper.ValidateHelper;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Statistic implements Initializable {
    private final String FEMALE = "Nữ";
    private final String MALE = "Nam";
    private final String OTHER ="Khác";

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

    private ObservableList<String> genderList;

    @FXML
    private TableColumn<People, String> peopleID;

    @FXML
    private TableColumn<People, String> peopleName;

    @FXML
    private Text chartName;


    private PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> dataChart;

    @FXML
    private TableColumn<People, String> peopleIdenNo;

    @FXML
    private TableColumn<People, String> peopleGender;

    private List<People> peoples;

    @FXML
    void checkOnClick(ActionEvent event) {
        try {
            if(paneView.getChildren().contains(chart)){
                paneView.getChildren().remove(chart);
            }
            int genderIndex = gender.getSelectionModel().getSelectedIndex();
            int ageFrom = ageStart.getText().isEmpty() ? -1 : Integer.parseInt(ageStart.getText());
            int ageTo = ageFinish.getText().isEmpty() ? -1 : Integer.parseInt(ageFinish.getText());
            Date timeFrom = startTime.getValue() == null ? null : ValidateHelper.validateDate(startTime.getValue());
            Date timeTo = endTime.getValue() == null ? null : ValidateHelper.validateDate(endTime.getValue());

            peoples = ServiceFactory.getPeopleService().statistic(genderIndex, ageFrom, ageTo, timeFrom, timeTo);
            tblPeoPleData.getItems().setAll(FXCollections.observableArrayList(peoples));
            if(peoples != null) getChart();
        } catch (Exception e) {
            NotiService.error(e.getMessage());
        }
    }
    private void getChart(){
        chartName.setVisible(true);
        double rate = 100.00*peoples.size()/ServiceFactory.getPeopleService().coutAllPeople();
        dataChart = FXCollections.observableArrayList(
                new PieChart.Data("Thỏa mãn",rate),
                new PieChart.Data("Còn lại",100.00-rate)
        );
        chart.setData(dataChart);
        chart.setLayoutY(50);
        chart.setLayoutX(40);
        chart.setPrefSize(300,300);
        chart.setLabelsVisible(false);
        chart.setClockwise(false);
        paneView.getChildren().add(chart);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderList = FXCollections.observableArrayList(MALE,FEMALE,OTHER);
        gender.setItems(genderList);

        peopleID.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        peopleName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFullName()));
        peopleIdenNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentityNo()));
        peopleGender.setCellValueFactory(c -> new SimpleStringProperty(Gender.NONE.setValue(c.getValue().getGender()).getGenderName()));
        chartName.setVisible(false);
    }
}
