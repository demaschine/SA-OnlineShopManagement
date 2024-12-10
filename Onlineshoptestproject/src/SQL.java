/**
 * Diese Klasse setzt die mit der SQLite Datenbank um.
 * @author Simon Braun
 * @version 1.0
 */

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class SQL {
    /**Pfad, der Datenbankdatei*/
    public static String dbPath = "jdbc:sqlite:" + Paths.get("src", "Database.db").toString();
    /**Connection-Objekt, welches die Interaktion mit der Datenbank ermöglicht*/
    public static Connection conn;

    static{
        init();
    }

    /**Initialisiert die Verbindung mit der Datenbank*/
    private static boolean init() {
        try {
            conn = DriverManager.getConnection(dbPath);
        } catch (SQLException e) {
            System.out.println("Datenbankverbindungsfehler");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**Führt eine Query in der Datenbank aus
     * @param statement Die Query, die in der Datenbank ausgeführt werden soll
     * @return ResultSet-Objekt, welches das Ergebnis der Query beinhaltet
     */
    public static ResultSet runQuery(String statement) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(statement);
    }

    /**Führt ein Update/Delete/Insert-Statement in der Datenbank aus
     * @param statement Das Update/Delete/Insert-Statement, das in der Datenbank ausgeführt werden soll
     * @return Anzahl der veränderten Zeilen oder 0, je nach dem, was Andendbar ist
     */
    public static int runUpdate(String statement) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(statement);
    }

    /**
     * Fügt ein Produkt in der Datenbak ein
     * @param p Produkt, dass in die Datenbank aufgenommen werden soll
     * @return true, wenn erfolgreich eingefügt wurde
     */
    public static boolean addArtikel(Produkt p){
        Formatter f = new Formatter(Locale.US);
        String stmt = "INSERT INTO Artikel(_id, Name, Einkaufspreis, Verkaufspreis, Bestand) VALUES(%d, '%s', %f, %f, %d);";
        stmt = String.valueOf(f.format(stmt, p.getId(), p.getName(), p.getEinkaufspreis(), p.getVerkaufspreis(), p.getBestandsmenge()));
        if(p instanceof Freizeitartikel){
            stmt = "INSERT INTO Freizeit(_id, MaxSpieler) VALUES(%d, %d);";
            stmt = String.valueOf(f.format(stmt, p.getId(), ((Freizeitartikel) p).getSpieler()));
        }

        if(p instanceof Bueroartikel){
            stmt = "INSERT INTO Buero(_id, Packungsgroesse) VALUES(%d, %d);";
            stmt = String.valueOf(f.format(stmt, p.getId(), ((Bueroartikel) p).getPackungsgroesse()));
        }

        if(p instanceof Kuechenartikel){
            stmt = "INSERT INTO Kueche(_id, Farbe) VALUES(%d, '%s');";
            stmt = String.valueOf(f.format(stmt, p.getId(), ((Kuechenartikel) p).getGehaeusefarbe()));
        }

        try {
            runUpdate(stmt);
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        }

        return true;
    }


    /**Löscht alle Produkte samt Vererbungshierarchie aus der Datenbank*/
    public static void clearDBProdukte(){
        try {
            runUpdate("DELETE FROM Artikel;");
            runUpdate("DELETE FROM Freizeit;");
            runUpdate("DELETE FROM Kueche;");
            runUpdate("DELETE FROM Buero;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Importiert alle Produkte in der Datenbank
     * @return Array an typisierten Produkten
     */
    public static Produkt[] getArtikel(){
        String stmt = "SELECT a._id, f._id, b._id, k._id, a.Name, a.BildURL, a.Einkaufspreis, a.Verkaufspreis, a.Bestand, f.MaxSpieler, b.Packungsgroesse, k.Farbe FROM Artikel a LEFT JOIN Freizeit f ON a._id = f._id LEFT JOIN Buero b ON a._id LEFT JOIN Kueche k ON a._id = k._id;";
        ResultSet res = null;
        ArrayList<Produkt> out = new ArrayList<Produkt>();
        try {
            res = runQuery(stmt);
            while (res.next()){
                if(res.getInt(1) == res.getInt(2)){
                    out.add(new Freizeitartikel(res.getInt(1), res.getString(5), res.getString(6), res.getDouble(7), res.getDouble(8), res.getInt(9), res.getInt(10)));
                }
                if(res.getInt(1) == res.getInt(3)){
                    out.add(new Bueroartikel(res.getInt(1), res.getString(5), res.getString(6), res.getDouble(7), res.getDouble(8), res.getInt(9), res.getInt(11)));
                }
                if(res.getInt(1) == res.getInt(4)){
                    out.add(new Kuechenartikel(res.getInt(1), res.getString(5), res.getString(6), res.getDouble(7), res.getDouble(8), res.getInt(9), res.getString(12)));
                }
            }
            return out.toArray(new Produkt[out.size()]);
        } catch (SQLException e) {
            System.out.print(e);
        }
        return null;
    }

    /**
     * Überprüft, ob ein User in der Datenbank eingetragen ist
     * @param name Name des Users
     * @param pin PIN des Users
     * @return Boolean: true, wenn der nutzer Existiert
     */
    public static boolean authUser(String name, int pin){
        Formatter f = new Formatter(Locale.US);
        String stmt = "SELECT * FROM User WHERE name = '%s' AND pin = %d";
        try {
            ResultSet res = runQuery(String.valueOf(f.format(stmt, name, pin)));
            if(res.next()){
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * Überprüft, ob ein User als Vendor in der Datenbank eingetragen ist
     * @param name Name des Vendors
     * @param pin PIN des Vendors
     * @return Boolean: true, wenn der User als Vendor Existiert
     */
    public static boolean authVendor(String name, int pin){
        Formatter f = new Formatter(Locale.US);
        String stmt = "SELECT * FROM User WHERE name = '%s' AND pin = %d AND vendor = 1";
        try {
            ResultSet res = runQuery(String.valueOf(f.format(stmt, name, pin)));
            if(res.next()){
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * Fügt einen User in der Datenbank ein
     * @param name Name des anzulegenden Users
     * @param pin PIN des anzulegenden Users
     * @param vendor Bestimmt ob der User ein Vendor sein soll oder nicht
     * @return int: Anzahl der veränderten Zeilen in der Datenbank, 1 wenn nichts schief gelaufen ist
     */
    public static int addUser(String name, int pin, boolean vendor){
        Formatter f = new Formatter(Locale.US);
        String stmt = "INSERT INTO User(Name, PIN, Vendor) VALUES('%s', %d, %b)";

        try {
            return runUpdate(String.valueOf(f.format(stmt, name, pin, vendor)));
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Aktualisiert den Vendor-Status eines Nutzers
     * @param name Name des Users
     * @param pin PIN des Users
     * @param vendor Bestimmt ob der User ein Vendor sein soll oder nicht
     * @return int: Anzahl der veränderten Zeilen in der Datenbank, 1 wenn nichts schief gelaufen ist
     */
    public static int updateUserVendor(String name, int pin, boolean vendor){
        Formatter f = new Formatter(Locale.US);
        String stmt = "UPDATE User SET Vendor = %b WHERE Name = '%s' AND pin = %d";

        try {
            return runUpdate(String.valueOf(f.format(stmt, vendor, name, pin)));
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Aktualisiert die PIN eines Nutzers
     * @param name Name des Users
     * @param pin PIN des Users
     * @return int: Anzahl der veränderten Zeilen in der Datenbank, 1 wenn nichts schief gelaufen ist
     */
    public static int updateUserPIN(String name, int pin){
        Formatter f = new Formatter(Locale.US);
        String stmt = "UPDATE User SET PIN = %d WHERE Name = '%s'";

        try {
            return runUpdate(String.valueOf(f.format(stmt, pin, name)));
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Aktualisiert ein Produkt in der Datenbak basierend auf dem übergebenen Produkt
     * (aktuell ungetestet)
     * @param p Produkt, wie es in der Datenbank stehen soll
     * @return true, wenn erfolgreich aktualisiert wurde
     */
    public static boolean updateArtikel(Produkt p){
        Formatter f = new Formatter(Locale.US);
        String stmt = "UPDATE Artikel SET Name = '%s', Einkaufspreis = %f, Verkaufspreis = %f, Bestand = %d WHERE _id = %d;";
        stmt = String.valueOf(f.format(stmt, p.getName(), p.getEinkaufspreis(), p.getVerkaufspreis(), p.getBestandsmenge(), p.getId()));
        if(p instanceof Freizeitartikel){
            stmt = "UPDATE Freizeit SET MaxSpieler = %d WHERE _id = %d;";
            stmt = String.valueOf(f.format(stmt,((Freizeitartikel) p).getSpieler(), p.getId()));
        }

        if(p instanceof Bueroartikel){
            stmt = "UPDATE Buero SET Packungsgroesse = %d WHERE _id = %d;);";
            stmt = String.valueOf(f.format(stmt,((Bueroartikel) p).getPackungsgroesse(), p.getId()));
        }

        if(p instanceof Kuechenartikel){
            stmt = "UPDATE Kueche SET Farbe = '%s' WHERE _id = %d;";
            stmt = String.valueOf(f.format(stmt, ((Kuechenartikel) p).getGehaeusefarbe(), p.getId()));
        }
        try {
            runUpdate(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //return false; (nach debuggen der funktion so einfügen)
        }
        return true;
    }

}
