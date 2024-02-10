/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DeduccionesDAO;
import Helpers.OpenWindowsHandler;
import Models.Deduccion;
import Models.Empleado;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import DAO.EmpleadoDAO;
import DAO.TipoDeduccionDAO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DeduccionesController implements Initializable {

    @FXML
    private TextField txtfiltrarEmpleado;
    @FXML
    private TableView<Deduccion> tblDeduccionesEmpleados;
    @FXML
    private TableColumn<Deduccion, String> colCedula;
    @FXML
    private TableColumn<Deduccion, String> colNombre;
    @FXML
    private TableColumn<Deduccion, String> colTipoDeduccion;
    @FXML
    private TableColumn<Deduccion, String> colCuota;
    @FXML
    private TableColumn<Deduccion, String> colEstado;
    @FXML
    private TableColumn<Deduccion, String> colMonto;
    @FXML
    private TableColumn<Deduccion, String> colPendiente;

    final private EmpleadoDAO empleadoService = new EmpleadoDAO();
    final private TipoDeduccionDAO tipoDeduccionService = new TipoDeduccionDAO();

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Deduccion> ObservableDeduccion = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarDeducciones();
    }

    public void configurar() {
//        colCedula.setCellValueFactory(cellData -> {
//            var empleado = empleadoService.obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
//            return new SimpleStringProperty("hola!");
//        });
//        
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));

        colNombre.setCellValueFactory(cellData -> {
            var empleado = empleadoService.obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(empleado.getNombre() + " " + empleado.getApellidos());
        });

        colTipoDeduccion.setCellValueFactory(cellData -> {
            var tipo = tipoDeduccionService.obtenerPorId(cellData.getValue().getTipo());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        colCuota.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
    }

    private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }

    public void cargarDeducciones() {
        var ObservableIncapacidad
                = FXCollections.observableArrayList(new DeduccionesDAO().obtenerListaDeduccion());
        tblDeduccionesEmpleados.setItems(ObservableIncapacidad);
    }

    private void filtrarDeduccion() {
        if (txtfiltrarEmpleado.getText() != null && !txtfiltrarEmpleado.getText().trim().equals("")) {
            Predicate<Deduccion> pReporte = x
                    -> x.getEmpleado().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreTipoDeduccion().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtfiltrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableDeduccion.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pReporte.test(x));
            tblDeduccionesEmpleados.setItems(listaTemporal);
        } else {
            tblDeduccionesEmpleados.setItems(ObservableDeduccion);
        }
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarDeduccion("/views/AgregarDeducciones");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarDeduccion("/views/ActualizarDeducciones");
    }

    @FXML
    private void OnAbonos(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAbonoDeduccion("/views/AbonoDeducciones");
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarDeduccion();
    }

}
