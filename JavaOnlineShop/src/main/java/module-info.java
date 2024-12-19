module com.example.javaonlineshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens de.barm.jos_ArtikelFrontend to javafx.fxml;
    opens de.barm.jos_ArtikelBackend to javafx.fxml;
    exports de.barm.jos_ArtikelFrontend;
    exports de.barm.jos_ArtikelBackend;
}