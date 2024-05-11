/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import DAO.*;
import Models.*;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import java.sql.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class GenerarPagosController implements Initializable {

    @FXML
    private DatePicker dpFechaInicial1;
    @FXML
    private DatePicker dpFechaFinal1;
    @FXML
    private ComboBox<Empleado> cbxEmpleado1;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextField txtDeduccion;
    @FXML
    private TextField txtIncapacidad;
    @FXML
    private TextField txtVacacion;
    @FXML
    private TextField txtTotal;

    ObservableList<Empleado> ObservableEmpleado;
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

    boolean canGenerate = false;
    double resultadoPorBitacoras = 0.0;
    double resultadoPorContratos = 0.0;
    double resultadoPorIncapacidad = 0.0;
    double resultadoPorVacaciones = 0.0;
    double resultadoDeducciones = 0.0;
    double pago = 0.0;

    @FXML
    private TextField txtContrato;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        ObservableEmpleado = FXCollections.observableArrayList(empleadoService.obtenerListaEmpleados());
        cbxEmpleado1.setItems(ObservableEmpleado);

        if (ObservableEmpleado.size() > 0) {
            cbxEmpleado1.setValue(ObservableEmpleado.get(0));
        }

        cbxEmpleado1.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return "";
                }
                return t.getNombreCompleto();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });
    }

    @FXML
    private void cbxEmpleado1(ActionEvent event) {
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        var empleado = cbxEmpleado1.getSelectionModel().getSelectedItem();
        var fechaInicio = dpFechaInicial1.getValue();
        var fechaFinal = dpFechaFinal1.getValue();

        // verificar los campos...
        if (fechaFinal == null
                || fechaInicio == null
                || empleado == null) {
            MensajePersonalizado.Ver("Error", "El rango de fechas es requerido ", Alert.AlertType.ERROR);
            return; // No se puede buscar sin las fechas o el empleado...
        }

        var inicio = Date.valueOf(fechaInicio);
        var finalizo = Date.valueOf(fechaFinal);

        // bloquear campos...
        cbxEmpleado1.setDisable(true);
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
            pago -= total * rebajos.obtenerPorcentajesRebajos().getGobierno();
        }

        // Cargar datos en la interfaz grafica...
        txtMonto.setText(resultadoPorBitacoras + ""); // Salario base..
        txtDeduccion.setText(resultadoDeducciones * -1 + ""); // Deducciones 
        txtIncapacidad.setText(resultadoPorIncapacidad + ""); // Incapacidades
        txtVacacion.setText((resultadoPorVacaciones + "")); // vacaciones
        txtContrato.setText(resultadoPorContratos + ""); // contratos

        txtTotal.setText(pago + ""); // pago...
        canGenerate = true;
    }

    @FXML
    private void OnPressed(KeyEvent event) {
        
    }

    @FXML
    private void btnGenerarPago(ActionEvent event) {
        if (canGenerate) {
            var empleado = cbxEmpleado1.getSelectionModel().getSelectedItem();
            var fechaInicio = dpFechaInicial1.getValue();
            var fechaFinal = dpFechaFinal1.getValue();

            var inicio = Date.valueOf(fechaInicio);
            var finalizo = Date.valueOf(fechaFinal);

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
            btnBuscar.setDisable(true);
        }
    }

}
