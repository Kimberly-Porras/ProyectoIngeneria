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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarIncapacidadesController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoAgre;
    @FXML
    private TextField txtDiasDisponiblesAgre;
    @FXML
    private TextField txtAnnoAgre;
    @FXML
    private TextField txtTotalAgre;
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
    private void FiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void validarCedula(KeyEvent event) {
    }

    @FXML
    private void validarNombre(KeyEvent event) {
    }

    @FXML
    private void validarEstadoCivil(KeyEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }
    
}
