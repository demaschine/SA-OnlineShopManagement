package de.barm.jos_ArtikelBackend;

import de.barm.jos_Datenbankanbindung.SQL;

import java.util.ArrayList;
import java.util.Objects;

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

        Kuechenartikel test1 = new Kuechenartikel(1004, "testBuero2","", 69.0,420.0, 1337, "Schwarz-Rosa-Gold");
        Kuechenartikel test2 = new Kuechenartikel(1005, "testBuero3","", 69.0,420.0, 1337, "Schwarz-Rosa-Gold");
        Kuechenartikel test3 = new Kuechenartikel(1006, "testBuero4","", 69.0,420.0, 1337, "Schwarz-Rosa-Gold");

        Freizeitartikel test4 = new Freizeitartikel(1007, "testFrei1","",70.0,420.0,1333,100);
        Freizeitartikel test5 = new Freizeitartikel(1008, "testFrei2","",70.0,420.0,1333,100);
        Freizeitartikel test6 = new Freizeitartikel(1009, "testFrei3","",70.0,420.0,1333,100);

        Bueroartikel test7 = new Bueroartikel(1010, "testKuche1", "", 50.0, 420.0, 222, 20);
        Bueroartikel test8 = new Bueroartikel(1011, "testKuche2", "", 50.0, 420.0, 222, 20);
        Bueroartikel test9 = new Bueroartikel(1012, "testKuche3", "", 50.0, 420.0, 222, 20);

        SQL.addArtikel(test1);
        SQL.addArtikel(test2);
        SQL.addArtikel(test3);
        SQL.addArtikel(test4);
        SQL.addArtikel(test5);
        SQL.addArtikel(test6);
        SQL.addArtikel(test7);
        SQL.addArtikel(test8);
        SQL.addArtikel(test9);



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
