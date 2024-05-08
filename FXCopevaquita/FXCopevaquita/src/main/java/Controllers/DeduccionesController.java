/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DeduccionesDAO;
import Helpers.OpenWindowsHandler;
import Models.Deduccion;
import Models.Empleado;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import DAO.EmpleadoDAO;
import DAO.TipoDeduccionDAO;
import Database.DatabaseConnection;
import JasperReports.JAppReport;
import JasperReports.JReportDeducciones;
import java.util.HashMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class DeduccionesController implements Initializable {

    @FXML
    private TextField txtfiltrarEmpleado;
    @FXML
    private TableView<Deduccion> tblDeduccionesEmpleados;
    @FXML
    private TableColumn<Deduccion, String> colCedula;
    @FXML
    private TableColumn<Deduccion, String> colNombre;
    @FXML
    private TableColumn<Deduccion, String> colFecha;
    @FXML
    private TableColumn<Deduccion, String> colTipoDeduccion;
    @FXML
    private TableColumn<Deduccion, String> colCuota;
    @FXML
    private TableColumn<Deduccion, String> colEstado;
    @FXML
    private TableColumn<Deduccion, String> colMonto;
    @FXML
    private TableColumn<Deduccion, String> colPendiente;
    @FXML
    private ComboBox<String> cbx_status;

    final private EmpleadoDAO empleadoService = new EmpleadoDAO();
    final private TipoDeduccionDAO tipoDeduccionService = new TipoDeduccionDAO();

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Deduccion> ObservableDeduccion = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarDeducciones(true, false);
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));

        colNombre.setCellValueFactory(cellData -> {
            var empleado = empleadoService.obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(empleado.getNombre() + " " + empleado.getApellidos());
        });

        colTipoDeduccion.setCellValueFactory(cellData -> {
            var tipo = tipoDeduccionService.obtenerPorId(cellData.getValue().getTipo());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_registro"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        colCuota.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarDeducciones(true, true);
            } else {
                cargarDeducciones(false, true);
            }
        });
    }

    public void cargarDeducciones(boolean status, boolean filtro) {
        if (filtro) {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new DeduccionesDAO().obtenerListaDeduccion())
                            .filtered(empl -> empl.isStatus() == status);
            tblDeduccionesEmpleados.setItems(ObservableIncapacidad);
        } else {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new DeduccionesDAO().obtenerListaDeduccion());
            tblDeduccionesEmpleados.setItems(ObservableIncapacidad);
        }
    }

    private void filtrarDeduccion() {
        if (txtfiltrarEmpleado.getText() != null && !txtfiltrarEmpleado.getText().trim().equals("")) {
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableDeduccion.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())));
            tblDeduccionesEmpleados.setItems(listaTemporal);
        } else {
            cargarDeducciones(true, false);
        }
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarDeduccion("/views/AgregarDeducciones");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarDeduccion("/views/ActualizarDeducciones");
    }

    @FXML
    private void OnAbonos(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAbonoDeduccion("/views/AbonoDeducciones");
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarDeduccion();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarDeducciones(true, false);
    }

    @FXML
    private void OnReporte(ActionEvent event) {
        var report = new JReportDeducciones();
        var jreport = report.getTodasLasDeducciones();

        if (dp_fin.getValue() != null && dp_inicio.getValue() != null) {

            HashMap<String, Object> map = new HashMap();
            
            System.out.println("Fechas " + dp_inicio.getValue().toString());
            
            map.put("PInicio", dp_inicio.getValue().toString());
            map.put("PFin", dp_fin.getValue().toString());

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
            return;
        }

        // Lanzar mensaje de abvertencia...
    }
}
