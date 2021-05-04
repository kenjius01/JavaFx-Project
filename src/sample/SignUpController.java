package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import Database.databaseConnnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.File;
import java.sql.Connection;


public class SignUpController implements Initializable {
    @FXML
    private ImageView userImageView;
    @FXML
    private ImageView passImageView;
    @FXML
    private ImageView genderImageView;
    @FXML
    private ImageView emailImageView;
    @FXML
    private ImageView loadImageView;
    @FXML
    private ImageView brandImageview;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label signUpMessageLabel;
    @FXML
    private JFXRadioButton maleButton;
    @FXML
    private JFXRadioButton otherButton;
    @FXML
    private JFXRadioButton femaleButton;
    @FXML
    private TextField emailField;
    @FXML
    private JFXButton logIn;
    @FXML
    private JFXButton register;
    @FXML
    private Label signUpMessageLabel1;



    public void signUpButton(ActionEvent event) throws SQLException {
        loadImageView.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(4));
        pt.setOnFinished(event1 -> {
            if (!usernameTextField.getText().isBlank() &&
                    !passwordField.getText().isBlank() &&
                    !emailField.getText().isBlank() && !getGender().isBlank()) {
                signUpMessageLabel.setText("");
                try {
                    validateSignUp();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                loadImageView.setVisible(false);
            } else {
                signUpMessageLabel.setText("Please Enter Your username or password!");
                signUpMessageLabel1.setText("");
                loadImageView.setVisible(false);
            }
        });
        pt.play();
    }

    public void validateSignUp() throws SQLException {
        databaseConnnection connnection = new databaseConnnection();
        Connection connectDB = connnection.getConnection();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String checkAcc = "SELECT COUNT(username) from account WHERE username = '" + username + "'";
        String insert = "INSERT INTO `account`(`username`, `password`, `gender`, `email`, remember) VALUES ('"
                + username + "', '" + password + "','" + getGender() + "', '" + email +"'," + 0 + ")";
        Statement check = connectDB.createStatement();
        ResultSet rs = check.executeQuery(checkAcc);
        if (!email.contains("@gmail.com")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid email!");
            alert.show();
        }
        else {
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    Alert error = new Alert(Alert.AlertType.WARNING);
                    error.setContentText("The username is already in use. Try a different name.");
                    error.show();
                }
                else {
                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insert);
                        signUpMessageLabel1.setText("Successfull!");


                    } catch (Exception e) {
                        e.getCause();
                        e.printStackTrace();
                    }
                }
            }
        }



    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        register.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage register = new Stage();
        register.initStyle(StageStyle.DECORATED);
        register.setScene(new Scene(root, 600, 457));
        register.show();
    }

    public String getGender() {
        String gen = "";
        if (maleButton.isSelected()) {
            gen = "Male";
        }
        else if (femaleButton.isSelected()) {
            gen = "Female";
        }
        else if (otherButton.isSelected()) {
            gen = "Other";
        }
        return gen;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("res/symbol/animated_register.gif");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandImageview.setImage(brandingImage);

        File userFile = new File("res/symbol/user.png");
        Image userImage = new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);

        File passFile = new File("res/symbol/password.png");
        Image passImage = new Image(passFile.toURI().toString());
        passImageView.setImage(passImage);

        File genderFile = new File("res/symbol/gender.png");
        Image genderImage = new Image(genderFile.toURI().toString());
        genderImageView.setImage(genderImage);

        File emailFile = new File("res/symbol/email.png");
        Image emailImage = new Image(emailFile.toURI().toString());
        emailImageView.setImage(emailImage);

        File loadFile = new File("res/symbol/oxygen-loader.gif");
        Image loadImage = new Image(loadFile.toURI().toString());
        loadImageView.setImage(loadImage);
        loadImageView.setVisible(false);
    }

}
