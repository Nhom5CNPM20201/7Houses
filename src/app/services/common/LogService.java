package app.services.common;

import javafx.scene.control.Alert;

public class LogService implements IMessageService {
    public static void info(String content) {
        System.out.println("[Info] " + content);
    }

    public static void error(String content) {
        System.out.println("[Error] " + content);
    }
}
