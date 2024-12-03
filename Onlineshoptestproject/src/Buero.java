public class Buero extends Produkt {
    private String packungsgroesse;

    //Konstruktor mit der Implementierung der super Methode um alle Attribute der Superklasse zu erben
    public Buero(int id,String name, double einkaufspreis, double verkaufspreis, int bestandsmenge, String packungsgroesse) {
        super(id, name, einkaufspreis, verkaufspreis, bestandsmenge);
        this.packungsgroesse = packungsgroesse;
    }

    //Getter und Setter für Packungsgröße
    public String getPackungsgroesse() {
        return packungsgroesse;
    }

    public void setPackungsgroesse(String packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }

    //ToString Methode fuer die Subklasse, uebernimmt die Attribute und die toString Methode der abstrakten Superklasse
    @Override
    public String toString() {
        return super.toString() + ", Packungsgroesse: " + packungsgroesse;
    }
}