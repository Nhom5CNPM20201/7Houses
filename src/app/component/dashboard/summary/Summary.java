package app.component.dashboard.summary;

import app.entity.HouseHold;
import app.helper.DateHelper;
import app.services.ServiceFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Summary implements Initializable {

    @FXML
    private Text peopleSummaryText;
    @FXML
    private Text newPeopleSummaryText;
    @FXML
    private Text householdSummaryText;
    @FXML
    private Text newHouseHoldSummaryText;
    @FXML
    private TableView newHouseHoldTable;
    @FXML
    private TableColumn<HouseHold, String> noColumn;
    @FXML
    private TableColumn<HouseHold, String> holderNameColumn;
    @FXML
    private TableColumn<HouseHold, String> addressColumn;
    @FXML
    private TableColumn<HouseHold, String> addedTimeColumn;

    @FXML
    private Pane paneTCTV;

    @FXML
    private Pane panePeople;

    private ObservableList<HouseHold> houseHolds;

    private PieChart chartTCTV = new PieChart();
    private ObservableList<PieChart.Data> dataTCTV;
    private PieChart chartPeople = new PieChart();
    private ObservableList<PieChart.Data> dataPeople;

    public Summary() {
        houseHolds = FXCollections.observableList(ServiceFactory.getHouseHoldService().getNewHouseHold());
    }
    private void getTCTVChart(long allPeople){
        double rateTC = 100.00*ServiceFactory.getTemporaryresidentService().countTC()/allPeople;
        double rateTV = 100.00*ServiceFactory.getTemporaryresidentService().countTV()/allPeople;
        dataTCTV = FXCollections.observableArrayList(
                new PieChart.Data("Tạm trú",rateTC),
                new PieChart.Data("Tạm vắng",rateTV),
                new PieChart.Data("Thường trú",100.00-rateTC-rateTV)
        );
        chartTCTV.setData(dataTCTV);
        chartTCTV.setLayoutX(-15);
        chartTCTV.setLayoutY(-10);
        chartTCTV.setClockwise(true);
        chartTCTV.setLabelsVisible(true);
        chartTCTV.setLabelLineLength(10);
        chartTCTV.setStartAngle(20);
        chartTCTV.setPrefSize(290,290);
        paneTCTV.getChildren().add(chartTCTV);
    }
    private void getPeopleChart(long newPeople,long allPeople){
        double rateNew= 100.00*newPeople/allPeople;
        dataPeople = FXCollections.observableArrayList(
                new PieChart.Data("Mới",rateNew),
                new PieChart.Data("Còn lại",100.000-rateNew)
        );
        chartPeople.setData(dataPeople);
        chartPeople.setLayoutX(-10);
        chartPeople.setLayoutY(-10);
        chartPeople.setClockwise(true);
        chartPeople.setLabelsVisible(true);
        chartPeople.setLabelLineLength(10);
        chartPeople.setStartAngle(20);
        chartPeople.setPrefSize(250,250);
        panePeople.getChildren().add(chartPeople);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        long countAllPeople = ServiceFactory.getPeopleService().coutAllPeople();
        long countAllHouseHold = ServiceFactory.getHouseHoldService().countAllHouseHold();
        long countNewPeople = ServiceFactory.getPeopleService().countNewPeople();
        long countNewHouseHold = ServiceFactory.getHouseHoldService().countNewHouseHold();

        peopleSummaryText.setText(String.valueOf(countAllPeople));
        householdSummaryText.setText(String.valueOf(countAllHouseHold));
        newHouseHoldSummaryText.setText("+" + String.valueOf(countNewHouseHold));
        newPeopleSummaryText.setText("+" + String.valueOf(countNewPeople));

        // household table
        noColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHoldBook().trim()));
        holderNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        addressColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddressDetail()));
        addedTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(DateHelper.getDateString(c.getValue().getCreatedDate())));

        newHouseHoldTable.getItems().setAll(houseHolds);
        getTCTVChart(countAllPeople);
        getPeopleChart(countNewPeople,countAllPeople);
    }
}
