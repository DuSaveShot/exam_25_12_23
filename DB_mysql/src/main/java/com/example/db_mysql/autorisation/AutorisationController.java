package com.example.db_mysql.autorisation;

import com.example.db_mysql.HelloApplication;
import com.example.db_mysql.connection.MSC_Auth;
import com.example.db_mysql.tables_softs.soft_account;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AutorisationController {

    @FXML
    private TextField login_txt;

    @FXML
    private TextField pwd_txt;

    @FXML
    void enter_click(ActionEvent event) throws IOException {
        String login = login_txt.getText();
        String password = pwd_txt.getText();

        // Получаем данные из базы данных
        ObservableList<soft_account> accounts = MSC_Auth.getDatausers();

        // Проверяем авторизацию
        boolean authorized = false;
        int userRole = -1; // -1, чтобы обозначить, что пользователь с таким логином/паролем не найден
        for (soft_account account : accounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                authorized = true;
                userRole = account.getRole();
                break;
            }
        }

        // Обработка результатов авторизации
        if (authorized) {
            if (userRole == 1) {  // Роль Пользователь

                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-user.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1287, 634);
                Stage stage = new Stage();
                stage.setTitle("Название");
                stage.setScene(scene);
                stage.show();

                JOptionPane.showMessageDialog(null,"Здравствуйте, " + login_txt.getText().toString() + " - вы пользователь.");

            } else { // Роль Администратор
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene1 = new Scene(fxmlLoader.load(), 1287, 634);
                Stage stage1 = new Stage();
                stage1.setTitle("Название");
                stage1.setScene(scene1);
                stage1.show();

                JOptionPane.showMessageDialog(null,"Здравствуйте, " + login_txt.getText().toString() + " - вы адмннистратор.");
            }
        } else {
            // Выводите сообщение об ошибке
            JOptionPane.showMessageDialog(null, "Неверный логин или пароль. Пожалуйста, проверьте введенные данные");
        }
    }

}
