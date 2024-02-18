module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.mail;
    requires jasperreports;
    requires javafx.swing;
    opens MainApp to javafx.fxml;
    opens Layout to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens floatingWindows to javafx.fxml;

    exports MainApp;
    exports Models;
    exports DAO;
}
