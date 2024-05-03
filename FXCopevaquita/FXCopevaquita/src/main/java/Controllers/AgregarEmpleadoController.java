/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import Models.Empleado;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class AgregarEmpleadoController implements Initializable {

    @FXML
    private TextField txtCedulaAgre;
    @FXML
    private TextField txtNombreAgre;
    @FXML
    private TextField txtApellidosAgre;
    @FXML
    private ComboBox<String> cbxSexoAgre;
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
    private ComboBox<String> cbxTipoEmpleadoAgre;
    @FXML
    private CheckBox cbEstadoAgre;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> observableSexo = FXCollections.observableArrayList("MASCULINO", "FEMENINO", "OTRO");
    ObservableList<String> observableTipoEmpleados = FXCollections.observableArrayList("ADMINISTRADOR", "SECRETARIO", "SOCIO", "PEON");
    EmpleadoDAO EmpleadoDao = new EmpleadoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxTipoEmpleadoAgre.setItems(observableTipoEmpleados);
        cbxSexoAgre.setItems(observableSexo);
        cbEstadoAgre.setSelected(true);
    }

    public void guardar() {
        boolean estadoActivo = cbEstadoAgre.isSelected();
        if (verificarEspaciosAgregar()) {
            if (dpFechaNacimientoAgre.getValue() != null && dpFechaIngresoAgre.getValue() != null) {
                boolean exito = EmpleadoDao.insertarEmpleado(
                        new Empleado(
                                txtCedulaAgre.getText(),
                                txtNombreAgre.getText(),
                                txtApellidosAgre.getText(),
                                cbxSexoAgre.getValue(),
                                txtEstadoCilvilAgre.getText(),
                                txtTipoSangreAgre.getText(),
                                java.sql.Date.valueOf(dpFechaNacimientoAgre.getValue()),
                                java.sql.Date.valueOf(dpFechaIngresoAgre.getValue()),
                                cbxTipoEmpleadoAgre.getValue(),
                                txtCuentaAgre.getText(),
                                txtNivelAcademicoAgre.getText(),
                                estadoActivo)
                );

                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL INSERTAR", "Empleado insertado correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCamposAgregar();
                    
                } else {
                    MensajePersonalizado.Ver("ERROR AL INSERTAR", "Error al insertar el empleado", Alert.AlertType.ERROR);
                }

            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

            }
        }
    }

    public boolean verificarEspaciosAgregar() {
        return !txtCedulaAgre.getText().trim().equals("")
                && !txtNombreAgre.getText().trim().equals("")
                && !txtApellidosAgre.getText().trim().equals("")
                && !txtEstadoCilvilAgre.getText().trim().equals("")
                && !txtTipoSangreAgre.getText().trim().equals("")
                && !cbxSexoAgre.getValue().equals("")
                && !txtCuentaAgre.getText().equals("")
                && !txtNivelAcademicoAgre.getText().equals("")
                && dpFechaNacimientoAgre.getValue() != null
                && dpFechaIngresoAgre.getValue() != null
                && !dpFechaNacimientoAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && !dpFechaIngresoAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && !cbxTipoEmpleadoAgre.getValue().equals("")
                && !cbEstadoAgre.getText().trim().equals("");
    }
    
    public void limpiarCamposAgregar() {
        txtCedulaAgre.setText("");
        txtNombreAgre.setText("");
        txtApellidosAgre.setText("");
        txtEstadoCilvilAgre.setText("");
        txtTipoSangreAgre.setText("");
        cbxSexoAgre.setValue("");
        txtCuentaAgre.setText("");
        txtNivelAcademicoAgre.setText("");
        dpFechaNacimientoAgre.setValue(LocalDate.now());
        dpFechaIngresoAgre.setValue(LocalDate.now());
        cbxTipoEmpleadoAgre.setValue("");
        cbEstadoAgre.setText("");
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
        guardar();
    }

}
