package app.component.dashboard;

import app.common.AccountPositionEnum;
import app.utility.viewUtils.Mediator;
import app.entity.Account;
import app.services.ServiceFactory;
import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
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

    public Dashboard() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("summary/Summary.fxml"));
        setPosition(ServiceFactory.getAuthService().getCurrentAccount());
    }

    private void setPosition(Account account) {
        String accountPositionName = account != null ? account.getAccountPositionEnum().getPositionName()
                : AccountPositionEnum.NONE.getPositionName();

        this.positionTextField.setText(accountPositionName);
    }

    // ====> Function region
    @FXML
    void logOutOnClick(ActionEvent event) {
        ServiceFactory.getAuthService().clearAccount();
        Mediator.Notify("onGoingWelcome");
    }


    // ====> Switch view region.
    private void switchView(URL FXMLname) {
        ScreenController.activeSubscreen(this.mainDashboard, FXMLname);
    }

    @FXML
    public void summaryOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("summary/Summary.fxml"));
    }

    @FXML
    public void houseHoldOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("householdManage/HouseholdManage.fxml"));
    }

    @FXML
    public void peopleOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("peopleManage/PeopleManage.fxml"));
    }

    @FXML
    public void feeOnClick(ActionEvent event) {

        this.switchView(getClass().getResource("feeManage/FeeManage.fxml"));
    }

    @FXML
    public void informationOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("information/Information.fxml"));
    }

    @FXML
    void searchOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("search/Search.fxml"));
    }

    @FXML
    void statisticOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("statistic/Statistic.fxml"));
    }
}
