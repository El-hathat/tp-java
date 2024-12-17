module com.dbconnect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens com.dbconnect to javafx.fxml;
    exports com.dbconnect;
}
