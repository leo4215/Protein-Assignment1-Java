package com.example.assignment1java;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class ProteinApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProteinApplication.class.getResource("chart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/design/styles.css")).toString());
        Image icon = new Image (Objects.requireNonNull(getClass().getResourceAsStream("/design/muscles.png")));
        stage.getIcons().add(icon);
        stage.setTitle("Food protein!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
