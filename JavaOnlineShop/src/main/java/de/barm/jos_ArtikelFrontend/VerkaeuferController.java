package de.barm.jos_ArtikelFrontend;

import de.barm.jos_ArtikelBackend.Produkt;
import de.barm.jos_Datenbankanbindung.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Diese Klasse ist für die Steuerung zwischen dem Backend und dem Frontend zuständig.
 *
 * @author Paul Sas, Team Einfach.Zuhause
 * @version 0.9
 * */

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
    @FXML private TableColumn<Produkt, String> nameColumn;
    @FXML private TableColumn<Produkt, Double> einPreisColumn;
    @FXML private TableColumn<Produkt, Double> verPreisColumn;
    @FXML private TableColumn<Produkt, Double> verlustColumn;
    private ObservableList<Produkt> verlustProdukte = FXCollections.observableArrayList();

    private double gesammtEinPreis = 0;
    private double gesammtVerPreis = 0;
    private Stage hauptfenster;  //Die Stage, die als Hauptfenster verwendet wird
    private int selectedProdID;

    //Globale ArrayList der Produkte
    private ArrayList<Produkt> produkte = new ArrayList<Produkt>();
    private Produkt gefundenesProdukt = null;

    /**
     * Initialisiert die Ansicht, setzt die Labels auf leere Werte und lädt die Produktdaten aus der Datenbank.
     * Setzt die entsprechenden Werte für die Tabellen und berechnet statistische Informationen.
     */
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        einPreisColumn.setCellValueFactory(new PropertyValueFactory<>("einkaufspreis"));
        verPreisColumn.setCellValueFactory(new PropertyValueFactory<>("verkaufspreis"));
        verlustColumn.setCellValueFactory(cellData -> {
            Produkt produkt = cellData.getValue();
            double verlust = produkt.getEinkaufspreis() - produkt.getVerkaufspreis();
            return new javafx.beans.property.SimpleDoubleProperty(verlust).asObject();
        });
        verlustTable.setItems(verlustProdukte);

        //Zuständig für alle Statistikwerte, die immer wieder berechnet werden müssen.
        statistikenWerte();
        ermittleArtikelMitVerlust();
    }

    /**
     * Berechnet und zeigt die Gesamtpreise der Produkte sowie den Artikel mit dem höchsten Gewinn.
     * Summiert die Einkaufspreise und Verkaufspreise aller Produkte und zeigt diese als Labels an.
     * Rufen Sie die Methode auf, um den Artikel mit dem höchsten Gewinn zu ermitteln.
     */
    public void statistikenWerte(){
        //For-Schleife die durch alle Produkte durchgeht und dessen Preis pro Durchlauf auf die Summe addiert.
        for (Produkt produkt : produkte) {
            gesammtEinPreis = gesammtEinPreis + produkt.getEinkaufspreis();
            gesammtVerPreis = gesammtVerPreis + produkt.getVerkaufspreis();
        }

        einPreisAllerWarenLabel.setText(String.format("%.2f €", gesammtEinPreis));
        verPreisAllerWarenLabel.setText(String.format("%.2f €", gesammtVerPreis));

        //Aufruf der dritten Statistik ist eine eigene Methode für die Übersicht.
        artikelMitMeistenGewinn(produkte);
    }

    /**
     * Ermittelt das Produkt mit der höchsten Gewinnspanne (Verkaufspreis - Einkaufspreis) aus einer Liste von Produkten
     * und zeigt den Namen sowie den Verkaufspreis dieses Produkts an.
     * Falls kein Produkt mit Gewinnspanne gefunden wird, wird eine entsprechende Nachricht angezeigt.
     *
     * @param produktListe Die Liste von Produkten, aus der das Produkt mit der höchsten Gewinnspanne ermittelt werden soll.
     */
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
            artikelMitMeistGewLabel.setText(String.format("%s, %.2f €", produktMitGroessterSpanne.getName(), produktMitGroessterSpanne.getVerkaufspreis()));
        } else {
            //Falls kein Produkt gefunden werden konnte, wird das Label mit folgender Nachricht beschrieben.
            artikelMitMeistGewLabel.setText("Kein Produkt mit Gewinnspanne gefunden");
        }
    };

    /**Methode um das Hauptfenster zu ermitteln und dieses zu deklarieren*/
    public void setHauptfenster(Stage hauptfenster) {
        this.hauptfenster = hauptfenster;
    }

    /**
     * Öffnet ein Dialogfenster zur Vergabe eines neuen PINs für einen Verkäufer.
     * Wird beim Klicken auf einen Button im Hauptfenster ausgeführt.
     *
     * @throws IOException Wenn ein Fehler beim Laden der FXML-Datei oder bei der Anzeige des Dialogs auftritt.
     */
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

    /**
     * Wechselt die Szene im Hauptfenster zu einem anderen Modus, der den Einkaufsmodus darstellt.
     * Diese Methode wird durch ein Klick-Ereignis ausgelöst und stellt sicher, dass das Hauptfenster korrekt gesetzt ist.
     *
     * @throws IOException Wenn ein Fehler beim Laden der FXML-Datei oder beim Wechsel der Szene auftritt.
     */
    @FXML
    public void wechselEinkauf() throws IOException {
        //Stelle sicher, dass hauptfenster gesetzt wurde
        if (hauptfenster != null) {
            //Lade die neue Scene (Einkaufsmodus)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KaeuferModus.fxml"));
            AnchorPane root = loader.load();

            //Neue Scene erstellen und setzen
            Scene einkaufScene = new Scene(root, 1527, 1000);
            einkaufScene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());
            hauptfenster.setScene(einkaufScene);
        } else {
            System.out.println("Fehler: Hauptfenster ist null, Scene kann nicht gewechselt werden.");
        }
    }

    /**
     * Lädt die Produktdaten basierend auf der angegebenen Produkt-ID und zeigt diese in den entsprechenden Labels an.
     * Falls keine gültige Produkt-ID eingegeben wurde, werden keine Daten geladen.
     *
     * @throws IOException Wenn ein Fehler beim Laden der Produktdaten auftritt.
     */
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
                    einPreisLabel.setText(String.format("%.2f €", gefundenesProdukt.getEinkaufspreis()));
                    verPreisLabel.setText(String.format("%.2f €", gefundenesProdukt.getVerkaufspreis()));
                    mengeLabel.setText(String.valueOf(gefundenesProdukt.getBestandsmenge()) + " Produkte");
                }
            }catch (NumberFormatException e){
                System.out.println("Kein gültiger Format fur eine ProduktID eingegeben.");
            }
        }
    }

    /**
     * Aktualisiert die Produktdaten basierend auf den Eingabewerten in den Textfeldern.
     * Ändert den Produktnamen, Einkaufspreis, Verkaufspreis und die Bestandsmenge des ausgewählten Produkts
     * und speichert die Änderungen in der Datenbank.
     * Anschließend werden die Produktdaten und statistischen Werte neu geladen.
     *
     * @throws IOException Wenn ein Fehler beim Aktualisieren der Produktdaten auftritt.
     */
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

    /**
     * Ermittelt alle Produkte, bei denen der Einkaufspreis höher ist als der Verkaufspreis,
     * und fügt diese der Liste der Produkte mit Verlust hinzu.
     * Wird verwendet, um Produkte mit Verlust zu identifizieren und anzuzeigen.
     */
    @FXML
    public void ermittleArtikelMitVerlust() {
        verlustProdukte.clear();
        for (Produkt produkt : produkte) {
            if (produkt.getEinkaufspreis() > produkt.getVerkaufspreis()) {
                // Füge das Produkt in die ObservableList hinzu
                verlustProdukte.add(produkt);
            }
        }
        if (verlustProdukte.isEmpty()) {
            System.out.println("Es wurden keine Produkte mit Verlust gefunden.");
        }
    }
}
