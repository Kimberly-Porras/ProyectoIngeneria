/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Empleado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarRegistroFamiliarController implements Initializable {


    @FXML
    private ComboBox<Empleado> cbxBuscarEmpleado;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnGuardar(ActionEvent event) {
    }

}
