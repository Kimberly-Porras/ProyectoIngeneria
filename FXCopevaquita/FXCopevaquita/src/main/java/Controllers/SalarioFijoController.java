/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.EmpleadoDAO;
import DAO.PlanillaSociosDAO;
import Database.DatabaseConnection;
import Helpers.OpenWindowsHandler;
import JasperReports.JAppReport;
import JasperReports.JReporteContratoNuevo;
import JasperReports.JReporteSalarioFijoNuevojrxml;
import Models.Empleado;
import Models.PlanillaSocios;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class SalarioFijoController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<PlanillaSocios> tblSalarioFijo;
    @FXML
    private TableColumn<PlanillaSocios, String> colCedula;
    @FXML
    private TableColumn<PlanillaSocios, String> colNombre;
    @FXML
    private TableColumn<PlanillaSocios, String> colMonto;
    @FXML
    private TableColumn<PlanillaSocios, String> colEstado;
    @FXML
    private ComboBox<String> cbx_status;
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<PlanillaSocios> Observableplanilla = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarPlanillasSocios(true, false);
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

        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "inactivo"));
        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarPlanillasSocios(true, true);
            } else {
                cargarPlanillasSocios(false, true);
            }
        });
    }

    public void cargarPlanillasSocios(boolean status, boolean filtro) {
        if (filtro) {
            Observableplanilla
                    = FXCollections.observableArrayList(new PlanillaSociosDAO().obtenerListaPlanillaSocios())
                            .filtered(empl -> empl.isStatus() == status);
            tblSalarioFijo.setItems(Observableplanilla);
        } else {
            Observableplanilla
                    = FXCollections.observableArrayList(new PlanillaSociosDAO().obtenerListaPlanillaSocios());
            tblSalarioFijo.setItems(Observableplanilla);
        }
    }

    private void filtrarPlanillas() {
        if (filtrarEmpleado.getText() != null && !filtrarEmpleado.getText().trim().equals("")) {

            var listaTemporal = Observableplanilla.filtered((x) -> {
                var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getEmpleado());
                return x.getEmpleado().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                        || empleado.getNombre().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                        || empleado.getApellidos().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                        || empleado.getNombreCompleto().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            });
            tblSalarioFijo.setItems(listaTemporal);
        } else {
            cargarPlanillasSocios(true, false);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarSalarioFijo("/views/AgregarSalarioFijo");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarSalarioFijo("/views/ActualizarSalarioFijo");
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarPlanillas();
    }

    @FXML
    private void OnPresionarCualquierLado(MouseEvent event) {
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarPlanillasSocios(true, false);
    }

    @FXML
    private void OnReporte(ActionEvent event) {
        var report = new JReporteSalarioFijoNuevojrxml();
        var jreport = report.getTodosLosSalariosFijos();

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
