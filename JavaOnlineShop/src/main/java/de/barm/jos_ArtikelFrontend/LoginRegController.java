package de.barm.jos_ArtikelFrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginRegController {

    //JavaFX TextField Variablen
    @FXML TextField loginUsername;
    @FXML TextField loginPassword;
    @FXML TextField registerUsername;
    @FXML TextField registerPassword;

    //JavaFX Button IDs
    @FXML Button loginButton;
    @FXML Button registerButton;
    @FXML Button cancelButton;

    @FXML
    public void dialogAbbrechen() {
        // Schließt den Dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public String shopEinloggen() {
        //Überprüfe Datenbank nach Name und Passwort
        //Wenn Eintrag vorhanden, gebe Userobjekt zurück
        return "Userobjekt";
    }

    @FXML
    public void userRegistrieren() {
        //Nehme die Werte aus den TextFields und schreibe diese in die Datenbank
    }
}
