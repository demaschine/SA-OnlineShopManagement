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

public class KaeuferController {

    //FXML Label Variablen
    @FXML Label userName;
    @FXML Label summePreis;

    @FXML Label kArtikelName1;
    @FXML Label kArtikelName2;
    @FXML Label kArtikelName3;
    @FXML Label kArtikelName4;
    @FXML Label kArtikelPreis1;
    @FXML Label kArtikelPreis2;
    @FXML Label kArtikelPreis3;
    @FXML Label kArtikelPreis4;
    @FXML Label kZusatz1;
    @FXML Label kZusatz2;
    @FXML Label kZusatz3;
    @FXML Label kZusatz4;


    @FXML Label bArtikelName1;
    @FXML Label bArtikelName2;
    @FXML Label bArtikelName3;
    @FXML Label bArtikelName4;
    @FXML Label bArtikelPreis1;
    @FXML Label bArtikelPreis2;
    @FXML Label bArtikelPreis3;
    @FXML Label bArtikelPreis4;
    @FXML Label bZusatz1;
    @FXML Label bZusatz2;
    @FXML Label bZusatz3;
    @FXML Label bZusatz4;

    @FXML Label fArtikelName1;
    @FXML Label fArtikelName2;
    @FXML Label fArtikelName3;
    @FXML Label fArtikelName4;
    @FXML Label fArtikelPreis1;
    @FXML Label fArtikelPreis2;
    @FXML Label fArtikelPreis3;
    @FXML Label fArtikelPreis4;
    @FXML Label fZusatz1;
    @FXML Label fZusatz2;
    @FXML Label fZusatz3;
    @FXML Label fZusatz4;

    @FXML Label fehler1;
    @FXML Label fehler2;

    //FXML Button Variablen
    @FXML Button shopVerwaltung;
    @FXML Button artikelKaufen;
    @FXML Button einkaufWiederherstellen;

    @FXML Button kKaufenButton1;
    @FXML Button kKaufenButton2;
    @FXML Button kKaufenButton3;
    @FXML Button kKaufenButton4;

    @FXML Button bKaufenButton1;
    @FXML Button bKaufenButton2;
    @FXML Button bKaufenButton3;
    @FXML Button bKaufenButton4;

    @FXML Button fKaufenButton1;
    @FXML Button fKaufenButton2;
    @FXML Button fKaufenButton3;
    @FXML Button fKaufenButton4;

    @FXML Button anmeldenButton;
    @FXML Button registrierenButton;

    //FXML TableView Variable
    @FXML private TableView<Produkt> warenkorbTableView;
    @FXML private ObservableList<Produkt> warenkorbListe = FXCollections.observableArrayList();
    @FXML private TableColumn<Produkt, String> nameColumn;
    @FXML private TableColumn<Produkt, Double> preisColumn;

    //FXML ObjektIDs für das Aussehen
    @FXML Pane kopfPane;

    //FXML TextField für die Anmeldung
    @FXML TextField loginNameFeld;
    @FXML TextField loginPinFeld;
    @FXML TextField neuerNameFeld;
    @FXML TextField neuePinFeld;

    private String currentUser;

