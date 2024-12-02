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

    // Getter und Setter für Einkaufspreis
    public double getEinkaufspreis() {
        return einkaufspreis;
    }

    public void setEinkaufspreis(double einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }

    // Getter und Setter für Verkaufspreis
    public double getVerkaufspreis() {
        return verkaufspreis;
    }

    public void setVerkaufspreis(double verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }

    // Getter und Setter für Bestandsmenge
    public int getBestandsmenge() {
        return bestandsmenge;
    }

    public void setBestandsmenge(int bestandsmenge) {
        this.bestandsmenge = bestandsmenge;
        
        
    // ToString-Methode (optional, aber praktisch)
    @Override
    public String toString() {
        return "Einkaufspreis: " + einkaufspreis +
                ", Verkaufspreis: " + verkaufspreis +
                ", Bestandsmenge: " + bestandsmenge;
    }
}

// SubklasseFreizeit 


public class Freizeit extends Produkt {
    private String spieler;

    // Konstruktor
    public Freizeit(double einkaufspreis, double verkaufspreis, int bestandsmenge, String spieler) {
        super(einkaufspreis, verkaufspreis, bestandsmenge);
        this.spieler = spieler;
    }

    // Getter und Setter für Spieler
    public String getSpieler() {
        return spieler;
    }

    public void setSpieler(String spieler) {
        this.spieler = spieler;
    }
}

// SubklasseBüro


public class Büro extends Produkt {
    private String packungsgroesse;

    // Konstruktor
    public Büro(double einkaufspreis, double verkaufspreis, int bestandsmenge, String packungsgroesse) {
        super(einkaufspreis, verkaufspreis, bestandsmenge);
        this.packungsgroesse = packungsgroesse;
    }

    // Getter und Setter für Packungsgröße
    public String getPackungsgroesse() {
        return packungsgroesse;
    }

    public void setPackungsgroesse(String packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }
}

// SubklasseKüche 

public class Küche extends Produkt {
    private String gehaeusefarbe;

    // Konstruktor
    public Küche(double einkaufspreis, double verkaufspreis, int bestandsmenge, String gehaeusefarbe) {
        super(einkaufspreis, verkaufspreis, bestandsmenge);
        this.gehaeusefarbe = gehaeusefarbe;
    }

    // Getter und Setter für Gehäusefarbe
    public String getGehaeusefarbe() {
        return gehaeusefarbe;
    }

    public void setGehaeusefarbe(String gehaeusefarbe) {
        this.gehaeusefarbe = gehaeusefarbe;
    }
}
