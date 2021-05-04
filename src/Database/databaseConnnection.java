package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import sample.Information;
import sample.LoginController;

import java.io.*;
import java.sql.*;

public class databaseConnnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "demo_jbdc";
        String databaseUser = "root";
        String databasePass = "";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
    public static ObservableList<Information> getDataUsers() {
        ObservableList<Information> list = FXCollections.observableArrayList();
        databaseConnnection connnection = new databaseConnnection();
        Connection connectDb = connnection.getConnection();
        String sql = "SELECT pl.* FROM products p " +
                "JOIN productlist pl ON p.productCode = pl.productCode" +
                " WHERE p.username = '" + LoginController.getInstance().username() + "'";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(new Information(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getBytes(4)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