    public void initialize() {
        labelInitialsierung();
    }
    /**In dieser Methode werden die Labels initialisiert, sodass die Placeholder mit den Werten aus der Datenbank ersetzt werden, Schleife geht über alle Labels.*/
    public void labelInitialsierung (){
        //So werden die Labels gesetzt.
        userName.setText("Nicht angemeldet");
        summePreis.setText("0,00 €");

        ArrayList<Produkt> produktListe = new ArrayList<Produkt>();

        for (Produkt produkt : Objects.requireNonNull(SQL.getArtikel())) {
            try{
                produktListe.add(produkt);
                System.out.println("DebugLog - Produkt wurde in die Liste geladen");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Button[] kKaufenButtons = {kKaufenButton1, kKaufenButton2, kKaufenButton3, kKaufenButton4};
        Button[] bKaufenButtons = {bKaufenButton1, bKaufenButton2, bKaufenButton3, bKaufenButton4};
        Button[] fKaufenButtons = {fKaufenButton1, fKaufenButton2, fKaufenButton3, fKaufenButton4};

        //Buttons für Küchenartikel - Parameterübergabe an die Funktionen mithilfe von Lambda ausdrücken
        for (int i = 0; i < kKaufenButtons.length; i++) {
            final int index = i;
            if (i < produktListe.size()) {
                Produkt produkt = produktListe.get(i);
                kKaufenButtons[i].setOnAction(event -> {
                    try {
                        fuegeArtikelZumWarenkorb(produkt, index);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                kKaufenButtons[i].setDisable(false);
            } else {
                kKaufenButtons[i].setDisable(true);
            }
        }

        // Buttons für Büroartikel
        for (int i = 0; i < bKaufenButtons.length; i++) {
            final int index = i;
            if (i < produktListe.size()) {
                Produkt produkt = produktListe.get(i+4);
                bKaufenButtons[i].setOnAction(event -> {
                    try {
                        fuegeArtikelZumWarenkorb(produkt, index);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                bKaufenButtons[i].setDisable(false);
            } else {
                bKaufenButtons[i].setDisable(true);
            }
        }

        //Buttons für Freizeitartikel
        for (int i = 0; i < fKaufenButtons.length; i++) {
            final int index = i;
            if (i < produktListe.size()) {
                Produkt produkt = produktListe.get(i+8);
                fKaufenButtons[i].setOnAction(event -> {
                    try {
                        fuegeArtikelZumWarenkorb(produkt, index);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                fKaufenButtons[i].setDisable(false);
            } else {
                fKaufenButtons[i].setDisable(true);
            }
        }

        System.out.println(produktListe.toString());

        Label[] artikelNames = {kArtikelName1, kArtikelName2, kArtikelName3, kArtikelName4,
                bArtikelName1, bArtikelName2, bArtikelName3, bArtikelName4,
                fArtikelName1, fArtikelName2, fArtikelName3, fArtikelName4};

        Label[] artikelPreise = {kArtikelPreis1, kArtikelPreis2, kArtikelPreis3, kArtikelPreis4,
                bArtikelPreis1, bArtikelPreis2, bArtikelPreis3, bArtikelPreis4,
                fArtikelPreis1, fArtikelPreis2, fArtikelPreis3, fArtikelPreis4};
        /*
        Label[] zusatzKueche = {kZusatz1, kZusatz2, kZusatz3, kZusatz4};
        Label[] zusatzBuero = {bZusatz1, bZusatz2, bZusatz3, bZusatz4};
        Label[] zusatzFreizeit = {fZusatz1, fZusatz2, fZusatz3, fZusatz4};
        */

        for (int i = 0; i < Math.min(artikelNames.length, produktListe.size()); i++) {
            Produkt produkt = produktListe.get(i);

            // Artikelname und Preis setzen
            artikelNames[i].setText(produkt != null ? produkt.getName() : "Name nicht verfügbar");
            artikelPreise[i].setText(produkt != null ? Double.toString(produkt.getVerkaufspreis()) + " €" : "Preis nicht verfügbar");

            /* Datenbank Statement nimmt nicht die Produkte mit den eigenen Werten, muss gefixt werden.
            // Zusätzliche Informationen basierend auf dem Typ des Produkts
            if (produkt instanceof Kuechenartikel && i < zusatzKueche.length) {
                Kuechenartikel k = (Kuechenartikel) produkt;
                zusatzKueche[i].setText(produkt != null ? k.getGehaeusefarbe() : "Keine Daten verfügbar");
            }
            if (produkt instanceof Bueroartikel && i < zusatzBuero.length) {
                Bueroartikel b = (Bueroartikel) produkt;
                zusatzBuero[i].setText(produkt != null ? String.valueOf(b.getPackungsgroesse()) : "Packungsgröße unbekannt");
            }
            if (produkt instanceof Freizeitartikel && i < zusatzFreizeit.length) {
                Freizeitartikel f = (Freizeitartikel) produkt;
                zusatzFreizeit[i].setText(produkt != null ? String.valueOf(f.getSpieler()) : "Spielerzahl unbekannt");
            }
             */
        }

// Für alle verbleibenden Artikel, die nicht in produktListe sind, setzen wir Standardwerte.
        for (int i = produktListe.size(); i < artikelNames.length; i++) {
            artikelNames[i].setText("Name nicht verfügbar");
            artikelPreise[i].setText("Preis nicht verfügbar");
            /*
            if (i < zusatzKueche.length) {
                zusatzKueche[i].setText("Keine Daten verfügbar");
            }
            if (i < zusatzBuero.length) {
                zusatzBuero[i].setText("Keine Daten verfügbar");
            }
            if (i < zusatzFreizeit.length) {
                zusatzFreizeit[i].setText("Keine Daten verfügbar");
            }
             */
        }

        // Setze die TableColumn für den Namen des Produkts
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Setze die TableColumn für den Preis des Produkts
        preisColumn.setCellValueFactory(new PropertyValueFactory<>("verkaufspreis"));

        // Setze die ObservableList als Datenquelle für das TableView
        warenkorbTableView.setItems(warenkorbListe);
    }

    public void wechselZuVerkauf() throws IOException {
        Stage venLoginDialog = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VendorLogin.fxml"));
        AnchorPane root = loader.load();

        Scene dialogScene = new Scene(root, 400, 275);
        dialogScene.getStylesheets().add(getClass().getResource("/de/barm/jos_ArtikelFrontend/Styling/style.css").toExternalForm());

        venLoginDialog.setScene(dialogScene);
        venLoginDialog.setTitle("In den Verkäufermodus einloggen");

        InputStream iconStream = getClass().getResourceAsStream("/de/barm/jos_ArtikelFrontend/ImagesAndIcons/JOS_Transparent_Logo.png");
        if (iconStream == null) {
            System.out.println("Icon konnte nicht gefunden werden. Ueberpruefe den Pfad.");
        } else {
            Image icon = new Image(iconStream);
            venLoginDialog.getIcons().add(icon);
        }
        venLoginDialog.show();
        dialogAbbrechen();
    }

    @FXML
    public void dialogAbbrechen() {
        // Schließt den Dialog
        Stage stage = (Stage) shopVerwaltung.getScene().getWindow();
        stage.close();
    }


    /**Methode fügt ausgewählten Artikel zur Warenkorbliste hinzu - benötigt eine Überladung*/
    public void fuegeArtikelZumWarenkorb(Produkt produkt, int index) throws IOException {
        //Benötigt noch das Login

        //Button Lambdaparamaterzuweisung - Test das es geht.
        System.out.println("DEBUG - Produkt " + produkt.getName() + " mit Index " + index + " wurde gekauft.");
        //In die Datenbank schreiben
        SQL.addArtikelInWarenkorb(produkt,"Paul", 1);

        warenkorbListe.add(produkt);
        updateWarenkorbSumme();
    }

    private void updateWarenkorbSumme() {
        double summe = 0;
        for (Produkt produkt : warenkorbListe) {
            summe += produkt.getVerkaufspreis();
        }
        summePreis.setText(String.format("%.2f €", summe));
    }

    /**Methode um Warenkorb zu löschen - nur wenn angemeldet*/
    public void kaufeArtikelausWarenkorb(){
        SQL.removeWarenkorb("Paul");
        warenkorbListe.removeAll(warenkorbListe);
        summePreis.setText("0,00 €");
    }

    public void ladeWarenkorb(){
        Produkt[] warenkorbAusDatenbank = SQL.getWarenkorb("Paul");
        for (Produkt produkt : warenkorbAusDatenbank) {
            warenkorbListe.add(produkt);
        }
        updateWarenkorbSumme();
    }

    /**Diese Methode stellt eine Anfrage an die Datenbank, wenn ein Nutzer mit der Pin übereinstimmt erfolgt die Anmeldung, wenn nicht, kommt eine Feedback-Nachricht*/
    public void benutzerAnmeldung(){
        String name = loginNameFeld.getText();
        String pin =  loginPinFeld.getText();
        if (name != null && !name.isEmpty() && pin != null && !pin.isEmpty()) {
            if(SQL.authUser(name, Integer.parseInt(pin))){
                userName.setText(name);
                currentUser = name; //Ist auch direkt der PrimaryKey aus der Datenbank
            }else{
                fehler1.setVisible(true);
                fehler2.setVisible(true);
            }
        }
    }

    /**Methode für die Nutzerregistrierung, Daten werden in die Datenbank geschrieben*/
    public void registriereNeuenBenutzer() throws IOException {
        String name = neuerNameFeld.getText();
        String pin =  neuePinFeld.getText();
        if (name != null && !name.isEmpty() && pin != null && !pin.isEmpty()) {
            if(!pin.equals("1111") && !pin.equals("2222") && !pin.equals("3333") && !pin.equals("1234")){
                SQL.addUser(name, Integer.parseInt(pin),false);
                System.out.println("Neuer Nutzer erstellt.");
            }
        }
    }

    /**Methode um sich als Benutzer abzumelden - Warenkorb ist abgespeichert*/
    @FXML
    public void abmelden(){
        if(currentUser != null){
            warenkorbListe.removeAll(warenkorbListe);
            currentUser = null;
            userName.setText("");
        }
    }
}
