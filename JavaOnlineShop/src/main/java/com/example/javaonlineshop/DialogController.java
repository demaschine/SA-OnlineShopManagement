package com.example.javaonlineshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogController {
    // Variable für den Szenenwechsel
    private Stage hauptfenster;

    @FXML
    private Button bestaetigen;
    @FXML
    private Button abbrechen;

    // Methode zum Abbrechen des Dialogs
    @FXML
    public void dialogAbbrechen() {
        // Schließt den Dialog
        Stage stage = (Stage) abbrechen.getScene().getWindow();
        stage.close();
    }

    // Methode, die bei Bestätigung aufgerufen wird
    @FXML
    public void dialogBestaetigen() throws IOException {
        // ToDo: Eine Anbindung an die Datenbank, um die neu vergebene Pin abzuspeichern

        // Wechsel der Scene von Verkauf zu Einkauf
        if (hauptfenster != null) {
            // VerkaeuferController mit dem Stage (Hauptfenster) initialisieren
            VerkaeuferController verkaueferController = new VerkaeuferController();
            verkaueferController.setHauptfenster(hauptfenster); // Stage setzen
            verkaueferController.wechselEinkauf(); // Wechsel zur nächsten Ansicht
            this.dialogAbbrechen(); //Dialog schließen nach dem Wechsel
        } else {
            System.out.println("Hauptfenster ist null, der Stage konnte nicht gesetzt werden.");
        }
    }

    // Methode um das Hauptfenster (Stage) zu setzen
    public void setVerkauferFenster(Stage hauptfenster) {
        this.hauptfenster = hauptfenster;
    }
}