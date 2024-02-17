/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author User
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

        if (!txtUsuario.getText().isEmpty() && !txtContrasena.getText().isEmpty()) {
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
        }
    }

    @FXML
    private void onOlvidoContrasenia(MouseEvent event) {
        // abrir nueva vista...
        OpenWindowsHandler.AbrirVentanaRecuperarContrasenia("/views/RecuperarContrasenia");
    }

}
