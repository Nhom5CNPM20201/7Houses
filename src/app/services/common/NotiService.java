package app.services.common;

import javafx.scene.control.Alert;

public class NotiService {

    public static void info(String content) {
        noti(content, Alert.AlertType.INFORMATION);
    }

    public static void error(String content) {
        noti(content, Alert.AlertType.ERROR);
    }

    public static void noti(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(content);
        alert.showAndWait();
    }
}
