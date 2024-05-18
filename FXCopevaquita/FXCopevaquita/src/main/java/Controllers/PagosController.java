/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import Helpers.OpenWindowsHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import DAO.*;
import Database.DatabaseConnection;
import JasperReports.JAppReport;
import JasperReports.JReportDeducciones;
import JasperReports.JReportPlanilla;
import Models.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class PagosController implements Initializable {

    private ComboBox<Empleado> cbxEmpleado;
    @FXML
    private TableView<Pagos> tblPagos;
    @FXML
    private TableColumn<Pagos, String> colEmpleado;
    @FXML
    private TableColumn<Pagos, String> colFechaInicio;
    @FXML
    private TableColumn<Pagos, String> colFechaFin;
    @FXML
    private TableColumn<Pagos, String> colIncapacidades;
    @FXML
    private TableColumn<Pagos, String> colVacaciones;
    @FXML
    private TableColumn<Pagos, String> colDeducciones;
    @FXML
    private TableColumn<Pagos, String> colContratos;
    @FXML
    private TableColumn<Pagos, String> colSalarioBase;

    private ObservableList<Pagos> observablePagos;
    @FXML
    private DatePicker dpFechaInicial1;
    @FXML
    private DatePicker dpFechaFinal1;

    @FXML
    private ProgressBar progressBar;

    double resultadoPorBitacoras = 0.0;
    double resultadoPorContratos = 0.0;
    double resultadoPorIncapacidad = 0.0;
    double resultadoPorVacaciones = 0.0;
    double resultadoDeducciones = 0.0;
    double pago = 0.0;

    EmpleadoDAO empleadoService = new EmpleadoDAO();
    BitacoraEmpleadoDAO bitacoraService = new BitacoraEmpleadoDAO();
    ContratoDAO contratoService = new ContratoDAO();
    IncapacidadDAO incapacidadService = new IncapacidadDAO();
    VacacionesDAO vacacionesService = new VacacionesDAO();
    DeduccionesDAO deduccionService = new DeduccionesDAO();
    PagosDAO pagosService = new PagosDAO();
    PagoContratoDAO pagoContratoService = new PagoContratoDAO();
    PagoDeduccionDAO pagoDeduccionService = new PagoDeduccionDAO();
    PagoIncapacidadDAO pagoIncapacidadService = new PagoIncapacidadDAO();
    PagoVacacionDAO pagoVacacionService = new PagoVacacionDAO();
    PagoBitacoraDAO pagoBitacoraService = new PagoBitacoraDAO();
    @FXML
    private Button btnGenerarPagoGlobal;
    @FXML
    private Button btnGenerarReporte;

    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    PagosDAO daoPago = new PagosDAO();
    @FXML
    private TextField txt_filtrarEmpleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configure();
    }

    public void cargarPagos() {
        observablePagos = FXCollections.observableArrayList(new PagosDAO().obtenerListaPagos());
        tblPagos.setItems(observablePagos);
    }

    public void configure() {
        cargarPagos();

        colFechaFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
        colFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));

        // Cargar los datos de la base de datos ...
        colEmpleado.setCellValueFactory((cellData) -> {
            var cedula = cellData.getValue().getEmpleado();
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cedula);
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });

        colSalarioBase.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoBitacoraDAO().obtenerPagoBitacoraPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalBitacora() + "");
        });

        colContratos.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoContratoDAO().obtenerPagoContratoPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalContrato() + "");
        });

        colDeducciones.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoDeduccionDAO().obtenerPagoDeduccionPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalDeduccion() + "");
        });

        colIncapacidades.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoIncapacidadDAO().obtenerPagoIncapacidadPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalIncapacidad() + "");
        });

        colVacaciones.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoVacacionDAO().obtenerPagoVacacionPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalVacacion() + "");
        });
    }

    @FXML
    private void OnGenerarPago(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaGenerarPagos("/views/GenerarPagos");
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarPagos();
    }

    private void definirDatosEmpleado(Empleado empleado, Date inicio, Date finalizo) {
        dpFechaFinal1.setDisable(true);
        dpFechaInicial1.setDisable(true);

        // ¿CUANTO SE GANO POR ACTIVIDADES?
        var bitacoras = bitacoraService.obtenerListaBitacoraPorCedulaEmpleadoEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        resultadoPorBitacoras = 0.0;

        if (!empleado.getTipo().equals("SOCIO")) {
            for (BitacoraEmpleado bitacora : bitacoras) {
                resultadoPorBitacoras += bitacora.getCosto() * bitacora.getCantidad();
            }
        } else {
            var planilla = new PlanillaSociosDAO().obtenerPlanillaPorCedulaEmpleado(empleado.getCedula());
            resultadoPorBitacoras = planilla.getMonto();
        }

        // ¿CUANTO SE GANO POR CONTRATOS?
        var contratos = contratoService.obtenerListaContratosEntreFechas(empleado.getCedula(),
                inicio,
                finalizo
        );

        resultadoPorContratos = 0.0;
        for (Contrato contrato : contratos) {
            resultadoPorContratos += contrato.getMonto();
        }

        // ¿CUANTO SE GANO POR INCAPACIDAD?
        var incapacidades = incapacidadService.obtenerListaIncapacidadesEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        resultadoPorIncapacidad = 0.0;
        for (Incapacidad incapacidad : incapacidades) {
            resultadoPorIncapacidad += incapacidad.getMonto();
        }

        // ¿CUANTO SE GANO EN VACACIONES?
        var vacaciones = vacacionesService.obtenerListaVacacionesEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        resultadoPorVacaciones = 0.0;
        for (Vacaciones vacacion : vacaciones) {
            resultadoPorVacaciones += vacacion.getMonto();
        }

        // ¿CUANTO SE GANO EN DEDUCCIONES?
        var deducciones = deduccionService.obtenerListaDeduccionEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        resultadoDeducciones = 0.0;
        for (Deduccion deduccion : deducciones) {

            if (deduccion.getPendiente() > 0) {
                // Debe más de lo que vale la couta...
                if (deduccion.getPendiente() > deduccion.getCuota()) {
                    resultadoDeducciones += deduccion.getCuota();
                } else {
                    // Debe menos de lo que vale la cuota...
                    resultadoDeducciones += deduccion.getPendiente();
                }
            }
        }

        // CALCULAR EL TOTAL...
        // TODO; Crear un DAO de la tabla de porcentaje que consulte y me traiga el %;
        var total = ((resultadoPorVacaciones
                + resultadoPorIncapacidad
                + resultadoPorContratos
                + resultadoPorBitacoras) - resultadoDeducciones);

        // obtener los porcentajes de rebajo...
        PorcentajeRebajosDAO rebajos = new PorcentajeRebajosDAO();
        pago = total;

        if (empleado.getTipo().equals("PEON") || empleado.getTipo().equals("SECRETARIO")) {
            pago -= total * rebajos.obtenerPorcentajeRebajos().getGobierno();
        }
    }

    private void generarPagoEmpleado(Empleado empleado, Date inicio, Date finalizo) {
        // BITACORAS...
        // inhabilitar todas las bitacoras...
        bitacoraService.actualizarEstadoBitacoraEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo,
                (byte) 0
        );
        // Generar pago...
        pagosService.insertarPagos(new Pagos(
                0,
                inicio,
                empleado.getCedula(),
                finalizo
        ));
        var idPago = pagosService.obtenerIdPago(empleado.getCedula(), inicio, finalizo);
        // generar el pago por bitacoras...
        pagoBitacoraService.insertarPagoBitacora(new PagoBitacora(resultadoPorBitacoras, idPago));

        // CONTRATOS...
        // actualizar estados...
        contratoService.actualizarEstadoContratoEntreFechas(
                empleado.getCedula(),
                inicio, finalizo,
                (byte) 0
        );

        // generar el pago por contratos...
        pagoContratoService.pagoContrato(new PagoContrato(resultadoPorContratos, idPago));

        // INCAPACIDADES
        // actualizar estados..
        incapacidadService.actualizarEstadoIncapacidadEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo,
                (byte) 0
        );
        // generar el pago por incapacidad...
        pagoIncapacidadService.insertarIncapacidad(new PagoIncapacidad(resultadoPorIncapacidad, idPago));

        // VACACIONES...
        // actualizar estados...
        vacacionesService.actualizarEstadoVacacionesEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo,
                (byte) 0
        );
        // generar el pago por vacación...
        pagoVacacionService.insertarVacacion(new PagoVacacion(resultadoPorVacaciones, idPago));

        // DEDUCCIONES..
        // ¿CUANTO SE GANO EN DEDUCCIONES?
        var deducciones = deduccionService.obtenerListaDeduccionEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        for (Deduccion deduccion : deducciones) {
            if (deduccion.getPendiente() > 0) {
                // Debe más de lo que vale la couta...
                if (deduccion.getPendiente() > deduccion.getCuota()) {
                    resultadoDeducciones += deduccion.getCuota();
                    var pendiente = deduccion.getPendiente();
                    var couta = deduccion.getCuota();
                    deduccion.setPendiente(pendiente - couta);
                } else {
                    // Debe menos de lo que vale la cuota...
                    resultadoDeducciones += deduccion.getPendiente();
                    deduccion.setPendiente(0);
                }
                deduccionService.actualizarDeduccion(deduccion);
            }
        }

        // generar el pago por deducción...
        pagoDeduccionService.insertarDeduccion(new PagoDeduccion(resultadoPorIncapacidad, idPago));
    }

