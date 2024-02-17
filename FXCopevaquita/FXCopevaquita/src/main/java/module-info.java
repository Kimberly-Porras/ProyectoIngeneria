module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.mail;
    

    opens MainApp to javafx.fxml;
    opens Layout to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens floatingWindows to javafx.fxml;
//    opens Clases.Report to javafx.fxml;
    
//    opens MainApp.App to javafx.fxml, jasperreports;
    
    exports MainApp;
    exports Models;
    exports DAO;
}
