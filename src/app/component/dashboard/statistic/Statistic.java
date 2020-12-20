package app.component.dashboard.statistic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Statistic implements Initializable {

    @FXML
    private ComboBox<?> typeStatistic;

    @FXML
    private Pane paneView;

    @FXML
    private Button btnTest;

    @FXML
    void testOnclick(ActionEvent event) {

    }

    @FXML
    void typeCombobox(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
