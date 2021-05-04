package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Xiaomi implements Initializable {
    @FXML
    private JFXButton back;
    AnchorPane holderPane;
    AnchorPane phoneAnchor;
    @FXML
    private ImageView backImageView;
    @FXML
    private ImageView mi11ImgView;
    @FXML
    private ImageView mi10tImgView;
    @FXML
    private ImageView pocoImgView;
    @FXML
    private ImageView mi9ImgView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backfile = new File("res/symbol/return.png");
        Image backImage = new Image(backfile.toURI().toString());
        backImageView.setImage(backImage);

        File mi11 = new File("res/content/mi11.jpg");
        Image mi11Img = new Image(mi11.toURI().toString());
        mi11ImgView.setImage(mi11Img);

        File mi10t = new File("res/content/mi10t.jpg");
        Image mi10tImg = new Image(mi10t.toURI().toString());
        mi10tImgView.setImage(mi10tImg);

        File poco = new File("res/content/pocof2.jpg");
        Image pocoImg = new Image(poco.toURI().toString());
        pocoImgView.setImage(pocoImg);

        File mi9 = new File("res/content/redmi9.jpg");
        Image mi9Img = new Image(mi9.toURI().toString());
        mi9ImgView.setImage(mi9Img);
    }

    public void backAction() {
        HomePageController.getInstance().createPage(holderPane, "Home.fxml");
    }
    Samsung samsung = new Samsung();
    public void add1() {
        samsung.insertSql("mi11", "Xiaomi Mi 11 5G", 18490000);
        HomePageController.getInstance().setNum();
    }
    public void add2() {
        samsung.insertSql("mi10t", "Xiaomi Mi 10T", 10300000);
        HomePageController.getInstance().setNum();
    }
    public void add3() {
        samsung.insertSql("pocof2", "POCO F2 Pro 5G", 9200000);
        HomePageController.getInstance().setNum();
    }
    public void add4() {
        samsung.insertSql("minote9", "Xiaomi Redmi 9 4G", 2950000);
        HomePageController.getInstance().setNum();
    }
    public void mi11Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Xiaomi/mi11.fxml");
    }
    public void mi10tClick() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Xiaomi/mi10T.fxml");
    }
    public void pocoF2Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Xiaomi/pocoF2.fxml");
    }

    public void note9Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Xiaomi/miNote9.fxml");
    }
}
