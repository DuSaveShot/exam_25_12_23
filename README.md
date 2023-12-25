# exam_25_12_23

# Класс Controller для Админа
<pre class="shiki" style="background-color: #ffffff">
<code>package com.example.db_mysql.controllers.for_admin;

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
"</code><button class="button-copy-code"><svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="copy-docs-icon"></svg></button>
</pre>

# Класс soft 
<pre class="shiki" style="background-color: #000000">
<code>package com.example.db_mysql.tables_softs;

public class soft_inventory {

    Integer id;
    String kabina, Invent_nomer, Name, Counts, Primich,  Powers,  PI_a,  Name_OS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKabina() {
        return kabina;
    }

    public void setKabina(String kabina) {
        this.kabina = kabina;
    }

    public String getInvent_nomer() {
        return Invent_nomer;
    }

    public void setInvent_nomer(String invent_nomer) {
        Invent_nomer = invent_nomer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCounts() {
        return Counts;
    }

    public void setCounts(String counts) {
        Counts = counts;
    }

    public String getPrimich() {
        return Primich;
    }

    public void setPrimich(String primich) {
        Primich = primich;
    }

    public String getPowers() {
        return Powers;
    }

    public void setPowers(String powers) {
        Powers = powers;
    }

    public String getPI_a() {
        return PI_a;
    }

    public void setPI_a(String PI_a) {
        this.PI_a = PI_a;
    }

    public String getName_OS() {
        return Name_OS;
    }

    public void setName_OS(String name_OS) {
        Name_OS = name_OS;
    }

    public soft_inventory(Integer id, String kabina, String invent_nomer, String name, String counts, String primich, String powers, String PI_a, String name_OS) {
        this.id = id;
        this.kabina = kabina;
        this.Invent_nomer = invent_nomer;
        this.Name = name;
        this.Counts = counts;
        this.Primich = primich;
        this.Powers = powers;
        this.PI_a = PI_a;
        this.Name_OS = name_OS;
    }
}
"</code><button class="button-copy-code"><svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="copy-docs-icon"></svg></button>
</pre>

# Класс connection
<pre class="shiki" style="background-color: #ffffff">
<code>package com.example.db_mysql.connection;


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
"</code><button class="button-copy-code"><svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="copy-docs-icon"></svg></button>
</pre>
