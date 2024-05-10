/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helpers.OpenWindowsHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author alber
 * @author kim03
 */
public class ConfiguracionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActividades(MouseEvent event) {
        OpenWindowsHandler.AbrirVentanaActividades("/views/Actividades");
    }

    @FXML
    private void OnAreas(MouseEvent event) {
        OpenWindowsHandler.AbrirVentanaAreas("/views/Areas");
    }

    @FXML
    private void OnTipoDeducciones(MouseEvent event) {
        OpenWindowsHandler.AbrirVentanaTipoDeducciones("/views/TipoDeducciones");
    }

    @FXML
    private void OnPorcentajes(MouseEvent event) {
        OpenWindowsHandler.AbrirVentanaPorcentajes("/views/Porcentajes");
    }

    @FXML
    private void OnCredenciales(MouseEvent event) {
         OpenWindowsHandler.AbrirVentanaCredenciales("/views/Credenciales");
    }
    
}
