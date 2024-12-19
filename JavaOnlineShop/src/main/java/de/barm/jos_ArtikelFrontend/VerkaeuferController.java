package de.barm.jos_ArtikelFrontend;

import de.barm.jos_ArtikelBackend.Produkt;
import de.barm.jos_Datenbankanbindung.SQL;
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
import java.util.ArrayList;
import java.util.Objects;

public class VerkaeuferController {

    //FXML Label Variablen
    @FXML Label idLabel;
    @FXML Label prodNameLabel;
    @FXML Label einPreisLabel;
    @FXML Label verPreisLabel;
    @FXML Label mengeLabel;

    //FXML Label Varliablen errechnet
    @FXML Label einPreisAllerWarenLabel;
    @FXML Label verPreisAllerWarenLabel;
    @FXML Label artikelMitMeistGewLabel;

    //FXML Buttons
    @FXML Button einkaufenButton;
    @FXML Button werteBestaetigButton;
    @FXML Button werteAktualisierenButton;

    //FXML Textfield Varliablen
    @FXML TextField idFeld;
    @FXML TextField neuProdNameFeld;
    @FXML TextField neuEinPreisFeld;
    @FXML TextField neuVerPreisFeld;
    @FXML TextField neuMengeFeld;

    //FXML Variablenaufrufe
    @FXML Pane kopfPane;

    //FXML TableView Variable
    @FXML TableView verlustTable;

    private double gesammtEinPreis = 0;
    private double gesammtVerPreis = 0;
    private Stage hauptfenster;  //Die Stage, die als Hauptfenster verwendet wird
    private int selectedProdID;

    //Globale ArrayList der Produkte
    private ArrayList<Produkt> produkte = new ArrayList<Produkt>();
    private Produkt gefundenesProdukt = null;

