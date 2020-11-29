package app.component.dashboard.householdManage;

import app.entity.HouseHold;
import app.services.ServiceFactory;
import app.utility.viewUtils.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HouseholdManage implements Initializable {
    public HouseholdManage(){}



    @FXML
    private TableView<?> tblListHouseHold;

    @FXML
    private SubScene mainHouseHold;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    public void addOnClick(ActionEvent event) {
        this.switchView(getClass().getResource("../../common/HouseholdForm.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.switchView(getClass().getResource("./HouseholdList.fxml"));
    }
    public void switchView(URL FXMLname){
        ScreenController.activeSubscreen(this.mainHouseHold,FXMLname);
    }
}
