/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.ActividadDAO;
import DAO.ContratoDAO;
import DAO.EmpleadoDAO;
import Models.Actividad;
import Models.Contrato;
import Models.Empleado;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.util.StringConverter;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class AgregarContratosController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
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
    @FXML
    private ComboBox<Actividad> cbxActividades;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Actividad> ObservableActividad = FXCollections.observableArrayList();
    ContratoDAO daoContrato = new ContratoDAO();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    ActividadDAO daoActividad = new ActividadDAO();
    Contrato contrato = new Contrato();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoAgre.setItems(ObservableEmpleado);
        cbxFiltrarEmpleadoAgre.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return "";
                }
                return t.getNombreCompleto();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });

        ObservableActividad = FXCollections.observableArrayList(daoActividad.obtenerListaActividades());
        cbxActividades.setItems(ObservableActividad);
        cbxActividades.setConverter(new StringConverter<Actividad>() {
            @Override
            public String toString(Actividad t) {
                if (t == null) {
                    return "";
                }
                return t.getNombre();
            }

            @Override
            public Actividad fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }

                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Actividad> firstMatch = ObservableActividad.filtered(x -> find.test(x.getNombre())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });
    }

    private void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = daoContrato.insertarContrato(
                    new Contrato(0,
                            cbxFiltrarEmpleadoAgre.getValue().getCedula(),
                            java.sql.Date.valueOf(dpFechaInicioAgre.getValue()),
                            java.sql.Date.valueOf(dpFechaFinalAgre.getValue()),
                            java.sql.Date.valueOf(dpFechaRegistroAgre.getValue()),
                            Double.parseDouble(txtMontoAgre.getText()),
                            cbEstadoAgre.isSelected(),
                            cbxActividades.getValue().getId()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Contrato insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el contrato", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAgregar() {
        if (txtMontoAgre.getText() != null
                && !txtMontoAgre.getText().trim().equals("")
                && cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && dpFechaInicioAgre.getValue() != null
                && !dpFechaInicioAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && dpFechaFinalAgre.getValue() != null
                && !dpFechaFinalAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && dpFechaRegistroAgre.getValue() != null
                && !dpFechaRegistroAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && cbxActividades.getValue() != null
                && !cbxActividades.getValue().getNombre().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposAgregar() {
        txtMontoAgre.setText("");
        cbxFiltrarEmpleadoAgre.setValue(ObservableEmpleado.get(0));
        cbxActividades.setValue(ObservableActividad.get(0));
        dpFechaInicioAgre.setValue(LocalDate.now());
        dpFechaFinalAgre.setValue(LocalDate.now());
        dpFechaRegistroAgre.setValue(LocalDate.now());
        cbEstadoAgre.setSelected(contrato.isStatus());
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }
}
