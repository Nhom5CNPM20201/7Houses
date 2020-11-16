package app.component.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Dashboard {

    @FXML
    private AnchorPane mainView;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSummary;

    @FXML
    private Button btnHouseHold;

    @FXML
    private Button btnPeople;

    @FXML
    private Button btnFee;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnStatistic;

    @FXML
    private Button btnInformation;

    @FXML
    private AnchorPane homePane;

    @FXML
    private BorderPane subscene;

    @FXML
    void summaryOnClick(ActionEvent event) {
        try {
            Parent summary  = FXMLLoader.load(getClass().getResource("/app/component/dashboard/summary/Summary.fxml"));

            subscene.setCenter(summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void feeOnClick(ActionEvent event) {

    }

    @FXML
    void houseHoldOnClick(ActionEvent event) {
        try {
            Parent householdManage  = FXMLLoader.load(getClass().getResource("/app/component/dashboard/householdManage/HouseholdManage.fxml"));

            subscene.setCenter(householdManage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void informationOnClick(ActionEvent event) {

    }

    @FXML
    void logOutOnClick(ActionEvent event) {

    }

    @FXML
    void peopleOnClick(ActionEvent event) {
        try {
            Parent peopleManage  = FXMLLoader.load(getClass().getResource("/app/component/dashboard/peopleManage/PeopleManage.fxml"));

            subscene.setCenter(peopleManage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchOnClick(ActionEvent event) {
        try {
            Parent search  = FXMLLoader.load(getClass().getResource("/app/component/dashboard/search/Search.fxml"));

            subscene.setCenter(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void statisticOnClick(ActionEvent event) {

    }

}
