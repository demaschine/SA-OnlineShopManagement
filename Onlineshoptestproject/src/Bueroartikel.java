/**
 * Diese Klasse stellt den Bauplan für Produkte der Kategorie: Büroartikel.
 * <p>Hierbei handelt es sich um eine Subklasse, die von der Klasse Produkt erbt.</p>
 *
 * @author Team Einfach.Zuahuse, Maximilian Mohr, Paul Sas.
 * @version 1.0
 */

public class Bueroartikel extends Produkt {
    private String packungsgroesse;

    /**
     * Konstruktor der die Datenstruktur aus der Produktklasse für Büroartikel erweitert.
     *
     * @code super() ruft den Konstruktor der abstrakten Produktmethode, welche eine Produktstruktur generell vorgibt.
     * @param packungsgroesse Das Büroartikel-Spezifische Attribut um Informationen über die Packungsgröße dazustellen.
     */
    public Bueroartikel(int id, String name, double einkaufspreis, double verkaufspreis, int bestandsmenge, String packungsgroesse) {
        super(id, name, einkaufspreis, verkaufspreis, bestandsmenge);
        this.packungsgroesse = packungsgroesse;
    }

    /**@return Gibt die Packungsgröße des Büroartikelproduktes zurück.*/
    public String getPackungsgroesse() {
        return packungsgroesse;
    }

    /**@param packungsgroesse Zugriffsmethode um auf die Packungsgröße eines Produktes zuzugreifen.*/
    public void setPackungsgroesse(String packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }

    /**
     * @return Folgende ToString-Methode implementiert die ToString-Methode aus der Superklasse, um die Parameter:
     * ProduktID, Artikelbezeichnung, Einkaufspreis, Verkaufspreis, Bestandsmenge
     * aus der vorgegebenen Struktur als ein String. Dazu kommt noch die Information über die Packungsgröße
     * im gleichen Format.
     */
    @Override
    public String toString() {
        return super.toString() + ", Packungsgroesse: " + packungsgroesse;
    }
}