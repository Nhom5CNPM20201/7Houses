package app.component.dashboard;

import app.common.AccountPositionEnum;
import app.utility.viewUtils.Mediator;
import app.entity.Account;
import app.services.ServiceFactory;
import app.utility.viewUtils.ScreenController;
import com.sun.media.jfxmedia.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private Text positionTextField;
    @FXML
    private SubScene mainDashboard;
    @FXML
    private Button btnFee;
    @FXML
    private Button btnHouseHold;
    @FXML
    private Button btnPeople;
    @FXML
    private Button btnStatistic;
    @FXML
    private Button btnInformation;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSummary;

    public Dashboard() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("summary/Summary.fxml"));
        var account = ServiceFactory.getAuthService().getCurrentAccount();
        setPosition(account);

        Mediator.unSubscribe("houseHoldOnClick");
        Mediator.unSubscribe("peopleOnClick");
        Mediator.unSubscribe("feeOnClick");
        Mediator.subscribe("houseHoldOnClick", this::houseHoldOnClick);
        Mediator.subscribe("peopleOnClick", this::peopleOnClick);
        Mediator.subscribe("feeOnClick", this::feeOnClick);
    }

    private void setPosition(Account account) {
        String accountPositionName;
        if (account != null) {
            accountPositionName = account.getAccountPositionEnum().getPositionName();
            switch(account.getAccountPositionEnum()) {
                case NONE:
                    btnFee.setVisible(false);
                case ACCOUNTANT:
                    btnHouseHold.setVisible(false);
                    btnPeople.setVisible(false);
                    btnStatistic.setVisible(false);
                    break;
                case VICE_LEADER:
                case LEADER:
                    break;
            }
        } else {
            accountPositionName = AccountPositionEnum.NONE.getPositionName();
            btnFee.setVisible(false);
            btnHouseHold.setVisible(false);
            btnPeople.setVisible(false);
            btnStatistic.setVisible(false);
        }

        this.positionTextField.setText(accountPositionName);
    }

    // ====> Function region
    @FXML
    void logOutOnClick(ActionEvent event) {
        ServiceFactory.getAuthService().clearAccount();
        Mediator.Notify("onGoingWelcome");
    }
    private void getSelectButton(Button button){
        btnFee.setStyle("-fx-background-color: transparent");
        btnHouseHold.setStyle("-fx-background-color: transparent");
        btnInformation.setStyle("-fx-background-color: transparent");
        btnPeople.setStyle("-fx-background-color: transparent");
        btnSearch.setStyle("-fx-background-color: transparent");
        btnStatistic.setStyle("-fx-background-color: transparent");
        btnSummary.setStyle("-fx-background-color: transparent");
        button.setStyle("-fx-background-color: #BDBDBD");

    }

    // ====> Switch view region.
    private void switchView(URL FXMLname) {

        ScreenController.activeSubscreen(this.mainDashboard, FXMLname);
    }

    @FXML
    public void summaryOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("summary/Summary.fxml"));
        getSelectButton(btnSummary);
    }

    @FXML
    public void houseHoldOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("householdManage/HouseHoldManage.fxml"));
        getSelectButton(btnHouseHold);
    }

    @FXML
    public void peopleOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("peopleManage/PeopleManage.fxml"));
        getSelectButton(btnPeople);
    }

    @FXML
    public void feeOnClick(ActionEvent event) {
        getSelectButton(btnFee);
        this.switchView(getClass().getResource("feeManage/FeeManage.fxml"));
    }

    @FXML
    public void informationOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("information/Information.fxml"));
        getSelectButton(btnInformation);
    }

    @FXML
    void searchOnClick(ActionEvent event) {
        getSelectButton(btnSearch);
        this.switchView(getClass().getResource("search/Search.fxml"));
    }

    @FXML
    void statisticOnClick(ActionEvent event) {
        getSelectButton(btnStatistic);
        this.switchView(getClass().getResource("statistic/Statistic.fxml"));
    }
}
