package com.example.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthForm extends Application {

    // Пример базы данных пользователей
    private static final String[][] USER_DATABASE = {
            {"Vova", "5f4dcc3b5aa765d61d8327deb882cf99"}, // alice:password
            {"NeVova", "098f6bcd4621d373cade4e832627b4f6"} // bob:password
    };

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Авторизация");

        // Создание элементов формы
        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Войти");

        // Установка обработчика события для кнопки входа
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authenticateUser(username, password)) {
                showAlert("Успешная авторизация", "Добро пожаловать, " + username + "!");
            } else {
                showAlert("Ошибка авторизации", "Неверные учетные данные.");
            }

            // Очистка полей после попытки авторизации
            usernameField.clear();
            passwordField.clear();
        });

        // Создание сетки для размещения элементов формы
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Создание сцены и отображение окна
        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для проверки учетных данных пользователя
    private boolean authenticateUser(String username, String password) {
        for (String[] user : USER_DATABASE) {
            String storedUsername = user[0];
            String storedPasswordHash = user[1];

            if (storedUsername.equals(username) && verifyPassword(password, storedPasswordHash)) {
                return true;
            }
        }

        return false;
    }

    // Метод для проверки соответствия хешированного пароля
    private boolean verifyPassword(String password, String storedPasswordHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] passwordHash = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : passwordHash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString().equals(storedPasswordHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Метод для отображения диалогового окна с сообщением
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
