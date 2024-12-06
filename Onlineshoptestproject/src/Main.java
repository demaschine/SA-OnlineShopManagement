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

        System.out.println(SQL.getArtikel().length);

    }
}
