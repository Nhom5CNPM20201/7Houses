package app.component.dashboard.peopleManage;


import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import app.entity.HouseHold;
import app.entity.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PeopleList implements Initializable {

    @FXML
    private TableView<People> tblListPeople;
    
    @FXML
    private TableColumn<People, Integer> id;
    
    @FXML
    private TableColumn<People, Integer> idHouseHold;

    @FXML
    private TableColumn<People, String> fullName;
    
    @FXML
    private TableColumn<People, String> nickName;
    
    @FXML
    private TableColumn<People, String> birthPlace;
    
    @FXML
    private TableColumn<People, String> job;
    
    @FXML
    private TableColumn<People, Date> dateOfBirth;
    
    @FXML
    private TableColumn<People, Date> regisDate;
    
    @FXML
    private TableColumn<People, Integer> houseHolderRela;
    
    @FXML
    private TableColumn<People, String> ethnic;
    
    @FXML
    private TableColumn<People, String> nativePlace;
    
    @FXML
    private TableColumn<People, Integer> gender;
    
    @FXML
    private TableColumn<People, String> workPlace;
    
    @FXML
    private TableColumn<People, String> identityNo;
    
    @FXML
    private TableColumn<People, Date> identityMfg;
    
    @FXML
    private TableColumn<People, String> identityOrigin;
    
    @FXML 
    private ObservableList<People> peopleList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        peopleList = FXCollections.observableArrayList(
                PeopleManage.getAllPeople()
        );
        id.setCellValueFactory(new PropertyValueFactory<People, Integer>("id"));
        idHouseHold.setCellValueFactory(new PropertyValueFactory<People, Integer>("idHouseHold"));
        fullName.setCellValueFactory(new PropertyValueFactory<People, String>("fullName"));
        nickName.setCellValueFactory(new PropertyValueFactory<People, String>("nickName"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<People, String>("birthPlace"));
        job.setCellValueFactory(new PropertyValueFactory<People, String>("job"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<People, Date>("dateOfBirth"));
        regisDate.setCellValueFactory(new PropertyValueFactory<People, Date>("regisDate"));
        houseHolderRela.setCellValueFactory(new PropertyValueFactory<People, Integer>("houseHolderRela"));
        ethnic.setCellValueFactory(new PropertyValueFactory<People, String>("ethnic"));
        nativePlace.setCellValueFactory(new PropertyValueFactory<People, String>("nativePlace"));
        gender.setCellValueFactory(new PropertyValueFactory<People, Integer>("gender"));
        workPlace.setCellValueFactory(new PropertyValueFactory<People, String>("workPlace"));
        identityNo.setCellValueFactory(new PropertyValueFactory<People, String>("identityNo"));
        identityMfg.setCellValueFactory(new PropertyValueFactory<People, Date>("identityMfg"));
        identityOrigin.setCellValueFactory(new PropertyValueFactory<People, String>("identityOrigin"));
       
        tblListPeople.setItems(peopleList);
    }
}