package app.component.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.annotation.processing.Messager;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void buttonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Hello mike french!");
        alert.showAndWait();
    }
}
