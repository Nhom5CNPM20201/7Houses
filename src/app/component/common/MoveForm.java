package app.component.common;

import app.entity.Address;
import app.utility.viewUtils.Mediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoveForm implements Initializable {
    private final String CHUYEN_DEN = "Chuyển đến";
    private final String CHUYEN_DI = "Chuyển đi";
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;
    @FXML
    private TextField tfAddressDes;
    @FXML
    private Button btnAdd;

    @FXML
    private TextField tfAddressCur;

    private Stage stage;

    private AddressForm addressController;
    @FXML
    private DatePicker dateMove;

    @FXML
    private ComboBox<String> moveType;
    public ObservableList<String> list = FXCollections.observableArrayList(CHUYEN_DEN,CHUYEN_DI);
    @FXML
    private TableView<?> tblContent;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    private Address newAddress;
    @FXML
    void cancelOnclick(ActionEvent event) {

    }

    @FXML
    void okOnclick(ActionEvent event) {

    }
    @FXML
    void addOnclick(ActionEvent event) throws IOException {
        if (this.stage != null) this.stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressForm.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 395, 422);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        this.addressController = loader.getController();
    }
    private void onCloseAddAddress(ActionEvent e) {
        this.newAddress = this.addressController != null ? this.addressController.getNewAddress() : null;

        if (newAddress != null) {
            tfAddressDes.setText(newAddress.getDetail());
        }
        this.stage.close();
    }

    @FXML
    void searchOnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveType.setItems(list);
        Mediator.unSubscribe("onCloseAddAddress");
        Mediator.subscribe("onCloseAddAddress", event -> onCloseAddAddress(null));
    }
}
