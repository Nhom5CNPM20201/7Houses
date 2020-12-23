package app.component.dashboard.information;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Information implements Initializable {

    @FXML
    private Pane paneHang;

    @FXML
    private Pane paneMinhAnh;

    @FXML
    private Pane paneHanh;

    @FXML
    private Pane paneTrang;

    @FXML
    private Pane paneYen;

    @FXML
    private Pane paneHung;

    @FXML
    private Pane paneTuan;

    @FXML
    private Pane paneMinh;

    @FXML
    private Pane paneQuy;

    @FXML
    private Pane paneTuyen;


    @FXML
    private Button btnGithub;

    @FXML
    void githubOnClick(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/Nhom5CNPM20201/7Houses").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            paneTuan.getChildren().add(getImageURL("resources/tuan.jpg"));
            paneHung.getChildren().add(getImageURL("resources/hung.jpg"));
            paneHang.getChildren().add(getImageURL("resources/hang.jpg"));
            paneHanh.getChildren().add(getImageURL("resources/hanh.jpg"));
            paneYen.getChildren().add(getImageURL("resources/yen.jpg"));
            paneQuy.getChildren().add(getImageURL("resources/quy.jpg"));
            paneMinh.getChildren().add(getImageURL("resources/minh.jpg"));
            paneMinhAnh.getChildren().add(getImageURL("resources/minhanh.jpg"));
            paneTrang.getChildren().add(getImageURL("resources/trang.jpg"));
            paneTuyen.getChildren().add(getImageURL("resources/tuyen.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private ImageView getImageURL(String url) throws MalformedURLException {
        return new ImageView(new Image(new File(url).toURI().toURL().toExternalForm(),138,150,false,false));
    }
}
