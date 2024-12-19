package de.barm.jos_ArtikelFrontend;

import de.barm.jos_ArtikelBackend.Produkt;
import de.barm.jos_Datenbankanbindung.SQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class KaeuferController {

    //FXML Label Variablen
    @FXML
    Label userName;
    @FXML
    Label summePreis;

    @FXML
    Label kArtikelName1;
    @FXML
    Label kArtikelName2;
    @FXML
    Label kArtikelName3;
    @FXML
    Label kArtikelName4;
    @FXML
    Label kArtikelPreis1;
    @FXML
    Label kArtikelPreis2;
    @FXML
    Label kArtikelPreis3;
    @FXML
    Label kArtikelPreis4;

    @FXML
    Label bArtikelName1;
    @FXML
    Label bArtikelName2;
    @FXML
    Label bArtikelName3;
    @FXML
    Label bArtikelName4;
    @FXML
    Label bArtikelPreis1;
    @FXML
    Label bArtikelPreis2;
    @FXML
    Label bArtikelPreis3;
    @FXML
    Label bArtikelPreis4;

    @FXML
    Label fArtikelName1;
    @FXML
    Label fArtikelName2;
    @FXML
    Label fArtikelName3;
    @FXML
    Label fArtikelName4;
    @FXML
    Label fArtikelPreis1;
    @FXML
    Label fArtikelPreis2;
    @FXML
    Label fArtikelPreis3;
    @FXML
    Label fArtikelPreis4;

    //FXML Button Variablen
    @FXML
    Button shopVerwaltung;
    @FXML
    Button ladeWarenkorb;
    @FXML
    Button benutzerWechseln;
    @FXML
    Button artikelKaufen;
    @FXML
    Button benutzerRegistrieren;
    @FXML
    Button alsGastKaufen;

    @FXML
    Button kKaufenButton1;
    @FXML
    Button kKaufenButton2;
    @FXML
    Button kKaufenButton3;
    @FXML
    Button kKaufenButton4;

    @FXML
    Button bKaufenButton1;
    @FXML
    Button bKaufenButton2;
    @FXML
    Button bKaufenButton3;
    @FXML
    Button bKaufenButton4;

    @FXML
    Button fKaufenButton1;
    @FXML
    Button fKaufenButton2;
    @FXML
    Button fKaufenButton3;
    @FXML
    Button fKaufenButton4;

    //FXML TableView Variable
    @FXML
    TableView warenkorb;

    //FXML ObjektIDs für das Aussehen
    @FXML
    Pane kopfPane;

    public void initialize() {
        labelInitialsierung();
    }
    /**In dieser Methode werden die Labels initialisiert, sodass die Placeholder mit den Werten aus der Datenbank ersetzt werden, Schleife geht über alle Labels.*/
    public void labelInitialsierung (){
        //So werden die Labels gesetzt.
        userName.setText("Username");

        ArrayList<Produkt> produktListe = new ArrayList<Produkt>();
        for (Produkt produkt : Objects.requireNonNull(SQL.getArtikel())) {
            try{
                produktListe.add(produkt);
                System.out.println("DebugLog - Produkt wurde in die Liste geladen");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(produktListe.toString());

        Label[] artikelNames = {kArtikelName1, kArtikelName2, kArtikelName3, kArtikelName4,
                bArtikelName1, bArtikelName2, bArtikelName3, bArtikelName4,
                fArtikelName1, fArtikelName2, fArtikelName3, fArtikelName4};

        Label[] artikelPreise = {kArtikelPreis1, kArtikelPreis2, kArtikelPreis3, kArtikelPreis4,
                bArtikelPreis1, bArtikelPreis2, bArtikelPreis3, bArtikelPreis4,
                fArtikelPreis1, fArtikelPreis2, fArtikelPreis3, fArtikelPreis4};

        for (int i = 0; i < artikelNames.length; i++) {
            if (i < produktListe.size()) {
                Produkt produkt = produktListe.get(i);
                artikelNames[i].setText(produkt != null ? produkt.getName() : "Name nicht verfügbar");
                artikelPreise[i].setText(produkt != null ? Double.toString(produkt.getVerkaufspreis()) + " €" : "Preis nicht verfügbar");
            } else {
                artikelNames[i].setText("Name nicht verfügbar");
                artikelPreise[i].setText("Preis nicht verfügbar");
            }
        }
    }

    /**Methode fügt ausgewählten Artikel zur Warenkorbliste hinzu - benötigt eine Überladung*/
    public void fuegeArtikelZumWarenkorb(){

    }


    /**Methode um Warenkorb zu löschen - nur wenn angemeldet*/
    public void kaufeArtikelausWarenkorb(){

    }
    /**Methode um Warenkorb zu löschen*/
    public void kaufeArtikelalsGast(){

    }
}
