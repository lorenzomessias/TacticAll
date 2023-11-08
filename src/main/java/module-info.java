module com.mycompany.tacticall {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.tacticall to javafx.fxml;
    exports com.mycompany.tacticall;
}
