module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens MainApp to javafx.fxml;
    opens Layout to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens floatingWindows to javafx.fxml;
    
    exports Models;
    exports MainApp;
}
