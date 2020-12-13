package app.component.dashboard.feeManage;

import app.entity.Contribute;
import app.services.ServiceFactory;
import app.services.common.LogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeManage implements Initializable {
    @FXML
    private Button btnContributeAdd;

    @FXML
    private Button btnFeeAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<Contribute> tblListFee;
    @FXML
    private TableColumn<Contribute, String> contributeId;
    @FXML
    private TableColumn<Contribute, String> houseHolderName;
    @FXML
    private TableColumn<Contribute, String> houseHoldAddress;
    @FXML
    private TableColumn<Contribute, String> contributeAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            contributeId.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
            houseHolderName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHold().getName()));
            houseHoldAddress.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHouseHold().getAddressDetail()));
            contributeAmount.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getAmount())));

            tblListFee.getItems().setAll(FXCollections.observableArrayList(ServiceFactory.getContributeService().getAllContributes()));
        } catch(Exception e) {
            LogService.error(e.getMessage());
        }
    }

    @FXML
    void contributeAddOnclick(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("../../common/ContributeForm.fxml"));
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editOnclick(ActionEvent event) {

    }

    @FXML
    void feeAddOnclick(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("../../common/formPayment.fxml"));
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();;
    }
}
