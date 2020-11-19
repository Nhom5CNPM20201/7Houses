package app;

import app.utility.viewUtils.Mediator;
import app.utility.viewUtils.ScreenController;
import app.services.ServiceFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {
    public static Stage primaryStage;

    private ScreenController screenController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("component/dashboard/Dashboard.fxml"));

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("app/component/style.css");
        screenController = new ScreenController(scene);

        primaryStage.setTitle("7Houses");
        //File f = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

        Mediator.unSubscribe("onGoingDashboard");
        Mediator.unSubscribe("onGoingWelcome");
        Mediator.subscribe("onGoingDashboard", event -> onGoingDashboard(null));
        Mediator.subscribe("onGoingWelcome", event -> onGoingWelcome(null));
    }

    public static void main(String[] args) {
        ServiceFactory.Init();
        launch(args);
    }

    private void onGoingWelcome(ActionEvent event) {
        screenController.active(getClass().getResource("component/welcome/Welcome.fxml"));
    }

    private void onGoingDashboard(ActionEvent event) {
        screenController.active(getClass().getResource("component/dashboard/Dashboard.fxml"));
    }
}
