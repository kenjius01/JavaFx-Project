package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Database.databaseConnnection;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Myphone implements Initializable {
    @FXML
    private TableView<Information> tableView;
    @FXML
    private TableColumn<Information, String> col_Code;
    @FXML
    private TableColumn<Information, String> col_Name;
    @FXML
    private TableColumn<Information, Integer> col_price;
    @FXML
    private TableColumn<Information, Byte> col_Img;
    ObservableList<Information> listM;
    @FXML
    private ImageView imageProduct;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File cartFile = new File("res/content/mycart.gif");
        Image cartImg = new Image(cartFile.toURI().toString());
        imageProduct.setImage(cartImg);

        col_Code.setCellValueFactory(new PropertyValueFactory<Information, String>("prdCode"));
        col_Name.setCellValueFactory(new PropertyValueFactory<Information, String>("prdName"));
        col_price.setCellValueFactory(new PropertyValueFactory<Information, Integer>("price"));
        listM = databaseConnnection.getDataUsers();
        tableView.setItems(listM);
        tableView.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN ) {
                try {
                    databaseConnnection connnection = new databaseConnnection();
                    Connection connectDb = connnection.getConnection();
                    Information information = (Information)tableView.getSelectionModel().getSelectedItem();
                    System.out.println(information.getPrdName());
                    String query = "SELECT pl.imageProduct FROM products p JOIN productlist pl " +
                            "ON p.productCode = pl.productCode" +
                            " WHERE p.username = '" + LoginController.getInstance().username() + "' AND pl.productCode = '"
                            + information.getPrdCode() + "'";
                    Statement statement = connectDb.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        InputStream is = rs.getBinaryStream("imageProduct");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));
                        byte[] content = new byte[2048];
                        int size = 0;
                        while ((size = is.read(content)) != -1) {
                            os.write(content, 0, size);
                        }
                        os.close();
                        is.close();
                        Image image = new Image("File:photo.jpg");
                        imageProduct.setImage(image);

                    }

                } catch (SQLException | FileNotFoundException throwables) {
                    throwables.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        tableView.setOnMouseClicked(e -> {
                try {
                    databaseConnnection connnection = new databaseConnnection();
                    Connection connectDb = connnection.getConnection();
                    Information information = (Information)tableView.getSelectionModel().getSelectedItem();
                    System.out.println(information.getPrdName());
                    String query = "SELECT pl.imageProduct FROM products p JOIN productlist pl " +
                            "ON p.productCode = pl.productCode" +
                            " WHERE p.username = '" + LoginController.getInstance().username() + "' AND pl.productCode = '"
                            + information.getPrdCode() + "'";
                    Statement statement = connectDb.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        InputStream is = rs.getBinaryStream("imageProduct");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));
                        byte[] content = new byte[2048];
                        int size = 0;
                        while ((size = is.read(content)) != -1) {
                            os.write(content, 0, size);
                        }
                        os.close();
                        is.close();
                        Image image = new Image("File:photo.jpg");
                        imageProduct.setImage(image);

                    }

                } catch (SQLException | FileNotFoundException throwables) {
                    throwables.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



        });
    }
}
