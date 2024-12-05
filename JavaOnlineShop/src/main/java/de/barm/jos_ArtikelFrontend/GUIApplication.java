package de.barm.jos_ArtikelFrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class GUIApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Lade das FXML-Layout für die Verkäuferansicht
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("VerkaeuferModus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1360, 880);
        scene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());

        //Hole den VerkaeuferController aus dem FXMLLoader
        VerkaeuferController verkaueferController = fxmlLoader.getController();

        //Setze das Hauptfenster auf den Controller
        verkaueferController.setHauptfenster(stage);

        //Stagekonfiguration
        stage.setTitle("Einfach.Zuhause.");
        InputStream iconStream = getClass().getResourceAsStream("/de/barm/jos_ArtikelFrontend/ImagesAndIcons/JOS_Transparent_Logo.png");
        if (iconStream == null) {
            System.out.println("Icon konnte nicht gefunden werden. Ueberpruefe den Pfad.");
        } else {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
