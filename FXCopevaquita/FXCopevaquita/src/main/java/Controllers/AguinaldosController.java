/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.AguinaldoDAO;
import DAO.DeduccionesDAO;
import DAO.EmpleadoDAO;
import DAO.PagoBitacoraDAO;
import DAO.PagoContratoDAO;
import DAO.PagoDeduccionDAO;
import DAO.PagoIncapacidadDAO;
import DAO.PagoVacacionDAO;
import DAO.PagosDAO;
import Database.DatabaseConnection;
import JasperReports.JAppReport;
import JasperReports.JReportAguinaldos;
import Models.Aguinaldo;
import Models.Empleado;
import Models.PagoBitacora;
import Models.Pagos;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author aleke
 */
public class AguinaldosController implements Initializable {

    @FXML
    private TableColumn<Empleado, String> colCedula;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colIngreso;
    @FXML
    private TableColumn<Empleado, String> colTipo;
    @FXML
    private TableColumn<Empleado, String> colCuenta;
    @FXML
    private TableColumn<Empleado, String> colAcademico;
    @FXML
    private TableColumn<Empleado, String> colStatus;
    @FXML
    private TableView<Empleado> tblEmpleados;

    EmpleadoDAO empleadosService = new EmpleadoDAO();
    PagosDAO pagosService = new PagosDAO();

    ObservableList<Empleado> observableEmpleado = FXCollections.observableArrayList();
    @FXML
    private DatePicker dp_inicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory((data) -> {
            var fullName = data.getValue().getNombreCompleto();
            return new SimpleStringProperty(fullName);
        });
        colAcademico.setCellValueFactory(new PropertyValueFactory<>("nivelAcademico"));
        colCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        colIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));

        observableEmpleado = FXCollections.observableArrayList(empleadosService.obtenerListaEmpleados());
        tblEmpleados.setItems(observableEmpleado);
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
    }

    private int obtenerListaAguinaldos() {
        if (dp_inicio.getValue() == null) {
            return 2; // Aquí poner una alerta de que se seleccione un elemento y se ponga la fecha...
        }

        var dateSeleted = dp_inicio.getValue();

        List<Empleado> empleados = empleadosService.obtenerListaEmpleados();
        List<Aguinaldo> aguinaldos = new ArrayList();

        for (Empleado empleado : empleados) {
            var aguinaldo = obtenerAguinaldoExportar(empleado, dateSeleted);
            if (aguinaldo != null) {
                aguinaldos.add(aguinaldo);
            }
        }

        dp_inicio.setDisable(false);
        new AguinaldoDAO().removeAguinaldos();
        if (aguinaldos.size() > 0) {
            boolean insercion = new AguinaldoDAO().insertarAguinaldos(aguinaldos);
            return insercion ? 1 : 0;
        }

        return 0;
    }

    private Aguinaldo obtenerAguinaldoExportar(Empleado empleado, LocalDate dateSeleted) {
        dp_inicio.setDisable(true);
        var pagos = pagosService.obtenerPagosPorEmpleadoPosterioresAUnaFecha(empleado.getCedula(), dateSeleted.toString());
        var meses = pagos.size() / 2; // Cada dos quincenas son un mes...

        if (meses < 3) {
            return null;
        }

        var total = 0.0;

        // sumar todos los pagos de esos meses...
        for (Pagos pago : pagos) {
            total += new PagoBitacoraDAO().obtenerPagoBitacoraPorPago(pago.getId()).getTotalBitacora(); // Total por Bitacoras..
            total += new PagoContratoDAO().obtenerPagoContratoPorPago(pago.getId()).getTotalContrato(); // Total por contratos...
            total += new PagoIncapacidadDAO().obtenerPagoIncapacidadPorPago(pago.getId()).getTotalIncapacidad(); // Total por incapacidades...
            total += new PagoVacacionDAO().obtenerPagoVacacionPorPago(pago.getId()).getTotalVacacion(); // Total por vacaciones...
            total -= new PagoDeduccionDAO().obtenerPagoDeduccionPorPago(pago.getId()).getTotalDeduccion(); // Total por deducciones...
        }

        // Dividir los pagos entre la cantidad de meses trabajados...
        var aguinaldo = new Aguinaldo(empleado.getNombre(), empleado.getApellidos(),
                empleado.getNumeroCuenta(), empleado.getTipo(), total / meses);

        return aguinaldo;
    }

    @FXML
    private void OnGenerarAguinaldo(ActionEvent event) {
        if (tblEmpleados.getSelectionModel().getSelectedItem() == null || dp_inicio.getValue() == null) {
            return; // Aquí poner una alerta de que se seleccione un elemento y se ponga la fecha...
        }
        var model = tblEmpleados.getSelectionModel().getSelectedItem();
        var dateSeleted = dp_inicio.getValue();

        var pagos = pagosService.obtenerPagosPorEmpleadoPosterioresAUnaFecha(model.getCedula(), dateSeleted.toString());
        var meses = pagos.size() / 2; // Cada dos quincenas son un mes...

        if (meses < 3) {
            MensajePersonalizado.Ver("Error ", "Mae, ocupa al menos 3 meses de contrato", Alert.AlertType.ERROR);
            return;
        }
        var total = 0.0;

        // sumar todos los pagos de esos meses...
        for (Pagos pago : pagos) {
            total += new PagoBitacoraDAO().obtenerPagoBitacoraPorPago(pago.getId()).getTotalBitacora(); // Total por Bitacoras..
            total += new PagoContratoDAO().obtenerPagoContratoPorPago(pago.getId()).getTotalContrato(); // Total por contratos...
            total += new PagoIncapacidadDAO().obtenerPagoIncapacidadPorPago(pago.getId()).getTotalIncapacidad(); // Total por incapacidades...
            total += new PagoVacacionDAO().obtenerPagoVacacionPorPago(pago.getId()).getTotalVacacion(); // Total por vacaciones...
            total -= new PagoDeduccionDAO().obtenerPagoDeduccionPorPago(pago.getId()).getTotalDeduccion(); // Total por deducciones...
        }

        // Dividir los pagos entre la cantidad de meses trabajados...
        var aguinaldo = total / meses;
        MensajePersonalizado.Ver("Cálculo: ", "El aguinaldo a partir del " + dateSeleted.toString()
                + " Tiene un valor de: " + aguinaldo, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void OnExportarAguinaldo(ActionEvent event) {
        var aguinaldos = obtenerListaAguinaldos();

        if (aguinaldos == 0) {
            MensajePersonalizado.Ver("Cálculo: ", "No hay datos respecto a aguinaldos a partir de la fecha seleccionada ", Alert.AlertType.INFORMATION);
            return;
        }

        if (aguinaldos == 2) {
            MensajePersonalizado.Ver("Info: ", "Por favor, seleccione una fecha", Alert.AlertType.INFORMATION);
            return;
        }

        var report = new JReportAguinaldos();
        var jreport = report.getAguinaldos();

        JAppReport.getReport(DatabaseConnection.getConnection(), null, jreport);
        JAppReport.showReport();
    }

}
