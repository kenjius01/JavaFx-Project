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

public class Vivo implements Initializable {
    @FXML
    private JFXButton back;
    AnchorPane phoneAnchor;
    AnchorPane holderPane;
    @FXML
    private ImageView x60ImageView;
    @FXML
    private ImageView v20ImageView;
    @FXML
    private ImageView y11ImageView;
    @FXML
    private ImageView y20ImageView;
    @FXML
    private ImageView backImageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backfile = new File("res/symbol/return.png");
        Image backImage = new Image(backfile.toURI().toString());
        backImageView.setImage(backImage);

        File v20 = new File("res/content/vivo-v20_2_.jpg");
        Image v20Img = new Image(v20.toURI().toString());
        v20ImageView.setImage(v20Img);

        File x60 = new File("res/content/vivox60.jpg");
        Image x60Img = new Image(x60.toURI().toString());
        x60ImageView.setImage(x60Img);

        File y11 = new File("res/content/vivo-y11_1_.png");
        Image y11Img = new Image(y11.toURI().toString());
        y11ImageView.setImage(y11Img);

        File y20 = new File("res/content/vivo-y20.png");
        Image y20Img = new Image(y20.toURI().toString());
        y20ImageView.setImage(y20Img);
    }
    public void backAction() {
        HomePageController.getInstance().createPage(holderPane, "Home.fxml");
    }
    Samsung samsung = new Samsung();
    public void add1() {
        samsung.insertSql("vvX60", "Vivo X60 Pro 5G", (int) 14992000.0);
        HomePageController.getInstance().setNum();
    }
    public void add2() {
        samsung.insertSql("vvV20", "Vivo V20", 6790000);
        HomePageController.getInstance().setNum();
    }
    public void add3() {
        samsung.insertSql("vvY11", "Vivo Y11", 2590000);
        HomePageController.getInstance().setNum();
    }
    public void add4() {
        samsung.insertSql("vvY20", "Vivo Y20", 3290000);
        HomePageController.getInstance().setNum();
    }
    public void x60Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Vivo/vivoX60.fxml");
    }
    public void v20Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Vivo/vivoV20.fxml");
    }
}
