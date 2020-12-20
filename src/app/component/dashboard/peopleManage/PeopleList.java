package app.component.dashboard.peopleManage;

import app.entity.HouseHold;
import app.entity.People;
import app.helper.DateHelper;
import app.services.ServiceFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;


public class PeopleList implements Initializable {

    @FXML
    private TableView<People> tblListPeople;
    @FXML
    private TableColumn<People, String> peopleNo;
    @FXML
    private TableColumn<People, String> peopleName;
    @FXML
    private TableColumn<People, String> idCardNo;
    @FXML
    private TableColumn<People, String> peopleBirthDay;

    private ObservableList<People> peoples;

    public PeopleList() {
        this.peoples = FXCollections.observableArrayList(ServiceFactory.getPeopleService().getAllPeople());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // people table
        peopleNo.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        peopleName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFullName()));
        idCardNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentityNo()));
        peopleBirthDay.setCellValueFactory(c -> new SimpleStringProperty(DateHelper.getDateString(c.getValue().getDateOfBirth())));

        tblListPeople.getItems().setAll(peoples);
    }
}
