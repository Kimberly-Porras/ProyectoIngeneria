/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import DAO.CredencialesDAO;
import Helpers.OpenWindowsHandler;
import MainApp.App;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label olvidoContrasena;
    @FXML
    private Button btnIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void MPressedOlvidoContrasena(MouseEvent event) {
    }

    @FXML
    private void AccionIngresar(ActionEvent event) {
        // Validar que el usuario y la contrasenia existe..
        String user = txtUsuario.getText();
        String password = txtContrasena.getText();

        if (isValidFields(txtContrasena.getText(), txtUsuario.getText())) {
            var credentials = new CredencialesDAO();
            var credential = credentials.obtenerCredencialPorUsuarioYContrasena(user, password);

            if (credential != null) {
                try {
                    App.setRoot("/views/Layout");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            MensajePersonalizado.Ver("Error",
                    "El usuario no es válido",
                    Alert.AlertType.INFORMATION);
        }
    }

    public boolean isValidFields(String txt1, String txt2) {
        if (!txt1.isEmpty() && !txt2.isEmpty()) {
            return true;
        }
        return false;
    }

    @FXML
    private void onOlvidoContrasenia(MouseEvent event) {
        olvidoContrasena.setStyle("-fx-text-fill: blue;");

        try {
            // Intenta abrir la nueva ventana
            OpenWindowsHandler.AbrirVentanaRecuperarContrasenia("/views/RecuperarContrasenia");
            olvidoContrasena.setStyle("-fx-text-fill: black;");
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la nueva ventana
            e.printStackTrace();
        }
    }

}
