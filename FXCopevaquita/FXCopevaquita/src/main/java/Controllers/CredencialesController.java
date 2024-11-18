/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.CredencialesDAO;
import DAO.EmpleadoDAO;
import Models.Actividad;
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
import javafx.scene.control.Alert;
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
    CredencialesDAO daoCredencial = new CredencialesDAO();
    Credencial credencial = new Credencial();

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

    public void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = daoCredencial.insertarCredencial(
                    new Credencial(0,
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            txtUsuario.getText(),
                            txtCorreo.getText(),
                            txtContrasenia.getText(),
                            cbEstado.isSelected())
            );
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Credencial insertada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
                configurar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la credencial", Alert.AlertType.ERROR);
            }

        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

        }
    }

    public boolean VerificarEspaciosAgregar() {
        if (!txtUsuario.getText().trim().equals("")
                && !txtCorreo.getText().trim().equals("")
                && !txtContrasenia.getText().trim().equals("")
                && cbxFiltrarEmpleado.getValue() != null
                && !cbxFiltrarEmpleado.getValue().getNombre().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void limpiarCamposAgregar() {
        txtContrasenia.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        cbEstado.setSelected(true);
    }

    public void actualizar() {
        // Obtiene el modelo seleccionado de la tabla
        var model = tblCredencial.getSelectionModel().getSelectedItem();

        // Verifica si hay un elemento seleccionado en la tabla
        if (model == null) {
            MensajePersonalizado.Ver("ERROR", "Por favor, selecciona una credencial para actualizar", Alert.AlertType.WARNING);
            return;
        }

        // Verifica si los campos tienen información válida
        if (VerificarEspaciosAgregar()) {
            // Crea el objeto Credencial con los valores del formulario
            Credencial credencialActualizada = new Credencial(
                    model.getId(), // Obtiene el ID del elemento seleccionado
                    cbxFiltrarEmpleado.getValue().getCedula(), // Cédula del empleado seleccionado
                    txtUsuario.getText(), // Usuario del formulario
                    txtCorreo.getText(), // Correo del formulario
                    txtContrasenia.getText(), // Contraseña del formulario
                    cbEstado.isSelected() // Estado activo/inactivo del CheckBox
            );

            // Llama al método del DAO para actualizar la credencial
            boolean exito = daoCredencial.actualizarCredencial(credencialActualizada);

            // Verifica si la actualización fue exitosa
            if (exito) {
                MensajePersonalizado.Ver("ÉXITO AL ACTUALIZAR", "La credencial se actualizó correctamente.", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar(); // Limpia los campos del formulario
                cargarEmpleados(); // Refresca los datos en la tabla
            } else {
                MensajePersonalizado.Ver("ERROR", "Ocurrió un error al actualizar la credencial. Verifica los datos ingresados.", Alert.AlertType.ERROR);
            }
        } else {
            // Muestra un mensaje si los campos no están completos
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Por favor, asegúrate de que todos los campos estén llenos.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void OnFiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        actualizar();
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

        txtUsuario.setText(model.getUsuario());
        txtContrasenia.setText(model.getContrasenia());
        txtCorreo.setText(model.getCorreo());
        cbEstado.setSelected(model.isStatus());

        cbxFiltrarEmpleado.setValue(empleadito);
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarEmpleados();
    }

}
