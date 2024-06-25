/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.CredencialesDAO;
import DAO.EmpleadoDAO;
import Models.Credencial;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author aleke
 */
public class CredencialesController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleado;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasenia;
    @FXML
    private TextField txtCorreo;
    @FXML
    private CheckBox cbEstado;
    @FXML
    private TextField txtFiltrarCredenciales;
    @FXML
    private TableView<Credencial> tblCredencial;
    @FXML
    private TableColumn<Credencial, String> colId;
    @FXML
    private TableColumn<Credencial, String> colCedula;
    @FXML
    private TableColumn<Credencial, String> colUsuario;
    @FXML
    private TableColumn<Credencial, String> colContrasenia;
    @FXML
    private TableColumn<Credencial, String> colCorreo;
    @FXML
    private TableColumn<Credencial, String> colEstado;

    ObservableList<Empleado> empleados = FXCollections.observableArrayList();
    ObservableList<Credencial> credenciales = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarEmpleados();
        configurar();
    }

    private void cargarEmpleados() {
        var service = new EmpleadoDAO();
        var serviceCredencial = new CredencialesDAO();

        empleados = FXCollections.observableArrayList(service.obtenerListaEmpleados());
        credenciales = FXCollections.observableArrayList(serviceCredencial.obtenerTodos());
    }

    private void configurar() {
        cbxFiltrarEmpleado.setItems(empleados);
        cbxFiltrarEmpleado.setConverter(new StringConverter<Empleado>() {
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
                Optional<Empleado> firstMatch = empleados.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });

        // cargar la tabla de empleados administradores
        tblCredencial.setItems(credenciales);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
    }

    @FXML
    private void OnFiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
    }

    @FXML
    private void OnFiltrarCredenciales(KeyEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
        var model = tblCredencial.getSelectionModel().getSelectedItem();
        if (model == null) {
            return;
        }

        var empleadoService = new EmpleadoDAO();
        var empleadito = empleadoService.obtenerEmpleadoPorCedula(model.getEmpleado());

        if (empleadito == null) {
            // mostrar alerta TODO;
            return;
        }

        txtUsuario.setText(model.getEmpleado());
        txtContrasenia.setText(model.getContrasenia());
        txtCorreo.setText(model.getCorreo());
        cbEstado.setSelected(model.isStatus());

        cbxFiltrarEmpleado.setValue(empleadito);
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
    }

}
