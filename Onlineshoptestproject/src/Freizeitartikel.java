/**
 * Diese Klasse stellt den Bauplan für Produkte der Kategorie: Freizeitartikel.
 * <p>Hierbei handelt es sich um eine Subklasse, die von der Klasse Produkt erbt</p>
 *
 * @author Team Einfach.Zuahuse, Maximilian Mohr, Paul Sas
 * @version 1.0
 */

public class Freizeitartikel extends Produkt {
    private int spieler;

    /**
     * Konstruktor der die Datenstruktur aus der Produktklasse für Freizeitartikel erweitert.
     *
     * @code super() ruft den Konstruktor der abstrakten Produktmethode, welche eine Produktstruktur generell vorgibt.
     * @param spieler Das Freizeitartikel-Spezifische Attribut um Informationen über die mögliche Spieleranzahl eines Produktes dazustellen.
     */
    public Freizeitartikel(int id,String name, String bildURL, double einkaufspreis, double verkaufspreis, int bestandsmenge, int spieler) {
        super(id, name, bildURL, einkaufspreis, verkaufspreis, bestandsmenge);
        this.spieler = spieler;
    }

    /**@return Gibt die Spieleranzahl eines Freizeitartikels zurück*/
    public int getSpieler() {
        return spieler;
    }

    /**@param spieler Zugriffsmethode um auf das Attribut der Spieleranzahl eines Produktes zuzugreifen*/
    public void setSpieler(int spieler) {
        this.spieler = spieler;
    }

    /**
     * @return Folgende ToString-Methode implementiert die ToString-Methode aus der Superklasse, um die Parameter:
     * ProduktID, Artikelbezeichnung, Einkaufspreis, Verkaufspreis, Bestandsmenge
     * aus der vorgegebenen Struktur als ein String. Dazu kommt noch die Information über die Spieleranzahl
     * im gleichen Format
     */
    @Override
    public String toString() {
        return super.toString() + ", Spieleranzahl: " + spieler;
    }
}

