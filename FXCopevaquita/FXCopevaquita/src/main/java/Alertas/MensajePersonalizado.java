package Alertas;

import javafx.scene.control.Alert;

public final class MensajePersonalizado {
    public static void Ver(String encabezado, String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(encabezado);
        alert.setContentText(mensaje);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
