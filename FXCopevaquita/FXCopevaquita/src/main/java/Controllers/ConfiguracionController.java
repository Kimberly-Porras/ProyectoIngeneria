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
    private void onGestionarActividades(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActividades("/views/Actividades");
    }

    @FXML
    private void onGestionarAreas(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAreas("/views/Areas");
    }

    @FXML
    private void onGestionarDeducciones(ActionEvent event) {
      OpenWindowsHandler.AbrirVentanaTipoDeducciones("/views/TipoDeducciones");
    }
    
}
