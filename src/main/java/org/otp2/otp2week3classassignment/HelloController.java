package org.otp2.otp2week3classassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "secret";

    @FXML
    private ChoiceBox<String> choiceboxLanguage;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;
    
    @FXML
    public void initialize() {
        choiceboxLanguage.setValue("English");

        Locale.setDefault(Locale.ENGLISH);

        ResourceBundle bundle = ResourceBundle.getBundle("bundle", Locale.ENGLISH);

        choiceboxLanguage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "English":
                        setEN();
                        break;
                    case "Japanese":
                        setJP();
                        break;
                    case "Russian":
                        setRU();
                        break;
                }
            }
        });
    }

    @FXML
    public void setEN() {
        Locale l = new Locale("EN");
        loadView(l);
    }

    @FXML
    public void setJP() {
        Locale l = new Locale("JP");
        loadView(l);
    }

    @FXML
    public void setRU() {
        Locale l = new Locale("RU");
        loadView(l);
    }
    public void loadView(Locale l) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", l);
        firstNameLabel.setText(bundle.getString("firstnamelabel.text"));
        lastNameLabel.setText(bundle.getString("lastnamelabel.text"));
        emailLabel.setText(bundle.getString("email.text"));
        saveButton.setText(bundle.getString("savebutton.text"));
    }

    public void saveButtonAction() {
        System.out.println("Save button clicked!");
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                String sql = null;
                if (choiceboxLanguage.getValue().equals("English")) {
                    sql = "INSERT INTO employees_en (first_name, last_name, email) VALUES (?, ?, ?)";
                } else if (choiceboxLanguage.getValue().equals("Japanese")) {
                    sql = "INSERT INTO employees_jp (first_name, last_name, email) VALUES (?, ?, ?)";
                } else if (choiceboxLanguage.getValue().equals("Russian")) {
                    sql = "INSERT INTO employees_ru (first_name, last_name, email) VALUES (?, ?, ?)";
                }
    
                if (sql != null) {
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, firstNameField.getText());
                        statement.setString(2, lastNameField.getText());
                        statement.setString(3, emailField.getText());
                        statement.executeUpdate();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
