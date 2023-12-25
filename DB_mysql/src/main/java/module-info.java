module com.example.db_mysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.db_mysql to javafx.fxml;
    exports com.example.db_mysql;
    exports com.example.db_mysql.autorisation;
    opens com.example.db_mysql.autorisation to javafx.fxml;
    exports com.example.db_mysql.connection;
    opens com.example.db_mysql.connection to javafx.fxml;
    exports com.example.db_mysql.tables_softs;
    opens com.example.db_mysql.tables_softs to javafx.fxml;
    exports com.example.db_mysql.controllers.for_admin;
    opens com.example.db_mysql.controllers.for_admin to javafx.fxml;
    exports com.example.db_mysql.controllers.for_users;
    opens com.example.db_mysql.controllers.for_users to javafx.fxml;
}