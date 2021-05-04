package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private ImageView ssImageView;
    @FXML
    private ImageView ipImageView;
    @FXML
    private ImageView xmImageView;
    @FXML
    private ImageView vvImageView;
    @FXML
    private JFXButton samsungBtn;
    @FXML
    private JFXButton ipBtn;
    @FXML
    private JFXButton viBtn;
    @FXML
    private JFXButton xiBtn;
    @FXML
    private AnchorPane home;
    AnchorPane phoneAnchor;
    AnchorPane samsungAnchor;
    AnchorPane note20Anchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File ssFIle = new File("res/content/samsung.jpg");
        Image ssImage = new Image(ssFIle.toURI().toString());
        ssImageView.setImage(ssImage);

        File ipFIle = new File("res/content/iphone.jpg");
        Image ipImage = new Image(ipFIle.toURI().toString());
        ipImageView.setImage(ipImage);

        File xmFIle = new File("res/content/xiaomi.jpg");
        Image xmImage = new Image(xmFIle.toURI().toString());
        xmImageView.setImage(xmImage);

        File vvFIle = new File("res/content/vivo.jpg");
        Image vvImage = new Image(vvFIle.toURI().toString());
        vvImageView.setImage(vvImage);
    }
    public void samsungBtn() {

        HomePageController.getInstance().createPage(note20Anchor, "Samsung.fxml");

    }

    public void iphoneBtn() {
        HomePageController.getInstance().createPage(phoneAnchor, "Iphone.fxml");
    }

    public void xiaomiBtn() {
        HomePageController.getInstance().createPage(note20Anchor, "Xiaomi.fxml");
    }

    public void vivoBtn() {
        HomePageController.getInstance().createPage(phoneAnchor, "Vivo.fxml");
    }
}
