package com.example.db_mysql.controllers.for_admin;

import com.example.db_mysql.connection.mysqlconnection;
import com.example.db_mysql.tables_softs.soft_inventory;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TableController {

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

    @FXML
    private TextField counts_txt;

    @FXML
    private TextField field_finder_txt;

    @FXML
    private TextField id_txt;

    @FXML
    private TextField invent_nomer_txt;

    @FXML
    private TextField kabina_txt;

    @FXML
    private TextField name_os_txt;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField pi_a_txt;

    @FXML
    private TextField powers_txt;

    @FXML
    private TextField primich_txt;


    ObservableList<soft_inventory> listM;
    ObservableList<soft_inventory> dataList;


    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

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

    @FXML
    void add_click(ActionEvent event) {
        conn = mysqlconnection.ConnectDb();

        String sql = "insert into `inventory` (`Кабинет`, `Инвентарный номер`, `Наименование`," +
                " `Кол-во`, `Примечание`, `Характеристики`, `PI адрес`, `Имя ОС`) values (?,?,?,?,?,?,?,?)";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, (kabina_txt.getText()));
            pst.setString(2, (invent_nomer_txt.getText()));
            pst.setString(3, (name_txt.getText()));
            pst.setString(4, (counts_txt.getText()));
            pst.setString(5, (primich_txt.getText()));
            pst.setString(6, (powers_txt.getText()));
            pst.setString(7, (pi_a_txt.getText()));
            pst.setString(8, (name_os_txt.getText()));

            pst.execute();
            update_click(event);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void delete_click(ActionEvent event) {
        conn = mysqlconnection.ConnectDb();

        String sql = "delete from `inventory` where id = ?";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, id_txt.getText());
            pst.execute();
            update_click(event);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void find_element(ActionEvent event) {
        // Очищаем предыдущие фильтры
        tab.getColumns().forEach(column -> {
            TableColumn<soft_inventory, ?> col = (TableColumn<soft_inventory, ?>) column;
            col.setCellValueFactory(new PropertyValueFactory<>(col.getId()));
        });

        // Получаем данные из базы данных
        dataList = mysqlconnection.getDatausers();

        // Создаем отфильтрованный список
        FilteredList<soft_inventory> filteredData = new FilteredList<>(dataList, b -> true);

        // Добавляем слушателя к текстовому полю фильтра
        field_finder_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(inventory -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Показывать все записи, если поле фильтра пусто
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Перебираем все столбцы и проверяем, содержится ли искомое значение
                for (TableColumn<soft_inventory, ?> column : tab.getColumns()) {
                    if (column.getCellData(inventory) != null &&
                            column.getCellData(inventory).toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Запись соответствует фильтру в одном из столбцов
                    }
                }

                return false; // Запись не соответствует фильтру ни в одном из столбцов
            });
        });

        // Оборачиваем отфильтрованный список в SortedList
        SortedList<soft_inventory> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());

        // Устанавливаем элементы в таблицу
        tab.setItems(sortedData);

        // Добавляем компаратор для сортировки
        tab.getSortOrder().add(tab.getColumns().get(0)); // Выберите колонку для сортировки (в данном случае первый столбец)
        tab.sort();
    }


}
