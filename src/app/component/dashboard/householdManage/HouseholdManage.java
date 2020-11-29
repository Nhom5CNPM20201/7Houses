package app.component.dashboard.householdManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import app.entity.HouseHold;
import app.services.ServiceFactory;

public class HouseholdManage {

    @FXML
    private TableView<?> tblListHouseHold;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    public void addOnClick(ActionEvent event) {

    }
    @FXML
    public void deleteOnClick(ActionEvent event) {

    }

    @FXML
    public void editOnClick(ActionEvent event) {

    }
    
    public static void createHouseHold(HouseHold houseHold) {
		ServiceFactory.getHouseHoldService().createHouseHold(houseHold);
	}
	
	public static void getAllHouseHold() {
		ServiceFactory.getHouseHoldService().getAllHouseHold();
	}
	
	public static void deleteHouseHold(String houseHoldBook) {
		ServiceFactory.getHouseHoldService().deleteHouseHold(houseHoldBook);
	}
	
	public static void updateHouseHold() {
		
	}
	
	public static void searchHouseHold(String houseHoldBook) {
		ServiceFactory.getHouseHoldService().searchHouseHold(houseHoldBook);
	}

}
