package de.barm.jos_ArtikelFrontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class VerkaeuferController {

    //FXML Label Variablen
    @FXML
    Label idLabel;
    @FXML
    Label prodNameLabel;
    @FXML
    Label einPreisLabel;
    @FXML
    Label verPreisLabel;
    @FXML
    Label mengeLabel;

    //FXML Label Varliablen errechnet
    @FXML
    Label einPreisAllerWarenLabel;
    @FXML
    Label verPreisAllerWarenLabel;
    @FXML
    Label artikelMitMeistGewLabel;

    //FXML Buttons
    @FXML
    Button einkaufenButton;
    @FXML
    Button werteBestaetigButton;
    @FXML
    Button werteAktualisierenButton;

    //FXML Textfield Varliablen
    @FXML
    TextField idFeld;
    @FXML
    TextField neuProdNameFeld;
    @FXML
    TextField neuEinPreisFeld;
    @FXML
    TextField neuVerPreisFeld;
    @FXML
    TextField neuMengeFeld;

    //FXML Variablenaufrufe
    @FXML
    Pane kopfPane;

    //FXML TableView Variable
    @FXML
    TableView verlustTable;

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
        Scene dialogScene = new Scene(root, 490, 220);
        dialogScene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());

        //Dialogfenster konfigurieren und anzeigen
        dialog.setScene(dialogScene);
        dialog.setTitle("Neue Pin vergeben");
        InputStream iconStream = getClass().getResourceAsStream("/de/barm/jos_ArtikelFrontend/ImagesAndIcons/JOS_Transparent_Logo.png");
        if (iconStream == null) {
            System.out.println("Icon konnte nicht gefunden werden. Ueberpruefe den Pfad.");
        } else {
            Image icon = new Image(iconStream);
            dialog.getIcons().add(icon);
        }
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
            Scene einkaufScene = new Scene(root, 1360, 880);
            einkaufScene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());
            hauptfenster.setScene(einkaufScene);
        } else {
            System.out.println("Fehler: Hauptfenster ist null, Scene kann nicht gewechselt werden.");
        }
    }
}
