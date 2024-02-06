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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class GenerarPagosController implements Initializable {

    @FXML
    private DatePicker dpFechaInicial1;
    @FXML
    private DatePicker dpFechaFinal1;
    @FXML
    private ComboBox<?> cbxEmpleado1;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextField txtDeduccion;
    @FXML
    private TextField txtIncapacidad;
    @FXML
    private TextField txtVacacion;
    @FXML
    private TextField txtTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cbxEmpleado1(ActionEvent event) {
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
    }

    @FXML
    private void OnPressed(KeyEvent event) {
    }

    @FXML
    private void btnGenerarPago(ActionEvent event) {
    }
    
}
