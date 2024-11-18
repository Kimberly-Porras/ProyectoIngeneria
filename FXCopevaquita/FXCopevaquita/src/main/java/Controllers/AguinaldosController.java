/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.AguinaldoDAO;
import DAO.AguinaldoExportarDAO;
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
import Models.AguinaldoExportar;
import Models.Empleado;
import Models.PagoBitacora;
import Models.Pagos;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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
    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private DatePicker dp_fin;

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

    private int obtenerListaAguinaldos() {
        if (dp_inicio.getValue() == null) {
            return 2; // Aquí poner una alerta de que se seleccione un elemento y se ponga la fecha...
        }

        if (dp_fin.getValue() == null) {
            return 2; // Aquí poner una alerta de que se seleccione un elemento y se ponga la fecha...
        }

        var dateSeleted = dp_inicio.getValue();
        var dateSeleted2 = dp_fin.getValue();

        List<Empleado> empleados = empleadosService.obtenerListaEmpleados();
        List<AguinaldoExportar> aguinaldos = new ArrayList();

        for (Empleado empleado : empleados) {
            var aguinaldo = obtenerAguinaldoExportar(empleado, dateSeleted, dateSeleted2);
            if (aguinaldo != null) {
                aguinaldos.add(aguinaldo);
            }
        }

        dp_inicio.setDisable(false);
        new AguinaldoExportarDAO().removeAguinaldos();
        if (aguinaldos.size() > 0) {
            boolean insercion = new AguinaldoExportarDAO().insertarAguinaldos(aguinaldos);
            return insercion ? 1 : 0;
        }

        return 0;
    }

    private AguinaldoExportar obtenerAguinaldoExportar(Empleado empleado, LocalDate dateSeleted, LocalDate dateSelected2) {
        dp_inicio.setDisable(true);
        var pagos = pagosService.obtenerPagosPorEmpleadoPosterioresAUnaFechaExportar(empleado.getCedula(), dateSeleted.toString(), dateSelected2.toString());
        var meses = pagos.size();// Cada dos quincenas son un mes...

        if (meses < 1) {
            // TAREA PONER UN AVÍSOOOOOOOOOOOOO!
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
        var aguinaldo = new AguinaldoExportar(empleado.getNombre(), empleado.getApellidos(),
                empleado.getNumeroCuenta(), empleado.getTipo(), total * 8.33
        );

        return aguinaldo;
    }

    @FXML
    private void OnGenerarAguinaldo(ActionEvent event) {
        if (tblEmpleados.getSelectionModel().getSelectedItem() == null
                || dp_inicio.getValue() == null
                || dp_fin.getValue() == null) {

            if (tblEmpleados.getSelectionModel().getSelectedItem() == null) {
                MensajePersonalizado.Ver("Error", "No hay nigún empleado seleccionado",
                        Alert.AlertType.INFORMATION);
            }

            if (dp_inicio.getValue() == null || dp_fin.getValue() == null) {
                MensajePersonalizado.Ver("Error", "Debe seleccionar el rango de fechas",
                        Alert.AlertType.INFORMATION);
            }

            return;// Aquí poner una alerta de que se seleccione un elemento y se ponga la fecha...
        }
        var model = tblEmpleados.getSelectionModel().getSelectedItem();
        var dateSeleted = dp_inicio.getValue();
        var dateSeleted2 = dp_fin.getValue();

        var pagos = pagosService.obtenerPagosPorEmpleadoPosterioresAUnaFecha(model.getCedula(), dateSeleted.toString(), dateSeleted2.toString());
        var meses = pagos.size(); // Cada dos quincenas son un mes...

        if (meses < 1) {
            MensajePersonalizado.Ver("Error ", "Necesita al menos una quincena de salario", Alert.AlertType.ERROR);
            return;
        }

        double total = 0.0;

        // sumar todos los pagos de esos meses...
        for (Pagos pago : pagos) {
            total += new PagoBitacoraDAO().obtenerPagoBitacoraPorPago(pago.getId()).getTotalBitacora(); // Total por Bitacoras..
            total += new PagoContratoDAO().obtenerPagoContratoPorPago(pago.getId()).getTotalContrato(); // Total por contratos...
            total += new PagoIncapacidadDAO().obtenerPagoIncapacidadPorPago(pago.getId()).getTotalIncapacidad(); // Total por incapacidades...
            total += new PagoVacacionDAO().obtenerPagoVacacionPorPago(pago.getId()).getTotalVacacion(); // Total por vacaciones...
            total -= new PagoDeduccionDAO().obtenerPagoDeduccionPorPago(pago.getId()).getTotalDeduccion(); // Total por deducciones...
        }

        // Inhabilitar los pagos usados...
        for (Pagos pago : pagos) {
            pagosService.actualizarStatusPago(0, pago.getId());
        }

        // Dividir los pagos entre la cantidad de meses trabajados...
        double aguinaldoMonto = total * 8.33;

        // Crear los aguinaldos en la base de datos...
        var aguinaldoModel = new Aguinaldo(0,
                aguinaldoMonto,
                Date.valueOf(dp_inicio.getValue()),
                Date.valueOf(dp_fin.getValue()),
                model.getCedula()
        );

        new AguinaldoDAO().insertarAguinaldo(aguinaldoModel);
        MensajePersonalizado.Ver("Cálculo: ", "El aguinaldo a partir del " + dateSeleted.toString()
                + " Tiene un valor de: " + aguinaldoMonto, Alert.AlertType.INFORMATION);
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
        
        if (dp_fin.getValue() != null && dp_inicio.getValue() != null) {
            HashMap<String, Object> map = new HashMap();

            map.put("Pinicio", dp_inicio.getValue().toString());
            map.put("PFin", dp_fin.getValue().toString());

            var report = new JReportAguinaldos();
            var jreport = report.getAguinaldos();

            JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
            JAppReport.showReport();
        }
        
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
    }

}
