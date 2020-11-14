package app.component.welcome;

import app.utility.viewUtils.Mediator;
import app.model.AuthMessage;
import app.services.ServiceFactory;
import app.services.common.NotiService;
import app.utility.SecurityUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void login(ActionEvent e) {
        String username = usernameField.getText().trim().toUpperCase();
        String password = passwordField.getText().trim();

        AuthMessage authMessage = ServiceFactory.getAuthService().login(username, SecurityUtils.getMD5Hash(password));
        if (authMessage.getStatus()) {
            Mediator.Notify("onGoingDashboard");
        } else {
            NotiService.info(authMessage.getMessage());
        }
    }

    @FXML
    public void guestLogin(ActionEvent e) {
        Mediator.Notify("onGoingDashboard");
    }
}
