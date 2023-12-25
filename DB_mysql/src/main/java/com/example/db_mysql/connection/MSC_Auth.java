package com.example.db_mysql.connection;


import com.example.db_mysql.tables_softs.soft_account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MSC_Auth {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bd_practic","root","");
//            JOptionPane.showMessageDialog(null, "Соединение установлено");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    public static ObservableList<soft_account> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<soft_account> listAccount = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `account`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listAccount.add(new soft_account(
                        rs.getString("login"),
                        rs.getString("password"),
                        Integer.parseInt(rs.getString("role"))));
            }
        } catch (Exception ignored) {
        }
        return listAccount;
    }

}
