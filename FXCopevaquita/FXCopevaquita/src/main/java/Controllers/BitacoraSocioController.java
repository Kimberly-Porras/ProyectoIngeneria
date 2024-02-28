/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.BitacoraSocioDAO;
import DAO.EmpleadoDAO;
import Helpers.OpenWindowsHandler;
import Models.BitacoraSocio;
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
import javafx.scene.control.ComboBox;
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
public class BitacoraSocioController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraSocio> tblListarReporteSocio;
    @FXML
    private TableColumn<BitacoraSocio, String> colCedula;
    @FXML
    private TableColumn<BitacoraSocio, String> colNombre;
    @FXML
    private TableColumn<BitacoraSocio, Double> colHoras;
    @FXML
    private TableColumn<BitacoraSocio, String> colDescripcion;
    @FXML
    private TableColumn<BitacoraSocio, String> colEstado;
    @FXML
    private ComboBox<String> cbx_status;

    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<BitacoraSocio> ObservableBitacoraSocio = FXCollections.observableArrayList();
    final private EmpleadoDAO empleadoService = new EmpleadoDAO();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Activo", "Inactivo");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Configurar();
        cargarBitacoraSocio(true, false);

    }

    private void Configurar() {
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula_empleado()));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = empleadoService.obtenerEmpleadoPorCedula(cellData.getValue().getCedulaEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(empleado.getNombre() + " " + empleado.getApellidos());
        });
        colHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Activo")) {
                cargarBitacoraSocio(true, true);
            } else {
                cargarBitacoraSocio(false, true);
            }
        });
    }

    private void filtrarBitacoraSocio() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
            Predicate<BitacoraSocio> pReporte = x
                    -> x.getCedula_empleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableBitacoraSocio.filtered((x) -> pEmpleado.test(Get(x.getCedula_empleado())) || pReporte.test(x));
            tblListarReporteSocio.setItems(listaTemporal);
        } else {
            cargarBitacoraSocio(true, false);
        }
    }

    public void cargarBitacoraSocio(boolean status, boolean filtro) {
        if (filtro) {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new BitacoraSocioDAO().obtenerListaBitacoraSocio())
                            .filtered(empl -> empl.isStatus() == status);
            tblListarReporteSocio.setItems(ObservableIncapacidad);
        } else {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new BitacoraSocioDAO().obtenerListaBitacoraSocio());
            tblListarReporteSocio.setItems(ObservableIncapacidad);
        }
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitacoraSocio("/views/AgregarBitacoraSocio");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarBitacoraSocio("/views/ActualizarBitacoraSocio");
    }

    @FXML
    private void OnReporteDiarioSocio(ActionEvent event) {
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
        filtrarBitacoraSocio();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarBitacoraSocio(true, false);
    }

}
