/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helpers.OpenWindowsHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import Models.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import DAO.EmpleadoDAO;


/**
 * FXML Controller class
 *
 * @author User
 */
public class EmpleadosController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<Empleado> tblEmpleados;
    @FXML
    private TableColumn<Empleado, String> colCedula;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colApellidos;
    @FXML
    private TableColumn<Empleado, String> colSexo;
    @FXML
    private TableColumn<Empleado, String> colEstadoCivil;
    @FXML
    private TableColumn<Empleado, String> colTipoSangre;
    @FXML
    private TableColumn<Empleado, String> colNivelAcademico;
    @FXML
    private TableColumn<Empleado, String> colCuenta;
    @FXML
    private TableColumn<Empleado, String> colFechaNacimiento;
    @FXML
    private TableColumn<Empleado, String> colFechaIngreso;
    @FXML
    private TableColumn<Empleado, String> colTipo;
    @FXML
    private TableColumn<Empleado, String> colEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarElementos();
        cargarEmpleados();
    }

    public void cargarEmpleados() {
        var observableEmpleado
                = FXCollections.observableArrayList(new EmpleadoDAO().obtenerListaEmpleados());
        tblEmpleados.setItems(observableEmpleado);
    }

    private void configurarElementos() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        colTipoSangre.setCellValueFactory(new PropertyValueFactory<>("tipoSangre"));
        colNivelAcademico.setCellValueFactory(new PropertyValueFactory<>("nivelAcademico"));
        colCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarEmpleado("/views/AgregarEmpleado");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarEmpleado("/views/ActualizarEmpleado");
    }

    @FXML
    private void OnFiltrarEmpleado(KeyEvent event) {

    }
}
