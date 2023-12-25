package com.example.db_mysql.connection;


import com.example.db_mysql.tables_softs.soft_inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnection {

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
    public static ObservableList<soft_inventory> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<soft_inventory> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `inventory`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new soft_inventory(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("Кабинет"),
                        rs.getString("Инвентарный номер"),
                        rs.getString("Наименование"),
                        rs.getString("Кол-во"),
                        rs.getString("Примечание"),
                        rs.getString("Характеристики"),
                        rs.getString("PI адрес"),
                        rs.getString("Имя ОС")));
            }
        } catch (Exception ignored) {
        }
        return list;
    }

}
