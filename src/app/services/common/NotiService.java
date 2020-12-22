package app.services.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class NotiService {

    public static void info(String content) {
        noti(content, Alert.AlertType.INFORMATION);
    }

    public static void error(String content) {
        noti(content, Alert.AlertType.ERROR);
    }

    public static Optional<ButtonType> confirm(String content) {
        return noti(content, Alert.AlertType.CONFIRMATION);
    }

    public static Optional<ButtonType> noti(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(content);
        return alert.showAndWait();
    }
}
