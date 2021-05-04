package sample;

import Database.databaseConnnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.mysql.cj.log.Log;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView accImageView;
    @FXML
    private ImageView passImageView;
    @FXML
    private ImageView loadImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private JFXButton login;
    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private JFXButton signUp;
    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    public String username() {
        return usernameTextField.getText();
    }

    public void loginButton(ActionEvent event) {
        check();
        loadImageView.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(event1 -> {
            if (!usernameTextField.getText().isBlank() &&
                    !passwordField.getText().isBlank()) {
                databaseConnnection connection = new databaseConnnection();
                Connection connectDB = connection.getConnection();

                String verifyLogign = "SELECT COUNT(1) FROM account WHERE username ='" + usernameTextField.getText()
                        + "'AND password ='" + passwordField.getText() + "'";
                try {
                    Statement statement = connectDB.createStatement();
                    ResultSet queryResult = statement.executeQuery(verifyLogign);
                    while (queryResult.next()) {
                        if (queryResult.getInt(1) == 1) {
                            login.getScene().getWindow().hide();
                            try {
                                Stage home = new Stage();
                                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                                Scene scene = new Scene(root);
                                home.setScene(scene);
                                home.setResizable(false);
                                home.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else  {
                            loginMessageLabel.setText("your password or username is incorrect");
                            loadImageView.setVisible(false);
                            System.out.println("Incorrect");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else {
                loginMessageLabel.setText("Please Enter Your username or password!");
                loadImageView.setVisible(false);
            }
        });
        pt.play();
    }


    public void signUp(ActionEvent event) throws IOException {
        login.getScene().getWindow().hide();
        Stage signUp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/SignUp.fxml"));
        Scene scene = new Scene(root);
        signUp.setScene(scene);
        signUp.show();
        signUp.setResizable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("res/symbol/Smartphone-PNG-Pic.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File accFile = new File("res/symbol/user.png");
        Image accImage = new Image(accFile.toURI().toString());
        accImageView.setImage(accImage);

        File passFile = new File("res/symbol/password.png");
        Image passImage = new Image(passFile.toURI().toString());
        passImageView.setImage(passImage);

        File loadFile = new File("res/symbol/load.gif");
        Image loadImage = new Image(loadFile.toURI().toString());
        loadImageView.setImage(loadImage);
        loadImageView.setVisible(false);

        databaseConnnection connnection = new databaseConnnection();
        Connection conn = connnection.getConnection();

        String check = "SELECT * FROM account";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while (rs.next()) {
                if (rs.getInt("remember") == 1) {
                    usernameTextField.setText(rs.getString("username"));
                    passwordField.setText(rs.getString("password"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void check() {
        if (checkBox.isSelected()) {
            databaseConnnection connnection = new databaseConnnection();
            Connection conn = connnection.getConnection();
            String sql = "UPDATE account SET remember = '1' WHERE account.username = '"
                    + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
            String updateSql = "UPDATE account SET remember = 0 \n" +
                    "WHERE username != '" + usernameTextField.getText() + "'";
            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                statement.executeUpdate(updateSql);
                System.out.println("change");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
