package com.example.javaonlineshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VerkaeuferController {

    //FXML Variablenaufrufe
    @FXML
    Pane kopfPane;
    @FXML
    Pane seitenPane;
    @FXML
    Pane verwaltungPane;
    @FXML
    Pane statistikenPane;


    private Stage hauptfenster;  //Die Stage, die als Hauptfenster verwendet wird

    //Methode um aus dem Dialog die Hauptstage zu sezten
    public void setHauptfenster(Stage hauptfenster) {
        this.hauptfenster = hauptfenster;
    }

    //Methode, die ein neues Fenster öffnet für die einmalige Pin-Vergabe
    @FXML
    public void vergebePinDialog() throws IOException {
        //ToDO: Überprüfen, ob schon eine Pin in der Datenbank vorhanden ist.

        //Erstelle ein neues Stage-Objekt für das Dialogfenster
        Stage dialog = new Stage();

        //FXML-Datei für das Dialogfenster laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GeheimNrDialog.fxml"));

        //Lade das FXML und den dazugehörigen Controller
        AnchorPane root = loader.load(); //Der Controller wird hier automatisch zugewiesen

        //Hole den DialogController aus dem FXMLLoader
        DialogController controller = loader.getController();

        //Setze das Hauptfenster
        if (hauptfenster != null) {
            controller.setVerkauferFenster(hauptfenster); //Die Stage an den DialogController übergeben
        } else {
            System.out.println("Warnung: Hauptfenster ist null!"); //Debugging, falls hauptfenster null ist
        }

        //Erstelle die Szene für das Dialogfenster
        Scene dialogScene = new Scene(root, 490, 210);
        dialogScene.getStylesheets().add(getClass().getResource("/com/example/javaonlineshop/Styling/style.css").toExternalForm());

        //Dialogfenster konfigurieren und anzeigen
        dialog.setScene(dialogScene);
        dialog.setTitle("Neue Pin vergeben");
        dialog.show();
    }

    //Wechsel zu Einkauf-Scene onClick Event
    @FXML
    public void wechselEinkauf() throws IOException {
        //Stelle sicher, dass hauptfenster gesetzt wurde
        if (hauptfenster != null) {
            //Lade die neue Scene (Einkaufsmodus)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KaeuferModus.fxml"));
            AnchorPane root = loader.load();

            //Neue Scene erstellen und setzen
            Scene einkaufScene = new Scene(root);
            einkaufScene.getStylesheets().add(getClass().getResource("/com/example/javaonlineshop/Styling/style.css").toExternalForm());
            hauptfenster.setScene(einkaufScene);
        } else {
            System.out.println("Fehler: Hauptfenster ist null, Scene kann nicht gewechselt werden.");
        }
    }
}
