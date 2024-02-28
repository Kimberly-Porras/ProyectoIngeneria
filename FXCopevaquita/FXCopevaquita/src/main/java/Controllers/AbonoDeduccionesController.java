/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.AbonoDAO;
import Models.Abono;
import Models.Deduccion;
import Models.Empleado;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
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
    AbonoDAO daoAbono = new AbonoDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarAbonos();
        configurarDeducciones();
    }

    public void configurarDeducciones() {
        colTipoDeduccionAct1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreTipoDeduccion()));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colCuota.setCellValueFactory(new PropertyValueFactory<>("couta"));
        colPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

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
                boolean exito = daoAbono.insertarAbono(
                        new Abono(0,
                                deduc.getId(),
                                Double.parseDouble(txtMontoDet.getText()),
                                java.sql.Date.valueOf(dpFechaRegistroDet.getValue()),
                                txtDescripcionDet.getText()));
                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL INSERTAR", "Deducción insertada correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCampos();
                    cargarDatosDeduccionDetalle();
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
    }
    
    private void cargarDatosDeduccionDetalle() {
        var deduc = tblDeducciones.getSelectionModel().getSelectedItem();
        if (deduc != null && !"".equals(deduc.getEmpleado())) {
            txtTipoDeduccionDet.setText(deduc.getNombreTipoDeduccion());
            txtMontoTotalDet.setText(deduc.getMonto()+ "");
            // cargar detalles de deduccion
            var lista = FXCollections.observableArrayList(daoAbono.obtenerDetalleAbonoIdDeduccion(deduc.getId()));
            tblAbono.setItems(lista);
        } else {
            MensajePersonalizado.Ver("SELEECIONE", "Debe de seleccionar una deducción para cargar", Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    private void OnFiltrarEmpleadoDet(ActionEvent event) {
    }

    @FXML
    private void btnCargarDeduccion(ActionEvent event) {
    }

    @FXML
    private void OnGuardar(ActionEvent event) {
        guardar();
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
    }

    @FXML
    private void OnCargarDatosAbonos(ActionEvent event) {
    }

}
