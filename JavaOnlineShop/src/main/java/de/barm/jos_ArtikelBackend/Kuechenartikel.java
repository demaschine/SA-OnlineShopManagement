package de.barm.jos_ArtikelBackend;

/**
 * Diese Klasse stellt den Bauplan für Produkte der Kategorie: Küchenartikel.
 * <p>Hierbei handelt es sich um eine Subklasse, die von der Klasse Produkt erbt</p>
 *
 * @author Team Einfach.Zuahuse, Maximilian Mohr, Paul Sas
 * @version 1.0
 */

public class Kuechenartikel extends Produkt {
    private String gehaeusefarbe;

    /**
     * Konstruktor der die Datenstruktur aus der Produktklasse für Küchenartikel erweitert.
     *
     * @code super() ruft den Konstruktor der abstrakten Produktmethode, welche eine Produktstruktur generell vorgibt.
     * @param gehaeusefarbe Das Küchenartikel-Spezifische Attribut um Informationen über die Gehäusefarbe abzubilden.
     */
    //Konstruktor mit der Implementierung der super Methode um alle Attribute der Superklasse zu erben
    public Kuechenartikel(int id, String name, String bildURL, double einkaufspreis, double verkaufspreis, int bestandsmenge, String gehaeusefarbe) {
        super(id, name, bildURL, einkaufspreis, verkaufspreis, bestandsmenge);
        this.gehaeusefarbe = gehaeusefarbe;
    }

    /**@return Gibt die Gehäusefarbe des Küchenartikels zurück.*/
    public String getGehaeusefarbe() {
        return gehaeusefarbe;
    }

    /**@param gehaeusefarbe Zugriffsmethode um auf die Werte von dem Gehäusefarbe-Attribut zuzugreifen.*/
    public void setGehaeusefarbe(String gehaeusefarbe) {
        this.gehaeusefarbe = gehaeusefarbe;
    }

    /**
     * @return Folgende ToString-Methode implementiert die ToString-Methode aus der Superklasse, um die Parameter:
     * ProduktID, Artikelbezeichnung, Einkaufspreis, Verkaufspreis, Bestandsmenge
     * aus der vorgegebenen Struktur als ein String. Dazu kommt noch die Information über die Gehäusefarbe
     * im gleichen Format hinzu.
     */
    @Override
    public String toString() {
        return super.toString() + ", Gehaeusefarbe: " + gehaeusefarbe;
    }
}