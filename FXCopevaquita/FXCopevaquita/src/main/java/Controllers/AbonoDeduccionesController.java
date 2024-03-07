/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.AbonoDAO;
import DAO.DeduccionesDAO;
import DAO.EmpleadoDAO;
import DAO.TipoDeduccionDAO;
import Models.Abono;
import Models.Deduccion;
import Models.Empleado;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 *
 * @author alber
 * @author kim03
 */
public class AbonoDeduccionesController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoDetalle;
    @FXML
    private TextField txtTipoDeduccionDet;
    @FXML
    private TextField txtMontoTotalDet;
    @FXML
    private DatePicker dpFechaRegistroDet;
    @FXML
    private TextField txtMontoDet;
    @FXML
    private TextField txtDescripcionDet;
    @FXML
    private TableView<Deduccion> tblDeducciones;
    @FXML
    private TableColumn<Deduccion, String> colTipoDeduccionAct1;
    @FXML
    private TableColumn<Deduccion, String> colFecha;
    @FXML
    private TableColumn<Deduccion, String> colMonto;
    @FXML
    private TableColumn<Deduccion, String> colCuota;
    @FXML
    private TableColumn<Deduccion, String> colPendiente;
    @FXML
    private TableColumn<Deduccion, String> colEstado;
    @FXML
    private TableView<Abono> tblAbono;
    @FXML
    private TableColumn<Abono, String> colFechaAbono;
    @FXML
    private TableColumn<Abono, String> colDescripcionAbono;
    @FXML
    private TableColumn<Abono, String> colMontoAbono;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Abono> ObservableAbonos = FXCollections.observableArrayList();

    EmpleadoDAO empleadoDao = new EmpleadoDAO();
    AbonoDAO daoAbono = new AbonoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarAbonos();
        configurarDeducciones();
    }

    public void configurarDeducciones() {
        colTipoDeduccionAct1.setCellValueFactory((cellData) -> {
            var model = cellData.getValue();
            var ded = new TipoDeduccionDAO().obtenerPorId(model.getTipo());
            return new SimpleStringProperty(ded.getNombre());
        });
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colCuota.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        colPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        colFecha.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFecha_registro().toString()));

        cbxFiltrarEmpleadoDetalle.setConverter(new StringConverter<Empleado>() {
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

        ObservableEmpleado = FXCollections.observableArrayList(empleadoDao.obtenerListaEmpleados());
        cbxFiltrarEmpleadoDetalle.setItems(ObservableEmpleado);
    }

    public void configurarAbonos() {
        colDescripcionAbono.setCellValueFactory(new PropertyValueFactory<>("nota"));
        colMontoAbono.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colFechaAbono.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    private void guardar() {
        var deduc = tblDeducciones.getSelectionModel().getSelectedItem();
        if (deduc != null && !"".equals(deduc.getEmpleado())) {
            if (VerificarEspacios()) {
                var abono = ObtenerAbonoFromDataFields(true);
                // Antes de insertar el abono, hay que considerar los casos...
                var pendiente = deduc.getPendiente();

                if (pendiente < abono.getMonto()) {
                    MensajePersonalizado.Ver("ERROR", "El monto es mayor al saldo pendiente", Alert.AlertType.ERROR);
                    return;
                }

                deduc.setPendiente(pendiente - abono.getMonto());
                boolean exito = daoAbono.generarAbono(abono, deduc);

                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL INSERTAR", "Deducción insertada correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCampos();
                    cargarDatosDeduccionDetalle();

                    // actualizar la tabla de deducciones..
                    var model = cbxFiltrarEmpleadoDetalle.getSelectionModel().getSelectedItem();
                    if (model == null) {
                        return;
                    }
                    cargarDeducciones(model);
                } else {
                    MensajePersonalizado.Ver("ERROR", "Error al insertar la deducción", Alert.AlertType.ERROR);
                }
            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);
            }
        } else {
            MensajePersonalizado.Ver("SELEECIONE", "Debe de seleccionar una deducción para cargar", Alert.AlertType.WARNING);
        }
    }

    public boolean VerificarEspacios() {
        if (cbxFiltrarEmpleadoDetalle.getValue() != null
                && txtMontoDet.getText() != null && !txtMontoDet.getText().trim().equals("")
                && txtDescripcionDet.getText() != null && !txtDescripcionDet.getText().trim().equals("")
                && dpFechaRegistroDet.getValue() != null
                && !dpFechaRegistroDet.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCampos() {
        txtMontoDet.setText("");
        txtDescripcionDet.setText("");
        dpFechaRegistroDet.setValue(LocalDate.now());
        dpFechaRegistroDet.setValue(null);
    }

    private void cargarDatosDeduccionDetalle() {
        var deduc = tblDeducciones.getSelectionModel().getSelectedItem();
        if (deduc != null && !"".equals(deduc.getEmpleado())) {

            var ded = new TipoDeduccionDAO().obtenerPorId(deduc.getTipo());
            txtTipoDeduccionDet.setText(ded.getNombre());
            txtMontoTotalDet.setText(deduc.getMonto() + "");

            // cargar detalles de deduccion
            ObservableAbonos = FXCollections.observableArrayList(daoAbono.obtenerAbonosPorDeduccion(deduc.getId()));
            tblAbono.setItems(ObservableAbonos);
        } else {
            MensajePersonalizado.Ver("SELEECIONE", "Debe de seleccionar una deducción para cargar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void OnFiltrarEmpleadoDet(ActionEvent event) {
        var model = cbxFiltrarEmpleadoDetalle.getSelectionModel().getSelectedItem();
        if (model == null) {
            return;
        }
        cargarDeducciones(model);
    }

    private void cargarDeducciones(Empleado model) {
        var deducciones = new DeduccionesDAO().obtenerListaDeduccionesPorCedulaEmpleado(model.getCedula());
        var data = FXCollections.observableArrayList(deducciones);
        tblDeducciones.setItems(data);
    }

    @FXML
    private void btnCargarDeduccion(ActionEvent event) {
        cargarDatosDeduccionDetalle();
    }

    @FXML
    private void OnGuardar(ActionEvent event) {
        guardar();
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        var deduc = tblDeducciones.getSelectionModel().getSelectedItem();
        
        if (deduc != null && !"".equals(deduc.getEmpleado())) {

            if (VerificarEspacios() && ObtenerAbonoFromDataFields(false) != null) {
                System.out.println("Los espacios en blanco estan bien....");

                var abono = ObtenerAbonoFromDataFields(false); // modificado..
                var abonoInmutable = tblAbono.getSelectionModel().getSelectedItem(); // original..

                var pendiente = deduc.getPendiente();
                // restableceemos el pendiente original, y ajustamos...
                deduc.setPendiente(pendiente + abonoInmutable.getMonto() - abono.getMonto());

                if (deduc.getPendiente() < 0) {
                    MensajePersonalizado.Ver("ERROR", "El monto es mayor al saldo pendiente", Alert.AlertType.ERROR);
                    return;
                }

                AbonoDAO abonoDao = new AbonoDAO();
                if (abonoDao.AjustarAbono(abono, deduc)) {
                    MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Deducción actualizada correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCampos();
                    cargarDatosDeduccionDetalle();

                    // actualizar la tabla de deducciones..
                    var model = cbxFiltrarEmpleadoDetalle.getSelectionModel().getSelectedItem();
                    if (model == null) {
                        return;
                    }
                    cargarDeducciones(model);
                }

            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);
            }
        } else {
            MensajePersonalizado.Ver("SELEECIONE", "Debe de seleccionar una deducción para cargar", Alert.AlertType.WARNING);
        }

    }

    @FXML
    private void OnCargarDatosAbonos(ActionEvent event) {
        var abono = tblAbono.getSelectionModel().getSelectedItem();

        if (abono == null) {
            return;
        }
        dpFechaRegistroDet.setValue(abono.getFecha().toLocalDate());
        txtDescripcionDet.setText(abono.getNota());
        txtMontoDet.setText(abono.getMonto() + "");
    }

    private Abono ObtenerAbonoFromDataFields(boolean create) {
        var abono = tblAbono.getSelectionModel().getSelectedItem();
        // edición...
        if (!create && abono != null) {
            return new Abono(
                    abono.getId(),
                    abono.getDeduccion(),
                    Double.parseDouble(txtMontoDet.getText()),
                    Date.valueOf(dpFechaRegistroDet.getValue()),
                    txtDescripcionDet.getText());
        };
        // Creación...
        var deduc = tblDeducciones.getSelectionModel().getSelectedItem();
        if (create && deduc != null) {
            return new Abono(
                    0,
                    deduc.getId(),
                    Double.parseDouble(txtMontoDet.getText()),
                    java.sql.Date.valueOf(dpFechaRegistroDet.getValue()),
                    txtDescripcionDet.getText()
            );
        }

        return null;
    }

}
