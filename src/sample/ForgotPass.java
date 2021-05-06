package sample;

import Database.databaseConnnection;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ForgotPass implements Initializable {
    @FXML
    private ImageView emailImgView;
    @FXML
    private ImageView passImgView;
    @FXML
    private ImageView pass1;
    @FXML
    private TextField emailText;
    @FXML
    private AnchorPane Pane;
    @FXML
    private PasswordField passText;
    @FXML
    private PasswordField passText1;
    @FXML
    private JFXButton check;
    @FXML JFXButton back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File emailFile = new File("res/symbol/email.png");
        Image emailImg = new Image(emailFile.toURI().toString());
        emailImgView.setImage(emailImg);

        File passFile = new File("res/symbol/password.png");
        Image passImg = new Image(passFile.toURI().toString());
        passImgView.setImage(passImg);

        File passFile1 = new File("res/symbol/password.png");
        Image passImg1 = new Image(passFile1.toURI().toString());
        pass1.setImage(passImg1);
    }
    databaseConnnection connnection = new databaseConnnection();
    Connection conn = connnection.getConnection();

    public void checkEmail() {

        String sql = "SELECT COUNT(username) FROM account WHERE email = '" + emailText.getText() +
                "' AND username ='" + LoginController.getInstance().username() + "'";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    Pane.setVisible(true);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Your email is incorrect!");
                    alert.setHeaderText("Sorry");
                    alert.show();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void setNewPass() {
        String sql = "UPDATE account SET password = '" + passText1.getText() + "' WHERE email = '"
                + emailText.getText() + "'";
        if (passText.getText().equals(passText1.getText()) && !passText.getText().isBlank()) {
            Statement statement = null;
            try {
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                check.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage register = new Stage();
                register.initStyle(StageStyle.DECORATED);
                register.setScene(new Scene(root, 600, 457));
                register.setResizable(false);
                register.show();


            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        }
        else {
            JOptionPane.showMessageDialog(null, "Password incorrect or you didn't filled anything in password field!");
        }
    }

    public void back() throws IOException {
        check.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage register = new Stage();
        register.initStyle(StageStyle.DECORATED);
        register.setScene(new Scene(root, 600, 457));
        register.setResizable(false);
        register.show();
    }
}
