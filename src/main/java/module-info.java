module com.dbconnect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens com.dbconnect.controller to javafx.fxml;
    opens com.dbconnect.models to javafx.base;
    exports com.dbconnect;
}
