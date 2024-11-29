import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Initialisierung der Produktklasse
//        Produkt produkt = new Produkt(320.0, 420.0, 30);
//        System.out.println(produkt);

        System.out.println("Input Bitte (\"/end\" zum beenden nutzen btw)");
        Scanner sc = new Scanner(System.in);
        if (SQL.init()) {
            while (true) {
                String in = sc.nextLine();
                if (in.equals("/end")) {
                    break;
                }
                try {
                    ResultSet res = SQL.runQuery(in);
                    while (res.next()) {
                        for(int i = 1; i < 5; i++) {
                            System.out.println(res.getString(i));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Nächster Befehl wann?");
            }
        }

        System.out.println("Programm Beendet");
    }
}
