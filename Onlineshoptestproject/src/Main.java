import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Initialisierung der Produktklasse
//        Produkt produkt = new Produkt(320.0, 420.0, 30);
//        System.out.println(produkt);

//        System.out.println("Input Bitte (\"/end\" zum beenden nutzen btw)");
//        Scanner sc = new Scanner(System.in);
//        if (SQL.init()) {
//            while (true) {
//                String in = sc.nextLine();
//                if (in.equals("/end")) {
//                    break;
//                }
//                try {
//                    ResultSet res = SQL.runQuery(in);
//                    while (res.next()) {
//                        for(int i = 1; i < 5; i++) {
//                            System.out.println(res.getString(i));
//                        }
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Nächster Befehl wann?");
//            }
//        }
//
//        System.out.println("Programm Beendet");

        Freizeitartikel test = new Freizeitartikel(1001,"testFreizeit1","", 69.0, 420.0, 1337, 100);
        Bueroartikel test1 = new Bueroartikel(1002, "testBuero1","", 69.0,420.0, 1337, 100);
        Kuechenartikel test2 = new Kuechenartikel(1003, "testBuero1","", 69.0,420.0, 1337, "Schwarz-Rosa-Gold");

        SQL.clearDBProdukte();

        SQL.addArtikel(test);
        SQL.addArtikel(test1);
        SQL.addArtikel(test2);

        test.setName("testFreizeit1 Hier könnte IHRE Werbung stehen");

        SQL.updateArtikel(test);

       //Test für die SQL Logik, damit ich mich daran orientieren kann.

        ArrayList<Produkt> produktListe = new ArrayList<Produkt>();

        for (Produkt produkt : SQL.getArtikel()) {
            try{
                produktListe.add(produkt);
                System.out.println("Produkt in CacheListe geladen");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //Gebe das Produkt nach einem Parameter für die ID aus
        Produkt gefundenesProdukt = null;

        for (Produkt produkt : SQL.getArtikel()) {
            if (produkt.getId() == 1001) {
                gefundenesProdukt = produkt;
                break;
            }
        }
        System.out.println(gefundenesProdukt);

        System.out.println("Artikelanzahl in der SQL-Datenbank: " + SQL.getArtikel().length);

        //User-Tests

        //System.out.println(SQL.addUser("testUser1", 1234, false));
        System.out.println(SQL.updateUserPIN("testUser1", 1234));
        System.out.println(SQL.updateUserVendor("testUser1", 1234, false));
        System.out.println(SQL.authUser("testUser1", 1234));
        System.out.println(SQL.authVendor("testUser1", 1234));
        System.out.println(SQL.updateUserVendor("testUser1", 1234, true));
        System.out.println(SQL.authUser("testUser1", 1234));
        System.out.println(SQL.authVendor("testUser1", 1234));
        System.out.println(SQL.updateUserPIN("testUser1", 1235));
        System.out.println(SQL.authUser("testUser1", 1234));
        System.out.println(SQL.authVendor("testUser1", 1234));
    }
}
