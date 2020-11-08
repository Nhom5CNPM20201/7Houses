package app.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainView {
    @FXML
    private AnchorPane homePane;
    @FXML
    private Button btnSearch;
    @FXML
    private AnchorPane mainView;
    public void searchOnClick(){
        System.out.println("You have clicked on Search button!");
    }

}
