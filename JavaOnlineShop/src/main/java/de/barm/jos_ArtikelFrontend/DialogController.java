package de.barm.jos_ArtikelFrontend;

import de.barm.jos_Datenbankanbindung.SQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogController {
    //Variable für den Szenenwechsel
    private Stage hauptfenster;
    private int dialogPin;
    private String dialogName;

    //FXML Label Variablen
    @FXML Label sicherheitLabel;

    //FXML Button Variablen
    @FXML private Button bestaetigen;
    @FXML private Button abbrechen;

    //FXML TextField Variablen
    @FXML TextField vendorBenutzername;
    @FXML TextField vendorPin;

    //Methode zum Abbrechen des Dialogs
    @FXML
    public void dialogAbbrechen() {
        // Schließt den Dialog
        Stage stage = (Stage) abbrechen.getScene().getWindow();
        stage.close();
    }

    /**Methode um bei der Bestätigung neuen Verkäufer zu erstellen*/
    @FXML
    public void dialogBestaetigen() throws IOException {
        //Zuerst sicherstellen, dass vendorBenutzername nicht leer ist
        String vendorName = vendorBenutzername.getText();
        String pinText = vendorPin.getText();

        //Überprüfen, ob die Textfelder nicht leer sind und der Name gesetzt wurde
        if (pinText != null && !pinText.isEmpty() && vendorName != null && !vendorName.isEmpty()) {
            //Sicherheitscheck, damit nicht 1111 oder 1234 als Pin vergeben wird
            if (!pinText.equals("1111") && !pinText.equals("1234")) {
                //Hier wird dialogName gesetzt, bevor wir es verwenden
                dialogPin = Integer.parseInt(pinText);
                dialogName = vendorName;

                //Benutzerdaten in SQL einfügen
                SQL.addUser(dialogName, dialogPin, true);
                this.szenenWechselFortfahren();
            } else {
                System.out.println("Verwenden Sie eine sichere Pin.");
                this.sicherheitsnachricht();
            }
        } else {
            System.out.println("Eins der Werte aus den Textfeldern ist Null oder leer");
        }
    }

    public void szenenWechselFortfahren() throws IOException {
        if (hauptfenster != null) {
            // VerkaeuferController mit dem Stage (Hauptfenster) initialisieren
            VerkaeuferController verkaueferController = new VerkaeuferController();
            verkaueferController.setHauptfenster(hauptfenster); // Stage setzen
            verkaueferController.wechselEinkauf(); // Wechsel zur nächsten Ansicht
            this.dialogAbbrechen(); // Den Dialog schließen nach dem Wechsel
        } else {
            System.out.println("Hauptfenster ist null, die Stage konnte nicht gesetzt werden.");
        }
    }

    //Methode um das Hauptfenster (Stage) zu setzen
    public void setVerkauferFenster(Stage hauptfenster) {
        this.hauptfenster = hauptfenster;
    }
    public void sicherheitsnachricht(){
        sicherheitLabel.setVisible(true);
    }
}