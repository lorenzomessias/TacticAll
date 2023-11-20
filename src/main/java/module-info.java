module com.mycompany.tacticall {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens com.mycompany.tacticall to javafx.fxml;
    exports com.mycompany.tacticall;
}
