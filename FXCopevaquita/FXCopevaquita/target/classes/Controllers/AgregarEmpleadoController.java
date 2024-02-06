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
import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarEmpleadoController implements Initializable {


    @FXML
    private TextField txtCedulaAgre;
    @FXML
    private TextField txtNombreAgre;
    @FXML
    private TextField txtApellidosAgre;
    @FXML
    private ComboBox<?> cbxSexoAgre;
    @FXML
    private TextField txtEstadoCilvilAgre;
    @FXML
    private TextField txtTipoSangreAgre;
    @FXML
    private DatePicker dpFechaNacimientoAgre;
    @FXML
    private TextField txtNivelAcademicoAgre;
    @FXML
    private TextField txtCuentaAgre;
    @FXML
    private DatePicker dpFechaIngresoAgre;
    @FXML
    private ComboBox<?> cbxTipoEmpleadoAgre;
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
    private void validarCedula(KeyEvent event) {
    }

    @FXML
    private void validarNombre(KeyEvent event) {
    }

    @FXML
    private void validaApellido(KeyEvent event) {
    }

    @FXML
    private void validarEstadoCivil(KeyEvent event) {
    }

    @FXML
    private void validarTipoSangre(KeyEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

}
