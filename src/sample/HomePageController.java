package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Database.databaseConnnection;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private ImageView brandImageView;
    @FXML
    private ImageView detailImageView;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane home;
    @FXML
    private Text welcome;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private Label lbMenu;
    @FXML
    private VBox overflowContainer;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnExit;
    @FXML
    private ImageView welImageView;
    @FXML
    private HBox toolbarRight;
    @FXML
    private ImageView outImageView;
    @FXML
    private ImageView exitImageView;
    @FXML
    private JFXButton none;
    @FXML
    private JFXButton aboutButton;
    @FXML
    private  JFXButton homeBtn;
    @FXML
    private JFXButton contactBtn;
    @FXML
    private JFXButton myPhone;
    @FXML
    private Label label;

    private static HomePageController instance;

    public HomePageController() {
        instance = this;
    }

    public static HomePageController getInstance() {
        return instance;
    }

    public void setNum() {
        databaseConnnection connnection = new databaseConnnection();
        Connection conn = connnection.getConnection();
        String sql = "SELECT COUNT(productCode) FROM products WHERE username = '" +
                LoginController.getInstance().username() + "'";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int num = rs.getInt(1);
                label.setText(String.valueOf(num));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("res/symbol/Smartphone-PNG-Pic.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandImageView.setImage(brandingImage);

        File detailFile = new File("res/symbol/detail.png");
        Image detailImage = new Image(detailFile.toURI().toString());
        detailImageView.setImage(detailImage);

        File welFile = new File("res/symbol/welcome.gif");
        Image welImage = new Image(welFile.toURI().toString());
        welImageView.setImage(welImage);

        File outFile = new File("res/symbol/logout.png");
        Image outImage = new Image(outFile.toURI().toString());
        outImageView.setImage(outImage);

        File exitFile = new File("res/symbol/exit.png");
        Image exitImage = new Image(exitFile.toURI().toString());
        exitImageView.setImage(exitImage);

        JFXRippler rippler = new JFXRippler(lbMenu);
        rippler.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler.setRipplerFill(Paint.valueOf("green"));
        toolbarRight.getChildren().add(rippler);
        openMenu();
        createPage(home, "Home.fxml");
        setUsername(LoginController.getInstance().username());
        myCart();

    }

    private void myCart() {
        databaseConnnection connnection = new databaseConnnection();
        Connection connectDb = connnection.getConnection();
        String sql = "SELECT COUNT(productName) FROM products WHERE username = '"
                + LoginController.getInstance().username() + "'";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                label.setText(String.valueOf(rs.getInt(1)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setUsername(String user) {
        this.welcome.setText("Welcome, " + user + "!");
    }

    private void openMenu() {
        JFXPopup pop = new JFXPopup(overflowContainer);
        pop.setPopupContent(overflowContainer);
        pop.setAutoHide(true);
        overflowContainer.setVisible(false);
            lbMenu.setOnMouseClicked(e -> {
                overflowContainer.setVisible(true);
                pop.show(lbMenu, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -1, 42);

        });

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public void createPage(AnchorPane a, String title) {
        try {
            a = FXMLLoader.load(getClass().getResource(title));
            setNode(a);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void Exit() {
        Platform.exit();
    }

    @FXML
    public void LogOut() {
        none.getParent().getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            login.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aboutUs() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("About Us");
        alert.setHeaderText(null);
        alert.setContentText("The Mobile Phone Review.\n We will show you all information " +
                "\n about many types of phone\n such as: Samsung, Iphone, Xiaomi, Vivo");
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.show();
    }
    public void homeBtn() {
        createPage(holderPane, "Home.fxml");
    }

    public void contactUs() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Contact Us");
        alert.setHeaderText(null);
        alert.setContentText("SDT: 0962369056");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.show();

    }

    public void myPhoneBtn() {
        createPage(holderPane, "Myphone.fxml");
    }
}
