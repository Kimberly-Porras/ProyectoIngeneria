package Controllers;

import DAO.CredencialesDAO;
import Models.Credencial;
import Models.Empleado;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.scene.control.Alert;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author User
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
    

    /**
     * Initializes the controller class.
     */
    CredencialesDAO daoCredencial = new CredencialesDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    Credencial credencial = new Credencial();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        List<Credencial> listaCredenciales = daoCredencial.obtenerTodos();
        ObservableList<Credencial> CredencialObservable = FXCollections.observableArrayList(listaCredenciales);
        tblCredencial.setItems(CredencialObservable);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        cbEstado.setSelected(true);
    
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleado.setItems(ObservableEmpleado);

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
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });
    }

    public void guardar() {
        if (VerificarEspacios()) {
            boolean exito = daoCredencial.insertarCredencial(
                    new Credencial(0,
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            txtUsuario.getText(),
                            txtCorreo.getText(),
                            txtContrasenia.getText(),
                            cbEstado.isSelected())
            );
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Credencial insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la credencial", Alert.AlertType.ERROR);
            }

        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

        }
    }

    public boolean VerificarEspacios() {
        if (cbxFiltrarEmpleado.getValue() != null
                && !cbxFiltrarEmpleado.getValue().getCedula().trim().equals("")
                && !txtContrasenia.getText().trim().equals("")
                && !txtUsuario.getText().trim().equals("")
                && !txtCorreo.getText().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    private void limpiarCampos() {
        cbxFiltrarEmpleado.setValue(ObservableEmpleado.get(0));
        txtContrasenia.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
    }

    private void actualizar() {
        if (VerificarEspacios()) {
            boolean exito = daoCredencial.actualizarCredencial(
                    new Credencial(
                            credencial.getId(),
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            txtUsuario.getText(),
                            txtCorreo.getText(),
                            txtContrasenia.getText(),
                            cbEstado.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Credencial actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
                

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la credencial", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void cargarCredencialesPorEmpleado() {
        credencial = tblCredencial.getSelectionModel().getSelectedItem();
        if (credencial != null && credencial.getId() != 0) {
            cargarCredencialesPorEmpleado();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una credencial", Alert.AlertType.WARNING);
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
        cargarCredencialesPorEmpleado();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarCredencialesPorEmpleado();
    }

    @FXML
    private void OnFiltrarCredenciales(KeyEvent event) {
    }

}