//    private void FiltrarPagosPorCedulaEmpleado() {
//        try {
//            var empleado = cbxEmpleado.getValue();
//            if (empleado != null && !empleado.getCedula().isEmpty()) {
//                var lista = FXCollections.observableArrayList(daoPago.obtenerListaIncapacidadPorCedulaEmpleado(empleado.getCedula()));
//                tblPagos.setItems(lista);
//            }
//        } catch (Exception ex) {
//            MensajePersonalizado.Ver("Error", "Error al buscar las incapacidades del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
//        }
//    }
    
    @FXML
    private void OnGenerarPagoGlobal() {
        if (dpFechaInicial1.getValue() == null || dpFechaFinal1.getValue() == null) {
            MensajePersonalizado.Ver("Problemas en el rango de fechas",
                    "El rango de fechas es requerido",
                    Alert.AlertType.ERROR);
            return;
        }

        var startDate = dpFechaInicial1.getValue();
        var endDate = dpFechaFinal1.getValue();

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            MensajePersonalizado.Ver("Problemas en el rango de fechas",
                    "El rango de fechas es incoherente",
                    Alert.AlertType.INFORMATION);
            return;
        }

        // Bloquear la interacción del usuario
        btnGenerarPagoGlobal.setDisable(true);

        // Crear un Task para la operación larga
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                var empleados = new EmpleadoDAO().obtenerListaEmpleados();
                for (Empleado empleado : empleados) {
                    definirDatosEmpleado(empleado, Date.valueOf(startDate), Date.valueOf(endDate));
                    generarPagoEmpleado(empleado, Date.valueOf(startDate), Date.valueOf(endDate));
                    updateProgress(empleados.indexOf(empleado) + 1, empleados.size());
                }
                return null;
            }
        };

        // Configurar el progreso del Task
        progressBar.progressProperty().bind(task.progressProperty());

        // Manejar el evento de éxito del Task
        task.setOnSucceeded((e) -> {
            // Desbloquear la interacción del usuario
            MensajePersonalizado.Ver("Operación Completa",
                    "El proceso de generación de pagos ha finalizado",
                    Alert.AlertType.INFORMATION);

            btnGenerarPagoGlobal.setDisable(false);
            dpFechaFinal1.setDisable(false);
            dpFechaInicial1.setDisable(false);
            cargarPagos();
        });

        // Iniciar el Task en un nuevo hilo
        Thread thread = new Thread(task);
        thread.start();
    }

    @FXML
    private void OnGenerarReporte(ActionEvent event) {
        var report = new JReportPlanilla();
        var jreport = report.getPlanillaFromDatesRange();

        if (dpFechaInicial1.getValue() == null || dpFechaFinal1.getValue() == null) {
            MensajePersonalizado.Ver("Problemas en el rango de fechas",
                    "El rango de fechas es requerido",
                    Alert.AlertType.ERROR);
            return;
        }

        var startDate = dpFechaInicial1.getValue();
        var endDate = dpFechaFinal1.getValue();

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            MensajePersonalizado.Ver("Problemas en el rango de fechas",
                    "El rango de fechas es incoherente",
                    Alert.AlertType.INFORMATION);
            return;
        }

        HashMap<String, Object> map = new HashMap();

        System.out.println("Fechas " + startDate.toString());

        map.put("p_dateStart", startDate.toString());
        map.put("p_endDate", endDate.toString());

        JAppReport.getReport(DatabaseConnection.getConnection(), map, jreport);
        JAppReport.showReport();
        return;
    }

    @FXML
    private void FilterEmployee(KeyEvent event) {
        var text = txt_filtrarEmpleado.getText();
        List<Empleado> empleados = new EmpleadoDAO().obtenerListaAutocompletado(text);
        
        List<String> cedulas = empleados.stream()
                .map(e -> e.getCedula())
                .toList();
        
        var newData = FXCollections.observableArrayList(
                observablePagos.stream()
                .filter(e -> {
                    return cedulas.contains(e.getEmpleado());
                })
                .toList()
        );
        
        tblPagos.setItems(newData);
    }


}
