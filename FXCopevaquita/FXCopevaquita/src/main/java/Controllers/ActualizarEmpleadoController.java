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
public class ActualizarEmpleadoController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleado;
    @FXML
    private TextField txtCedula1;
    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtApellidos1;
    @FXML
    private ComboBox<?> cbxSexo1;
    @FXML
    private TextField txtEstadoCilvil1;
    @FXML
    private TextField txtTipoSangre1;
    @FXML
    private DatePicker dpFechaNacimiento1;
    @FXML
    private TextField txtNivelAcamicoAct;
    @FXML
    private TextField txtCuentaAct;
    @FXML
    private DatePicker dpFechaIngreso1;
    @FXML
    private ComboBox<?> cbxTipoEmpleado1;
    @FXML
    private CheckBox cbEstado1;

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
    private void btnActualizar(ActionEvent event) {
    }
    
}
