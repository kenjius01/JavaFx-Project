package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Iphone implements Initializable {
    @FXML
    private ImageView backImageView;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView ip12ImageView;
    public AnchorPane holderPane;
    @FXML
    private ImageView ip11ImageView;
    @FXML
    private ImageView ipxrImageView;
    @FXML
    private ImageView ip8ImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backfile = new File("res/symbol/return.png");
        Image backImage = new Image(backfile.toURI().toString());
        backImageView.setImage(backImage);

        File ip12 = new File("res/content/12promax.jpg");
        Image ip12Image = new Image(ip12.toURI().toString());
        ip12ImageView.setImage(ip12Image);

        File ip11 = new File("res/content/ip11prmax.jpg");
        Image ip11Image = new Image(ip11.toURI().toString());
        ip11ImageView.setImage(ip11Image);

        File ipxr = new File("res/content/ipxr.jpg");
        Image ipxrImage = new Image(ipxr.toURI().toString());
        ipxrImageView.setImage(ipxrImage);

        File ip8 = new File("res/content/ip8plus.jpg");
        Image ip8Img = new Image(ip8.toURI().toString());
        ip8ImageView.setImage(ip8Img);

    }

    public void backAction() {
        HomePageController.getInstance().createPage(holderPane, "Home.fxml");
    }
    Samsung samsung = new Samsung();
    public void add1() {

        samsung.insertSql("ip12Pro", "IPhone 12 Pro Max 512GB", 38500000);
    }
    public void add2() {
        samsung.insertSql("ip11Pro", "IPhone 11 Pro Max 256GB", 32000000);
        HomePageController.getInstance().setNum();
    }
    public void add3() {
        samsung.insertSql("ipXr", "IPhone XR 256GB", 16990000);
        HomePageController.getInstance().setNum();
    }
    public void add4() {
        samsung.insertSql("ip8Plus", "IPhone 8 Plus 64GB", 12000000);
        HomePageController.getInstance().setNum();
    }
    public void ip12Click() {
        HomePageController.getInstance().createPage(holderPane, "/Iphone/ip12Pro.fxml");
    }

    public void ip11Click() {
        HomePageController.getInstance().createPage(holderPane, "/Iphone/ip11Pro.fxml");
    }

    public void ipXrClick() {
        HomePageController.getInstance().createPage(holderPane, "/Iphone/ipXr.fxml");
    }
    public void ip8Click() {
        HomePageController.getInstance().createPage(holderPane, "/Iphone/ip8Plus.fxml");
    }
}
