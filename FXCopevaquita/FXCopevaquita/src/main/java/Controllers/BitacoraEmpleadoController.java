/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.ActividadDAO;
import DAO.AreaDAO;
import DAO.BitacoraEmpleadoDAO;
import DAO.EmpleadoDAO;
import Helpers.OpenWindowsHandler;
import Models.BitacoraEmpleado;
import Models.Empleado;
import java.net.URL;
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

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class BitacoraEmpleadoController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraEmpleado> tblListarReporteEmpleado;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colCedula;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colFechaRegistro;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colActividades;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colAreas;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colCantidad;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colPrecio;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colEstado;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colNombre;

    /**
     * Initializes the controller class.
     */
    final private EmpleadoDAO empleadoService = new EmpleadoDAO();
    final private ActividadDAO actividadService = new ActividadDAO();
    final private AreaDAO areaService = new AreaDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> cbx_status;
    @FXML
    private DatePicker dp_inicio;
    @FXML
    private DatePicker dp_fin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarBitacoras();
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = empleadoService.obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(empleado.getNombre() + " " + empleado.getApellidos());
        });

        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colActividades.setCellValueFactory(cellData -> {
            var tipo = actividadService.obtenerPorId(cellData.getValue().getActividad());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colAreas.setCellValueFactory(cellData -> {
            var tipo = areaService.obtenerPorId(cellData.getValue().getArea());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));
    }

    public void cargarBitacoras() {
        var ObservableContrato
                = FXCollections.observableArrayList(new BitacoraEmpleadoDAO().obtenerListaBitacoraEmpleado());
        tblListarReporteEmpleado.setItems(ObservableContrato);
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitacoraEmpleado("/views/AgregarBitacoraEmpleado");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarBitacoraEmpleado("/views/ActualizarBitacoraEmpleado");
    }

    @FXML
    private void OnReportDaily(ActionEvent event) {

    }

    @FXML
    private void PresionarEnter(KeyEvent event) {

    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarBitacoras();
    }

}
