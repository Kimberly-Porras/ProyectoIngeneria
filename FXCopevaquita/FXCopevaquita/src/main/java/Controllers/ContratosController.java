/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.ActividadDAO;
import DAO.ContratoDAO;
import DAO.EmpleadoDAO;
import Database.DatabaseConnection;
import Helpers.OpenWindowsHandler;
import JasperReports.JAppReport;
import JasperReports.JReporteAsistenciaNuevo;
import JasperReports.JReporteContratoNuevo;
import Models.Contrato;
import Models.Empleado;
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
 *
 * @author alber
 * @author kim03
 */
public class ContratosController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<Contrato> tblListaContrato;
    @FXML
    private TableColumn<Contrato, String> colCedula;
    @FXML
    private TableColumn<Contrato, String> colNombre;
    @FXML
    private TableColumn<Contrato, String> colFechaInicio;
    @FXML
    private TableColumn<Contrato, String> colFechaFinal;
    @FXML
    private TableColumn<Contrato, String> colFechaRegistro;
    @FXML
    private TableColumn<Contrato, String> colMonto;
    @FXML
    private TableColumn<Contrato, String> colEstado;
    @FXML
    private TableColumn<Contrato, String> colActividad;
    @FXML
    private ComboBox<String> cbx_status;
    @FXML
    private DatePicker dp_inicio;
    private DatePicker dp_fin;

    /**
     * Initializes the controller class.
     */
    ObservableList<Contrato> ObservableContrato = FXCollections.observableArrayList();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    final private ActividadDAO ActividadService = new ActividadDAO();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarContratos(true, false);
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaEmpleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getCedulaEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colActividad.setCellValueFactory(cellData -> {
            var tipo = ActividadService.obtenerPorId(cellData.getValue().getMotivo());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarContratos(true, true);
            } else {
                cargarContratos(false, true);
            }
        });
    }

    public void cargarContratos(boolean status, boolean filtro) {
        if (filtro) {
            ObservableContrato
                    = FXCollections.observableArrayList(new ContratoDAO().obtenerListaContratos())
                            .filtered(cont -> cont.isStatus() == status);
            tblListaContrato.setItems(ObservableContrato);
        } else {
            ObservableContrato
                    = FXCollections.observableArrayList(new ContratoDAO().obtenerListaContratos());
            tblListaContrato.setItems(ObservableContrato);
        }
    }

    private void filtrarContrato() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
            var listaTemporal = ObservableContrato.filtered((x) -> {
                var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getCedulaEmpleado());
                return x.getCedulaEmpleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            });
             tblListaContrato.setItems(listaTemporal);
        } else {
            cargarContratos(true, false);
        }
    }


    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarContratos("/views/AgregarContratos");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAreas("/views/ActualizarContratos");
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
        filtrarContrato();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarContratos(true, false);
    }

    @FXML
    private void OnReporte(ActionEvent event) {
        
        var report = new JReporteContratoNuevo();
        var jreport = report.getTodosLosContratos();

        if (dp_inicio.getValue() != null) {

            HashMap<String, Object> map = new HashMap();

            System.out.println("Fechas " + dp_inicio.getValue().toString());

            map.put("PFechaRegistro", dp_inicio.getValue().toString());
            

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
            return;
        }
    }

}
