/**
 * Diese Klasse gilt der Generalisierung der Struktur, für die im Onlineshop angebotenen Produkte.
 * Angebotene Produkte müssen für die Datenbankstruktur diesen Aufbau über die super() Methode implementieren.
 *
 * <p>Diese Klasse verfügt über folgende Attribute: ID, Name, Einkaufspreis, Verkaufspreis.
 * Darüber hinaus stellt diese eine Zugriffsstruktur mithilfe von Getter und Setter, für die Datenkapselung da.</p>
 *
 * @author Team Einfach.Zuhause, Felix Hempel, Paul Sas
 * @version 1.0
 */

public abstract class Produkt {
    //Private Modifier, Einbindung des Prinzips der Datenkapselung - Getter Setter fuer Attribute
    /**ProduktID für die Datenbank-Strukur*/
    private int id;
    /**Produktbezeichnung, weitergabe an Frontend*/
    private String name;
    /**Einkaufspreis, zu welchem, ein gegebenes Produkt erworben wird*/
    private double einkaufspreis;
    /**Verkaufspreis, zu welchem das gegebene Produkt im Shop angeboten wird*/
    private double verkaufspreis;
    /**Das Attribut der Bestandsmenge gibt darüber auskunft, wie viele Entitäten eines Produktes im Lager vorhanden sind*/
    private int bestandsmenge;

    /**
     * Konstruktor, der von den Subklassen vererbt werden soll
     *
     * @param id ProduktID
     * @param name Name eines Produkts
     * @param einkaufspreis Preis des Produkterwerbs
     * @param verkaufspreis Preis des Produktvertriebs
     * @param bestandsmenge Attribut der Lagermenge eines bestimmten Produktes mit eindeutiger ProduktID
     */
    public Produkt(int id, String name, double einkaufspreis, double verkaufspreis, int bestandsmenge) {
        this.id = id;
        this.name = name;
        this.einkaufspreis = einkaufspreis;
        this.verkaufspreis = verkaufspreis;
        this.bestandsmenge = bestandsmenge;
    }

    /**@return Gibt die ProduktID zurück*/
    public int getId() {
        return id;
    }
    /**@param id Zugriffsmethode um die ProduktID manuell zu setzen*/
    public void setId(int id) {
        this.id = id;
    }
    /**@return Gibt die Produktbezeichnung zurück*/
    public String getName() {
        return name;
    }
    /**@param name Zugriffsmethode um den Namen eines Produktes zu beschreiben*/
    public void setName(String name) {
        this.name = name;
    }
    /**@return Gibt den Einkaufspreis eines Produktes zurück*/
    public double getEinkaufspreis() {
        return einkaufspreis;
    }
    /**@param einkaufspreis Zugriffsmethode um auf den Einkaufspreis eines Produktes abzuändern*/
    public void setEinkaufspreis(double einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }
    /**@return Gibt den Verkaufspreis eines Produktes zurück*/
    public double getVerkaufspreis() {
        return verkaufspreis;
    }
    /**@param verkaufspreis Zugriffsmethode um áuf den Verkaufspreis abzuändern*/
    public void setVerkaufspreis(double verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }

    /**@return bestandsmenge gibt die Bestandsmenge eines Produkts zurück*/
    public int getBestandsmenge() {
        return bestandsmenge;
    }

    /**@param bestandsmenge Zugriffsmethode um die Bestandsmenge eines Produkts zu verändern*/
    public void setBestandsmenge(int bestandsmenge) {
        this.bestandsmenge = bestandsmenge;
    }

    /**@return Folgende ToString-Methode setzt den Aufbau der Rückgabe eines Produktes. Wird von Subklassen vererbt*/
    @Override
    public String toString() {
        return "ID_PK (ProduktID): " + id +
                ", Artikelbezeichnung: " + name +
                ", Einkaufspreis: " + einkaufspreis +
                ", Verkaufspreis: " + verkaufspreis +
                ", Bestandsmenge: " + bestandsmenge;
    }
}



