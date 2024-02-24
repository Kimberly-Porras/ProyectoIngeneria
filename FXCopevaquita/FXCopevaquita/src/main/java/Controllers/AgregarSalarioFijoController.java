/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.PlanillaSociosDAO;
import Models.Empleado;
import Models.PlanillaSocios;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarSalarioFijoController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
    @FXML
    private TextField txtMontoAgre;
    @FXML
    private CheckBox cbEstadoAgre;

    /**
     * Initializes the controller class.
     */
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    PlanillaSociosDAO daoPlanilla = new PlanillaSociosDAO();
    PlanillaSocios planilla = new PlanillaSocios();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }   
    
    public void configurar() {
        
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoAgre.setItems(ObservableEmpleado);

        cbxFiltrarEmpleadoAgre.setConverter(new StringConverter<Empleado>() {
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
            boolean exito = daoPlanilla.insertarPlanillaSocios(
                    new PlanillaSocios(0,
                            cbxFiltrarEmpleadoAgre.getValue().getCedula(),
                            Double.parseDouble(txtMontoAgre.getText()),
                            cbEstadoAgre.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Salario insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el salario", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void limpiarCamposAgregar() {
        cbxFiltrarEmpleadoAgre.setValue(ObservableEmpleado.get(0));
        txtMontoAgre.setText("");
        cbEstadoAgre.setSelected(planilla.isStatus());
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && txtMontoAgre.getText() != null
                && !txtMontoAgre.getText().trim().equals("")) {
            return true;
        } else {
            return false;

        }
    }
    
    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }
    
}
