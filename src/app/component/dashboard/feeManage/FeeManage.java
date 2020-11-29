package app.component.dashboard.feeManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FeeManage {

    @FXML
    private TableView<?> tblListFee;

    @FXML
    private Button btnContributeAdd;

    @FXML
    private Button btnFeeAdd;

    @FXML
    private Button btnEdit;

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
    void feeAddOnclick(ActionEvent event) {

    }

}
