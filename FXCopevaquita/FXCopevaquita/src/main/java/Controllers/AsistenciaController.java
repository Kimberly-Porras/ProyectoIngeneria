/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.BitacoraAsistenciaDAO;
import DAO.EmpleadoDAO;
import Database.DatabaseConnection;
import Helpers.OpenWindowsHandler;
import JasperReports.JAppReport;
import JasperReports.JReportVacaciones;
import JasperReports.JReporteAsistenciaNuevo;
import Models.BitacoraAsistencia;
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
public class AsistenciaController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraAsistencia> tblBitacoraAsistencia;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colCedula;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colNombre;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colFecha;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colPresente;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colJustifica;
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<BitacoraAsistencia> ObservableAsistencia = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarBitacorasAsistencias(true);
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
        colPresente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isEstaPresente() ? "Presente" : "Ausente"));
        colJustifica.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isJustifica() ? "Justificada" : "Sin justificar"));
    }

    public void cargarBitacorasAsistencias(boolean filtro) {
        if (filtro) {
            ObservableAsistencia
                    = FXCollections.observableArrayList(new BitacoraAsistenciaDAO().obtenerListaBitacoraAsitencia());
            tblBitacoraAsistencia.setItems(ObservableAsistencia);
        } else {
            ObservableAsistencia
                    = FXCollections.observableArrayList(new BitacoraAsistenciaDAO().obtenerListaBitacoraAsitencia());
            tblBitacoraAsistencia.setItems(ObservableAsistencia);
        }
    }

    private void filtrarAsistencias() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
            var listaTemporal = ObservableAsistencia.filtered((x) -> {
                var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getEmpleado());
                return x.getEmpleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                        || empleado.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            });
            tblBitacoraAsistencia.setItems(listaTemporal);
        } else {
            cargarBitacorasAsistencias(true);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitaAsistencia("/views/AgregarAsistencia");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarBitaAsistencia("/views/ActualizarAsistencia");
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
        filtrarAsistencias();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarBitacorasAsistencias(true);
    }

    @FXML
    private void OnExportar(ActionEvent event) {
        
        var report = new JReporteAsistenciaNuevo();
        var jreport = report.getTodasLasAsistencias();

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
