public class Freizeitartikel extends Produkt {
    private String spieler;

    //Konstruktor mit der Implementierung der super Methode um alle Attribute der Superklasse zu erben
    public Freizeitartikel(int id,String name, double einkaufspreis, double verkaufspreis, int bestandsmenge, String spieler) {
        super(id, name, einkaufspreis, verkaufspreis, bestandsmenge);
        this.spieler = spieler;
    }

    //Getter und Setter für Spieler
    public String getSpieler() {
        return spieler;
    }

    public void setSpieler(String spieler) {
        this.spieler = spieler;
    }

    //ToString Methode fuer die Subklasse, uebernimmt die Attribute und die toString Methode der abstrakten Superklasse
    @Override
    public String toString() {
        return super.toString() + ", Spieleranzahl: " + spieler;
    }
}

