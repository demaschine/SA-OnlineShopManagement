public class Produkt {
    private double einkaufspreis;
    private double verkaufspreis;
    private int bestandsmenge;

    // Konstruktor
    public Produkt(double einkaufspreis, double verkaufspreis, int bestandsmenge) {
        this.einkaufspreis = einkaufspreis;
        this.verkaufspreis = verkaufspreis;
        this.bestandsmenge = bestandsmenge;
    }

    // Getter und Setter
    public double getEinkaufspreis() {
        return einkaufspreis;
    }

    public void setEinkaufspreis(double einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }

    public double getVerkaufspreis() {
        return verkaufspreis;
    }

    public void setVerkaufspreis(double verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }

    public int getBestandsmenge() {
        return bestandsmenge;
    }

    public void setBestandsmenge(int bestandsmenge) {
        this.bestandsmenge = bestandsmenge;
    }

    // ToString-Methode (optional, aber praktisch)
    @Override
    public String toString() {
        return "Einkaufspreis: " + einkaufspreis +
                ", Verkaufspreis: " + verkaufspreis +
                ", Bestandsmenge: " + bestandsmenge;
    }
}