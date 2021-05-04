package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Database.databaseConnnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class Samsung implements Initializable {
    @FXML
    private ImageView note20ImageView;
    @FXML
    private ImageView s21ImageView;
    @FXML
    private ImageView foldImageView;
    @FXML
    private ImageView a11ImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private JFXButton back;
    AnchorPane holderPane;
    AnchorPane phoneAnchor;
    AnchorPane note20Anchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backfile = new File("res/symbol/return.png");
        Image backImage = new Image(backfile.toURI().toString());
        backImageView.setImage(backImage);

        File note20File = new File("res/content/samsungNote20.jpg");
        Image note20Image = new Image(note20File.toURI().toString());
        note20ImageView.setImage(note20Image);

        File s21File = new File("res/content/samsungs21.jpg");
        Image s21Image = new Image(s21File.toURI().toString());
        s21ImageView.setImage(s21Image);

        File foldFile = new File("res/content/ssfold.jpg");
        Image foldImage = new Image(foldFile.toURI().toString());
        foldImageView.setImage(foldImage);

        File a11File = new File("res/content/ssA11.jpg");
        Image a11Image = new Image(a11File.toURI().toString());
        a11ImageView.setImage(a11Image);
    }

    public void backAction() {
        HomePageController.getInstance().createPage(holderPane, "Home.fxml");
    }
    public void insertSql(String prdCode, String prdName, int price) {
        databaseConnnection connnection = new databaseConnnection();
        Connection connectDB = connnection.getConnection();
        String username = LoginController.getInstance().username();
        String sql = "Select id FROM account WHERE username = '" + username + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet id = statement.executeQuery(sql);

            while (id.next()){
                String check = "SELECT COUNT(productCode) FROM products WHERE productCode = '" +
                        prdCode + "' AND username = '" + username + "'";
                Statement checkRs = connectDB.createStatement();
                ResultSet resultSet = checkRs.executeQuery(check);
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Can't add this phone to My phone");
                        alert.setContentText("You have added this phone before!");
                        alert.show();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        ButtonType one = new ButtonType("Yes");
                        ButtonType two = new ButtonType("No");
                        alert.getButtonTypes().setAll(one, two);
                        alert.setHeaderText("Do you want to add to My Phone ?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == one){
                            String insert = "INSERT INTO `products`(`id`, `username`, `productName`, `price`, `productCode`) VALUES ('"
                                    + id.getInt(1) + "', '" + username + "', '"
                                    + prdName + "', '" + price + "', '" + prdCode + "')";
                            Statement statement1 = connectDB.createStatement();
                            statement1.executeUpdate(insert);
                            HomePageController.getInstance().setNum();

                        }

                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addBtn() {
        insertSql("ss20", "Samsung Galaxy Note 20 Ultra", 22500000);
    }
    public void addBtn1() {
        insertSql("ssS21", "Samsung Galaxy S21 Plus 5G", 21300000);
    }
    public void addBtn2() {
        insertSql("zfold2", "Samsung Galaxy Z Fold2 5G", 47500000);
    }
    public void addBtn3() {
        insertSql("ssA11", "Samsung Galaxy A11", 2700000);
    }
    public void ss20Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "samsungNote20.fxml");
    }
    public void s21Click() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Samsung/galaxys21.fxml");
    }
    public void zfoldClick() {
        HomePageController.getInstance().createPage(phoneAnchor, "/Samsung/zfold2.fxml");
    }

}

