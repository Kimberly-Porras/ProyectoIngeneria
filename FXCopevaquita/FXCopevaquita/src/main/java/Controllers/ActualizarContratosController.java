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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ActualizarContratosController implements Initializable {


    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoAgre;
    @FXML
    private DatePicker dpFechaInicioAgre;
    @FXML
    private DatePicker dpFechaFinalAgre;
    @FXML
    private DatePicker dpFechaRegistroAgre;
    @FXML
    private TextField txtMontoAgre;
    @FXML
    private CheckBox cbEstadoAgre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnGuardar(ActionEvent event) {
    }

}