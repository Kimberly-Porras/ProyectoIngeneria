/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.ActividadDAO;
import DAO.AreaDAO;
import DAO.BitacoraEmpleadoDAO;
import DAO.EmpleadoDAO;
import Database.DatabaseConnection;
import Helpers.OpenWindowsHandler;
import JasperReports.JAppReport;
import JasperReports.JReporteIncapacidadNuevo;
import JasperReports.JReporteRegistroEmpleadoGeneralNuevo;
import JasperReports.JReporteRegistroEmpleadoUnitarioNuevo;
import Models.BitacoraEmpleado;
import Models.Empleado;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
 *
 * @author alber
 * @author kim03
 */
public class BitacoraEmpleadoController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraEmpleado> tblListarReporteEmpleado;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colCedula;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colFechaRegistro;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colActividades;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colAreas;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colCantidad;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colPrecio;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colEstado;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colNombre;
    @FXML
    private ComboBox<String> cbx_status;
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    /**
     * Initializes the controller class.
     */
    final private EmpleadoDAO empleadoService = new EmpleadoDAO();
    final private ActividadDAO actividadService = new ActividadDAO();
    final private AreaDAO areaService = new AreaDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<BitacoraEmpleado> ObservableBitacoraEmpleado = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarBitacoras(true, false);
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

        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colActividades.setCellValueFactory(cellData -> {
            var tipo = actividadService.obtenerPorId(cellData.getValue().getActividad());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colAreas.setCellValueFactory(cellData -> {
            var tipo = areaService.obtenerPorId(cellData.getValue().getArea());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarBitacoras(true, true);
            } else {
                cargarBitacoras(false, true);
            }
        });
    }

    public void cargarBitacoras(boolean status, boolean filtro) {
        if (filtro) {
            ObservableBitacoraEmpleado
                    = FXCollections.observableArrayList(new BitacoraEmpleadoDAO().obtenerListaBitacoraEmpleado())
                            .filtered(empl -> empl.isStatus() == status);
            tblListarReporteEmpleado.setItems(ObservableBitacoraEmpleado);
        } else {
            ObservableBitacoraEmpleado
                    = FXCollections.observableArrayList(new BitacoraEmpleadoDAO().obtenerListaBitacoraEmpleado());
            tblListarReporteEmpleado.setItems(ObservableBitacoraEmpleado);
        }
    }

    private void filtrarBitacoras() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {

            var listaTemporal = ObservableBitacoraEmpleado.filtered((x) -> {
                var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getEmpleado());
                return x.getEmpleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());

            });
            tblListarReporteEmpleado.setItems(listaTemporal);

        } else {
            cargarBitacoras(true, false);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitacoraEmpleado("/views/AgregarBitacoraEmpleado");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarBitacoraEmpleado("/views/ActualizarBitacoraEmpleado");
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarBitacoras(true, false);
    }

    @FXML
    private void OnFiltrarEmpleado(KeyEvent event) {
        filtrarBitacoras();
    }

    @FXML
    private void OnReporteExportarGeneral(ActionEvent event) {

        var report = new JReporteRegistroEmpleadoGeneralNuevo();
        var jreport = report.getTodosLosRegistroEmpleados();

        if (dp_fin.getValue() != null && dp_inicio.getValue() != null) {

            HashMap<String, Object> map = new HashMap();

            System.out.println("Fechas " + dp_inicio.getValue().toString());

            map.put("Pinicio", dp_inicio.getValue().toString());
            map.put("PFin", dp_fin.getValue().toString());

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
            return;
        }

    }

    @FXML
    private void OnReporteUnitario(ActionEvent event) {

        var report = new JReporteRegistroEmpleadoUnitarioNuevo();
        var jreport = report.getRegistroDiarioUnitarioEmpleados();
        var model = tblListarReporteEmpleado.getSelectionModel().getSelectedItem();
        if (dp_fin.getValue() != null && dp_inicio.getValue() != null && model != null) {

            HashMap<String, Object> map = new HashMap();

            var empleadoCedula = model.getEmpleado();

            map.put("cedula", empleadoCedula);
            map.put("Pinicio", dp_inicio.getValue().toString());
            map.put("PFin", dp_fin.getValue().toString());

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
            return;
        }

    }

}
