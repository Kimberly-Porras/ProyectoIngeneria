/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import Models.Empleado;
import Models.Incapacidad;
import Models.Vacaciones;
import Models.Deduccion;

import javafx.collections.FXCollections;
import DAO.EmpleadoDAO;
import DAO.ContratoDAO;
import DAO.IncapacidadDAO;
import DAO.VacacionesDAO;
import DAO.DeduccionesDAO;

import DAO.BitacoraEmpleadoDAO;
import Models.BitacoraEmpleado;
import Models.Contrato;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author User
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

        System.out.println("Iniciar el proceso para calcular el salario del empleado...");
        var empleado = cbxEmpleado1.getSelectionModel().getSelectedItem();
        var fechaInicio = dpFechaInicial1.getValue();
        var fechaFinal = dpFechaFinal1.getValue();

        // verificar los campos...
        if (fechaFinal == null
                || fechaInicio == null
                || empleado == null) {
            return; // No se puede buscar sin las fechas o el empleado...
        }

        var inicio = Date.valueOf(fechaInicio);
        var finalizo = Date.valueOf(fechaFinal);

        // ¿CUANTO SE GANO POR ACTIVIDADES?
        var bitacoras = bitacoraService.obtenerListaBitacoraPorCedulaEmpleadoEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );
        var resultadoPorBitacoras = 0;
        for (BitacoraEmpleado bitacora : bitacoras) {
            resultadoPorBitacoras += bitacora.getCosto() * bitacora.getCantidad();
        }
        System.out.println("Se gano por las actividades: " + resultadoPorBitacoras);

        // ¿CUANTO SE GANO POR CONTRATOS?
        var contratos = contratoService.obtenerListaContratosEntreFechas(empleado.getCedula(),
                inicio,
                finalizo
        );
        var resultadoPorContratos = 0;
        for (Contrato contrato : contratos) {
            resultadoPorContratos += contrato.getMonto();
        }
        System.out.println("se gano por contratos: " + resultadoPorContratos);
        // ¿CUANTO SE GANO POR INCAPACIDAD?
        var incapacidades = incapacidadService.obtenerListaIncapacidadesEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );
        var resultadoPorIncapacidad = 0;
        for (Incapacidad incapacidad : incapacidades) {
            resultadoPorIncapacidad += incapacidad.getMonto();
        }
        System.out.println("se gano por incapacidades: " + resultadoPorIncapacidad);
        // ¿CUANTO SE GANO EN VACACIONES?
        var vacaciones = vacacionesService.obtenerListaVacacionesEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );
        var resultadoVacaciones = 0;
        for (Vacaciones vacacion : vacaciones) {
            resultadoVacaciones += vacacion.getMonto();
        }
        System.out.println("SE gano en vacaciones: " + resultadoVacaciones);
        // ¿CUANTO SE GANO EN DEDUCCIONES?
        var deducciones = deduccionService.obtenerListaDeduccionEntreFechas(
                empleado.getCedula(),
                inicio,
                finalizo
        );

        var resultadoDeducciones = 0;
        for (Deduccion deduccion : deducciones) {
            resultadoDeducciones += deduccion.getCuota();
            // actualizar la deducción...
            var pendiente = deduccion.getPendiente();
            var couta = deduccion.getCuota();

            deduccion.setPendiente(pendiente - couta);
            System.out.println("La deducción se actualizaría aquí");
//            deduccionService.actualizarDeduccion(deduccion);
        }
        System.out.println("El total por deducciones es: " + resultadoDeducciones);
        // CALCULAR EL TOTAL...
        // TODO; Crear un DAO de la tabla de porcentaje que consulte y me traiga el %;
        var total = ((resultadoVacaciones
                + resultadoPorIncapacidad
                + resultadoPorContratos
                + resultadoPorBitacoras) - resultadoDeducciones);
        
        var pago = total - (total * 0.1067);
        System.out.println("El empleado tiene como pago: " + pago);

    }

    @FXML
    private void OnPressed(KeyEvent event) {
    }

    @FXML
    private void btnGenerarPago(ActionEvent event) {
    }

}
