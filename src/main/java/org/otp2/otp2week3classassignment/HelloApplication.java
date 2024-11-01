package org.otp2.otp2week3classassignment;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

@Override
public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
    fxmlLoader.setResources(ResourceBundle.getBundle("bundle", Locale.ENGLISH));
    Scene scene = new Scene(fxmlLoader.load(), 520, 440);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
}
}
