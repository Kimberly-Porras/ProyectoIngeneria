/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.ActividadDAO;
import DAO.ContratoDAO;
import DAO.EmpleadoDAO;
import Helpers.OpenWindowsHandler;
import Models.Contrato;
import Models.Empleado;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author User
 */
public class ContratosController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<Contrato> tblListaContrato;
    @FXML
    private TableColumn<Contrato, String> colCedula;
    @FXML
    private TableColumn<Contrato, String> colNombre;
    @FXML
    private TableColumn<Contrato, String> colFechaInicio;
    @FXML
    private TableColumn<Contrato, String> colFechaFinal;
    @FXML
    private TableColumn<Contrato, String> colFechaRegistro;
    @FXML
    private TableColumn<Contrato, String> colMonto;
    @FXML
    private TableColumn<Contrato, String> colEstado;
    @FXML
    private TableColumn<Contrato, String> colActividad;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Contrato> ObservableContrato = FXCollections.observableArrayList();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    final private ActividadDAO ActividadService = new ActividadDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarContratos();
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaEmpleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getCedulaEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colActividad.setCellValueFactory(cellData -> {
            var tipo = ActividadService.obtenerPorId(cellData.getValue().getMotivo());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));
    }

    public void cargarContratos() {
        var ObservableContrato
                = FXCollections.observableArrayList(new ContratoDAO().obtenerListaContratos());
        tblListaContrato.setItems(ObservableContrato);
    }
    
    private void filtrarContrato() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
            Predicate<Contrato> pContrato = x
                    -> x.getCedulaEmpleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableContrato.filtered((x) -> pEmpleado.test(Get(x.getCedulaEmpleado())) || pContrato.test(x));
            tblListaContrato.setItems(listaTemporal);
        } else {
            tblListaContrato.setItems(ObservableContrato);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarContratos("/views/ActualizarContratos");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAreas("/views/AgregarContratos");
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
        filtrarContrato();
    }

}
