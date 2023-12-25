package com.example.db_mysql.controllers.for_users;

import com.example.db_mysql.connection.mysqlconnection;
import com.example.db_mysql.tables_softs.soft_inventory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class Table1_Controller {

    @FXML
    private TableView<soft_inventory> tab;
    @FXML
    private TableColumn<soft_inventory, String> Counts;

    @FXML
    private TableColumn<soft_inventory, Integer> ID;

    @FXML
    private TableColumn<soft_inventory, String> Invent_nomer;

    @FXML
    private TableColumn<soft_inventory, String> Kabina;

    @FXML
    private TableColumn<soft_inventory, String> Name;

    @FXML
    private TableColumn<soft_inventory, String> Name_OS;

    @FXML
    private TableColumn<soft_inventory, String> PI_a;

    @FXML
    private TableColumn<soft_inventory, String> Powers;

    @FXML
    private TableColumn<soft_inventory, String> Primich;

    ObservableList<soft_inventory> listM;


    @FXML
    void add_click(ActionEvent event) {

    }

    @FXML
    void delete_click(ActionEvent event) {

    }

    @FXML
    void update_click(ActionEvent event) {
        ID.setCellValueFactory(new PropertyValueFactory<soft_inventory, Integer>("id"));
        Kabina.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("kabina"));
        Invent_nomer.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("invent_nomer"));
        Name.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("name"));
        Counts.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("counts"));
        Primich.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("primich"));
        Powers.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("powers"));
        PI_a.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("PI_a"));
        Name_OS.setCellValueFactory(new PropertyValueFactory<soft_inventory, String>("name_OS"));

        listM = mysqlconnection.getDatausers();
        tab.setItems(listM);

        JOptionPane.showMessageDialog(null, "Обновлено");
    }

}
