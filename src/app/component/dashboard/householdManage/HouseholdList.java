package app.component.dashboard.householdManage;

import java.net.URL;
import java.util.ResourceBundle;

import app.entity.HouseHold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HouseholdList implements Initializable{

    @FXML
    private TableView<HouseHold> tblListHouseHold;
    
    @FXML
    private TableColumn<HouseHold, Integer> id;
    
    @FXML
    private TableColumn<HouseHold, String> name;
    
    @FXML
    private TableColumn<HouseHold, Integer> idAddress;
    
    @FXML
    private TableColumn<HouseHold, String> houseHoldBook;
    
    @FXML 
    private ObservableList<HouseHold> houseHoldList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        houseHoldList = FXCollections.observableArrayList(
                HouseholdManage.getAllHouseHold()
        );
        id.setCellValueFactory(new PropertyValueFactory<HouseHold, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<HouseHold, String>("name"));
        idAddress.setCellValueFactory(new PropertyValueFactory<HouseHold, Integer>("idAddress"));
        houseHoldBook.setCellValueFactory(new PropertyValueFactory<HouseHold, String>("houseHoldBook"));
        tblListHouseHold.setItems(houseHoldList);
    }

}
