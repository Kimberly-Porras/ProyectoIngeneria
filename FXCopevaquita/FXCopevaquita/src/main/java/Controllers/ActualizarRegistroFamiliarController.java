/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.ParentezcoDAO;
import Models.Empleado;
import Models.Parentezco;
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
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class ActualizarRegistroFamiliarController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<Parentezco> tblParentezco;
    @FXML
    private TableColumn<Parentezco, String> colCedula;
    @FXML
    private TableColumn<Parentezco, String> colNombre;
    @FXML
    private TableColumn<Parentezco, String> colApellidos;
    @FXML
    private TableColumn<Parentezco, String> colSexo;
    @FXML
    private TableColumn<Parentezco, String> colFechaNacimiento;
    @FXML
    private TableColumn<Parentezco, String> colParentezco;
    @FXML
    private TableColumn<Parentezco, String> colContactos;
    @FXML
    private TableColumn<Parentezco, String> colEstado;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox<String> cbxSexo;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField txtParentezco;
    @FXML
    private TextField txtContactoEmergencia;
    @FXML
    private CheckBox cbEstadoEmpleado;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    Parentezco parentezco = new Parentezco();
    ParentezcoDAO daoParentezco = new ParentezcoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colParentezco.setCellValueFactory(new PropertyValueFactory<>("parentezco"));
        colContactos.setCellValueFactory(new PropertyValueFactory<>("contactoEmergencia"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));

        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoActualizar.setItems(ObservableEmpleado);

        cbxFiltrarEmpleadoActualizar.setConverter(new StringConverter<Empleado>() {
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

    private void actualizar() {
        if (VerificarEspacios()) {
            boolean exito = daoParentezco.actualizarParentezco(
                    new Parentezco(
                            txtCedula.getText(),
                            cbxFiltrarEmpleadoActualizar.getValue().getCedula(),
                            txtNombre.getText(),
                            txtApellidos.getText(),
                            java.sql.Date.valueOf(dpFechaNacimiento.getValue()),
                            cbxSexo.getValue(),
                            cbEstadoEmpleado.isSelected(),
                            txtParentezco.getText(),
                            txtContactoEmergencia.getText()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Parentezco actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
                FiltrarParentezcosPorCedulaEmpleado();

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar el parentezco", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspacios() {
        if (cbxFiltrarEmpleadoActualizar.getValue() != null
                && !cbxFiltrarEmpleadoActualizar.getValue().getNombre().trim().equals("")
                && dpFechaNacimiento.getValue() != null
                && !dpFechaNacimiento.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && txtCedula.getText() != null
                && !txtCedula.getText().trim().equals("")
                && txtNombre.getText() != null
                && !txtNombre.getText().trim().equals("")
                && txtApellidos.getText() != null
                && !txtApellidos.getText().trim().equals("")
                && txtParentezco.getText() != null
                && !txtParentezco.getText().trim().equals("")
                && txtContactoEmergencia.getText() != null
                && !txtContactoEmergencia.getText().trim().equals("")
                && !cbxSexo.getValue().equals("")) {
            return true;
        } else {
            return false;

        }
    }

    private void limpiarCampos() {
        cbxFiltrarEmpleadoActualizar.setValue(ObservableEmpleado.get(0));
        dpFechaNacimiento.setValue(LocalDate.now());
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtParentezco.setText("");
        txtContactoEmergencia.setText("");
        cbxSexo.setValue("");
        cbEstadoEmpleado.setSelected(parentezco.isStatus());
        dpFechaNacimiento.setValue(null);
    }

    private void cargarCamposActualizar() {
        dpFechaNacimiento.setValue(parentezco.getFechaNacimiento().toLocalDate());
        txtCedula.setText(parentezco.getCedula());
        txtNombre.setText(parentezco.getNombre());
        txtApellidos.setText(parentezco.getApellidos()+ "");
        txtParentezco.setText(parentezco.getParentezco()+ "");
        cbxSexo.setValue(parentezco.getSexo());
        txtContactoEmergencia.setText(parentezco.getContactoEmergencia()+ "");
        cbEstadoEmpleado.setSelected(parentezco.isStatus());
    }
    
    private void FiltrarParentezcosPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoParentezco.obtenerListaParentezcosPorCedulaEmpleado(empleado.getCedula()));
                tblParentezco.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las incapacidades del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarParentezcosPorEmpleado() {
        parentezco = tblParentezco.getSelectionModel().getSelectedItem();
        if (parentezco != null && parentezco.getCedula()!= "") {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una incapacidad", Alert.AlertType.WARNING);
        }
    }
    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarParentezcosPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarParentezcosPorEmpleado();
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
    actualizar();
    }
    

}
