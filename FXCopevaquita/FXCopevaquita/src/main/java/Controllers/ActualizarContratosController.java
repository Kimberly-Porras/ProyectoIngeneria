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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;


/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class ActualizarContratosController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<Contrato> tblContratoAct;
    @FXML
    private TableColumn<Contrato, String> colActividad;
    @FXML
    private TableColumn<Contrato, String> colFechaInicioAct;
    @FXML
    private TableColumn<Contrato, String> colFechaFinalAct;
    @FXML
    private TableColumn<Contrato, String> colFechaRegistroAct;
    @FXML
    private TableColumn<Contrato, String> colMontoAct;
    @FXML
    private TableColumn<Contrato, String> colEstadoAct;
    @FXML
    private DatePicker dpFechaInicioAct;
    @FXML
    private DatePicker dpFechaFinalAct;
    @FXML
    private DatePicker dpFecharegistroAct;
    @FXML
    private TextField txtMontoAct;
    @FXML
    private CheckBox cbEstadoAct;
    @FXML
    private ComboBox<Actividad> cbxActividades;

    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Actividad> ObservableActividad = FXCollections.observableArrayList();
    Contrato contrato = new Contrato();
    Actividad actividad = new Actividad();
    ContratoDAO daoContrato = new ContratoDAO();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    ActividadDAO actividadService = new ActividadDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        colActividad.setCellValueFactory(cellData -> {
            var tipo = actividadService.obtenerPorId(cellData.getValue().getMotivo());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });
        colFechaInicioAct.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFinalAct.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        colFechaRegistroAct.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colMontoAct.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstadoAct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoActualizar.setItems(ObservableEmpleado);
        cbxFiltrarEmpleadoActualizar.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return "No hay datos"; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombreCompleto();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });

        ObservableActividad = FXCollections.observableArrayList(actividadService.obtenerListaActividades());
        cbxActividades.setItems(ObservableActividad);
        cbxActividades.setConverter(new StringConverter<Actividad>() {
            @Override
            public String toString(Actividad t) {
                if (t == null) {
                    return "No hay datos"; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombre();
            }

            @Override
            public Actividad fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Actividad> firstMatch = ObservableActividad.filtered(x -> find.test(x.getNombre())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });
    }

    private void actualizar() {
        if (VerificarEspaciosAtualizar()) {
            boolean exito = daoContrato.actualizarContrato(
                    new Contrato(
                            contrato.getId(),
                            cbxFiltrarEmpleadoActualizar.getValue().getCedula(),
                            java.sql.Date.valueOf(dpFechaInicioAct.getValue()),
                            java.sql.Date.valueOf(dpFechaFinalAct.getValue()),
                            java.sql.Date.valueOf(dpFecharegistroAct.getValue()),
                            Double.parseDouble(txtMontoAct.getText()),
                            cbEstadoAct.isSelected(),
                            cbxActividades.getValue().getId()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Contrato actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar el contrato", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void limpiarCampos() {
        txtMontoAct.setText("");
        cbxActividades.setItems(ObservableActividad);
        // Establecer el valor del DatePicker como null
        dpFechaFinalAct.setValue(null);
        dpFechaInicioAct.setValue(null);
        dpFecharegistroAct.setValue(null);

    }

    public boolean VerificarEspaciosAtualizar() {
        if (txtMontoAct.getText() != null && !txtMontoAct.getText().trim().equals("")
                && cbxFiltrarEmpleadoActualizar.getValue() != null
                && !cbxFiltrarEmpleadoActualizar.getValue().getCedula().trim().equals("")
                && dpFechaInicioAct.getValue() != null
                && !dpFechaInicioAct.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && dpFechaFinalAct.getValue() != null
                && !dpFechaFinalAct.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && dpFecharegistroAct.getValue() != null
                && !dpFecharegistroAct.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void cargarCamposActualizar() {
        txtMontoAct.setText(contrato.getMonto() + "");
        cbxFiltrarEmpleadoActualizar.setValue(Get(contrato.getCedulaEmpleado()));
        dpFechaInicioAct.setValue(contrato.getFechaInicio().toLocalDate());
        dpFechaFinalAct.setValue(contrato.getFechaFinal().toLocalDate());
        dpFecharegistroAct.setValue(contrato.getFechaRegistro().toLocalDate());
        cbEstadoAct.setSelected(contrato.isStatus());
        //FALTA LA ACTIVIDAD 
        Actividad actividad = actividadService.obtenerPorId(contrato.getMotivo());
        cbxActividades.setValue(actividad);
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    private void cargarContratosPorEmpleado() {
        contrato = tblContratoAct.getSelectionModel().getSelectedItem();
        if (contrato != null && contrato.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione un contrato", Alert.AlertType.WARNING);
        }
    }

    private void FiltrarContratoPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoContrato.obtenerListaContratoPorCedulaEmpleado(empleado.getCedula()));
                tblContratoAct.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las vacaciones del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarContratoPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarContratosPorEmpleado();
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        actualizar();
    }

}
