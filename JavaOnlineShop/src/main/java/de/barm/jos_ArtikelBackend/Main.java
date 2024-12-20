package de.barm.jos_ArtikelBackend;

import de.barm.jos_Datenbankanbindung.SQL;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Diese Klasse ist für Jegliche SQL Dumps oder Datenmanipulation sowie Test zuständig.
 * Wäre praktisch für die weitere Entwicklung
 *
 * @author Team Einfach.Zuhause, Simon Braun, Paul Sas
 * @version x
 */

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

        Kuechenartikel test1 = new Kuechenartikel(1001, "Gabel Set","", 20,9.99, 200, "Silber");
        Kuechenartikel test2 = new Kuechenartikel(1002, "Teller Set","", 30,19.9, 300, "Weiß");
        Kuechenartikel test3 = new Kuechenartikel(1003, "Kochtopf","", 40,39.9, 100, "Silber");
        Kuechenartikel test4 = new Kuechenartikel(1004, "Herd A++","", 320,420.0, 30, "Weiß");

        Freizeitartikel test5 = new Freizeitartikel(1005, "Tennisball","",5.9,9.9,3000,4);
        Freizeitartikel test6 = new Freizeitartikel(1006, "UNO-Karten","",3.9,8.9,50,4);
        Freizeitartikel test7 = new Freizeitartikel(1007, "Hantel","",45,29.9,100,1);
        Freizeitartikel test8 = new Freizeitartikel(1008, "Gaming PC","",980,1.199,3,1);


        Bueroartikel test9 = new Bueroartikel(1009, "Büroklammer", "", 0.1, 0.5, 2000, 100);
        Bueroartikel test10 = new Bueroartikel(10010, "Druckpapier A4", "", 1.0, 13.9, 1000, 500);
        Bueroartikel test11 = new Bueroartikel(1011, "Java-Tasse", "", 2.0, 49.9, 200, 1);
        Bueroartikel test12 = new Bueroartikel(1012, "Büro Monitor", "", 250, 299.9, 50, 1);

        SQL.updateArtikel(test1);
        SQL.updateArtikel(test2);
        SQL.updateArtikel(test3);
        SQL.updateArtikel(test4);
        SQL.updateArtikel(test5);
        SQL.updateArtikel(test6);
        SQL.updateArtikel(test7);
        SQL.updateArtikel(test8);
        SQL.updateArtikel(test9);
        SQL.updateArtikel(test10);
        SQL.updateArtikel(test11);
        SQL.updateArtikel(test12);



       //Test für die SQL Logik, damit ich mich daran orientieren kann.

  //      ArrayList<Produkt> produktListe = new ArrayList<Produkt>();

    //    for (Produkt produkt : SQL.getArtikel()) {
        //      try{
      //          produktListe.add(produkt);
        //      System.out.println("Produkt in CacheListe geladen");
            //  }catch (Exception e){
            //  e.printStackTrace();
            //  }
        //  }

        //Gebe das Produkt nach einem Parameter für die ID aus
    //      Produkt gefundenesProdukt = null;

        /**
         * for (Produkt produkt : SQL.getArtikel()) {
            if (produkt.getId() == 1001) {
                gefundenesProdukt = produkt;
                break;
            }
        }
        */
        //    System.out.println(gefundenesProdukt);

        System.out.println("Artikelanzahl in der SQL-Datenbank: " + Objects.requireNonNull(SQL.getArtikel()).length);

        //User-Tests

        //System.out.println(SQL.addUser("testUser1", 1234, false));
        //   System.out.println(SQL.updateUserPIN("testUser1", 1234));
        //   System.out.println(SQL.updateUserVendor("testUser1", 1234, false));
        //   System.out.println(SQL.authUser("testUser1", 1234));
        //    System.out.println(SQL.authVendor("testUser1", 1234));
        //   System.out.println(SQL.updateUserVendor("testUser1", 1234, true));
        //   System.out.println(SQL.authUser("testUser1", 1234));
        //   System.out.println(SQL.authVendor("testUser1", 1234));
        //   System.out.println(SQL.updateUserPIN("testUser1", 1235));
        //    System.out.println(SQL.authUser("testUser1", 1234));
        //    System.out.println(SQL.authVendor("testUser1", 1234));
    }
}