    /**Die Initialise-Methode um die Variablen und Programmablauf vorab zu Konfigurieren*/
    public void initialize() {
        idLabel.setText("");
        prodNameLabel.setText("");
        einPreisLabel.setText("");
        verPreisLabel.setText("");
        mengeLabel.setText("");

        for (Produkt produkt : Objects.requireNonNull(SQL.getArtikel())) {
            try{
                produkte.add(produkt);
                System.out.println("DebugLog - Produkt wurde in die Liste geladen");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //Zuständig für alle Statistikwerte, die immer wieder berechnet werden müssen.
        statistikenWerte();
    }

    public void statistikenWerte(){
        //For-Schleife die durch alle Produkte durchgeht und dessen Preis pro Durchlauf auf die Summe addiert.
        for (Produkt produkt : produkte) {
            gesammtEinPreis = gesammtEinPreis + produkt.getEinkaufspreis();
            gesammtVerPreis = gesammtVerPreis + produkt.getVerkaufspreis();
        }

        einPreisAllerWarenLabel.setText(String.valueOf(gesammtEinPreis) + " €");
        verPreisAllerWarenLabel.setText(String.valueOf(gesammtVerPreis) + " €");

        //Aufruf der dritten Statistik ist eine eigene Methode für die Übersicht.
        artikelMitMeistenGewinn(produkte);
    }

    public void artikelMitMeistenGewinn(ArrayList<Produkt> produktListe){
        if (produktListe == null || produktListe.isEmpty()){
            //Ohne Produktliste kann nichts ermittelt werden, fehler abgreifen.
            artikelMitMeistGewLabel.setText("");
        }

        //Lokale Variablen für die Berechnung
        Produkt produktMitGroessterSpanne = null;
        double groessteGewinnspanne = Double.MIN_VALUE;

        assert produktListe != null; //Hierbei stellen wir eine Überprüfung hin, die Wahr sein muss. Produktliste darf nicht Null sein.

        for (Produkt produkt : produktListe) {
            //Berechnung der Gewinnspanne Verkaufspreis - Einkaufspreis
            double gewinnspanne = produkt.getVerkaufspreis() - produkt.getEinkaufspreis();

            if (gewinnspanne > groessteGewinnspanne) {
                groessteGewinnspanne = gewinnspanne;
                produktMitGroessterSpanne = produkt;
            }
        }

        if (produktMitGroessterSpanne != null) {
            //Wennn ein Produkt ermittelt wurde, wird das Label mit dem Namen und dem Preis beschrieben
            artikelMitMeistGewLabel.setText(produktMitGroessterSpanne.getName() + ", " + produktMitGroessterSpanne.getVerkaufspreis() + " €");
        } else {
            //Falls kein Produkt gefunden werden konnte, wird das Label mit folgender Nachricht beschrieben.
            artikelMitMeistGewLabel.setText("Kein Produkt mit Gewinnspanne gefunden");
        }
    };

    /**Methode um das Hauptfenster zu ermitteln und dieses zu deklarieren*/
    public void setHauptfenster(Stage hauptfenster) {
        this.hauptfenster = hauptfenster;
    }

    /**Methode um einen Szenenwechsel zu der Verkäufer-Erstellung zu ermöglichen*/
    @FXML
    public void vergebePinDialog() throws IOException {
        Stage dialog = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GeheimNrDialog.fxml"));
        AnchorPane root = loader.load(); //Der Controller wird hier automatisch zugewiesen
        DialogController controller = loader.getController();

        //Setze das Hauptfenster für den Controller damit diese übergeben werden kann.
        if (hauptfenster != null) {
            controller.setVerkauferFenster(hauptfenster); //Die Stage an den DialogController übergeben
        } else {
            System.out.println("Warnung: Hauptfenster ist null!"); //Debugging, falls hauptfenster null ist
        }

        //Erstelle das neue Dialogfenster für Verkäufererstellung
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

    /**Diese Methode dient dem On-Klick-Event für den Szenenwechsel im Hauptfenster, wird aus dem Dialog ferngesteuert*/
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

    /**Lade die Daten nach der ProduktID in die dazugehörigen Labels*/
    @FXML
    public void ladeProduktdaten() throws IOException {
        //Nehme ein Objekt aus der ArrayList entsprechend der ID aus den Produkten und beschreibe die Labels mit den Daten
        String id = idFeld.getText();
        if (id == null || id.isEmpty()) {
            System.out.println("Keine ProduktID in dem TextField vorhanden.");
            selectedProdID = 0;
        }else{
            try{
                selectedProdID = Integer.parseInt(id);

                if (selectedProdID == 0){
                    System.out.println("Keine ProduktID in dem TextField vorhanden.");
                }else{

                    for (Produkt produkt : Objects.requireNonNull(SQL.getArtikel())) {
                        if (produkt.getId() == selectedProdID) {
                            gefundenesProdukt = produkt;
                            break;
                        }
                    }

                    //Beschreibe die Labels mit den gefundenen Werten
                    idLabel.setText(String.valueOf(gefundenesProdukt.getId()));
                    prodNameLabel.setText(gefundenesProdukt.getName());
                    einPreisLabel.setText(String.valueOf(gefundenesProdukt.getEinkaufspreis()) + " €");
                    verPreisLabel.setText(String.valueOf(gefundenesProdukt.getVerkaufspreis()) + " €");
                    mengeLabel.setText(String.valueOf(gefundenesProdukt.getBestandsmenge()) + " Produkte");
                }
            }catch (NumberFormatException e){
                System.out.println("Kein gültiger Format fur eine ProduktID eingegeben.");
            }
        }
    }

    /**Nehme die Daten aus den TextField und beschreibe damit das gefundene Produkt neu*/
    @FXML
    public void aktualisiereProduktdaten() throws IOException {
        if (neuProdNameFeld.getText() != null && !neuProdNameFeld.getText().isEmpty()) {
            gefundenesProdukt.setName(neuProdNameFeld.getText());
        }
        if (neuEinPreisFeld.getText() != null && !neuEinPreisFeld.getText().isEmpty()) {
            gefundenesProdukt.setEinkaufspreis(Double.parseDouble(neuEinPreisFeld.getText()));
        }
        if (neuVerPreisFeld.getText() != null && !neuVerPreisFeld.getText().isEmpty()) {
            gefundenesProdukt.setVerkaufspreis(Double.parseDouble(neuVerPreisFeld.getText()));
        }
        if (neuMengeFeld.getText() != null && !neuMengeFeld.getText().isEmpty()) {
            gefundenesProdukt.setBestandsmenge(Integer.parseInt(neuMengeFeld.getText()));
        }
        SQL.updateArtikel(gefundenesProdukt);
        ladeProduktdaten();
        statistikenWerte();
    }
}
