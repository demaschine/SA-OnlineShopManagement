package de.barm.jos_ArtikelFrontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Diese Klasse ist für den Wechsel aus der Shop Ansicht zu der Verkäuferansicht zuständig.
 *
 * @author Team Einfach.Zuhause, Paul Sas
 * @version 1.0
 */

public class VendorLoginController {
    // FXML Button Variablen
    @FXML Button loginButton;
    @FXML Button cancelButton;

    @FXML
    public void wechselZuVerkaeufer() throws IOException {
        Stage verkaeufer = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VerkaeuferModus.fxml"));
        AnchorPane root = loader.load();

        Scene verkaeuferScene = new Scene(root, 1360, 880);
        verkaeuferScene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());
        verkaeufer.setScene(verkaeuferScene);
        verkaeufer.setTitle("Einfach.Zuhause.");

        InputStream iconStream = getClass().getResourceAsStream("/de/barm/jos_ArtikelFrontend/ImagesAndIcons/JOS_Transparent_Logo.png");
        if (iconStream == null) {
            System.out.println("Icon konnte nicht gefunden werden. Ueberpruefe den Pfad.");
        } else {
            Image icon = new Image(iconStream);
            verkaeufer.getIcons().add(icon);
        }
        verkaeufer.show();
        dialogAbbrechen();
    }

    @FXML
    public void dialogAbbrechen() {
        // Schließt den Dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}