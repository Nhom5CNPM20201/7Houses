package app.component.dashboard.peopleManage;

import app.entity.People;
import app.services.PeopleService;
import app.services.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class PeopleManage {
	private ServiceFactory serviceFactory;

    @FXML
    private TableView<?> tblListHouseHold;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    void addOnClick(ActionEvent event) {

    }

    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void editOnClick(ActionEvent event) {

    }
    
    public static void createPeople(People  people) {
    	ServiceFactory.Init();
		ServiceFactory.getPeopleService().createPeople(people);
	}
	
	public static void getAllPeople() {
	//	ServiceFactory.Init();
		ServiceFactory.getPeopleService().getAllPeople();
	}
	
	public static void deletePeople(String fullName) {
//		ServiceFactory.Init();
		ServiceFactory.getPeopleService().deletePeople(fullName);
	}
	
	public static void updatePeople() {
		
	}
	
	public static void searchPeople(String fullName) {
		ServiceFactory.getPeopleService().searchPeople(fullName);
	}    

	public static void main(String[] args) {
//	People people;
//	people = new People();
		//	PeopleManage.getAllPeople();
	//	PeopleManage.deletePeople("abc css");
//		PeopleManage.createPeople(people);
	} 	
}




