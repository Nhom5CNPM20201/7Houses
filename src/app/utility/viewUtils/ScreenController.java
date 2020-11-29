package app.utility.viewUtils;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.util.Duration;

import java.net.URL;

public class ScreenController {
    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void active(URL FXMLname) {
        try {
            Parent parent = FXMLLoader.load(FXMLname);
            main.setRoot(parent);
            makeFadeInEffect(parent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void activeSubscreen(SubScene subScene, URL FXMLname) {
        try {
            Parent parent = FXMLLoader.load(FXMLname);
            subScene.setRoot(parent);
            makeFadeInEffect(parent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeFadeInEffect(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private static void makeFadeOutEffect(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
}
