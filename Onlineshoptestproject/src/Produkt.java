public abstract class Produkt {
    //Private Modifier, Einbindung des Prinzips der Datenkapselung - Getter Setter fuer Attribute
    private int id;
    private String name;
    private double einkaufspreis;
    private double verkaufspreis;
    private int bestandsmenge;

    //Konstruktor, der von den Subklassen vererbt werden soll
    public Produkt(int id, String name, double einkaufspreis, double verkaufspreis, int bestandsmenge) {
        this.id = id;
        this.name = name;
        this.einkaufspreis = einkaufspreis;
        this.verkaufspreis = verkaufspreis;
        this.bestandsmenge = bestandsmenge;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

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

    //Methode, die Werte des Objektes beim Objektaufruf strukturiert wiedergibt
    @Override
    public String toString() {
        return "ID_PK: " + id +
                ", Artikelbezeichnung: " + name +
                ", Einkaufspreis: " + einkaufspreis +
                ", Verkaufspreis: " + verkaufspreis +
                ", Bestandsmenge: " + bestandsmenge;
    }
}



