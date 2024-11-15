/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.EmpleadoDAO;
import DAO.IncapacidadDAO;
import Database.DatabaseConnection;
import Helpers.OpenWindowsHandler;
import JasperReports.JAppReport;
import JasperReports.JReporteContratoNuevo;
import JasperReports.JReporteIncapacidadNuevo;
import Models.Empleado;
import Models.Incapacidad;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class IncapacidadesController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<Incapacidad> tblIncapacidad;
    @FXML
    private TableColumn<Incapacidad, String> colCedula;
    @FXML
    private TableColumn<Incapacidad, String> colNombre;
    @FXML
    private TableColumn<Incapacidad, String> colEstado;
    @FXML
    private TableColumn<Incapacidad, String> colFecha;
    @FXML
    private TableColumn<Incapacidad, String> colMonto;
    @FXML
    private TableColumn<Incapacidad, String> colMotivo;
    @FXML
    private ComboBox<String> cbx_status;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Incapacidad> ObservableIncapacidad = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarIncapacidades(true, false);
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));
        
        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarIncapacidades(true, true);
            } else {
                cargarIncapacidades(false, true);
            }
        });
    }

    public void cargarIncapacidades(boolean status, boolean filtro) {
        if (filtro) {
            ObservableIncapacidad
                    = FXCollections.observableArrayList(new IncapacidadDAO().obtenerListaIncapacidades())
                            .filtered(empl -> empl.isStatus() == status);
            tblIncapacidad.setItems(ObservableIncapacidad);
        } else {
            ObservableIncapacidad
                    = FXCollections.observableArrayList(new IncapacidadDAO().obtenerListaIncapacidades());
            tblIncapacidad.setItems(ObservableIncapacidad);
        }
    }

    private void filtrarIncapacidad() {
        if (filtrarEmpleado.getText() != null && !filtrarEmpleado.getText().trim().equals("")) {
            var listaTemporal = ObservableIncapacidad.filtered((x) ->{
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getEmpleado());
            return x.getEmpleado().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || empleado.getNombre().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || empleado.getApellidos().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || empleado.getNombreCompleto().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            });
            tblIncapacidad.setItems(listaTemporal);
        } else {
            cargarIncapacidades(true, false);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarIncapacidades("/views/AgregarIncapacidades");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarIncapacidades("/views/ActualizarIncapacidades");
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarIncapacidad();
    }
    
    @FXML
    private void OnRefrescar(ActionEvent event) {
         cargarIncapacidades(true, false);
    }

    @FXML
    private void OnReporte(ActionEvent event) {
        var report = new JReporteIncapacidadNuevo();
        var jreport = report.getTodasLasIncapacidades();

        if (dp_fin.getValue() != null && dp_inicio.getValue() != null) {

            HashMap<String, Object> map = new HashMap();

            System.out.println("Fechas " + dp_inicio.getValue().toString());

            map.put("PInicio", dp_inicio.getValue().toString());
            map.put("PFin", dp_fin.getValue().toString());

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
            return;
        }
    }

}
