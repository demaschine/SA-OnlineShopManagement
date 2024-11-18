package com.example.javaonlineshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Lade das FXML-Layout für die Verkäuferansicht
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("VerkaueferModus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/com/example/javaonlineshop/Styling/style.css").toExternalForm());

        // Hole den VerkaueferController aus dem FXMLLoader
        VerkaueferController verkaueferController = fxmlLoader.getController();

        // Setze das Hauptfenster auf den Controller
        verkaueferController.setHauptfenster(stage);

        // Konfiguriere und zeige die Stage (Hauptfenster)
        stage.setTitle("Einfach.Zuhause.");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
