module com.example.ql_skateboarding {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.ql_skateboarding to javafx.fxml;
    exports com.example.ql_skateboarding;
}