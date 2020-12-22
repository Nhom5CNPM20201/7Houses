package app.component.dashboard.peopleManage;


import app.utility.viewUtils.ScreenController;

import app.entity.People;

import app.services.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PeopleManage implements Initializable {

    @FXML
    private SubScene mainPeople;

    @FXML
    void addOnClick(ActionEvent event) {

        this.switchView(getClass().getResource("../../common/PeopleForm.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("PeopleList.fxml"));
    }

    public void switchView(URL FXMLname){
        ScreenController.activeSubscreen(mainPeople,FXMLname);
    }
    @FXML
    void temporaryOnclick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/StayingTAForm.fxml"));
    }

    @FXML
    void changeOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/PeopleForm.fxml"));
    }
	public static void main(String[] args) {
//	People people;
//	people = new People();
		//	PeopleManage.getAllPeople();
	//	PeopleManage.deletePeople("abc css");
//		PeopleManage.createPeople(people);
	}

}