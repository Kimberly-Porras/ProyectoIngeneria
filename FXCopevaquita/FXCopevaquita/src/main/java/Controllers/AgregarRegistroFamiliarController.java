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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class AgregarRegistroFamiliarController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxBuscarEmpleado;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
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
    @FXML
    private TextField txtApellidos;
    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    Parentezco parentezco = new Parentezco();
    ParentezcoDAO daoParentezco = new ParentezcoDAO();
     EmpleadoDAO daoEmpleado = new EmpleadoDAO();
     ObservableList<String> observableSexo = FXCollections.observableArrayList("MASCULINO", "FEMENINO", "OTRO");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        cbxSexo.setItems(observableSexo);
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxBuscarEmpleado.setItems(ObservableEmpleado);

        cbxBuscarEmpleado.setConverter(new StringConverter<Empleado>() {
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
    
    private void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = daoParentezco.insertarParentezco(
                    new Parentezco(
                            txtCedula.getText(),
                            cbxBuscarEmpleado.getValue().getCedula(),
                            txtNombre.getText(),
                            txtApellidos.getText(),
                            java.sql.Date.valueOf(dpFechaNacimiento.getValue()),
                            cbxSexo.getValue(),
                            cbEstadoEmpleado.isSelected(),
                            txtParentezco.getText(),
                            txtContactoEmergencia.getText()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Parentezco insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el parentezco", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxBuscarEmpleado.getValue() != null
                && !cbxBuscarEmpleado.getValue().getNombre().trim().equals("")
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

    private void limpiarCamposAgregar() {
        cbxBuscarEmpleado.setValue(ObservableEmpleado.get(0));
        dpFechaNacimiento.setValue(LocalDate.now());
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtParentezco.setText("");
        txtContactoEmergencia.setText("");
        cbxSexo.setValue("");
        cbEstadoEmpleado.setSelected(parentezco.isStatus());
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }

}
