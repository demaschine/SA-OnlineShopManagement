public class Kueche extends Produkt {
    private String gehaeusefarbe;

    //Konstruktor mit der Implementierung der super Methode um alle Attribute der Superklasse zu erben
    public Kueche(int id,String name, double einkaufspreis, double verkaufspreis, int bestandsmenge, String gehaeusefarbe) {
        super(id, name, einkaufspreis, verkaufspreis, bestandsmenge);
        this.gehaeusefarbe = gehaeusefarbe;
    }

    // etter und Setter für Gehäusefarbe
    public String getGehaeusefarbe() {
        return gehaeusefarbe;
    }
    public void setGehaeusefarbe(String gehaeusefarbe) {
        this.gehaeusefarbe = gehaeusefarbe;
    }

    //ToString Methode fuer die Subklasse, uebernimmt die Attribute und die toString Methode der abstrakten Superklasse
    @Override
    public String toString() {
        return super.toString() + ", Gehaeusefarbe: " + gehaeusefarbe;
    }
}